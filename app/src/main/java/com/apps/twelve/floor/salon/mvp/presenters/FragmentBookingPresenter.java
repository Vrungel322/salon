package com.apps.twelve.floor.salon.mvp.presenters;

import com.apps.twelve.floor.salon.App;
import com.apps.twelve.floor.salon.mvp.presenters.interfaces.IFragmentBookingPresenter;
import com.apps.twelve.floor.salon.mvp.views.IFragmentBookingView;
import com.arellomobile.mvp.InjectViewState;

/**
 * Created by Vrungel on 20.02.2017.
 */

@InjectViewState public class FragmentBookingPresenter extends BasePresenter<IFragmentBookingView>
    implements IFragmentBookingPresenter {
  @Override protected void inject() {
    App.getAppComponent().inject(this);
  }
}