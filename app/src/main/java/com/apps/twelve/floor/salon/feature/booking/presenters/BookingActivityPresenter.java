package com.apps.twelve.floor.salon.feature.booking.presenters;

import com.apps.twelve.floor.salon.App;
import com.apps.twelve.floor.salon.base.BasePresenter;
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

  @Override protected void onFirstViewAttach() {
    super.onFirstViewAttach();
    getViewState().addFragmentBooking();
    closeBookingService();
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

  private void closeBookingService() {
    Subscription subscription = mRxBus.filteredObservable(RxBusHelper.CloseBookingService.class)
        .compose(ThreadSchedulers.applySchedulers())
        .subscribe(closeBookingService -> getViewState().closeBookingService(), Timber::e);
    addToUnsubscription(subscription);
  }
}
