package com.apps.twelve.floor.salon.mvp.presenters.fragments;

import com.apps.twelve.floor.salon.App;
import com.apps.twelve.floor.salon.mvp.presenters.BasePresenter;
import com.apps.twelve.floor.salon.mvp.presenters.interfaces.IMyBookFragmentPresenter;
import com.apps.twelve.floor.salon.mvp.views.IMyBookFragmentView;
import com.arellomobile.mvp.InjectViewState;

/**
 * Created by Vrungel on 21.02.2017.
 */

@InjectViewState public class MyBookPresenter extends BasePresenter<IMyBookFragmentView>
    implements IMyBookFragmentPresenter {
  @Override protected void inject() {
    App.getAppComponent().inject(this);
  }
}
