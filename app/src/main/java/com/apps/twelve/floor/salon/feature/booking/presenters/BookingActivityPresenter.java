package com.apps.twelve.floor.salon.feature.booking.presenters;

import com.apps.twelve.floor.salon.App;
import com.apps.twelve.floor.salon.base.BasePresenter;
import com.apps.twelve.floor.salon.data.DataManager;
import com.apps.twelve.floor.salon.feature.booking.views.IBookingActivityView;
import com.apps.twelve.floor.salon.utils.RxBus;
import com.apps.twelve.floor.salon.utils.RxBusHelper;
import com.apps.twelve.floor.salon.utils.ThreadSchedulers;
import com.arellomobile.mvp.InjectViewState;
import javax.inject.Inject;
import rx.Subscription;
import timber.log.Timber;

/**
 * Created by John on 23.03.2017.
 */

@InjectViewState public class BookingActivityPresenter extends BasePresenter<IBookingActivityView> {

  @Inject RxBus mRxBus;
  @Inject DataManager mDataManager;

  @Override protected void onFirstViewAttach() {
    super.onFirstViewAttach();
    getViewState().addFragmentBooking();
    //RxBus
    subscribeCloseBookingService();
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

  public void fetchBonusCount() {
    Subscription subscription = mDataManager.fetchBonusCount()
        .doOnNext(count -> mDataManager.setBonusCount(count))
        .compose(ThreadSchedulers.applySchedulers())
        .subscribe(count -> getViewState().setBonusCount(count), Timber::e);
    addToUnsubscription(subscription);
  }

  private void subscribeCloseBookingService() {
    Subscription subscription = mRxBus.filteredObservable(RxBusHelper.CloseBookingService.class)
        .compose(ThreadSchedulers.applySchedulers())
        .subscribe(closeBookingService -> getViewState().closeBookingService(), Timber::e);
    addToUnsubscription(subscription);
  }
}
