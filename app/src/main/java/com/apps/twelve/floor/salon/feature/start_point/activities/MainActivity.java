package com.apps.twelve.floor.salon.feature.start_point.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
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
    // remove title
    requestWindowFeature(Window.FEATURE_NO_TITLE);
    getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
        WindowManager.LayoutParams.FLAG_FULLSCREEN);
    setContentView(R.layout.activity_main);
    super.onCreate(savedInstanceState);
  }

  @Override public void afterSplash() {
    mNavigator.startActivity(MainActivity.this, new Intent(MainActivity.this, StartActivity.class));
    finish();
  }
}
