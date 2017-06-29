package com.apps.twelve.floor.salon.utils;

import android.content.Context;
import android.content.SharedPreferences;
import com.apps.twelve.floor.salon.R;

import static com.apps.twelve.floor.salon.data.local.PreferencesHelper.PREF_FILE_NAME;
import static com.apps.twelve.floor.salon.data.local.PreferencesHelper.PREF_THEME_SELECTED;
import static com.apps.twelve.floor.salon.utils.Constants.Theme.BLUE;
import static com.apps.twelve.floor.salon.utils.Constants.Theme.GRAY;
import static com.apps.twelve.floor.salon.utils.Constants.Theme.GREEN;
import static com.apps.twelve.floor.salon.utils.Constants.Theme.PINK;
import static com.apps.twelve.floor.salon.utils.Constants.Theme.PURPLE;
import static com.apps.twelve.floor.salon.utils.Constants.Theme.RED;
import static com.apps.twelve.floor.salon.utils.Constants.Theme.YELLOW;

/**
 * Created by John on 20.05.2017.
 */

public class ThemeUtils {

  public static int getThemeNoActionBar(Context context) {
    SharedPreferences preferences =
        context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE);
    switch (preferences.getInt(PREF_THEME_SELECTED, 0)) {
      case PINK:
        return R.style.AppTheme_NoActionBar;
      case BLUE:
        return R.style.AppThemeNoActionBarBlue;
      case GREEN:
        return R.style.AppThemeNoActionBarGreen;
      case YELLOW:
        return R.style.AppThemeNoActionBarYellow;
      case GRAY:
        return R.style.AppThemeNoActionBarGray;
      case PURPLE:
        return R.style.AppThemeNoActionBarPurple;
      case RED:
        return R.style.AppThemeNoActionBarRed;
      default:
        return R.style.AppTheme_NoActionBar;
    }
  }

  public static int getThemeActionBar(Context context) {
    SharedPreferences preferences =
        context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE);
    switch (preferences.getInt(PREF_THEME_SELECTED, 0)) {
      case PINK:
        return R.style.AppTheme;
      case BLUE:
        return R.style.AppThemeBlue;
      case GREEN:
        return R.style.AppThemeGreen;
      case YELLOW:
        return R.style.AppThemeYellow;
      case GRAY:
        return R.style.AppThemeGray;
      case PURPLE:
        return R.style.AppThemePurple;
      case RED:
        return R.style.AppThemeRed;
      default:
        return R.style.AppTheme;
    }
  }
}
