package com.apps.twelve.floor.salon.feature.main_screen.presenters;

import com.apps.twelve.floor.authorization.utils.AuthRxBusHelper;
import com.apps.twelve.floor.salon.App;
import com.apps.twelve.floor.salon.base.BasePresenter;
import com.apps.twelve.floor.salon.feature.main_screen.views.ISubFragmentBookingView;
import com.apps.twelve.floor.salon.utils.RxBusHelper;
import com.apps.twelve.floor.salon.utils.ThreadSchedulers;
import com.arellomobile.mvp.InjectViewState;
import rx.Observable;
import rx.Subscription;
import timber.log.Timber;

import static com.apps.twelve.floor.authorization.utils.Constants.Remote.RESPONSE_TOKEN_EXPIRED;
import static com.apps.twelve.floor.authorization.utils.Constants.Remote.RESPONSE_UNAUTHORIZED;

/**
 * Created by Vrungel on 28.02.2017.
 */

@InjectViewState public class SubBookingFragmentPresenter
    extends BasePresenter<ISubFragmentBookingView> {

  @Override protected void inject() {
    App.getAppComponent().inject(this);
  }

  @Override protected void onFirstViewAttach() {
    super.onFirstViewAttach();
    fetchBookingEntities();
    //RxBus
    subscribeUpdateSubBooking();
  }

  public void showAlertDialog() {
    mRxBus.post(new RxBusHelper.ShowAuthDialog());
  }

  @SuppressWarnings("ConstantConditions") private void fetchBookingEntities() {
    if (mAuthorizationManager.isAuthorized()) {
      Subscription subscription =
          mAuthorizationManager.checkToken(mDataManager.fetchLastBooking())
              .concatMap(response -> {
                if (response.code() == RESPONSE_TOKEN_EXPIRED) {
                  return mAuthorizationManager.checkToken(mDataManager.fetchLastBooking());
                }
                return Observable.just(response);
              })
              .concatMap(response -> {
                if (response.code() == RESPONSE_UNAUTHORIZED) {
                  mAuthorizationManager.getAuthRxBus()
                      .post(new AuthRxBusHelper.UnauthorizedEvent());
                  return Observable.empty();
                } else {
                  return Observable.just(response);
                }
              })
              .doOnNext(response -> mDataManager.putBooking(response.body()))
              .concatMap(response -> Observable.from(response.body()))
              .take(2)
              .toList()
              .compose(ThreadSchedulers.applySchedulers())
              .subscribe(
                  lastBookingEntities -> getViewState().showLastBookings(lastBookingEntities),
                  throwable -> {
                    Timber.e(throwable);
                    showMessageException(throwable);
                  });
      addToUnsubscription(subscription);
    }
  }

  private void subscribeUpdateSubBooking() {
    Subscription subscription =
        mRxBus.filteredObservable(RxBusHelper.UpdateLastBookingListEvent.class)
            .compose(ThreadSchedulers.applySchedulers())
            .subscribe(updateLastBookingListEvent -> fetchBookingEntities(), throwable -> {
              subscribeUpdateSubBooking();
              Timber.e(throwable);
              showMessageException(throwable);
            });
    addToUnsubscription(subscription);
  }
}
