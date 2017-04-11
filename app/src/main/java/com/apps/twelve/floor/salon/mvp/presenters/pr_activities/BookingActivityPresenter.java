package com.apps.twelve.floor.salon.mvp.presenters.pr_activities;

import com.apps.twelve.floor.salon.App;
import com.apps.twelve.floor.salon.mvp.presenters.BasePresenter;
import com.apps.twelve.floor.salon.mvp.views.IBookingActivityView;
import com.apps.twelve.floor.salon.utils.RxBus;
import com.apps.twelve.floor.salon.utils.RxBusHelper;
import com.arellomobile.mvp.InjectViewState;
import javax.inject.Inject;

/**
 * Created by John on 23.03.2017.
 */

@InjectViewState public class BookingActivityPresenter extends BasePresenter<IBookingActivityView> {

  @Inject RxBus mRxBus;

  @Override protected void onFirstViewAttach() {
    super.onFirstViewAttach();
    getViewState().addFragmentBooking();
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
}
