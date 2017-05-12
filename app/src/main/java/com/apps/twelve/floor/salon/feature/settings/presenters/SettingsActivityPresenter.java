package com.apps.twelve.floor.salon.feature.settings.presenters;

import com.apps.twelve.floor.salon.App;
import com.apps.twelve.floor.salon.base.BasePresenter;
import com.apps.twelve.floor.salon.feature.settings.views.ISettingsActivityView;
import com.arellomobile.mvp.InjectViewState;

/**
 * Created by Alexandra on 18.04.2017.
 */

@InjectViewState public class SettingsActivityPresenter
    extends BasePresenter<ISettingsActivityView> {

  @Override protected void inject() {
    App.getAppComponent().inject(this);
  }

  @Override protected void onFirstViewAttach() {
    super.onFirstViewAttach();
    getViewState().addFragmentSettings();
  }
}
