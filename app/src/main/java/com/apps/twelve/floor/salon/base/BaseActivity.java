package com.apps.twelve.floor.salon.base;

import android.app.ActivityManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.util.TypedValue;
import android.widget.Toast;
import butterknife.ButterKnife;
import com.apps.twelve.floor.authorization.AuthorizationManager;
import com.apps.twelve.floor.salon.App;
import com.apps.twelve.floor.salon.R;
import com.arellomobile.mvp.MvpAppCompatActivity;
import com.tapadoo.alerter.Alerter;
import javax.inject.Inject;

import static com.apps.twelve.floor.salon.R.mipmap.ic_launcher;

/**
 * Created by John on 27.01.2017.
 */

public abstract class BaseActivity extends MvpAppCompatActivity {

  @Inject protected Context mContext;
  @Inject protected Navigator mNavigator;
  @Inject protected AuthorizationManager mAuthorizationManager;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    ButterKnife.bind(this);
    App.getAppComponent().inject(this);

    /* app color in recent-apps */
    Bitmap bm = BitmapFactory.decodeResource(getResources(), ic_launcher);
    TypedValue value = new TypedValue();
    getTheme().resolveAttribute(R.attr.colorPrimary, value, true);
    ActivityManager.TaskDescription taskDesc;
    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
      taskDesc = new ActivityManager.TaskDescription(getString(R.string.app_name), bm,
          ContextCompat.getColor(this, value.resourceId));
      setTaskDescription(taskDesc);
    }
  }

  protected void showAlertMessage(String title, String message) {
    TypedValue value = new TypedValue();
    getTheme().resolveAttribute(R.attr.colorAccent, value, true);
    Alerter.create(this)
        .setTitle(title)
        .setText(message)
        .setBackgroundColor(value.resourceId)
        .setOnClickListener(view -> {
        })
        .show();
  }

  protected void showToastMessage(String message) {
    Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
  }

  protected void showToastMessage(@StringRes int id) {
    Toast.makeText(mContext, id, Toast.LENGTH_SHORT).show();
  }

  protected void showLongAlertMessage(String title, String message) {
    TypedValue value = new TypedValue();
    getTheme().resolveAttribute(R.attr.colorAccent, value, true);
    Alerter.create(this)
        .setTitle(title)
        .setText(message)
        .enableInfiniteDuration(true)
        .enableSwipeToDismiss()
        .setBackgroundColor(value.resourceId)
        .setOnClickListener(view -> {

        })
        .show();
  }

  public void setTitleAppBar(@StringRes int resId) {
    ActionBar actionBar = getSupportActionBar();
    if (actionBar != null) {
      actionBar.setTitle(resId);
    }
  }

  public void setIconAppBar(@DrawableRes int resId) {
    ActionBar actionBar = getSupportActionBar();
    if (actionBar != null) {
      actionBar.setHomeAsUpIndicator(resId);
    }
  }
}
