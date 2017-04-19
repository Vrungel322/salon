package com.apps.twelve.floor.salon.data.local;

import android.content.Context;
import android.content.SharedPreferences;
import rx.Observable;

/**
 * Created by John on 26.01.2017.
 */

public class PreferencesHelper {

  private static final String PREF_FILE_NAME = "com.salon.Salon";

  private static final String PREF_PROFILE_IMAGE = "PREF_PROFILE_IMAGE";
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

  public String getToken() {
    return mPreferences.getString(PREF_TOKEN, "1");
  }

  public void setToken(String token) {
    mPreferences.edit().putString(PREF_TOKEN, token).apply();
  }
}
