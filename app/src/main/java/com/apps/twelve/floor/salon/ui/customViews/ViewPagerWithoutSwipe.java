package com.apps.twelve.floor.salon.ui.customViews;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by Vrungel on 23.03.2017.
 */

public class ViewPagerWithoutSwipe extends ViewPager {

  public ViewPagerWithoutSwipe(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  @Override public boolean onTouchEvent(MotionEvent event) {

    return false;
  }

  @Override public boolean onInterceptTouchEvent(MotionEvent event) {

    return false;
  }
}