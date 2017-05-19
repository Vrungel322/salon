package com.apps.twelve.floor.salon.feature.settings.activities;

import android.os.Bundle;
import android.view.MenuItem;
import com.afollestad.aesthetic.Aesthetic;
import com.apps.twelve.floor.salon.R;
import com.apps.twelve.floor.salon.base.BaseActivity;
import com.apps.twelve.floor.salon.feature.settings.fragments.SettingsFragment;
import com.apps.twelve.floor.salon.feature.settings.presenters.SettingsActivityPresenter;
import com.apps.twelve.floor.salon.feature.settings.views.ISettingsActivityView;
import com.arellomobile.mvp.presenter.InjectPresenter;

public class SettingsActivity extends BaseActivity implements ISettingsActivityView {

  @InjectPresenter SettingsActivityPresenter mSettingsActivityPresenter;

  @Override protected void onCreate(Bundle savedInstanceState) {
    Aesthetic.attach(this);
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
}
