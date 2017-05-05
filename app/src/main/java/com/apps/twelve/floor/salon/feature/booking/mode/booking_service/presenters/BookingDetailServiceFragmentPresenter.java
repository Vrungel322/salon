package com.apps.twelve.floor.salon.feature.booking.mode.booking_service.presenters;

import com.apps.twelve.floor.salon.App;
import com.apps.twelve.floor.salon.base.BasePresenter;
import com.apps.twelve.floor.salon.data.model.BookingEntity;
import com.apps.twelve.floor.salon.feature.booking.mode.booking_service.views.IBookingDetailServiceView;
import com.apps.twelve.floor.salon.utils.RxBus;
import com.arellomobile.mvp.InjectViewState;
import javax.inject.Inject;

/**
 * Created by John on 05.05.2017.
 */

@InjectViewState public class BookingDetailServiceFragmentPresenter
    extends BasePresenter<IBookingDetailServiceView> {

  @Inject BookingEntity mBookingEntity;
  @Inject RxBus mRxBus;

  @Override protected void inject() {
    App.initBookingComponent();
    App.getBookingComponent().inject(this);
  }

  @Override public void onDestroy() {
    super.onDestroy();
    App.destroyBookingComponent();
  }
}
