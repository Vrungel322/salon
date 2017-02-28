package com.apps.twelve.floor.salon.mvp.presenters.fragments;

import com.apps.twelve.floor.salon.App;
import com.apps.twelve.floor.salon.mvp.presenters.BasePresenter;
import com.apps.twelve.floor.salon.mvp.presenters.interfaces.ISubFragmentBookingPresenter;
import com.apps.twelve.floor.salon.mvp.views.ISubFragmentBookingView;
import com.arellomobile.mvp.InjectViewState;

/**
 * Created by Vrungel on 28.02.2017.
 */

@InjectViewState public class SubFragmentBookingPresenter
    extends BasePresenter<ISubFragmentBookingView> implements ISubFragmentBookingPresenter {
  @Override protected void inject() {
    App.getAppComponent().inject(this);
  }
}
