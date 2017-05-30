package com.authorization.floor12.authorization.data.local;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Alexander Svyatetsky on 04.05.2017.
 */

public class PreferencesHelper {

  public static final String BEARER = "bearer";
  private static final String PREF_FILE_NAME = "com.authorization.floor12";

  //THEME
  private static final String THEME_PREF_FILE_NAME = "theme_pref_file_name";
  private static final String THEME_PREF_KEY = "theme_pref_key";

  //TOKEN
  private static final String TOKEN = "token";

  //AUTHORIZED USER
  private static final String USER_ID = "user_id";
  private static final String USER_NAME = "user_name";
  private static final String USER_PHONE = "user_phone";
  private static final String USER_EMAIL = "user_email";
  private static final String USER_PHOTO = "user_photo";
  private static final String USER_GENDER = "user_gender";

  private final SharedPreferences mPreferences;

  public PreferencesHelper(Context context) {
    mPreferences = context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE);
  }

  public void clear() {
    mPreferences.edit().clear().apply();
  }

  public String getToken() {
    return mPreferences.getString(TOKEN, "");
  }

  public void setToken(String token) {
    mPreferences.edit().putString(TOKEN, BEARER + " " + token).apply();
  }

  public long getUserId() {
    return mPreferences.getLong(USER_ID, -1);
  }

  public void setUserId(long userId) {
    mPreferences.edit().putLong(USER_ID, userId).apply();
  }

  public String getUserName() {
    return mPreferences.getString(USER_NAME, "");
  }

  public void setUserName(String userName) {
    mPreferences.edit().putString(USER_NAME, userName).apply();
  }

  public String getUserPhone() {
    return mPreferences.getString(USER_PHONE, "");
  }

  public void setUserPhone(String userPhone) {
    mPreferences.edit().putString(USER_PHONE, userPhone).apply();
  }

  public String getUserEmail() {
    return mPreferences.getString(USER_EMAIL, "");
  }

  public void setUserEmail(String userEmail) {
    mPreferences.edit().putString(USER_EMAIL, userEmail).apply();
  }

  public String getUserPhoto() {
    return mPreferences.getString(USER_PHOTO, "");
  }

  public void setUserPhoto(String userPhotoUrl) {
    mPreferences.edit().putString(USER_PHOTO, userPhotoUrl).apply();
  }

  public void setUserGender(String gender) {
    mPreferences.edit().putString(USER_GENDER, gender).apply();
  }

  public String getUserGender() {
    return mPreferences.getString(USER_GENDER, "");
  }

  public String getThemePrefFileName() {
    return mPreferences.getString(THEME_PREF_FILE_NAME, "");
  }

  public void setThemePrefFileName(String prefFileName) {
    mPreferences.edit().putString(PREF_FILE_NAME, prefFileName).apply();
  }

  public String getThemePrefKey() {
    return mPreferences.getString(THEME_PREF_KEY, "");
  }

  public void setThemePrefKey(String themePrefKey) {
    mPreferences.edit().putString(THEME_PREF_KEY, themePrefKey).apply();
  }
}
