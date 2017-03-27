package com.apps.twelve.floor.salon.ui.base;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Vrungel on 26.01.2017.
 */

public class Navigator implements INavigator {

  //for activity
  @Override public void finishActivity(@NonNull AppCompatActivity appCompatActivity) {
    appCompatActivity.finish();
  }

  @Override public void startActivityClearStack(@NonNull AppCompatActivity appCompatActivity,
      @NonNull Intent intent) {
    appCompatActivity.startActivity(intent);
    appCompatActivity.finishAffinity();
  }

  @Override
  public void startActivity(@NonNull AppCompatActivity appCompatActivity, @NonNull Intent intent) {
    appCompatActivity.startActivity(intent);
  }

  @Override
  public void startActivity(@NonNull AppCompatActivity appCompatActivity, @NonNull String action) {
    appCompatActivity.startActivity(new Intent(action));
  }

  @Override
  public void startActivity(@NonNull AppCompatActivity appCompatActivity, @NonNull String action,
      @NonNull Uri uri) {
    appCompatActivity.startActivity(new Intent(action, uri));
  }

  @Override public void startActivityForResult(@NonNull AppCompatActivity appCompatActivity,
      @NonNull Intent intent, int requestCode) {
    appCompatActivity.startActivityForResult(intent, requestCode);
  }

  //for fragment
  @Override public void addFragment(@NonNull AppCompatActivity appCompatActivity,
      @IdRes int containerId, @NonNull Fragment fragment) {
    appCompatActivity.getSupportFragmentManager()
        .beginTransaction()
        .add(containerId, fragment)
        .commit();
  }

  @Override
  public void addFragmentTag(@NonNull AppCompatActivity appCompatActivity, @IdRes int containerId,
      @NonNull Fragment fragment, @NonNull String fragmentTag) {
    appCompatActivity.getSupportFragmentManager()
        .beginTransaction()
        .add(containerId, fragment, fragmentTag)
        .commit();
  }

  @Override public void addFragmentBackStack(@NonNull AppCompatActivity appCompatActivity,
      @IdRes int containerId, @NonNull Fragment fragment) {
    appCompatActivity.getSupportFragmentManager()
        .beginTransaction()
        .addToBackStack(null)
        .add(containerId, fragment)
        .commit();
  }

  @Override public void addFragmentTagBackStack(@NonNull AppCompatActivity appCompatActivity,
      @IdRes int containerId, @NonNull Fragment fragment, @NonNull String fragmentTag) {
    appCompatActivity.getSupportFragmentManager()
        .beginTransaction()
        .addToBackStack(null)
        .add(containerId, fragment, fragmentTag)
        .commit();
  }

  @Override
  public void replaceFragment(@NonNull AppCompatActivity appCompatActivity, @IdRes int containerId,
      @NonNull Fragment fragment) {
    appCompatActivity.getSupportFragmentManager()
        .beginTransaction()
        .replace(containerId, fragment)
        .commit();
  }

  @Override public void replaceFragmentTagNotCopy(@NonNull AppCompatActivity appCompatActivity,
      @IdRes int containerId, @NonNull Fragment fragment, @NonNull String fragmentTag) {
    FragmentManager fragmentManager = appCompatActivity.getSupportFragmentManager();
    Fragment fragmentCopy = fragmentManager.findFragmentByTag(fragmentTag);
    if (fragmentCopy == null) {
      fragmentManager.beginTransaction().replace(containerId, fragment, fragmentTag).commit();
    }
  }

  @Override
  public void replaceFragmentTagNotCopyBackStack(@NonNull AppCompatActivity appCompatActivity,
      @IdRes int containerId, @NonNull Fragment fragment, @NonNull String fragmentTag) {
    FragmentManager fragmentManager = appCompatActivity.getSupportFragmentManager();
    Fragment fragmentCopy = fragmentManager.findFragmentByTag(fragmentTag);
    if (fragmentCopy == null) {
      appCompatActivity.getSupportFragmentManager()
          .beginTransaction()
          .replace(containerId, fragment)
          .addToBackStack(null)
          .commit();
    }
  }

  @Override public void replaceFragmentBackStack(@NonNull AppCompatActivity appCompatActivity,
      @IdRes int containerId, @NonNull Fragment fragment) {
    appCompatActivity.getSupportFragmentManager()
        .beginTransaction()
        .replace(containerId, fragment)
        .addToBackStack(null)
        .commit();
  }

  @Override public void addChildFragment(@NonNull Fragment parent, @IdRes int containerId,
      @NonNull Fragment child) {
    parent.getChildFragmentManager().beginTransaction().add(containerId, child).commit();
  }

  @Override public void clearBackStack(@NonNull AppCompatActivity activity) {
    if (!isEmptyBackStack(activity)) {
      activity.getSupportFragmentManager()
          .popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
    }
  }

  @Override public boolean isEmptyBackStack(@NonNull AppCompatActivity activity) {
    return activity.getSupportFragmentManager().getBackStackEntryCount() == 0;
  }
}
