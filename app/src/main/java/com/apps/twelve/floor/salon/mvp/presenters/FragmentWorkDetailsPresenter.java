package com.apps.twelve.floor.salon.mvp.presenters;

import com.apps.twelve.floor.salon.App;
import com.apps.twelve.floor.salon.mvp.presenters.interfaces.IFragmentWorkDetailsPresenter;
import com.apps.twelve.floor.salon.mvp.views.IFragmentWorkDetailsView;
import com.arellomobile.mvp.InjectViewState;

/**
 * Created by Alexandra on 23.02.2017.
 */

@InjectViewState public class FragmentWorkDetailsPresenter
    extends BasePresenter<IFragmentWorkDetailsView> implements IFragmentWorkDetailsPresenter {

  @Override protected void inject() {
    App.getAppComponent().inject(this);
  }
}
