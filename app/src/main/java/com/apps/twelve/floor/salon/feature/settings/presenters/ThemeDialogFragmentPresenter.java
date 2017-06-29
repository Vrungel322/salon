package com.apps.twelve.floor.salon.feature.settings.presenters;

import com.apps.twelve.floor.salon.App;
import com.apps.twelve.floor.salon.base.BasePresenter;
import com.apps.twelve.floor.salon.feature.settings.views.IThemeDialogFragmentView;
import com.arellomobile.mvp.InjectViewState;

/**
 * Created by John on 29.06.2017.
 */
@InjectViewState public class ThemeDialogFragmentPresenter
    extends BasePresenter<IThemeDialogFragmentView> {

  @Override protected void inject() {
    App.getAppComponent().inject(this);
  }

  @Override protected void onFirstViewAttach() {
    super.onFirstViewAttach();
    getViewState().showSetThemeDialog(mDataManager.getThemeSelected());
  }

  public void setThemeApp(int themeApp) {
    mDataManager.setThemeSelected(themeApp);
  }
}
