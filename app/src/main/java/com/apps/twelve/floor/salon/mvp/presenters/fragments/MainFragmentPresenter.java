package com.apps.twelve.floor.salon.mvp.presenters.fragments;

import com.apps.twelve.floor.salon.App;
import com.apps.twelve.floor.salon.mvp.presenters.base.FragmentBasePresenter;
import com.apps.twelve.floor.salon.mvp.presenters.interfaces.IMainFragmentPresenter;
import com.apps.twelve.floor.salon.mvp.views.IMainFragmentView;
import com.arellomobile.mvp.InjectViewState;

/**
 * Created by Vrungel on 20.02.2017.
 */

@InjectViewState public class MainFragmentPresenter extends FragmentBasePresenter<IMainFragmentView>
    implements IMainFragmentPresenter {
  @Override protected void inject() {
    App.getAppComponent().inject(this);
  }
}
