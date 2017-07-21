package com.apps.twelve.floor.authorization.data.local;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.StyleRes;
import android.text.TextUtils;
import com.apps.twelve.floor.authorization.R;

/**
 * Created by Alexander Svyatetsky on 04.05.2017.
 */

public class PreferencesHelper {

  public static final String PREF_FILE_NAME = "com.authorization.floor12";
  //THEME
  public static final String PREF_STYLE_RES_ID = "pref_style_res_id";
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

  public long getUserId() {
    return mPreferences.getLong(PREF_USER_ID, -1);
  }

  public void setUserId(long userId) {
    mPreferences.edit().putLong(PREF_USER_ID, userId).apply();
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

  public String getUserGender() {
    return mPreferences.getString(PREF_USER_GENDER, mContext.getString(R.string.default_gender));
  }

  public void setUserGender(String gender) {
    mPreferences.edit().putString(PREF_USER_GENDER, gender).apply();
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

  public boolean isExistAdditionalField(String key) {
    return !TextUtils.isEmpty(mPreferences.getString(key, ""));
  }
}