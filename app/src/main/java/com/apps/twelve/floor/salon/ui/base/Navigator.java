package com.apps.twelve.floor.salon.ui.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by Vrungel on 26.01.2017.
 */

@Singleton public class Navigator implements INavigator {

  private Context mContext;

  @Inject Navigator(Context context) {
    mContext = context;
  }

  //for activity
  @Override public void finishActivity(@NonNull Activity activity) {
    activity.finish();
  }

  @Override
  public void startActivityClearStack(@NonNull Activity activity, @NonNull Intent intent) {
    activity.startActivity(intent);
    activity.finishAffinity();
  }

  @Override public void startActivity(@NonNull Intent intent) {
    mContext.startActivity(intent);
  }

  @Override public void startActivity(@NonNull String action) {
    mContext.startActivity(new Intent(action));
  }

  @Override public void startActivity(@NonNull String action, @NonNull Uri uri) {
    mContext.startActivity(new Intent(action, uri));
  }

  @Override public void startActivityForResult(@NonNull Activity activity, @NonNull Intent intent,
      int requestCode) {
    activity.startActivityForResult(intent, requestCode);
  }

  //for fragment
  @Override public void addFragment(@NonNull Activity activity, @IdRes int containerId,
      @NonNull Fragment fragment) {
    ((FragmentActivity) activity).getSupportFragmentManager()
        .beginTransaction()
        .add(containerId, fragment)
        .commit();
  }

  @Override
  public void addFragmentAndAddToBackStack(@NonNull Activity activity, @IdRes int containerId,
      @NonNull Fragment fragment) {
    ((FragmentActivity) activity).getSupportFragmentManager()
        .beginTransaction()
        .addToBackStack(null)
        .add(containerId, fragment)
        .commit();
  }

  @Override
  public void addFragmentTagAndAddToBackStack(@NonNull Activity activity, @IdRes int containerId,
      @NonNull Fragment fragment, @NonNull String fragmentTag) {
    ((FragmentActivity) activity).getSupportFragmentManager()
        .beginTransaction()
        .addToBackStack(null)
        .add(containerId, fragment)
        .commit();
  }

  @Override public void replaceFragment(@NonNull Activity activity, @IdRes int containerId,
      @NonNull Fragment fragment) {
    FragmentManager fragmentManager = ((FragmentActivity) activity).getSupportFragmentManager();
    fragmentManager.beginTransaction().replace(containerId, fragment).commit();
  }

  @Override public void replaceFragment(@IdRes int containerId, @NonNull Fragment fragment,
      @NonNull String fragmentTag, Bundle args) {

  }

  @Override
  public void replaceFragmentAndAddToBackStack(@IdRes int containerId, @NonNull Fragment fragment,
      Bundle args, String backstackTag) {

  }

  @Override
  public void replaceFragmentAndAddToBackStack(@IdRes int containerId, @NonNull Fragment fragment,
      @NonNull String fragmentTag, Bundle args, String backstackTag) {

  }

  @Override public void replaceChildFragment(@IdRes int containerId, @NonNull Fragment fragment) {
    fragment.getChildFragmentManager().beginTransaction().replace(containerId, fragment).commit();
  }
}
