package com.apps.twelve.floor.salon.feature.start_point.activities;

import android.content.Intent;
import android.os.Bundle;
import com.apps.twelve.floor.salon.R;
import com.apps.twelve.floor.salon.base.BaseActivity;
import com.apps.twelve.floor.salon.feature.start_point.presenters.MainActivityPresenter;
import com.apps.twelve.floor.salon.feature.start_point.views.IMainActivityView;
import com.apps.twelve.floor.salon.utils.ThemeUtils;
import com.arellomobile.mvp.presenter.InjectPresenter;

public class MainActivity extends BaseActivity implements IMainActivityView {

  @InjectPresenter MainActivityPresenter mainActivityPresenter;

  @Override protected void onCreate(Bundle savedInstanceState) {
    setTheme(ThemeUtils.getThemeNoActionBar(getBaseContext()));
    setContentView(R.layout.activity_main);
    super.onCreate(savedInstanceState);
  }

  @Override public void afterSplash() {
    mNavigator.startActivity(MainActivity.this, new Intent(MainActivity.this, StartActivity.class));
    finish();
  }
}
