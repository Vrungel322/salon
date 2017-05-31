package com.apps.twelve.floor.salon.feature.main_screen.presenters;

import com.apps.twelve.floor.salon.App;
import com.apps.twelve.floor.salon.base.BasePresenter;
import com.apps.twelve.floor.salon.data.DataManager;
import com.apps.twelve.floor.salon.feature.main_screen.views.ISubFragmentBookingView;
import com.apps.twelve.floor.salon.utils.RxBus;
import com.apps.twelve.floor.salon.utils.RxBusHelper;
import com.apps.twelve.floor.salon.utils.ThreadSchedulers;
import com.arellomobile.mvp.InjectViewState;
import javax.inject.Inject;
import rx.Observable;
import rx.Subscription;
import timber.log.Timber;

/**
 * Created by Vrungel on 28.02.2017.
 */

@InjectViewState public class SubBookingFragmentPresenter
    extends BasePresenter<ISubFragmentBookingView> {

  @Inject DataManager mDataManager;
  @Inject RxBus mRxBus;

  @Override protected void inject() {
    App.getAppComponent().inject(this);
  }

  @Override protected void onFirstViewAttach() {
    super.onFirstViewAttach();
    fetchBookingEntities();
    //RxBus
    subscribeUpdateSubBooking();
  }

  private void fetchBookingEntities() {
    if (mDataManager.isAuthorized()) {
      Subscription subscription = mDataManager.fetchLastBooking()
          .concatMap(Observable::from)
          .take(2)
          .toList()
          .compose(ThreadSchedulers.applySchedulers())
          .subscribe(lastBookingEntities -> getViewState().showLastBookings(lastBookingEntities),
              throwable -> {
                Timber.e(throwable);
                showMessageConnectException(throwable);
              });
      addToUnsubscription(subscription);
    }
  }

  private void subscribeUpdateSubBooking() {
    Subscription subscription =
        mRxBus.filteredObservable(RxBusHelper.UpdateLastBookingListEvent.class)
            .concatMap(updateLastBookingListEvent -> mDataManager.fetchLastBooking()
                .concatMap(Observable::from)
                .take(2)
                .toList())
            .compose(ThreadSchedulers.applySchedulers())
            .subscribe(lastBookingEntities -> getViewState().showLastBookings(lastBookingEntities),
                throwable -> {
                  subscribeUpdateSubBooking();
                  Timber.e(throwable);
                  showMessageConnectException(throwable);
                });
    addToUnsubscription(subscription);
  }
}
