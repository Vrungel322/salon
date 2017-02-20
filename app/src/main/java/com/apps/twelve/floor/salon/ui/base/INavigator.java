package com.apps.twelve.floor.salon.ui.base;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;

/**
 * Created by John on 27.01.2017.
 */

public interface INavigator {

  //for activity
  void finishActivity(@NonNull Activity activity);

  void startActivityClearStack(@NonNull Activity activity, @NonNull Intent intent);

  void startActivity(@NonNull Intent intent);

  void startActivity(@NonNull String action);

  void startActivity(@NonNull String action, @NonNull Uri uri);

  void startActivityForResult(@NonNull Activity activity, @NonNull Intent intent, int requestCode);

  //for fragment
  void addFragment(@NonNull Activity activity, @IdRes int containerId, @NonNull Fragment fragment);

  void addFragmentAndAddToBackStack(@NonNull Activity activity, @IdRes int containerId,
      @NonNull Fragment fragment);

  void addFragmentTagAndAddToBackStack(@NonNull Activity activity, @IdRes int containerId,
      @NonNull Fragment fragment, @NonNull String fragmentTag);

  void replaceFragment(@NonNull Activity activity, @IdRes int containerId,
      @NonNull Fragment fragment);

  void replaceFragment(@IdRes int containerId, @NonNull Fragment fragment,
      @NonNull String fragmentTag, Bundle args);

  void replaceFragmentAndAddToBackStack(@IdRes int containerId, @NonNull Fragment fragment,
      Bundle args, String backstackTag);

  void replaceFragmentAndAddToBackStack(@IdRes int containerId, @NonNull Fragment fragment,
      @NonNull String fragmentTag, Bundle args, String backstackTag);

  void replaceChildFragment(@IdRes int containerId, @NonNull Fragment fragment);
}
