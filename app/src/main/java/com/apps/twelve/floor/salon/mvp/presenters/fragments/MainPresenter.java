package com.apps.twelve.floor.salon.mvp.presenters.fragments;

import com.apps.twelve.floor.salon.App;
import com.apps.twelve.floor.salon.mvp.presenters.BasePresenter;
import com.apps.twelve.floor.salon.mvp.presenters.interfaces.IMainFragmentPresenter;
import com.apps.twelve.floor.salon.mvp.views.IMainFragmentView;
import com.arellomobile.mvp.InjectViewState;

/**
 * Created by Vrungel on 20.02.2017.
 */

@InjectViewState public class MainPresenter extends BasePresenter<IMainFragmentView>
    implements IMainFragmentPresenter {
  @Override protected void inject() {
    App.getAppComponent().inject(this);
  }

  @Override protected void onFirstViewAttach() {
    super.onFirstViewAttach();
    getViewState().addSubFragments();
  }
}
