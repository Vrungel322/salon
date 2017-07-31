package com.apps.twelve.floor.salon.feature.my_bonus.presenters;

import com.apps.twelve.floor.authorization.utils.AuthRxBusHelper;
import com.apps.twelve.floor.salon.App;
import com.apps.twelve.floor.salon.base.BasePresenter;
import com.apps.twelve.floor.salon.feature.my_bonus.views.IMyBonusFragmentView;
import com.apps.twelve.floor.salon.utils.Constants;
import com.apps.twelve.floor.salon.utils.RxBusHelper;
import com.apps.twelve.floor.salon.utils.ThreadSchedulers;
import com.arellomobile.mvp.InjectViewState;
import rx.Observable;
import rx.Subscription;
import timber.log.Timber;

import static com.apps.twelve.floor.authorization.utils.Constants.Remote.RESPONSE_TOKEN_EXPIRED;
import static com.apps.twelve.floor.authorization.utils.Constants.Remote.RESPONSE_UNAUTHORIZED;
import static com.apps.twelve.floor.salon.utils.Constants.StatusCode.RESPONSE_200;

/**
 * Created by Vrungel on 21.02.2017.
 */

@InjectViewState public class MyBonusFragmentPresenter extends BasePresenter<IMyBonusFragmentView> {

  @Override protected void inject() {
    App.getAppComponent().inject(this);
  }

  @Override protected void onFirstViewAttach() {
    super.onFirstViewAttach();
    mRxBus.post(new RxBusHelper.SetBonusItemInMenu());
    getBonusCount();
  }

  @SuppressWarnings("ConstantConditions") public void getBonusCount() {
    getViewState().startRefreshingView();
    if (mAuthorizationManager.isAuthorized()) {
      Subscription subscription =
          mAuthorizationManager.checkToken(mDataManager.fetchBonusCount()).concatMap(response -> {
            if (response.code() == RESPONSE_TOKEN_EXPIRED) {
              return mAuthorizationManager.checkToken(mDataManager.fetchBonusCount());
            }
            return Observable.just(response);
          }).doOnNext(response -> {
            if (response.code() == RESPONSE_200) {
              mDataManager.setBonusCount(response.body().getBonusesCount());
            }
          }).compose(ThreadSchedulers.applySchedulers()).subscribe(response -> {
            switch (response.code()) {
              case RESPONSE_200:
                getViewState().setBonusCount(response.body().getBonusesCount());
                mRxBus.post(new RxBusHelper.UpdateBonusFromChildren());
                break;
              case RESPONSE_UNAUTHORIZED:
                mAuthorizationManager.getAuthRxBus().post(new AuthRxBusHelper.UnauthorizedEvent());
                getViewState().setBonusCount(mDataManager.getBonusCountInt());
                break;
              default:
                showMessageException();
                getViewState().setBonusCount(mDataManager.getBonusCountInt());
                break;
            }
            getViewState().stopRefreshingView();
          }, throwable -> {
            getViewState().setBonusCount(mDataManager.getBonusCountInt());
            getViewState().stopRefreshingView();
            Timber.e(throwable);
            showMessageException(throwable);
          });
      addToUnsubscription(subscription);
    } else {
      getViewState().setBonusCount(mDataManager.getBonusCountInt());
      getViewState().startRefreshingView();
    }
  }

  public void showAuthDialogBooking() {
    mRxBus.post(new RxBusHelper.ShowAuthDialogBooking());
  }

  public void showAuthDialog() {
    mRxBus.post(new RxBusHelper.ShowAuthDialog());
  }

  public void sendFriendsCode(String friendsCode) {
    Subscription subscription = mDataManager.sendFriendsCode(friendsCode)
        .compose(ThreadSchedulers.applySchedulers())
        .subscribe(voidResponse -> {
          if (voidResponse.code() == Constants.StatusCode.RESPONSE_200){
            getViewState().showThankYouDialog();
          }
          if (voidResponse.code() != Constants.StatusCode.RESPONSE_200){
            getViewState().showErrorToast();
          }
        }, this::showMessageException);
    addToUnsubscription(subscription);
  }
}
