package com.apps.twelve.floor.salon.feature.settings.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import com.apps.twelve.floor.salon.R;
import com.apps.twelve.floor.salon.base.BaseActivity;
import com.apps.twelve.floor.salon.data.local.LocaleHelper;
import com.apps.twelve.floor.salon.feature.settings.fragments.SettingsFragment;
import com.apps.twelve.floor.salon.feature.settings.presenters.SettingsActivityPresenter;
import com.apps.twelve.floor.salon.feature.settings.views.ISettingsActivityView;
import com.apps.twelve.floor.salon.feature.start_point.activities.StartActivity;
import com.apps.twelve.floor.salon.utils.ThemeUtils;
import com.arellomobile.mvp.presenter.InjectPresenter;

public class SettingsActivity extends BaseActivity implements ISettingsActivityView {

  @InjectPresenter SettingsActivityPresenter mSettingsActivityPresenter;

  @Override protected void onCreate(Bundle savedInstanceState) {
    setTheme(ThemeUtils.getThemeActionBar(getBaseContext()));
    setContentView(R.layout.activity_settings);
    super.onCreate(savedInstanceState);

    setTitleAppBar(R.string.menu_settings);
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

  @Override public void addFragmentSettings() {
    mNavigator.addFragment(this, R.id.container_settings, SettingsFragment.newInstance());
  }

  @Override public void showConnectErrorMessage() {
    showAlertMessage(getString(R.string.error_connection),
        getString(R.string.сheck_internet_connection));
  }

  @Override public void logoutUser() {
    mNavigator.startActivityClearStack(this, new Intent(this, StartActivity.class));
  }

  @Override public void startSignInActivity() {
    mAuthorizationManager.startSignInActivity(this, ThemeUtils.getThemeActionBar(this),
        LocaleHelper.getLanguage(SettingsActivity.this));
  }

  @Override public void showWrongMessage() {
    showAlertMessage(getResources().getString(R.string.dialog_error_title),
        getResources().getString(R.string.wrong));
  }
}
