package com.apps.twelve.floor.salon.ui.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.StringRes;
import android.widget.Toast;
import butterknife.ButterKnife;
import com.arellomobile.mvp.MvpAppCompatActivity;
import javax.inject.Inject;

/**
 * Created by John on 27.01.2017.
 */

public abstract class BaseActivity extends MvpAppCompatActivity {

  @Inject Context mContext;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    ButterKnife.bind(this);
    inject();
  }

  protected void showToastMessage(String message) {
    Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
  }

  protected void showToastMessage(@StringRes int id) {
    Toast.makeText(mContext, id, Toast.LENGTH_SHORT).show();
  }

  protected abstract void inject();
}
