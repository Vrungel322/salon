package com.apps.twelve.floor.authorization.logic.recoverypassword.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.MenuItem;
import butterknife.ButterKnife;
import com.apps.twelve.floor.authorization.R;
import com.apps.twelve.floor.authorization.base.BaseActivity;
import com.apps.twelve.floor.authorization.logic.recoverypassword.fragments.RecoveryPasswordFragment;
import com.apps.twelve.floor.authorization.logic.recoverypassword.presenters.RecoveryPasswordActivityPresenter;
import com.apps.twelve.floor.authorization.logic.recoverypassword.views.IRecoveryPasswordActivity;
import com.apps.twelve.floor.authorization.utils.ThemeUtils;
import com.arellomobile.mvp.presenter.InjectPresenter;

/**
 * Created by Alexander Svyatetsky on 17.05.2017.
 */

public class RecoveryPasswordActivity extends BaseActivity implements IRecoveryPasswordActivity {

  @InjectPresenter RecoveryPasswordActivityPresenter mPresenter;

  @Override protected void onCreate(Bundle savedInstanceState) {
    setTheme(ThemeUtils.getCurrentTheme(this));
    setContentView(R.layout.activity_recovery_password);
    ButterKnife.bind(this);
    super.onCreate(savedInstanceState);

    setTitleAppBar(R.string.recovery_password_label);
  }

  @Override public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case android.R.id.home:
        FragmentManager fm = getSupportFragmentManager();
        if (fm.getBackStackEntryCount() > 0) {
          fm.popBackStack();
        } else {
          super.onBackPressed();
        }
        return true;
      default:
        return super.onOptionsItemSelected(item);
    }
  }

  @Override public void addRecoveryPasswordFragment() {
    mNavigator.addFragment(this, R.id.fragment_container, RecoveryPasswordFragment.newInstance());
  }

  @Override public void finishActivity() {
    finish();
  }
}
