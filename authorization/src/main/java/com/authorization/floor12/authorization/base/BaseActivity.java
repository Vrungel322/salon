package com.authorization.floor12.authorization.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.StringRes;
import android.support.v7.app.ActionBar;
import android.widget.Toast;
import butterknife.ButterKnife;
import com.arellomobile.mvp.MvpAppCompatActivity;
import com.authorization.floor12.authorization.App;
import com.authorization.floor12.authorization.R;
import com.authorization.floor12.authorization.data.local.PreferencesHelper;
import com.tapadoo.alerter.Alerter;
import javax.inject.Inject;

/**
 * Created by Alexander Svyatetsky on 04.05.2017.
 */

public class BaseActivity extends MvpAppCompatActivity {

  @Inject protected Context mContext;
  @Inject protected Navigator mNavigator;
  @Inject protected PreferencesHelper mPreferencesHelper;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    ButterKnife.bind(this);
    App.getAppComponent().inject(this);
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

  protected void showAlert(String title, String message) {
    Alerter.create(BaseActivity.this)
        .setTitle(title)
        .setText(message)
        .setBackgroundColor(R.color.colorAccent)
        .show();
  }
}
