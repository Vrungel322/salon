package com.authorization.floor12.authorization.logic.userdetail.activities;

import android.os.Bundle;
import android.view.MenuItem;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.authorization.floor12.authorization.R;
import com.authorization.floor12.authorization.base.BaseActivity;
import com.authorization.floor12.authorization.logic.userdetail.fragments.UserProfileFragment;
import com.authorization.floor12.authorization.logic.userdetail.presenters.UserProfileActivityPresenter;
import com.authorization.floor12.authorization.logic.userdetail.views.IUserProfileActivityView;

/**
 * Created by Alexander Svyatetsky on 05.05.2017.
 */

public class UserProfileActivity extends BaseActivity implements IUserProfileActivityView {

  @InjectPresenter UserProfileActivityPresenter mUserProfileActivityPresenter;

  @Override protected void onCreate(Bundle savedInstanceState) {
    setContentView(R.layout.activity_user_profile);
    super.onCreate(savedInstanceState);

    setTitleAppBar(R.string.user_profile_label);

    getSupportFragmentManager().addOnBackStackChangedListener(() -> {
      if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
      } else {
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setHomeButtonEnabled(false);
      }
    });
  }

  @Override public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case android.R.id.home:
        getSupportFragmentManager().popBackStack();
        return true;
      default:
        return super.onOptionsItemSelected(item);
    }
  }

  @Override public void addFragmentSettings() {
    mNavigator.addFragment(this, R.id.container_settings, UserProfileFragment.newInstance());
  }
}
