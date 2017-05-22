package com.apps.twelve.floor.salon.data.local;

import android.content.Context;
import android.content.SharedPreferences;
import rx.Observable;

/**
 * Created by John on 26.01.2017.
 */

public class PreferencesHelper {

  public static final String PREF_FILE_NAME = "com.salon.Salon";

  private static final String PREF_PROFILE_IMAGE = "PREF_PROFILE_IMAGE";
  private static final String PREF_PROFILE_NAME = "PREF_PROFILE_NAME";
  private static final String PREF_PROFILE_LOGIN = "PREF_PROFILE_LOGIN";
  private static final String PREF_PROFILE_PASSWORD = "PREF_PROFILE_PASSWORD";
  private static final String PREF_PROFILE_EMAIL = "PREF_PROFILE_EMAIL";
  private static final String PREF_PROFILE_PHONE = "PREF_PROFILE_PHONE";
  private static final String PREF_PROFILE_GENDER = "PREF_PROFILE_GENDER";
  private static final String PREF_BONUS_COUNT = "PREF_BONUS_COUNT";
  public static final String PREF_THEME_SELECTED = "PREF_THEME_SELECTED";
  private static final String PREF_NOTIF_HOURLY_ENABLED = "PREF_NOTIF_HOURLY_ENABLED";
  private static final String PREF_NOTIF_DAILY_ENABLED = "PREF_NOTIF_DAILY_ENABLED";
  private static final String PREF_NOTIF_HOURS = "PREF_NOTIF_HOURS";
  private static final String PREF_NOTIF_DAYS = "PREF_NOTIF_DAYS";
  private static final String PREF_TOKEN = "PREF_TOKEN";

  private final SharedPreferences mPreferences;

  public PreferencesHelper(Context context) {
    mPreferences = context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE);
  }

  public void clear() {
    mPreferences.edit().clear().apply();
  }

  public Observable<String> getProfileImage() {
    return Observable.just(mPreferences.getString(PREF_PROFILE_IMAGE, ""));
  }

  public void setProfileImage(String uri) {
    mPreferences.edit().putString(PREF_PROFILE_IMAGE, uri).apply();
  }

  public Observable<String> getProfileName() {
    return Observable.just(mPreferences.getString(PREF_PROFILE_NAME, "name"));
  }

  public void setThemeSelected(int themeSelected) {
    mPreferences.edit().putInt(PREF_THEME_SELECTED, themeSelected).apply();
  }

  public int getThemeSelected() {
    return mPreferences.getInt(PREF_THEME_SELECTED, 0);
  }

  public void setProfileName(String name) {
    mPreferences.edit().putString(PREF_PROFILE_NAME, name).apply();
  }

  public Observable<Integer> getBonusCount() {
    return Observable.just(mPreferences.getInt(PREF_BONUS_COUNT, 0));
  }

  public void setBonusCount(int bonusCount) {
    mPreferences.edit().putInt(PREF_BONUS_COUNT, bonusCount).apply();
  }

  public Observable<String> getProfileLogin() {
    return Observable.just(mPreferences.getString(PREF_PROFILE_LOGIN, "login"));
  }

  public void setProfileLogin(String login) {
    mPreferences.edit().putString(PREF_PROFILE_LOGIN, login).apply();
  }

  public Observable<String> getProfilePassword() {
    return Observable.just(mPreferences.getString(PREF_PROFILE_PASSWORD, "password"));
  }

  public void setProfilePassword(String password) {
    mPreferences.edit().putString(PREF_PROFILE_PASSWORD, password).apply();
  }

  public Observable<String> getProfileEmail() {
    return Observable.just(mPreferences.getString(PREF_PROFILE_EMAIL, "email"));
  }

  public void setProfileEmail(String email) {
    mPreferences.edit().putString(PREF_PROFILE_EMAIL, email).apply();
  }

  public Observable<String> getProfilePhone() {
    return Observable.just(mPreferences.getString(PREF_PROFILE_PHONE, "phone"));
  }

  public void setProfilePhone(String phone) {
    mPreferences.edit().putString(PREF_PROFILE_PHONE, phone).apply();
  }

  public Observable<Integer> getProfileGender() {
    return Observable.just(mPreferences.getInt(PREF_PROFILE_GENDER, 0));
  }

  public void setProfileGender(int gender) {
    mPreferences.edit().putInt(PREF_PROFILE_GENDER, gender).apply();
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

  public String getToken() {
    return mPreferences.getString(PREF_TOKEN, "1");
  }

  public void setToken(String token) {
    mPreferences.edit().putString(PREF_TOKEN, token).apply();
  }
}
