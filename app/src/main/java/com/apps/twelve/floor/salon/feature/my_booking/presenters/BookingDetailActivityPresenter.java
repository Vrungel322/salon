package com.apps.twelve.floor.salon.feature.my_booking.presenters;

import com.apps.twelve.floor.salon.App;
import com.apps.twelve.floor.salon.base.BasePresenter;
import com.apps.twelve.floor.salon.feature.my_booking.views.IBookingDetailActivity;
import com.arellomobile.mvp.InjectViewState;

/**
 * Created by alexandersvyatetsky on 28/07/17.
 */

@InjectViewState public class BookingDetailActivityPresenter
    extends BasePresenter<IBookingDetailActivity> {

  @Override protected void inject() {
    App.getAppComponent().inject(this);
  }

  @Override protected void onFirstViewAttach() {
    super.onFirstViewAttach();
    getViewState().addBookingDetailFragment();
  }
}
