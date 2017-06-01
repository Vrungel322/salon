package com.apps.twelve.floor.salon.utils;

import android.content.Context;
import android.content.SharedPreferences;
import com.apps.twelve.floor.salon.R;

import static com.apps.twelve.floor.salon.data.local.PreferencesHelper.PREF_FILE_NAME;
import static com.apps.twelve.floor.salon.data.local.PreferencesHelper.PREF_THEME_SELECTED;

/**
 * Created by John on 20.05.2017.
 */

public class ThemeUtils {

  public static int getThemeNoActionBar(Context context) {
    SharedPreferences preferences =
        context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE);
    switch (preferences.getInt(PREF_THEME_SELECTED, 0)) {
      case 0:
        return R.style.AppTheme_NoActionBar;
      case 1:
        return R.style.AppThemeNoActionBarBlue;
      case 2:
        return R.style.AppThemeNoActionBarGreen;
      case 3:
        return R.style.AppThemeNoActionBarYellow;
      case 4:
        return R.style.AppThemeNoActionBarGray;
      case 5:
        return R.style.AppThemeNoActionBarPurple;
      case 6:
        return R.style.AppThemeNoActionBarRed;
      default:
        return R.style.AppTheme_NoActionBar;
    }
  }

  public static int getThemeActionBar(Context context) {
    SharedPreferences preferences =
        context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE);
    switch (preferences.getInt(PREF_THEME_SELECTED, 0)) {
      case 0:
        return R.style.AppTheme;
      case 1:
        return R.style.AppThemeBlue;
      case 2:
        return R.style.AppThemeGreen;
      case 3:
        return R.style.AppThemeYellow;
      case 4:
        return R.style.AppThemeGray;
      case 5:
        return R.style.AppThemePurple;
      case 6:
        return R.style.AppThemeRed;
      default:
        return R.style.AppTheme;
    }
  }
}
