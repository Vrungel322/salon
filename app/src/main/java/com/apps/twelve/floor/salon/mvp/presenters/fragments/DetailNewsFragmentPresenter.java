package com.apps.twelve.floor.salon.mvp.presenters.fragments;

import com.apps.twelve.floor.salon.App;
import com.apps.twelve.floor.salon.mvp.presenters.BasePresenter;
import com.apps.twelve.floor.salon.mvp.presenters.interfaces.INewsDetailFragmentPresenter;
import com.apps.twelve.floor.salon.mvp.views.INewsDetailFragmentView;
import com.arellomobile.mvp.InjectViewState;

/**
 * Created by Vrungel on 23.02.2017.
 */

@InjectViewState public class DetailNewsFragmentPresenter
    extends BasePresenter<INewsDetailFragmentView> implements INewsDetailFragmentPresenter {
  @Override protected void inject() {
    App.getAppComponent().inject(this);
  }
}
