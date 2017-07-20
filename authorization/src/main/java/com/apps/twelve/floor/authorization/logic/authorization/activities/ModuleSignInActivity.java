package com.apps.twelve.floor.authorization.logic.authorization.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.StringRes;
import android.support.v4.content.ContextCompat;
import android.util.TypedValue;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import br.com.simplepass.loading_button_lib.customViews.CircularProgressButton;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.apps.twelve.floor.authorization.R;
import com.apps.twelve.floor.authorization.R2;
import com.apps.twelve.floor.authorization.base.BaseActivity;
import com.apps.twelve.floor.authorization.base.NoChangeBackgroundTextInputLayout;
import com.apps.twelve.floor.authorization.data.model.UserEntity;
import com.apps.twelve.floor.authorization.logic.authorization.presenters.ModuleSignInPresenter;
import com.apps.twelve.floor.authorization.logic.authorization.views.IModuleSignInActivityView;
import com.apps.twelve.floor.authorization.logic.recoverypassword.activities.RecoveryPasswordActivity;
import com.apps.twelve.floor.authorization.logic.registration.activities.ModuleRegistrationActivity;
import com.apps.twelve.floor.authorization.utils.ActionUtils;
import com.apps.twelve.floor.authorization.utils.DialogFactory;
import com.apps.twelve.floor.authorization.utils.ThemeUtils;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.basgeekball.awesomevalidation.AwesomeValidation;

import static com.apps.twelve.floor.authorization.utils.Constants.Regex.REGEX_EMAIL_OR_PHONE;
import static com.apps.twelve.floor.authorization.utils.Constants.Regex.REGEX_NOT_EMPTY;
import static com.basgeekball.awesomevalidation.ValidationStyle.TEXT_INPUT_LAYOUT;

/**
 * Created by Alexander Svyatetsky on 22.04.2017.
 */

public class ModuleSignInActivity extends BaseActivity implements IModuleSignInActivityView {

  @InjectPresenter ModuleSignInPresenter presenter;

  @BindView(R2.id.et_login) EditText mLogin;
  @BindView(R2.id.et_password) EditText mPassword;
  @BindView(R2.id.btn_sign_in) CircularProgressButton mBtnSignIn;
  @BindView(R2.id.til_login) NoChangeBackgroundTextInputLayout mTilLogin;
  @BindView(R2.id.til_password) NoChangeBackgroundTextInputLayout mTilPassword;

  private AwesomeValidation mAwesomeValidation;
  private ProgressDialog mProgressDialog;

  @Override protected void onCreate(Bundle savedInstanceState) {
    setTheme(ThemeUtils.getCurrentTheme(this));
    setContentView(R.layout.activity_sign_in);
    ButterKnife.bind(this);
    super.onCreate(savedInstanceState);

    setTitleAppBar(R.string.sign_in_label);
    setDisplayHomeAsUpEnabled(true);

    mAwesomeValidation = new AwesomeValidation(TEXT_INPUT_LAYOUT);

    mAwesomeValidation.addValidation(ModuleSignInActivity.this, R.id.til_login,
        REGEX_EMAIL_OR_PHONE, R.string.error_not_regex_field);

    mAwesomeValidation.addValidation(ModuleSignInActivity.this, R.id.til_password, REGEX_NOT_EMPTY,
        R.string.error_password_empty);
  }

  @Override public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case android.R.id.home:
        finish();
      default:
        return super.onOptionsItemSelected(item);
    }
  }

  @OnClick(R2.id.tv_registration) public void showRegistration() {
    mNavigator.startActivity(ModuleSignInActivity.this,
        new Intent(ModuleSignInActivity.this, ModuleRegistrationActivity.class));
  }

  @OnClick(R2.id.tv_recovery_password) public void showRecoveryPassword() {
    mNavigator.startActivity(ModuleSignInActivity.this,
        new Intent(ModuleSignInActivity.this, RecoveryPasswordActivity.class));
  }

  @OnClick(R2.id.btn_sign_in) public void login() {
    if (mAwesomeValidation.validate()) {
      mBtnSignIn.startAnimation();
      presenter.login(mLogin.getText().toString(), mPassword.getText().toString());
    }
  }

  @OnClick(R2.id.google_login_button) public void loginByGoogle() {
    presenter.loginByGoogle();
  }

  @OnClick(R2.id.facebook_login_button) public void loginByFacebook() {
    presenter.loginByFacebook();
  }

  @OnClick(R2.id.root_layout) public void onClickRootLayout(View v) {
    ActionUtils.hideKeyboard(ModuleSignInActivity.this, v);
  }

  @Override public void finishActivity() {
    finish();
  }

  @Override public void showRegistrationActivity(UserEntity user) {
    mNavigator.startActivity(ModuleSignInActivity.this,
        new Intent(ModuleSignInActivity.this, ModuleRegistrationActivity.class));
  }

  @Override public void showAlerter(@StringRes int id) {
    showAlert(getString(R.string.error_title), getString(id));
  }

  @Override public void showLoginError(String errorMessage) {
    mTilLogin.setError(errorMessage);
  }

  @Override public void showPasswordError() {
    mTilPassword.setError(getString(R.string.error_password));
  }

  @Override public void revertAnimation() {
    mBtnSignIn.revertAnimation();
  }

  @Override public void showConnectErrorMessage() {
    showAlert(getString(R.string.error_title_no_internet_connection),
        getString(R.string.no_internet_connection));
  }

  @Override public void showProgressDialog() {
    mProgressDialog = DialogFactory.createProgressDialog(ModuleSignInActivity.this,
        getString(R.string.dialog_message_sign_in));
    mProgressDialog.setCancelable(false);
    mProgressDialog.show();
  }

  @Override public void stopAnimation() {
    TypedValue value = new TypedValue();
    getTheme().resolveAttribute(R.attr.colorAccent, value, true);
    mBtnSignIn.doneLoadingAnimation(
        ContextCompat.getColor(ModuleSignInActivity.this, value.resourceId),
        BitmapFactory.decodeResource(getResources(), R.drawable.ic_done_white_48dp));
  }

  @Override public void closeProgressDialog() {
    if (mProgressDialog != null) {
      mProgressDialog.dismiss();
    }
  }

  @Override protected void onDestroy() {
    super.onDestroy();
    if (mProgressDialog != null) {
      mProgressDialog.dismiss();
    }
  }
}
