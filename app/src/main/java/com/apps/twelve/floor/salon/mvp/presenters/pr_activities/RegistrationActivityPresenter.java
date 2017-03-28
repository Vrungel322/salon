package com.apps.twelve.floor.salon.mvp.presenters.pr_activities;

import com.apps.twelve.floor.salon.App;
import com.apps.twelve.floor.salon.mvp.presenters.BasePresenter;
import com.apps.twelve.floor.salon.mvp.views.IRegistrationActivityView;
import com.arellomobile.mvp.InjectViewState;

/**
 * Created by Vrungel on 27.02.2017.
 */

@InjectViewState public class RegistrationActivityPresenter
    extends BasePresenter<IRegistrationActivityView> {
  @Override protected void inject() {
    App.getAppComponent().inject(this);
  }
}
