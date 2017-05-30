package com.authorization.floor12.authorization.logic.registration.activities;

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
import com.authorization.floor12.authorization.logic.registration.presenters.RegistrationActivityPresenter;
import com.authorization.floor12.authorization.logic.registration.views.IRegistrationActivityView;
import com.authorization.floor12.authorization.logic.userdetail.activities.UserProfileActivity;
import com.authorization.floor12.authorization.utils.ActionUtils;
import com.authorization.floor12.authorization.utils.Constants;
import com.authorization.floor12.authorization.utils.ThemeUtils;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.ConfirmPassword;
import com.mobsandgeeks.saripaar.annotation.Email;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.mobsandgeeks.saripaar.annotation.Password;
import com.mobsandgeeks.saripaar.annotation.Pattern;
import java.util.List;

/**
 * Created by Alexander Svyatetsky on 22.04.2017.
 */

public class ModuleRegistrationActivity extends BaseActivity
    implements IRegistrationActivityView, Validator.ValidationListener, View.OnClickListener {

  @InjectPresenter RegistrationActivityPresenter presenter;

  @NotEmpty(message = "Поле имя не должно быть пустым") @BindView(R2.id.et_name) EditText mEtName;

  @Pattern(regex = Constants.Login.REGEX_PHONE, message = "Неверно введенный телефон")
  @BindView(R2.id.et_phone) EditText mEtPhone;

  @Email(message = "Неверно введенный email") @BindView(R2.id.et_email) EditText mEtEmail;

  @Password(min = 6, scheme = Password.Scheme.ANY, message = "Минимальная длина пароля 6 символов")
  @BindView(R2.id.et_password) EditText mEtPassword;

  @ConfirmPassword(message = "Пароли не совпадают") @BindView(R2.id.et_confirm_password) EditText
      mEtConfirmPassword;
  @BindView(R2.id.btn_registration) CircularProgressButton mBtnRegistration;
  @BindView(R2.id.root_layout) ConstraintLayout mRootLayout;

  private Validator validator;

  @Override protected void onCreate(Bundle savedInstanceState) {
    setTheme(ThemeUtils.getThemeOtherActivity(ModuleRegistrationActivity.this));
    setContentView(R.layout.activity_registration);
    ButterKnife.bind(this);
    super.onCreate(savedInstanceState);

    validator = new Validator(this);
    validator.setValidationListener(this);

    setTitleAppBar(R.string.registration_label);

    UserEntity user = getIntent().getParcelableExtra(Constants.Registration.EXTRA_USER);
    if (user != null) {
      mEtName.setText(user.getName());
    }

    mBtnRegistration.setOnClickListener(v -> validator.validate());
    mRootLayout.setOnClickListener(
        v -> ActionUtils.hideKeyboard(ModuleRegistrationActivity.this, v));
  }

  @Override public void onValidationSucceeded() {
    mBtnRegistration.startAnimation();
    presenter.register(new UserEntity(mEtName.getText().toString(), mEtEmail.getText().toString(),
        mEtPhone.getText().toString(), mEtPassword.getText().toString()));
  }

  @Override public void onValidationFailed(List<ValidationError> errors) {
    for (ValidationError error : errors) {
      View view = error.getView();
      String message = error.getCollatedErrorMessage(this);

      // Display error messages ;)
      if (view instanceof EditText) {
        ((EditText) view).setError(message);
      } else if (view instanceof TextView) {
        ((TextView) view).setError(message);
      } else {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
      }
    }
    errors.get(0).getView().requestFocus();
  }

  @Override public void onClick(View v) {
    ActionUtils.hideKeyboard(ModuleRegistrationActivity.this, v);
  }

  @Override public void SignIn() {
    mNavigator.startActivityClearStack(ModuleRegistrationActivity.this,
        new Intent(ModuleRegistrationActivity.this, UserProfileActivity.class));
  }

  @Override public void showPhoneError(String message) {
    mEtPhone.setError(message);
  }

  @Override public void showEmailError(String message) {
    mEtEmail.setError(message);
  }

  public void showAlerter(@StringRes int id) {
    showAlert(getString(R.string.error_title), getString(id));
  }

  @Override public void stopAnimation() {
    mBtnRegistration.doneLoadingAnimation(R.color.colorAccent,
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
