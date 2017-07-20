package com.apps.twelve.floor.authorization.logic.userdetail.activities;
/*
 * Copyright 2017, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import com.apps.twelve.floor.authorization.R;
import com.apps.twelve.floor.authorization.base.BaseActivity;
import com.apps.twelve.floor.authorization.logic.authorization.activities.ModuleSignInActivity;
import com.apps.twelve.floor.authorization.logic.userdetail.fragments.UserProfileFragment;
import com.apps.twelve.floor.authorization.logic.userdetail.presenters.UserProfileActivityPresenter;
import com.apps.twelve.floor.authorization.logic.userdetail.views.IUserProfileActivity;
import com.apps.twelve.floor.authorization.utils.ThemeUtils;
import com.arellomobile.mvp.presenter.InjectPresenter;

public class UserProfileActivity extends BaseActivity implements IUserProfileActivity {

  @InjectPresenter UserProfileActivityPresenter mPresenter;

  @Override protected void onCreate(Bundle savedInstanceState) {
    setTheme(ThemeUtils.getCurrentTheme(getBaseContext()));
    setContentView(R.layout.activity_user_profile);
    super.onCreate(savedInstanceState);

    setTitleAppBar(R.string.user_profile_label);
    setDisplayHomeAsUpEnabled(true);
  }

  @Override public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case android.R.id.home:
        onBackPressed();
        return true;
      default:
        return super.onOptionsItemSelected(item);
    }
  }

  @Override public void addUserProfileFragment() {
    mNavigator.addFragment(this, R.id.container, UserProfileFragment.newInstance(R.id.container));
  }

  @Override public void showConnectErrorMessage() {
    showAlert(getString(R.string.error_title_no_internet_connection),
        getString(R.string.no_internet_connection));
  }

  @Override public void logoutUser() {
    finish();
  }

  @Override public void startSignInActivity() {
    mNavigator.startActivity(UserProfileActivity.this,
        new Intent(UserProfileActivity.this, ModuleSignInActivity.class));
  }
}
