package com.apps.twelve.floor.salon.feature.main_screen.adapters;

import com.apps.twelve.floor.salon.App;
import com.apps.twelve.floor.salon.base.BasePresenter;
import com.arellomobile.mvp.InjectViewState;

/**
 * Created by Vrungel on 01.03.2017.
 */

@InjectViewState public class MyLastBookingAdapterPresenter
    extends BasePresenter<IMyLastBookingAdapterView> {
  @Override protected void inject() {
    App.getAppComponent().inject(this);
  }

  public void cancelOrder(int position) {
    getViewState().removeBookedServiceFromList(position);
  }

  public void postponeOrder(int position) {
  }
}
