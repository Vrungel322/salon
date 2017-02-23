package com.apps.twelve.floor.salon.ui.base;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by Vrungel on 26.01.2017.
 */

@Singleton public class Navigator implements INavigator {

  @Inject Navigator() {
  }

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

  @Override public void addFragmentAndAddToBackStack(@NonNull AppCompatActivity appCompatActivity,
      @IdRes int containerId, @NonNull Fragment fragment) {
    appCompatActivity.getSupportFragmentManager()
        .beginTransaction()
        .addToBackStack(null)
        .add(containerId, fragment)
        .commit();
  }

  @Override
  public void addFragmentTagAndAddToBackStack(@NonNull AppCompatActivity appCompatActivity,
      @IdRes int containerId, @NonNull Fragment fragment, @NonNull String fragmentTag) {
    appCompatActivity.getSupportFragmentManager()
        .beginTransaction()
        .addToBackStack(null)
        .add(containerId, fragment)
        .commit();
  }

  @Override
  public void replaceFragment(@NonNull AppCompatActivity appCompatActivity, @IdRes int containerId,
      @NonNull Fragment fragment) {
    FragmentManager fragmentManager = appCompatActivity.getSupportFragmentManager();
    fragmentManager.beginTransaction().replace(containerId, fragment).commit();
  }

  @Override
  public void replaceFragmentAndAddToBackStack(@NonNull AppCompatActivity appCompatActivity, @IdRes int containerId,
      @NonNull Fragment fragment) {
    FragmentManager fragmentManager = appCompatActivity.getSupportFragmentManager();
    fragmentManager.beginTransaction().replace(containerId, fragment).addToBackStack(null).commit();
  }

  @Override public void addChildFragment(@NonNull Fragment parent, @IdRes int containerId,
      @NonNull Fragment child) {
    parent.getChildFragmentManager().beginTransaction().add(containerId, child).commit();
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

  @Override public void addChildFragment(@IdRes int containerId, @NonNull Fragment fragment) {
    fragment.getChildFragmentManager().beginTransaction().replace(containerId, fragment).commit();
  }

  @Override public void clearBackStack(@NonNull AppCompatActivity activity) {
    if (activity.getSupportFragmentManager().getBackStackEntryCount() != 0){
      activity.getSupportFragmentManager().popBackStack();
    }
  }
}
