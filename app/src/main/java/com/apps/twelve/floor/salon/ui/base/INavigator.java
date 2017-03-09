package com.apps.twelve.floor.salon.ui.base;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by John on 27.01.2017.
 */

public interface INavigator {

  //for activity
  void finishActivity(@NonNull AppCompatActivity appCompatActivity);

  void startActivityClearStack(@NonNull AppCompatActivity appCompatActivity,
      @NonNull Intent intent);

  void startActivity(@NonNull AppCompatActivity appCompatActivity, @NonNull Intent intent);

  void startActivity(@NonNull AppCompatActivity appCompatActivity, @NonNull String action);

  void startActivity(@NonNull AppCompatActivity appCompatActivity, @NonNull String action,
      @NonNull Uri uri);

  void startActivityForResult(@NonNull AppCompatActivity appCompatActivity, @NonNull Intent intent,
      int requestCode);

  //for fragment
  void addFragment(@NonNull AppCompatActivity appCompatActivity, @IdRes int containerId,
      @NonNull Fragment fragment);

  void addFragmentTag(@NonNull AppCompatActivity appCompatActivity, @IdRes int containerId,
      @NonNull Fragment fragment, @NonNull String fragmentTag);

  void addFragmentBackStack(@NonNull AppCompatActivity appCompatActivity, @IdRes int containerId,
      @NonNull Fragment fragment);

  void addFragmentTagBackStack(@NonNull AppCompatActivity appCompatActivity, @IdRes int containerId,
      @NonNull Fragment fragment, @NonNull String fragmentTag);

  void replaceFragment(@NonNull AppCompatActivity appCompatActivity, @IdRes int containerId,
      @NonNull Fragment fragment);

  void replaceFragmentTagNotCopy(@NonNull AppCompatActivity appCompatActivity,
      @IdRes int containerId, @NonNull Fragment fragment, @NonNull String fragmentTag);

  void replaceFragmentTagNotCopyBackStack(@NonNull AppCompatActivity appCompatActivity,
      @IdRes int containerId, @NonNull Fragment fragment, @NonNull String fragmentTag);

  void replaceFragmentBackStack(@NonNull AppCompatActivity appCompatActivity,
      @IdRes int containerId, @NonNull Fragment fragment);

  void addChildFragment(@NonNull Fragment parent, @IdRes int containerId, @NonNull Fragment child);

  void clearBackStack(@NonNull AppCompatActivity appCompatActivity);

  boolean isEmptyBackStack(@NonNull AppCompatActivity appCompatActivity);
}
