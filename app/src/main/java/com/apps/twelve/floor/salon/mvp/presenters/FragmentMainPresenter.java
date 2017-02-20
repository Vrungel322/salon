package com.apps.twelve.floor.salon.mvp.presenters;

import com.apps.twelve.floor.salon.App;
import com.apps.twelve.floor.salon.mvp.presenters.interfaces.IFragmentMainPresenter;
import com.apps.twelve.floor.salon.mvp.views.IFragmentMainView;
import com.arellomobile.mvp.InjectViewState;

/**
 * Created by Vrungel on 20.02.2017.
 */

@InjectViewState public class FragmentMainPresenter extends BasePresenter<IFragmentMainView> implements
    IFragmentMainPresenter {
  @Override protected void inject() {
    App.getAppComponent().inject(this);
  }
}
