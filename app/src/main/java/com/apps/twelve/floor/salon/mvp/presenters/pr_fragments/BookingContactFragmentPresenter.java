package com.apps.twelve.floor.salon.mvp.presenters.pr_fragments;

import com.apps.twelve.floor.salon.App;
import com.apps.twelve.floor.salon.mvp.presenters.BasePresenter;
import com.apps.twelve.floor.salon.mvp.presenters.pr_interfaces.IBookingContactFragmentPresenter;
import com.apps.twelve.floor.salon.mvp.views.IBookingContactFragmentView;
import com.arellomobile.mvp.InjectViewState;

/**
 * Created by Alexandra on 28.03.2017.
 */

@InjectViewState public class BookingContactFragmentPresenter
    extends BasePresenter<IBookingContactFragmentView> implements IBookingContactFragmentPresenter {

  @Override protected void inject() {
    App.getAppComponent().inject(this);
  }
}
