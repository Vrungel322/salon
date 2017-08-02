package com.apps.twelve.floor.salon.feature.main_screen.presenters;

import com.apps.twelve.floor.authorization.utils.AuthRxBusHelper;
import com.apps.twelve.floor.salon.App;
import com.apps.twelve.floor.salon.base.BasePresenter;
import com.apps.twelve.floor.salon.feature.main_screen.views.ISubBonusRegestrationFragmentView;
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
 * Created by Vrungel on 27.02.2017.
 */

@InjectViewState public class SubBonusRegistrationFragmentPresenter
    extends BasePresenter<ISubBonusRegestrationFragmentView> {

  @Override protected void inject() {
    App.getAppComponent().inject(this);
  }

  @Override protected void onFirstViewAttach() {
    super.onFirstViewAttach();
    //RxBus
    subscribeUpdateBonusFromParent();
    subscribeUpdateBonusSwipe();
  }

  private void fetchBonusCount() {
    Subscription subscription = mDataManager.getBonusCountObservable()
        .compose(ThreadSchedulers.applySchedulers())
        .subscribe(count -> getViewState().setBonusCount(String.valueOf(count)), throwable -> {
          Timber.e(throwable);
          showMessageException(throwable);
        });
    addToUnsubscription(subscription);
  }

  public void showCardBonusOrRegistration() {
    if (!mAuthorizationManager.isAuthorized()) {
      getViewState().showCardRegistration();
    } else {
      fetchBonusCount();
    }
  }

  @SuppressWarnings("ConstantConditions") private void subscribeUpdateBonusSwipe() {
    Subscription subscription = mRxBus.filteredObservable(RxBusHelper.UpdateBonusSwipe.class)
        .concatMap(
            updateBonusSwipe -> mAuthorizationManager.checkToken(mDataManager.fetchBonusCount()))
        .concatMap(response -> {
          if (response.code() == RESPONSE_TOKEN_EXPIRED) {
            return mAuthorizationManager.checkToken(mDataManager.fetchBonusCount());
          }
          return Observable.just(response);
        })
        .doOnNext(response -> {
          if (response.code() == RESPONSE_200) {
            mDataManager.setBonusCount(response.body().getBonusesCount());
          }
        })
        .compose(ThreadSchedulers.applySchedulers())
        .subscribe(response -> {
          switch (response.code()) {
            case RESPONSE_200:
              response.body().setId(0);
              cacheEntity(response.body());
              getViewState().setBonusCount(String.valueOf(response.body().getBonusesCount()));
              mRxBus.post(new RxBusHelper.UpdateBonusFromChildren());
              break;
            case RESPONSE_UNAUTHORIZED:
              mAuthorizationManager.getAuthRxBus().post(new AuthRxBusHelper.UnauthorizedEvent());
              getViewState().setBonusCount(String.valueOf(mDataManager.getBonusCountInt()));
              break;
            default:
              showMessageException();
              getViewState().setBonusCount(String.valueOf(mDataManager.getBonusCountInt()));
              break;
          }
        }, throwable -> {
          getViewState().setBonusCount(String.valueOf(mDataManager.getBonusCountInt()));
          subscribeUpdateBonusFromParent();
          Timber.e(throwable);
          showMessageException(throwable);
        });
    addToUnsubscription(subscription);
  }

  private void subscribeUpdateBonusFromParent() {
    Subscription subscription = mRxBus.filteredObservable(RxBusHelper.UpdateBonusFromParent.class)
        .concatMap(updateBonusSwipe -> mDataManager.getBonusCountObservable())
        .compose(ThreadSchedulers.applySchedulers())
        .subscribe(count -> getViewState().setBonusCount(String.valueOf(count)), Timber::e);
    addToUnsubscription(subscription);
  }
}
