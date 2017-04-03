package com.apps.twelve.floor.salon.ui.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.StringRes;
import android.support.v7.app.ActionBar;
import android.widget.Toast;
import butterknife.ButterKnife;
import com.apps.twelve.floor.salon.App;
import com.apps.twelve.floor.salon.R;
import com.arellomobile.mvp.MvpAppCompatActivity;
import com.tapadoo.alerter.Alerter;
import javax.inject.Inject;

/**
 * Created by John on 27.01.2017.
 */

public abstract class BaseActivity extends MvpAppCompatActivity {

  @Inject protected Context mContext;
  @Inject protected Navigator mNavigator;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    ButterKnife.bind(this);
    App.getAppComponent().inject(this);
  }

  protected void showAlertMessage(String message) {
    //Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
    Alerter.create(this)
        .setTitle("Пройти регистрацию")
        .setText(message)
        .setBackgroundColor(R.color.colorAccent)
        .setOnClickListener(view -> {
          // TODO: 22.02.2017 make registration activity
        })
        .show();
  }

  protected void showToastMessage(String message) {
    Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
  }

  protected void showToastMessage(@StringRes int id) {
    Toast.makeText(mContext, id, Toast.LENGTH_SHORT).show();
  }

  public void setTitleAppBar(@StringRes int resId) {
    ActionBar actionBar = getSupportActionBar();
    if (actionBar != null) {
      actionBar.setTitle(resId);
    }
  }
}
