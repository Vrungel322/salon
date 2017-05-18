package com.apps.twelve.floor.salon.feature.settings.presenters;

import com.apps.twelve.floor.salon.App;
import com.apps.twelve.floor.salon.base.BasePresenter;
import com.apps.twelve.floor.salon.data.DataManager;
import com.apps.twelve.floor.salon.feature.settings.views.INotificationSettingsFragmentView;
import com.arellomobile.mvp.InjectViewState;
import javax.inject.Inject;

/**
 * Created by Alexandra on 05.05.2017.
 */

@InjectViewState public class NotificationSettingsFragmentPresenter
    extends BasePresenter<INotificationSettingsFragmentView> {

  @Inject DataManager mDataManager;

  @Override protected void inject() {
    App.getAppComponent().inject(this);
  }

  @Override protected void onFirstViewAttach() {
    super.onFirstViewAttach();
  }

  public void setUpSwitches() {
    getViewState().setUpSwitchers(mDataManager.isHourlyNotificationsEnabled(),
        mDataManager.isDailyNotificationsEnabled());
  }

  public void setHourlyNotificationsEnabled(boolean checked) {
    mDataManager.setHourlyNotificationsEnabled(checked);
  }

  public void setDailyNotificationsEnabled(boolean checked) {
    mDataManager.setDailyNotificationsEnabled(checked);
  }
}
