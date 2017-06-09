package com.apps.twelve.floor.salon.data.local;

import android.content.Context;
import android.content.SharedPreferences;
import rx.Observable;

/**
 * Created by John on 26.01.2017.
 */

public class PreferencesHelper {

  public static final String PREF_FILE_NAME = "com.salon.Salon";

  private static final String PREF_BONUS_COUNT = "PREF_BONUS_COUNT";
  public static final String PREF_THEME_SELECTED = "PREF_THEME_SELECTED";
  private static final String PREF_NOTIF_HOURLY_ENABLED = "PREF_NOTIF_HOURLY_ENABLED";
  private static final String PREF_NOTIF_DAILY_ENABLED = "PREF_NOTIF_DAILY_ENABLED";
  private static final String PREF_NOTIF_HOURS = "PREF_NOTIF_HOURS";
  private static final String PREF_NOTIF_DAYS = "PREF_NOTIF_DAYS";

  private final SharedPreferences mPreferences;

  public PreferencesHelper(Context context) {
    mPreferences = context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE);
  }

  public void clear() {
    mPreferences.edit().clear().apply();
  }

  public void setThemeSelected(int themeSelected) {
    mPreferences.edit().putInt(PREF_THEME_SELECTED, themeSelected).apply();
  }

  public int getThemeSelected() {
    return mPreferences.getInt(PREF_THEME_SELECTED, 0);
  }

  public int getBonusCountInt() {
    return mPreferences.getInt(PREF_BONUS_COUNT, 0);
  }

  public Observable<Integer> getBonusCountObservable() {
    return Observable.just(mPreferences.getInt(PREF_BONUS_COUNT, 0));
  }

  public void setBonusCount(int bonusCount) {
    mPreferences.edit().putInt(PREF_BONUS_COUNT, bonusCount).apply();
  }

  public boolean isHourlyNotificationsEnabled() {
    return mPreferences.getBoolean(PREF_NOTIF_HOURLY_ENABLED, true);
  }

  public void setHourlyNotificationsEnabled(boolean enabled) {
    mPreferences.edit().putBoolean(PREF_NOTIF_HOURLY_ENABLED, enabled).apply();
  }

  public boolean isDailyNotificationsEnabled() {
    return mPreferences.getBoolean(PREF_NOTIF_DAILY_ENABLED, true);
  }

  public void setDailyNotificationsEnabled(boolean enabled) {
    mPreferences.edit().putBoolean(PREF_NOTIF_DAILY_ENABLED, enabled).apply();
  }

  public int getNotificationDays() {
    return mPreferences.getInt(PREF_NOTIF_DAYS, 1);
  }

  public void setNotificationDays(int days) {
    mPreferences.edit().putInt(PREF_NOTIF_DAYS, days).apply();
  }

  public long getNotificationHours() {
    return mPreferences.getLong(PREF_NOTIF_HOURS, 3600000);
  }

  public void setNotificationHours(long millis) {
    mPreferences.edit().putLong(PREF_NOTIF_HOURS, millis).apply();
  }

  public void logoutUser() {
    setBonusCount(0);
  }
}
