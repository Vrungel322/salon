package com.apps.twelve.floor.salon.utils;

import android.content.Context;
import android.content.SharedPreferences;
import com.apps.twelve.floor.salon.R;

import static com.apps.twelve.floor.salon.data.local.PreferencesHelper.PREF_FILE_NAME;
import static com.apps.twelve.floor.salon.data.local.PreferencesHelper.PREF_THEME_OTHER_ACTIVITY;
import static com.apps.twelve.floor.salon.data.local.PreferencesHelper.PREF_THEME_START_ACTIVITY;

/**
 * Created by John on 20.05.2017.
 */

public class ThemeUtils {

  public static int getThemeStartActivity(Context context) {
    SharedPreferences preferences =
        context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE);
    switch (preferences.getInt(PREF_THEME_START_ACTIVITY, 0)) {
      case 0:
        return R.style.AppTheme_NoActionBar;
      case 1:
        return R.style.AppThemeNoActionBarBlue;
      default:
        return R.style.AppTheme_NoActionBar;
    }
  }

  public static int getThemeOtherActivity(Context context) {
    SharedPreferences preferences =
        context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE);
    switch (preferences.getInt(PREF_THEME_OTHER_ACTIVITY, 0)) {
      case 0:
        return R.style.AppTheme_NoActionBar;
      case 1:
        return R.style.AppThemeNoActionBarBlue;
      default:
        return R.style.AppTheme_NoActionBar;
    }
  }
}
