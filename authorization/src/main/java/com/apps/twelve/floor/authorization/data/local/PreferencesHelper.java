package com.apps.twelve.floor.authorization.data.local;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.StyleRes;
import com.apps.twelve.floor.authorization.R;

/**
 * Created by Alexander Svyatetsky on 04.05.2017.
 */

public class PreferencesHelper {

  public static final String PREF_FILE_NAME = "com.authorization.floor12";
  //THEME
  public static final String PREF_STYLE_RES_ID = "pref_style_res_id";
  //LANGUAGE
  public static final String PREF_LANGUAGE = "pref_language";
  //TOKEN
  private static final String BEARER = "bearer";
  private static final String PREF_TOKEN = "token";
  //AUTHORIZED USER
  private static final String PREF_USER_ID = "user_id";
  private static final String PREF_USER_NAME = "user_name";
  private static final String PREF_USER_PHONE = "user_phone";
  private static final String PREF_USER_EMAIL = "user_email";
  private static final String PREF_USER_IMAGE = "user_image";
  private static final String PREF_USER_GENDER = "user_gender";
  private static final String PREF_USER_BIRTHDAY = "user_birthday";

  private final SharedPreferences mPreferences;
  private Context mContext;

  public PreferencesHelper(Context context) {
    mContext = context;
    mPreferences = context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE);
  }

  public void clear() {
    mPreferences.edit().clear().apply();
  }

  public String getToken() {
    return mPreferences.getString(PREF_TOKEN, "");
  }

  public void setToken(String token) {
    mPreferences.edit().putString(PREF_TOKEN, BEARER + " " + token).apply();
  }

  public String getUserId() {
    return mPreferences.getString(PREF_USER_ID, "");
  }

  public void setUserId(String userId) {
    mPreferences.edit().putString(PREF_USER_ID, userId).apply();
  }

  public String getUserName() {
    return mPreferences.getString(PREF_USER_NAME, "");
  }

  public void setUserName(String userName) {
    mPreferences.edit().putString(PREF_USER_NAME, userName).apply();
  }

  public String getUserPhone() {
    return mPreferences.getString(PREF_USER_PHONE, "");
  }

  public void setUserPhone(String userPhone) {
    mPreferences.edit().putString(PREF_USER_PHONE, userPhone).apply();
  }

  public String getUserEmail() {
    return mPreferences.getString(PREF_USER_EMAIL, "");
  }

  public void setUserEmail(String userEmail) {
    mPreferences.edit().putString(PREF_USER_EMAIL, userEmail).apply();
  }

  public String getUserImage() {
    return mPreferences.getString(PREF_USER_IMAGE, "");
  }

  public void setUserImage(String userPhotoUrl) {
    mPreferences.edit().putString(PREF_USER_IMAGE, userPhotoUrl).apply();
  }

  public int getUserGenderPosition() {
    return mPreferences.getInt(PREF_USER_GENDER, 0);
  }

  public void setUserGenderPosition(int position) {
    mPreferences.edit().putInt(PREF_USER_GENDER, position).apply();
  }

  public String getUserBirthDay() {
    return mPreferences.getString(PREF_USER_BIRTHDAY, "");
  }

  public void setUserBirthDay(String gender) {
    mPreferences.edit().putString(PREF_USER_BIRTHDAY, gender).apply();
  }

  public int getStyleResId() {
    return mPreferences.getInt(PREF_STYLE_RES_ID, R.style.ModuleTheme);
  }

  public void setStyleResId(@StyleRes int styleResId) {
    mPreferences.edit().putInt(PREF_STYLE_RES_ID, styleResId).apply();
  }

  public String getAdditionalField(String key, String defaultValue) {
    return mPreferences.getString(key, defaultValue);
  }

  public void setAdditionalField(String key, String value) {
    mPreferences.edit().putString(key, value).apply();
  }

  public int getAdditionalField(String key, int defaultValue) {
    return Integer.parseInt(getAdditionalField(key, String.valueOf(defaultValue)));
  }

  public void setAdditionalField(String key, int value) {
    setAdditionalField(key, String.valueOf(value));
  }

  public long getAdditionalField(String key, long defaultValue) {
    return Long.parseLong(getAdditionalField(key, String.valueOf(defaultValue)));
  }

  public void setAdditionalField(String key, long value) {
    setAdditionalField(key, String.valueOf(value));
  }

  public boolean getAdditionalField(String key, boolean defaultValue) {
    return Boolean.parseBoolean(getAdditionalField(key, String.valueOf(defaultValue)));
  }

  public void setAdditionalField(String key, boolean value) {
    setAdditionalField(key, String.valueOf(value));
  }

  public boolean isExistAdditionalField(String key) {
    return mPreferences.contains(key);
  }

  public void setLanguage(String language) {
    mPreferences.edit().putString(PREF_LANGUAGE, language).apply();
  }

  public String getLanguage() {
    return mPreferences.getString(PREF_LANGUAGE, "ru");
  }
}
