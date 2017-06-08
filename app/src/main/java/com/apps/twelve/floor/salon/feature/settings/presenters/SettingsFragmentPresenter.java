package com.apps.twelve.floor.salon.feature.settings.presenters;

import com.apps.twelve.floor.salon.App;
import com.apps.twelve.floor.salon.base.BasePresenter;
import com.apps.twelve.floor.salon.feature.settings.views.ISettingsFragmentView;
import com.arellomobile.mvp.InjectViewState;

/**
 * Created by Alexandra on 18.04.2017.
 */

@InjectViewState public class SettingsFragmentPresenter
    extends BasePresenter<ISettingsFragmentView> {

  @Override protected void inject() {
    App.getAppComponent().inject(this);
  }

  @Override protected void onFirstViewAttach() {
    super.onFirstViewAttach();
    if (mAuthorizationManager.isAuthorized()) {
      getViewState().openUserProfileFragment();
    }
  }

  public void setThemeApp(int themeApp) {
    mDataManager.setThemeSelected(themeApp);
  }

  public void showSetThemeDialog() {
    getViewState().showSetThemeDialog(mDataManager.getThemeSelected());
  }
}
