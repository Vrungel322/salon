package com.apps.twelve.floor.salon.mvp.presenters.fragments;

import com.apps.twelve.floor.salon.App;
import com.apps.twelve.floor.salon.mvp.presenters.BasePresenter;
import com.apps.twelve.floor.salon.mvp.presenters.interfaces.IBookingFragmentPresenter;
import com.apps.twelve.floor.salon.mvp.views.IBookingFragmentView;
import com.arellomobile.mvp.InjectViewState;

/**
 * Created by Vrungel on 20.02.2017.
 */

@InjectViewState public class BookingFragmentPresenter extends BasePresenter<IBookingFragmentView>
    implements IBookingFragmentPresenter {
  @Override protected void inject() {
    App.getAppComponent().inject(this);
  }
}