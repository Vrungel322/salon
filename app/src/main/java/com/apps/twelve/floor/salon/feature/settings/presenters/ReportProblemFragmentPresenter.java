package com.apps.twelve.floor.salon.feature.settings.presenters;

import com.apps.twelve.floor.salon.App;
import com.apps.twelve.floor.salon.base.BasePresenter;
import com.apps.twelve.floor.salon.feature.settings.views.IReportProblemFragmentView;
import com.arellomobile.mvp.InjectViewState;

/**
 * Created by Alexandra on 05.05.2017.
 */

@InjectViewState public class ReportProblemFragmentPresenter
    extends BasePresenter<IReportProblemFragmentView> {

  @Override protected void inject() {
    App.getAppComponent().inject(this);
  }

  @Override protected void onFirstViewAttach() {
    super.onFirstViewAttach();
  }
}