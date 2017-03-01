package com.apps.twelve.floor.salon.mvp.presenters.adapters;

import com.apps.twelve.floor.salon.App;
import com.apps.twelve.floor.salon.mvp.presenters.BasePresenter;
import com.apps.twelve.floor.salon.mvp.presenters.interfaces.IMyLastBookingAdapterPresenter;
import com.apps.twelve.floor.salon.mvp.views.IMyLastBookingAdapterView;
import com.arellomobile.mvp.InjectViewState;

/**
 * Created by Vrungel on 01.03.2017.
 */

@InjectViewState public class MyLastBookingAdapterPresenter
    extends BasePresenter<IMyLastBookingAdapterView> implements IMyLastBookingAdapterPresenter {
  @Override protected void inject() {
    App.getAppComponent().inject(this);
  }

  @Override public void cancelOrder(int position) {
    getViewState().removeBookedServiceFromList(position);
  }

  @Override public void postponeOrder(int position) {
  }
}
