package com.apps.twelve.floor.salon.data.local;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by John on 26.01.2017.
 */

public class PreferencesHelper {

  private static final String PREF_FILE_NAME = "com.salon.Salon";

  private final SharedPreferences mPreferences;

  public PreferencesHelper(Context context) {
    mPreferences = context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE);
  }

  public void clear() {
    mPreferences.edit().clear().apply();
  }
}
