package com.apps.twelve.floor.salon.feature.start_point.presenters;

import com.apps.twelve.floor.salon.App;
import com.apps.twelve.floor.salon.base.BasePresenter;
import com.apps.twelve.floor.salon.feature.start_point.views.IStartActivityView;
import com.arellomobile.mvp.InjectViewState;

/**
 * Created by Vrungel on 20.02.2017.
 */

@InjectViewState public class StartActivityPresenter extends BasePresenter<IStartActivityView> {

  @Override protected void onFirstViewAttach() {
    super.onFirstViewAttach();
    getViewState().addFragmentMain();
  }

  @Override protected void inject() {
    App.getAppComponent().inject(this);
  }
}
