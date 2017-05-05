package com.apps.twelve.floor.salon.base;

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

public class Navigator {

  //for activity
  public void finishActivity(@NonNull AppCompatActivity appCompatActivity) {
    appCompatActivity.finish();
  }

  public void startActivityClearStack(@NonNull AppCompatActivity appCompatActivity,
      @NonNull Intent intent) {
    appCompatActivity.startActivity(intent);
    appCompatActivity.finishAffinity();
  }

  public void startActivity(@NonNull AppCompatActivity appCompatActivity, @NonNull Intent intent) {
    appCompatActivity.startActivity(intent);
  }

  public void startActivity(@NonNull AppCompatActivity appCompatActivity, @NonNull String action) {
    appCompatActivity.startActivity(new Intent(action));
  }

  public void startActivity(@NonNull AppCompatActivity appCompatActivity, @NonNull String action,
      @NonNull Uri uri) {
    appCompatActivity.startActivity(new Intent(action, uri));
  }

  public void startActivityForResult(@NonNull AppCompatActivity appCompatActivity,
      @NonNull Intent intent, int requestCode) {
    appCompatActivity.startActivityForResult(intent, requestCode);
  }

  //for fragment
  public void addFragment(@NonNull AppCompatActivity appCompatActivity, @IdRes int containerId,
      @NonNull Fragment fragment) {
    appCompatActivity.getSupportFragmentManager()
        .beginTransaction()
        .add(containerId, fragment)
        .commit();
  }

  public void addFragmentTag(@NonNull AppCompatActivity appCompatActivity, @IdRes int containerId,
      @NonNull Fragment fragment, @NonNull String fragmentTag) {
    appCompatActivity.getSupportFragmentManager()
        .beginTransaction()
        .add(containerId, fragment, fragmentTag)
        .commit();
  }

  public void addFragmentBackStack(@NonNull AppCompatActivity appCompatActivity,
      @IdRes int containerId, @NonNull Fragment fragment) {
    appCompatActivity.getSupportFragmentManager()
        .beginTransaction()
        .addToBackStack(null)
        .add(containerId, fragment)
        .commit();
  }

  public void addFragmentTagBackStack(@NonNull AppCompatActivity appCompatActivity,
      @IdRes int containerId, @NonNull Fragment fragment, @NonNull String fragmentTag) {
    appCompatActivity.getSupportFragmentManager()
        .beginTransaction()
        .addToBackStack(null)
        .add(containerId, fragment, fragmentTag)
        .commit();
  }

  public void addFragmentTagClearBackStackNotCopy(@NonNull AppCompatActivity appCompatActivity,
      @IdRes int containerId, @NonNull Fragment fragment, @NonNull String fragmentTag) {
    FragmentManager fragmentManager = appCompatActivity.getSupportFragmentManager();
    Fragment fragmentCopy = fragmentManager.findFragmentByTag(fragmentTag);
    if (fragmentCopy == null) {
      clearBackStack(appCompatActivity);
      appCompatActivity.getSupportFragmentManager()
          .beginTransaction()
          .addToBackStack(null)
          .add(containerId, fragment, fragmentTag)
          .commit();
    }
  }

  public void addFragmentTagBackStackNotCopy(@NonNull AppCompatActivity appCompatActivity,
      @IdRes int containerId, @NonNull Fragment fragment, @NonNull String fragmentTag) {
    FragmentManager fragmentManager = appCompatActivity.getSupportFragmentManager();
    Fragment fragmentCopy = fragmentManager.findFragmentByTag(fragmentTag);
    if (fragmentCopy == null) {
      appCompatActivity.getSupportFragmentManager()
          .beginTransaction()
          .addToBackStack(null)
          .add(containerId, fragment, fragmentTag)
          .commit();
    }
  }

  public void replaceFragment(@NonNull AppCompatActivity appCompatActivity, @IdRes int containerId,
      @NonNull Fragment fragment) {
    appCompatActivity.getSupportFragmentManager()
        .beginTransaction()
        .replace(containerId, fragment)
        .commit();
  }

  public void replaceFragmentTag(@NonNull AppCompatActivity appCompatActivity,
      @IdRes int containerId, @NonNull Fragment fragment, @NonNull String fragmentTag) {
    appCompatActivity.getSupportFragmentManager()
        .beginTransaction()
        .replace(containerId, fragment, fragmentTag)
        .commit();
  }

  public void replaceFragmentTagNotCopy(@NonNull AppCompatActivity appCompatActivity,
      @IdRes int containerId, @NonNull Fragment fragment, @NonNull String fragmentTag) {
    FragmentManager fragmentManager = appCompatActivity.getSupportFragmentManager();
    Fragment fragmentCopy = fragmentManager.findFragmentByTag(fragmentTag);
    if (fragmentCopy == null) {
      fragmentManager.beginTransaction().replace(containerId, fragment, fragmentTag).commit();
    }
  }

  public boolean isFragmentTag(@NonNull AppCompatActivity appCompatActivity,
      @NonNull String fragmentTag) {
    FragmentManager fragmentManager = appCompatActivity.getSupportFragmentManager();
    Fragment fragmentCopy = fragmentManager.findFragmentByTag(fragmentTag);
    return fragmentCopy != null;
  }

  public boolean isVisibleFragmentTag(@NonNull AppCompatActivity appCompatActivity,
      @NonNull String fragmentTag) {
    FragmentManager fragmentManager = appCompatActivity.getSupportFragmentManager();
    Fragment fragment = fragmentManager.findFragmentByTag(fragmentTag);
    return fragment != null && fragment.isVisible();
  }

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

  public void replaceFragmentBackStack(@NonNull AppCompatActivity appCompatActivity,
      @IdRes int containerId, @NonNull Fragment fragment) {
    appCompatActivity.getSupportFragmentManager()
        .beginTransaction()
        .replace(containerId, fragment)
        .addToBackStack(null)
        .commit();
  }

  public void addChildFragment(@NonNull Fragment parent, @IdRes int containerId,
      @NonNull Fragment child) {
    parent.getChildFragmentManager().beginTransaction().add(containerId, child).commit();
  }

  public void addChildFragmentTag(@NonNull Fragment parent, @IdRes int containerId,
      @NonNull Fragment child, @NonNull String fragmentTag) {
    parent.getChildFragmentManager().beginTransaction()
        .add(containerId, child, fragmentTag)
        .commit();
  }

  public void clearBackStack(@NonNull AppCompatActivity activity) {
    if (!isEmptyBackStack(activity)) {
      activity.getSupportFragmentManager()
          .popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
    }
  }

  public void clearBackStackWithCountFragment(@NonNull AppCompatActivity appCompatActivity,
      int count) {
    FragmentManager fragmentManager = appCompatActivity.getSupportFragmentManager();
    for (int i = count; i < fragmentManager.getBackStackEntryCount(); i++) {
      fragmentManager.popBackStack();
    }
  }

  public boolean isEmptyBackStack(@NonNull AppCompatActivity activity) {
    return activity.getSupportFragmentManager().getBackStackEntryCount() == 0;
  }

  public boolean isOneFragmentBackStack(@NonNull AppCompatActivity appCompatActivity) {
    return appCompatActivity.getSupportFragmentManager().getBackStackEntryCount() == 1;
  }
}
