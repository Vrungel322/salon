package com.apps.twelve.floor.salon.feature.my_booking.presenters;

import com.apps.twelve.floor.salon.App;
import com.apps.twelve.floor.salon.base.BasePresenter;
import com.apps.twelve.floor.salon.data.DataManager;
import com.apps.twelve.floor.salon.feature.my_booking.views.IMyBookFragmentView;
import com.apps.twelve.floor.salon.utils.RxBus;
import com.apps.twelve.floor.salon.utils.RxBusHelper;
import com.apps.twelve.floor.salon.utils.ThreadSchedulers;
import com.arellomobile.mvp.InjectViewState;
import javax.inject.Inject;
import rx.Subscription;
import timber.log.Timber;

/**
 * Created by Vrungel on 21.02.2017.
 */

@InjectViewState public class MyBookFragmentPresenter extends BasePresenter<IMyBookFragmentView> {

  @Inject DataManager mDataManager;
  @Inject RxBus mRxBus;

  @Override protected void inject() {
    App.getAppComponent().inject(this);
  }

  @Override protected void onFirstViewAttach() {
    super.onFirstViewAttach();
    getEventFromRxBus();
    fetchBookingEntities();
  }

  private void fetchBookingEntities() {
    Subscription subscription = mDataManager.fetchLastBooking()
        .compose(ThreadSchedulers.applySchedulers())
        .subscribe(lastBookingEntities -> getViewState().showAllBooking(lastBookingEntities),
            Timber::e);
    addToUnsubscription(subscription);
  }

  public void startRefreshing() {
    getViewState().startRefreshingView();
    mRxBus.post(new RxBusHelper.UpdateLastBookingListEvent());
  }

  private void getEventFromRxBus() {
    Subscription subscription =
        mRxBus.filteredObservable(RxBusHelper.UpdateLastBookingListEvent.class)
            .flatMap(updateLastBookingListEvent -> mDataManager.fetchLastBooking())
            .compose(ThreadSchedulers.applySchedulers())
            .subscribe(lastBookingEntities -> {
              getViewState().showAllBooking(lastBookingEntities);
              getViewState().stopRefreshingView();
              mRxBus.post(new RxBusHelper.StopRefreshBookingMainFragment());
            }, throwable -> {
              mRxBus.post(new RxBusHelper.StopRefreshBookingMainFragment());
              Timber.e(throwable);
            });
    addToUnsubscription(subscription);
  }
}
