package com.apps.twelve.floor.salon.feature.my_booking.presenters;

import com.apps.twelve.floor.authorization.utils.AuthRxBusHelper;
import com.apps.twelve.floor.salon.App;
import com.apps.twelve.floor.salon.base.BasePresenter;
import com.apps.twelve.floor.salon.data.model.booking.LastBookingEntity;
import com.apps.twelve.floor.salon.feature.my_booking.views.IHistoryBookingFragmentView;
import com.apps.twelve.floor.salon.utils.Constants;
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
 * Created by Vrungel on 15.08.2017.
 */
@InjectViewState public class HistoryBookingFragmentPresenter
    extends BasePresenter<IHistoryBookingFragmentView> {
  @Override protected void inject() {
    App.getAppComponent().inject(this);
  }

  @Override protected void onFirstViewAttach() {
    super.onFirstViewAttach();
    fetchBookingEntities();
    //RxBus
    //mRxBus.post(new RxBusHelper.SetBookingItemInMenu());
  }

  private void fetchBookingEntities() {
    getViewState().startRefreshingView();
    showCachedEntities();
    Subscription subscription =
        mAuthorizationManager.checkToken(mDataManager.fetchLastBookingHistory())
            .concatMap(response -> {
              if (response.code() == RESPONSE_TOKEN_EXPIRED) {
                return mAuthorizationManager.checkToken(mDataManager.fetchLastBooking());
              }
              return Observable.just(response);
            })
            .compose(ThreadSchedulers.applySchedulers())
            .subscribe(response -> {
              switch (response.code()) {
                case RESPONSE_200:
                  getViewState().showAllBooking(response.body());
                  getViewState().stopRefreshingView();
                  mDataManager.putBooking(response.body());
                  cacheEntities(response.body(), LastBookingEntity.class);
                  break;
                case RESPONSE_UNAUTHORIZED:
                  mAuthorizationManager.getAuthRxBus()
                      .post(new AuthRxBusHelper.UnauthorizedEvent());
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

  private void showCachedEntities() {
    Subscription subscription =
        Observable.just(mDataManager.getAllElementsFromDB(LastBookingEntity.class))
            .compose(ThreadSchedulers.applySchedulers())
            .concatMap(Observable::from)
            .filter(lastBookingEntity -> lastBookingEntity.getStatus()
                .equals(Constants.StatusBooking.DONE))
            .filter(lastBookingEntity -> lastBookingEntity.getStatus()
                .equals(Constants.StatusBooking.MISSED))
            .toList()
            .subscribe(lastBookingEntities -> getViewState().showAllBooking(lastBookingEntities));
    addToUnsubscription(subscription);
  }

  public void startRefreshing() {
    fetchBookingEntities();
  }
}
