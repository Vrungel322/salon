package com.apps.twelve.floor.salon.feature.my_booking.presenters;

import com.apps.twelve.floor.authorization.utils.AuthRxBusHelper;
import com.apps.twelve.floor.salon.App;
import com.apps.twelve.floor.salon.base.BasePresenter;
import com.apps.twelve.floor.salon.feature.my_booking.views.IMyBookFragmentView;
import com.apps.twelve.floor.salon.utils.RxBusHelper;
import com.apps.twelve.floor.salon.utils.ThreadSchedulers;
import com.arellomobile.mvp.InjectViewState;
import rx.Observable;
import rx.Subscription;
import timber.log.Timber;

import static com.apps.twelve.floor.authorization.utils.Constants.Remote.RESPONSE_TOKEN_EXPIRED;
import static com.apps.twelve.floor.authorization.utils.Constants.Remote.RESPONSE_UNAUTHORIZED;
import static com.apps.twelve.floor.salon.utils.Constants.StatusCode.RESPONSE_200;
import static com.apps.twelve.floor.salon.utils.Constants.StatusCode.RESPONSE_503;

/**
 * Created by Vrungel on 21.02.2017.
 */

@InjectViewState public class MyBookFragmentPresenter extends BasePresenter<IMyBookFragmentView> {

  @Override protected void inject() {
    App.getAppComponent().inject(this);
  }

  @Override protected void onFirstViewAttach() {
    super.onFirstViewAttach();
    fetchBookingEntities();
    //RxBus
    subscribeUpdateBooking();
    mRxBus.post(new RxBusHelper.SetBookingItemInMenu());
  }

  private void fetchBookingEntities() {
    getViewState().startRefreshingView();
    Subscription subscription =
        mAuthorizationManager.checkToken(mDataManager.fetchLastBooking()).concatMap(response -> {
          if (response.code() == RESPONSE_TOKEN_EXPIRED) {
            return mAuthorizationManager.checkToken(mDataManager.fetchLastBooking());
          }
          return Observable.just(response);
        }).compose(ThreadSchedulers.applySchedulers()).subscribe(response -> {
          switch (response.code()) {
            case RESPONSE_200:
              getViewState().showAllBooking(response.body());
              getViewState().stopRefreshingView();
              mDataManager.putBooking(response.body());
              break;
            case RESPONSE_UNAUTHORIZED:
              mAuthorizationManager.getAuthRxBus().post(new AuthRxBusHelper.UnauthorizedEvent());
              getViewState().stopRefreshingView();
              break;
            case RESPONSE_503:
              getViewState().showServerErrorMsg();
              getViewState().stopRefreshingView();
            default:
              getViewState().stopRefreshingView();
              showMessageException();
              break;
          }
        }, throwable -> {
          getViewState().stopRefreshingView();
          Timber.e(throwable);
          showMessageException(throwable);
        });
    addToUnsubscription(subscription);
  }

  private void subscribeUpdateBooking() {
    Subscription subscription =
        mRxBus.filteredObservable(RxBusHelper.UpdateLastBookingListEvent.class)
            .compose(ThreadSchedulers.applySchedulers())
            .subscribe(updateLastBookingListEvent -> fetchBookingEntities(), throwable -> {
              Timber.e(throwable);
              showMessageException(throwable);
            });
    addToUnsubscription(subscription);
  }

  public void startRefreshing() {
    fetchBookingEntities();
  }
}
