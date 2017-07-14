package com.apps.twelve.floor.salon.feature.settings.presenters;

import com.apps.twelve.floor.salon.App;
import com.apps.twelve.floor.salon.base.BasePresenter;
import com.apps.twelve.floor.salon.feature.settings.views.INotificationSettingsFragmentView;
import com.arellomobile.mvp.InjectViewState;

/**
 * Created by Alexandra on 05.05.2017.
 */

@InjectViewState public class NotificationSettingsFragmentPresenter
    extends BasePresenter<INotificationSettingsFragmentView> {

  private int mLastPickedDays;

  @Override protected void inject() {
    App.getAppComponent().inject(this);
  }

  @Override protected void onFirstViewAttach() {
    super.onFirstViewAttach();
    mLastPickedDays = mDataManager.getNotificationDays();
  }

  public void setUpSwitches() {
    getViewState().setUpSwitchers(mDataManager.isHourlyNotificationsEnabled(),
        mDataManager.isDailyNotificationsEnabled(), mDataManager.isNightMode());
  }

  public void setUpStrings() {
    getViewState().setUpDaysString(mDataManager.getNotificationDays());
    getViewState().setUpHoursString(getHours());
    getViewState().setUpNightHours(mDataManager.getNotificationHoursNightStart(),
        mDataManager.getNotificationHoursNightEnd());
  }

  public long getHours() {
    return mDataManager.getNotificationHours();
  }

  public long getHoursNightStart() {
    return mDataManager.getNotificationHoursNightStart();
  }

  public long getHoursNightEnd() {
    return mDataManager.getNotificationHoursNightEnd();
  }

  public void setHourlyNotificationsEnabled(boolean checked) {
    mDataManager.setHourlyNotificationsEnabled(checked);
  }

  public void setDailyNotificationsEnabled(boolean checked) {
    mDataManager.setDailyNotificationsEnabled(checked);
  }

  public void setNightModeNotificationsEnabled(boolean checked) {
    mDataManager.setNightMode(checked);
  }

  public void setHours(long millis) {
    mDataManager.setNotificationHours(millis);
    getViewState().setUpHoursString(millis);
  }

  public void setHoursNightStart(long millis) {
    mDataManager.setNotificationHoursNightStart(millis);
    getViewState().setUpNightHours(millis, mDataManager.getNotificationHoursNightEnd());
  }

  public void setHoursNightEnd(long millis) {
    mDataManager.setNotificationHoursNightEnd(millis);
    getViewState().setUpNightHours(mDataManager.getNotificationHoursNightStart(), millis);
  }

  public int getLastPickedDays() {
    return mLastPickedDays;
  }

  public void setLastPickedDays(int days) {
    mLastPickedDays = days;
  }

  public void saveDays() {
    mDataManager.setNotificationDays(mLastPickedDays);
    getViewState().setUpDaysString(mLastPickedDays);
  }

  public void showPickDayDialog() {
    getViewState().showPickDayDialog();
  }

  public void cancelPickDayDialog() {
    getViewState().cancelPickDayDialog();
    mLastPickedDays = mDataManager.getNotificationDays();
  }
}
