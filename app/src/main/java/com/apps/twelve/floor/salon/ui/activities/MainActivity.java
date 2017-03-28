package com.apps.twelve.floor.salon.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import butterknife.BindView;
import com.apps.twelve.floor.salon.R;
import com.apps.twelve.floor.salon.mvp.presenters.pr_activities.MainActivityPresenter;
import com.apps.twelve.floor.salon.mvp.views.IMainActivityView;
import com.apps.twelve.floor.salon.ui.base.BaseActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;

public class MainActivity extends BaseActivity implements IMainActivityView {

  @InjectPresenter MainActivityPresenter mainActivityPresenter;

  @BindView(R.id.tvTest) TextView tvTest;

  @Override protected void onCreate(Bundle savedInstanceState) {
    setContentView(R.layout.activity_main);
    super.onCreate(savedInstanceState);
  }

  @Override public void afterSplash() {
    mNavigator.startActivity(MainActivity.this, new Intent(MainActivity.this, StartActivity.class));
    finish();
  }
}
