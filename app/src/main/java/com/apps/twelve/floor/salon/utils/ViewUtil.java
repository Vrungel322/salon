package com.apps.twelve.floor.salon.utils;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.support.design.widget.TabLayout;
import android.view.Surface;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.apps.twelve.floor.salon.utils.Constants.Rotation.LANDSCAPE;
import static com.apps.twelve.floor.salon.utils.Constants.Rotation.PORTRAIT;

/**
 * Created by John on 26.01.2017.
 */

public final class ViewUtil {

  public static float pxToDp(float px) {
    float densityDpi = Resources.getSystem().getDisplayMetrics().densityDpi;
    return px / (densityDpi / 160f);
  }

  public static int dpToPx(int dp) {
    float density = Resources.getSystem().getDisplayMetrics().density;
    return Math.round(dp * density);
  }

  public static void hideKeyboard(Activity activity) {
    InputMethodManager imm =
        (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
    imm.hideSoftInputFromWindow(activity.getWindow().getDecorView().getWindowToken(), 0);
  }

  public static void showKeyboard(Activity activity) {
    InputMethodManager imm =
        (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
    imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
  }

  public static boolean checkPhone(String value) {
    Pattern pattern = Pattern.compile("^[+][0-9]{12}$");
    Matcher matcher = pattern.matcher(value);
    return matcher.matches();
  }

  public static int getRotation(Context context) {
    final int rotation =
        ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay()
            .getOrientation();
    if (rotation == Surface.ROTATION_0 || rotation == Surface.ROTATION_180) {
      return PORTRAIT;
    } else {
      return LANDSCAPE;
    }
  }

  public static class TabLayoutUtils {

    public static void enableTabs(TabLayout tabLayout, Boolean enable) {
      ViewGroup viewGroup = getTabViewGroup(tabLayout);
      if (viewGroup != null) {
        for (int childIndex = 0; childIndex < viewGroup.getChildCount(); childIndex++) {
          View tabView = viewGroup.getChildAt(childIndex);
          if (tabView != null) tabView.setEnabled(enable);
        }
      }
    }

    private static ViewGroup getTabViewGroup(TabLayout tabLayout) {
      ViewGroup viewGroup = null;

      if (tabLayout != null && tabLayout.getChildCount() > 0) {
        View view = tabLayout.getChildAt(0);
        if (view != null && view instanceof ViewGroup) viewGroup = (ViewGroup) view;
      }
      return viewGroup;
    }
  }
}
