package com.apps.twelve.floor.salon.mvp.presenters.pr_activities;

import com.apps.twelve.floor.salon.App;
import com.apps.twelve.floor.salon.mvp.presenters.BasePresenter;
import com.apps.twelve.floor.salon.mvp.views.IBookingActivityView;
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
    visibleChoseServices();
  }

  @Override protected void inject() {
    App.getAppComponent().inject(this);
  }

  public void backCategories() {
    mRxBus.post(new RxBusHelper.BackCategories());
  }

  public void stateBooking() {
    mRxBus.post(new RxBusHelper.StateBooking());
  }

  private void visibleChoseServices() {
    Subscription subscription =
        mRxBus.filteredObservable(RxBusHelper.VisibleFragmentChooseService.class)
            .compose(ThreadSchedulers.applySchedulers())
            .subscribe(visibleFragmentChoseService -> getViewState().isVisibleChooseService(
                visibleFragmentChoseService.visible), Timber::e);
    addToUnsubscription(subscription);
  }
}
