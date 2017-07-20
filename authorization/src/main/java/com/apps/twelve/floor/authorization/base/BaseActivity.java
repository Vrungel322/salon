package com.apps.twelve.floor.authorization.base;

import android.app.ActivityManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.StringRes;
import android.support.design.widget.TextInputLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.util.TypedValue;
import android.view.ViewGroup;
import android.widget.Toast;
import butterknife.ButterKnife;
import com.apps.twelve.floor.authorization.App;
import com.apps.twelve.floor.authorization.R;
import com.arellomobile.mvp.MvpAppCompatActivity;
import com.tapadoo.alerter.Alerter;
import javax.inject.Inject;

import static com.apps.twelve.floor.authorization.R.mipmap.module_ic_launcher;

/**
 * Created by Alexander Svyatetsky on 04.05.2017.
 */

public class BaseActivity extends MvpAppCompatActivity {

  @Inject protected Context mContext;
  @Inject protected Navigator mNavigator;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    App.getAppComponent().inject(this);
    ButterKnife.bind(this);

    /* app color in recent-apps */
    Bitmap bm = BitmapFactory.decodeResource(getResources(), module_ic_launcher);
    TypedValue value = new TypedValue();
    getTheme().resolveAttribute(R.attr.colorPrimary, value, true);
    ActivityManager.TaskDescription taskDesc;
    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
      taskDesc = new ActivityManager.TaskDescription(getString(R.string.app_name), bm,
          ContextCompat.getColor(this, value.resourceId));
      setTaskDescription(taskDesc);
    }
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

  public void setDisplayHomeAsUpEnabled(boolean showHomeAsUp) {
    ActionBar actionBar = getSupportActionBar();
    if (actionBar != null) {
      actionBar.setDisplayHomeAsUpEnabled(showHomeAsUp);
    }
  }

  protected void showAlert(String title, String message) {
    TypedValue value = new TypedValue();
    getTheme().resolveAttribute(R.attr.colorAccent, value, true);
    Alerter.create(BaseActivity.this)
        .setTitle(title)
        .setText(message)
        .setDuration(3000)
        .setBackgroundColor(value.resourceId)
        .show();
  }

  protected void clearErrors() {
    final ViewGroup viewGroup =
        (ViewGroup) ((ViewGroup) this.findViewById(android.R.id.content)).getChildAt(0);
    ViewGroup rootView = (ViewGroup) viewGroup.getChildAt(0);
    for (int i = 0; i < rootView.getChildCount(); i++) {
      if (rootView.getChildAt(i) instanceof TextInputLayout) {
        ((TextInputLayout) rootView.getChildAt(i)).setError(null);
      }
    }
  }
}
