package com.apps.twelve.floor.salon.feature.booking.mode.booking_master.presenters;

import com.apps.twelve.floor.salon.App;
import com.apps.twelve.floor.salon.base.BasePresenter;
import com.apps.twelve.floor.salon.feature.booking.mode.booking_master.views.IBookingDetailMasterView;
import com.arellomobile.mvp.InjectViewState;

/**
 * Created by John on 04.05.2017.
 */

@InjectViewState public class BookingDetailMasterFragmentPresenter
    extends BasePresenter<IBookingDetailMasterView> {

  @Override protected void inject() {
    App.initBookingComponent();
    App.getBookingComponent().inject(this);
  }

  @Override protected void onFirstViewAttach() {
    super.onFirstViewAttach();
    getViewState().addFirstFragment();
  }

  @Override public void onDestroy() {
    super.onDestroy();
    App.destroyBookingComponent();
  }
}
