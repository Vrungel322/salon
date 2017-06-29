package com.apps.twelve.floor.salon.feature.start_point.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import butterknife.BindView;
import com.apps.twelve.floor.salon.R;
import com.apps.twelve.floor.salon.base.BaseActivity;
import com.apps.twelve.floor.salon.feature.start_point.presenters.MainActivityPresenter;
import com.apps.twelve.floor.salon.feature.start_point.views.IMainActivityView;
import com.apps.twelve.floor.salon.utils.ThemeUtils;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.bumptech.glide.Glide;

public class MainActivity extends BaseActivity implements IMainActivityView {

  @InjectPresenter MainActivityPresenter mainActivityPresenter;

  @BindView(R.id.ivSplash) ImageView mImageViewSplash;

  @Override protected void onCreate(Bundle savedInstanceState) {
    setTheme(ThemeUtils.getThemeNoActionBar(getBaseContext()));
    setContentView(R.layout.activity_main);
    super.onCreate(savedInstanceState);
    Glide.with(this).load(R.drawable.splash).into(mImageViewSplash);
  }

  @Override public void afterSplash() {
    mNavigator.startActivity(MainActivity.this, new Intent(MainActivity.this, StartActivity.class));
    finish();
  }
}
