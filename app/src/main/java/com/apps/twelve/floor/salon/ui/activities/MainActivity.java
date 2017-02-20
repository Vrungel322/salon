package com.apps.twelve.floor.salon.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import butterknife.BindView;
import com.apps.twelve.floor.salon.R;
import com.apps.twelve.floor.salon.mvp.presenters.MainActivityPresenter;
import com.apps.twelve.floor.salon.mvp.views.IMainActivityView;
import com.apps.twelve.floor.salon.ui.base.BaseActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;

public class MainActivity extends BaseActivity implements IMainActivityView {

  @InjectPresenter MainActivityPresenter mainActivityPresenter;

  @BindView(R.id.tvTest) TextView tvTest;

  @Override protected void onCreate(Bundle savedInstanceState) {
    setContentView(R.layout.activity_main);
    super.onCreate(savedInstanceState);

    // TODO: 20.02.2017 check if user logged in
    startActivity(new Intent(MainActivity.this, StartActivity.class));
    finish();
  }
}
