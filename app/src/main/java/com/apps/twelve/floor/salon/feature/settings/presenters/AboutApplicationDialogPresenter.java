package com.apps.twelve.floor.salon.feature.settings.presenters;

import com.apps.twelve.floor.salon.App;
import com.apps.twelve.floor.salon.base.BasePresenter;
import com.apps.twelve.floor.salon.feature.settings.views.IAboutApplicationDialog;
import com.arellomobile.mvp.InjectViewState;

/**
 * Created by alexandersvyatetsky on 4/08/17.
 */

@InjectViewState public class AboutApplicationDialogPresenter
    extends BasePresenter<IAboutApplicationDialog> {
  @Override protected void inject() {
    App.getAppComponent().inject(this);
  }
}
