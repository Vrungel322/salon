package com.authorization.floor12.authorization.utils;

import android.content.Context;
import android.content.SharedPreferences;
import com.authorization.floor12.authorization.R;

import static com.authorization.floor12.authorization.utils.Constants.PREF_FILE_NAME;
import static com.authorization.floor12.authorization.utils.Constants.PREF_THEME_SELECTED;

/**
 * Created by John on 20.05.2017.
 */

public class ThemeUtils {

  public static int getThemeOtherActivity(Context context) {
    SharedPreferences preferences =
        context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE);
    switch (preferences.getInt(PREF_THEME_SELECTED, 0)) {
      case 0:
        return R.style.ModuleAppTheme;
      case 1:
        return R.style.ModuleAppThemeBlue;
      case 2:
        return R.style.ModuleAppThemeGreen;
      case 3:
        return R.style.ModuleAppThemeYellow;
      case 4:
        return R.style.ModuleAppThemeGray;
      case 5:
        return R.style.ModuleAppThemePurple;
      case 6:
        return R.style.ModuleAppThemeRed;
      default:
        return R.style.ModuleAppTheme;
    }
  }
}
