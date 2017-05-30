package com.authorization.floor12.authorization.logic.authorization.activities;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.StringRes;
import android.support.constraint.ConstraintLayout;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import br.com.simplepass.loading_button_lib.customViews.CircularProgressButton;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.authorization.floor12.authorization.R;
import com.authorization.floor12.authorization.R2;
import com.authorization.floor12.authorization.base.BaseActivity;
import com.authorization.floor12.authorization.data.model.UserEntity;
import com.authorization.floor12.authorization.logic.authorization.presenters.SignInActivityPresenter;
import com.authorization.floor12.authorization.logic.authorization.views.ISignInActivityView;
import com.authorization.floor12.authorization.logic.recoverypassword.activities.ModuleRecoveryPasswordActivity;
import com.authorization.floor12.authorization.logic.registration.activities.ModuleRegistrationActivity;
import com.authorization.floor12.authorization.logic.userdetail.activities.UserProfileActivity;
import com.authorization.floor12.authorization.utils.ActionUtils;
import com.authorization.floor12.authorization.utils.Constants;
import com.authorization.floor12.authorization.utils.ThemeUtils;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.common.SignInButton;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.mobsandgeeks.saripaar.annotation.Password;
import com.mobsandgeeks.saripaar.annotation.Pattern;
import java.util.List;

/**
 * Created by Alexander Svyatetsky on 22.04.2017.
 */

public class ModuleSignInActivity extends BaseActivity
    implements ISignInActivityView, Validator.ValidationListener {

  @InjectPresenter SignInActivityPresenter presenter;

  @NotEmpty(message = "Поле обязательное для заполнения")
  @Pattern(regex = Constants.Login.REGEX_EMAIL
      + Constants.Login.REGEX_OR_CONDITION
      + Constants.Login.REGEX_PHONE, message = "Неверно введенный логин") @BindView(R2.id.et_login)
  EditText mLogin;
  @Password(min = 6, scheme = Password.Scheme.ANY, message = "Минимальная длина пароля 6 символов")
  @BindView(R2.id.et_password) EditText mPassword;
  @BindView(R2.id.facebook_login_button) LoginButton mFacebookLoginBtn;
  @BindView(R2.id.google_login_button) SignInButton mGoogleLoginButton;
  @BindView(R2.id.btn_sign_in) CircularProgressButton mBtnSignIn;
  @BindView(R2.id.tv_registration) TextView mRegistration;
  @BindView(R2.id.tv_recovery_password) TextView mRecoveryPassword;
  @BindView(R2.id.root_layout) ConstraintLayout mRootLayout;

  private Validator validator;

  @Override protected void onCreate(Bundle savedInstanceState) {
    setTheme(ThemeUtils.getThemeOtherActivity(getBaseContext()));
    setContentView(R.layout.activity_sign_in);
    ButterKnife.bind(this);
    super.onCreate(savedInstanceState);

    setTitleAppBar(R.string.sign_in_label);

    validator = new Validator(this);
    validator.setValidationListener(this);

    mFacebookLoginBtn.setReadPermissions("public_profile", "email");
    mFacebookLoginBtn.setOnClickListener(v -> presenter.loginByFacebook());

    mGoogleLoginButton.setOnClickListener(v -> presenter.loginByGoogle());

    mBtnSignIn.setOnClickListener(v -> validator.validate());
    mRegistration.setOnClickListener(v -> mNavigator.startActivity(ModuleSignInActivity.this,
        new Intent(ModuleSignInActivity.this, ModuleRegistrationActivity.class)));
    mRecoveryPassword.setOnClickListener(v -> mNavigator.startActivity(ModuleSignInActivity.this,
        new Intent(ModuleSignInActivity.this, ModuleRecoveryPasswordActivity.class)));
    mRootLayout.setOnClickListener(v -> ActionUtils.hideKeyboard(ModuleSignInActivity.this, v));
  }

  @Override public void onValidationSucceeded() {
    mBtnSignIn.startAnimation();
    presenter.login(mLogin.getText().toString(), mPassword.getText().toString());
  }

  @Override public void onValidationFailed(List<ValidationError> errors) {
    for (ValidationError error : errors) {
      View view = error.getView();
      String message = error.getCollatedErrorMessage(this);

      // Display error messages ;)
      if (view instanceof EditText) {
        ((EditText) view).setError(message);
      } else {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
      }
    }
    errors.get(0).getView().requestFocus();
  }

  @Override public void showUserProfile() {
    Intent intent = new Intent(ModuleSignInActivity.this, UserProfileActivity.class);
    mNavigator.startActivityClearStack(ModuleSignInActivity.this, intent);
  }

  @Override public void finishActivity() {
    finish();
  }

  @Override public void showRegistrationActivity(UserEntity user) {
    Intent intent = new Intent(ModuleSignInActivity.this, ModuleRegistrationActivity.class);
    intent.putExtra(Constants.Registration.EXTRA_USER, user);
    startActivity(intent);
  }

  @Override public void showAlerter(@StringRes int id) {
    showAlert(getString(R.string.error_title), getString(id));
  }

  @Override public void stopAnimation() {
    mBtnSignIn.doneLoadingAnimation(R.color.colorAccent,
        BitmapFactory.decodeResource(getResources(), R.drawable.ic_done_white_48dp));
  }

  @Override public void revertAnimation() {
    mBtnSignIn.revertAnimation();
  }

  @Override public void showConnectErrorMessage() {
    showAlert(getString(R.string.error_title_no_internet_connection),
        getString(R.string.no_internet_connection));
  }
}
