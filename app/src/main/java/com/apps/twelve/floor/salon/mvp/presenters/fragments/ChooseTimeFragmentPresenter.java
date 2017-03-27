package com.apps.twelve.floor.salon.mvp.presenters.fragments;

import com.apps.twelve.floor.salon.App;
import com.apps.twelve.floor.salon.mvp.presenters.BasePresenter;
import com.apps.twelve.floor.salon.mvp.presenters.interfaces.IChooseTimeFragmentPresenter;
import com.apps.twelve.floor.salon.mvp.views.IChooseTimeFragmentView;
import com.arellomobile.mvp.InjectViewState;

/**
 * Created by Vrungel on 27.03.2017.
 */

@InjectViewState public class ChooseTimeFragmentPresenter
    extends BasePresenter<IChooseTimeFragmentView> implements IChooseTimeFragmentPresenter {
  @Override protected void inject() {
    App.getAppComponent().inject(this);
  }

  @Override protected void onFirstViewAttach() {
    super.onFirstViewAttach();
    getViewState().setUpUi();
  }
}
