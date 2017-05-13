package com.apps.twelve.floor.salon.feature.booking.presenters;

import com.apps.twelve.floor.salon.App;
import com.apps.twelve.floor.salon.base.BasePresenter;
import com.apps.twelve.floor.salon.feature.booking.views.IBookingFragmentView;
import com.arellomobile.mvp.InjectViewState;

/**
 * Created by Vrungel on 20.02.2017.
 */

@InjectViewState public class BookingFragmentPresenter extends BasePresenter<IBookingFragmentView> {

  @Override protected void inject() {
    App.getAppComponent().inject(this);
  }
}