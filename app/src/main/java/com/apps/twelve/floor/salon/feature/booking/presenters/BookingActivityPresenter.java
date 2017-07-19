package com.apps.twelve.floor.salon.feature.booking.presenters;

import com.apps.twelve.floor.authorization.utils.AuthRxBusHelper;
import com.apps.twelve.floor.salon.App;
import com.apps.twelve.floor.salon.base.BasePresenter;
import com.apps.twelve.floor.salon.feature.booking.views.IBookingActivityView;
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
 * Created by John on 23.03.2017.
 */

@InjectViewState public class BookingActivityPresenter extends BasePresenter<IBookingActivityView> {

  @Override protected void onFirstViewAttach() {
    super.onFirstViewAttach();
    getViewState().addFragmentBooking();
    fetchBonusCount();
    //RxBus
    subscribeCloseBookingService();
    subscribeConnectException();
    subscribeWrongException();
    subscribeUpdateBonusFromChildren();
    subscribeShowAuthDialog();
    subscribeShowNoInternetDialog();
    subscribeLogoutUser();
    subscribeUnauthorizedUser();
  }

  @Override protected void inject() {
    App.getAppComponent().inject(this);
  }

  public void backCategories() {
    mRxBus.post(new RxBusHelper.BackCategories());
  }

  public void stateBackBookingMaster() {
    mRxBus.post(new RxBusHelper.StateBackBookingMaster());
  }

  public void stateBackBookingService() {
    mRxBus.post(new RxBusHelper.StateBackBookingService());
  }

  @SuppressWarnings("ConstantConditions") private void fetchBonusCount() {
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
                mRxBus.post(new RxBusHelper.UpdateBonusFromParent());
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
          }, throwable -> {
            getViewState().setBonusCount(mDataManager.getBonusCountInt());
            Timber.e(throwable);
            showMessageException(throwable);
          });
      addToUnsubscription(subscription);
    } else {
      getViewState().setBonusCount(0);
    }
  }

  private void subscribeCloseBookingService() {
    Subscription subscription = mRxBus.filteredObservable(RxBusHelper.CloseBookingService.class)
        .compose(ThreadSchedulers.applySchedulers())
        .subscribe(closeBookingService -> getViewState().closeBookingService(), Timber::e);
    addToUnsubscription(subscription);
  }

  private void subscribeConnectException() {
    Subscription subscription = mRxBus.filteredObservable(RxBusHelper.MessageConnectException.class)
        .compose(ThreadSchedulers.applySchedulers())
        .subscribe(event -> getViewState().showConnectErrorMessage(), Timber::e);
    addToUnsubscription(subscription);
  }

  private void subscribeWrongException() {
    Subscription subscription = mRxBus.filteredObservable(RxBusHelper.MessageWrongException.class)
        .compose(ThreadSchedulers.applySchedulers())
        .subscribe(event -> getViewState().showWrongMessage(), Timber::e);
    addToUnsubscription(subscription);
  }

  private void subscribeUpdateBonusFromChildren() {
    Subscription subscription = mRxBus.filteredObservable(RxBusHelper.UpdateBonusFromChildren.class)
        .concatMap(updateBonusSwipe -> mDataManager.getBonusCountObservable())
        .compose(ThreadSchedulers.applySchedulers())
        .subscribe(count -> getViewState().setBonusCount(count), Timber::e);
    addToUnsubscription(subscription);
  }

  private void subscribeShowAuthDialog() {
    Subscription subscription = mRxBus.filteredObservable(RxBusHelper.ShowAuthDialogBooking.class)
        .compose(ThreadSchedulers.applySchedulers())
        .subscribe(show -> getViewState().showAuthAlertDialog(), Timber::e);
    addToUnsubscription(subscription);
  }

  private void subscribeShowNoInternetDialog() {
    Subscription subscription = mRxBus.filteredObservable(RxBusHelper.NoInternetAlertDialog.class)
        .compose(ThreadSchedulers.applySchedulers())
        .subscribe(show -> {
          getViewState().showNoInternetAlertDialog();
        }, Timber::e);
    addToUnsubscription(subscription);
  }

  private void subscribeLogoutUser() {
    Subscription subscription = mAuthorizationManager.getAuthRxBus()
        .filteredObservable(AuthRxBusHelper.LogoutEvent.class)
        .compose(ThreadSchedulers.applySchedulers())
        .subscribe(event -> {
          getViewState().logoutUser();
          mDataManager.logoutUser();
        }, Timber::e);
    addToUnsubscription(subscription);
  }

  private void subscribeUnauthorizedUser() {
    Subscription subscription = mAuthorizationManager.getAuthRxBus()
        .filteredObservable(AuthRxBusHelper.UnauthorizedEvent.class)
        .compose(ThreadSchedulers.applySchedulers())
        .subscribe(event -> getViewState().startSignInActivity(), Timber::e);
    addToUnsubscription(subscription);
  }

  public void cancelAlertDialog() {
    getViewState().cancelAuthAlertDialog();
  }

  public void showExitAlertDialog() {
    getViewState().showExitAlertDialog();
  }

  public void cancelExitAlertDialog() {
    getViewState().cancelExitAlertDialog();
  }
}
