package com.apps.twelve.floor.authorization.logic.registration.activities;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.StringRes;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.util.TypedValue;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import br.com.simplepass.loading_button_lib.customViews.CircularProgressButton;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.apps.twelve.floor.authorization.App;
import com.apps.twelve.floor.authorization.R;
import com.apps.twelve.floor.authorization.R2;
import com.apps.twelve.floor.authorization.base.BaseActivity;
import com.apps.twelve.floor.authorization.base.NoChangeBackgroundTextInputLayout;
import com.apps.twelve.floor.authorization.data.model.UserEntity;
import com.apps.twelve.floor.authorization.logic.registration.presenters.ModuleRegistrationPresenter;
import com.apps.twelve.floor.authorization.logic.registration.views.IModuleRegistrationActivityView;
import com.apps.twelve.floor.authorization.utils.ActionUtils;
import com.apps.twelve.floor.authorization.utils.Constants;
import com.apps.twelve.floor.authorization.utils.ThemeUtils;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.basgeekball.awesomevalidation.AwesomeValidation;

import static com.apps.twelve.floor.authorization.utils.Constants.Regex.REGEX_EMAIL;
import static com.apps.twelve.floor.authorization.utils.Constants.Regex.REGEX_NOT_EMPTY;
import static com.apps.twelve.floor.authorization.utils.Constants.Regex.REGEX_PASSWORD_LENGTH;
import static com.apps.twelve.floor.authorization.utils.Constants.Regex.REGEX_PHONE;
import static com.basgeekball.awesomevalidation.ValidationStyle.TEXT_INPUT_LAYOUT;

/**
 * Created by Alexander Svyatetsky on 22.04.2017.
 */

public class ModuleRegistrationActivity extends BaseActivity
    implements IModuleRegistrationActivityView {

  @InjectPresenter ModuleRegistrationPresenter presenter;

  @BindView(R2.id.et_name) EditText mEtName;
  @BindView(R2.id.et_phone) EditText mEtPhone;
  @BindView(R2.id.et_email) EditText mEtEmail;
  @BindView(R2.id.et_password) EditText mEtPassword;
  @BindView(R2.id.btn_registration) CircularProgressButton mBtnRegistration;
  @BindView(R2.id.til_email) NoChangeBackgroundTextInputLayout mTilEmail;
  @BindView(R2.id.til_phone) NoChangeBackgroundTextInputLayout mTilPhone;
  @BindView(R2.id.til_password) NoChangeBackgroundTextInputLayout mTilPassword;
  @BindView(R2.id.tv_privacy_policy) TextView mTvPrivacyPolicy;
  @BindView(R2.id.chk_privacy_policy) CheckBox mChkPrivacyPolicy;
  @BindView(R2.id.tv_error_privacy_policy) TextView mTvErrorPrivacyPolicy;

  private AwesomeValidation mAwesomeValidation;

  @Override protected void onCreate(Bundle savedInstanceState) {
    App.getAppComponent().inject(this);
    setTheme(ThemeUtils.getCurrentTheme(this));
    setContentView(R.layout.activity_registration);
    ButterKnife.bind(this);
    super.onCreate(savedInstanceState);

    setTitleAppBar(R.string.registration_label);

    UserEntity user = getIntent().getParcelableExtra(Constants.Registration.EXTRA_USER);
    if (user != null) {
      mEtName.setText(user.getFullName());
    }

    mTvPrivacyPolicy.setMovementMethod(LinkMovementMethod.getInstance());
    mEtPassword.setOnFocusChangeListener((view, hasFocus) -> {
      if (!hasFocus) {
        String password = mEtPassword.getText().toString();
        if (TextUtils.isEmpty(password) || password.length() < 6) {
          mTilPassword.setError(getString(R.string.error_password_length));
        } else {
          mTilPassword.setError(null);
        }
      }
    });

    mAwesomeValidation = new AwesomeValidation(TEXT_INPUT_LAYOUT);

    mAwesomeValidation.addValidation(ModuleRegistrationActivity.this, R.id.til_name,
        REGEX_NOT_EMPTY, R.string.error_empty_name);
    mAwesomeValidation.addValidation(ModuleRegistrationActivity.this, R.id.til_email, REGEX_EMAIL,
        R.string.error_not_regex_field_email);
    mAwesomeValidation.addValidation(ModuleRegistrationActivity.this, R.id.til_phone, REGEX_PHONE,
        R.string.error_not_regex_field_phone);
    mAwesomeValidation.addValidation(ModuleRegistrationActivity.this, R.id.til_password,
        REGEX_PASSWORD_LENGTH, R.string.error_password_length);
    mAwesomeValidation.addValidation(ModuleRegistrationActivity.this, R.id.til_confirm_password,
        R.id.til_password, R.string.error_password_no_consist);
  }

  @OnClick(R2.id.btn_registration) public void register() {
    if (!mChkPrivacyPolicy.isChecked()) {
      mTvErrorPrivacyPolicy.setVisibility(View.VISIBLE);
    } else {
      mTvErrorPrivacyPolicy.setVisibility(View.GONE);
    }

    if (mAwesomeValidation.validate()) {
      if (!mChkPrivacyPolicy.isChecked()) {
        return;
      }
      mBtnRegistration.startAnimation();
      presenter.register(new UserEntity(mEtName.getText().toString(), mEtEmail.getText().toString(),
          mEtPhone.getText().toString(), mEtPassword.getText().toString()));
    }
  }

  @OnClick(R2.id.root_layout) public void onRootLayoutClick(View v) {
    ActionUtils.hideKeyboard(ModuleRegistrationActivity.this, v);
  }

  @Override public void finishActivity() {
    finish();
  }

  @Override public void showPhoneError(String message) {
    mTilPhone.setError(message);
  }

  @Override public void showEmailError(String message) {
    mTilEmail.setError(message);
  }

  public void showAlerter(@StringRes int id) {
    showAlert(getString(R.string.error_title), getString(id));
  }

  @Override public void stopAnimation() {
    TypedValue value = new TypedValue();
    getTheme().resolveAttribute(R.attr.colorAccent, value, true);
    mBtnRegistration.doneLoadingAnimation(
        ContextCompat.getColor(ModuleRegistrationActivity.this, value.resourceId),
        BitmapFactory.decodeResource(getResources(), R.drawable.ic_done_white_48dp));
  }

  @Override public void revertAnimation() {
    mBtnRegistration.revertAnimation();
  }

  @Override public void showConnectErrorMessage() {
    showAlert(getString(R.string.error_title_no_internet_connection),
        getString(R.string.no_internet_connection));
  }
}
