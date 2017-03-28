package com.apps.twelve.floor.salon.ui.activities;

import android.os.Bundle;
import com.apps.twelve.floor.salon.R;
import com.apps.twelve.floor.salon.mvp.presenters.pr_activities.RegistrationActivityPresenter;
import com.apps.twelve.floor.salon.mvp.views.IRegistrationActivityView;
import com.apps.twelve.floor.salon.ui.base.BaseActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;

public class RegistrationActivity extends BaseActivity implements IRegistrationActivityView {
  @InjectPresenter RegistrationActivityPresenter mRegistrationActivityPresenter;

  @Override protected void onCreate(Bundle savedInstanceState) {
    setContentView(R.layout.activity_registration);
    super.onCreate(savedInstanceState);
  }
}
