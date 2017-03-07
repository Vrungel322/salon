package com.apps.twelve.floor.salon.mvp.presenters.fragments;

import com.apps.twelve.floor.salon.App;
import com.apps.twelve.floor.salon.mvp.presenters.BasePresenter;
import com.apps.twelve.floor.salon.mvp.presenters.interfaces.IWorkDetailsFragmentPresenter;
import com.apps.twelve.floor.salon.mvp.views.IWorkDetailsFragmentView;
import com.arellomobile.mvp.InjectViewState;

/**
 * Created by Alexandra on 23.02.2017.
 */

@InjectViewState public class WorkDetailsFragmentPresenter extends BasePresenter<IWorkDetailsFragmentView>
    implements IWorkDetailsFragmentPresenter {

  @Override protected void inject() {
    App.getAppComponent().inject(this);
  }
}
