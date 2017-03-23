package com.apps.twelve.floor.salon.mvp.presenters.activities;

import com.apps.twelve.floor.salon.App;
import com.apps.twelve.floor.salon.mvp.presenters.BasePresenter;
import com.apps.twelve.floor.salon.mvp.presenters.interfaces.IBookingActivityPresenter;
import com.apps.twelve.floor.salon.mvp.views.IBookingActivityView;
import com.arellomobile.mvp.InjectViewState;

/**
 * Created by John on 23.03.2017.
 */

@InjectViewState public class BookingActivityPresenter extends BasePresenter<IBookingActivityView>
    implements IBookingActivityPresenter {

  @Override protected void onFirstViewAttach() {
    super.onFirstViewAttach();
    getViewState().addFragmentBooking();
  }

  @Override protected void inject() {
    App.getAppComponent().inject(this);
  }
}
