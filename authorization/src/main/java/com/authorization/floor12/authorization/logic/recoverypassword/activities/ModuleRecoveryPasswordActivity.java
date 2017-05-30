package com.authorization.floor12.authorization.logic.recoverypassword.activities;

import android.app.Dialog;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.StringRes;
import android.support.constraint.ConstraintLayout;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import br.com.simplepass.loading_button_lib.customViews.CircularProgressButton;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.authorization.floor12.authorization.R;
import com.authorization.floor12.authorization.R2;
import com.authorization.floor12.authorization.base.BaseActivity;
import com.authorization.floor12.authorization.logic.recoverypassword.presenters.RecoveryPasswordPresenter;
import com.authorization.floor12.authorization.logic.recoverypassword.views.IRecoveryPasswordActivity;
import com.authorization.floor12.authorization.utils.ActionUtils;
import com.authorization.floor12.authorization.utils.Constants;
import com.authorization.floor12.authorization.utils.DialogFactory;
import com.authorization.floor12.authorization.utils.ThemeUtils;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.mobsandgeeks.saripaar.annotation.Pattern;
import java.util.List;

/**
 * Created by Alexander Svyatetsky on 17.05.2017.
 */

public class ModuleRecoveryPasswordActivity extends BaseActivity
    implements IRecoveryPasswordActivity, Validator.ValidationListener {

  @BindView(R2.id.btn_recovery) CircularProgressButton mBtnRecovery;
  @BindView(R2.id.root_layout) ConstraintLayout mRootLayout;
  private Validator validator;
  private Dialog mDialog;

  @InjectPresenter RecoveryPasswordPresenter presenter;

  @NotEmpty(message = "Поле обязательное для заполнения")
  @Pattern(regex = Constants.Login.REGEX_EMAIL
      + Constants.Login.REGEX_OR_CONDITION
      + Constants.Login.REGEX_PHONE, message = "Неверно введенный логин")
  @BindView(R2.id.et_email_or_phone) EditText mLogin;

  @Override protected void onCreate(Bundle savedInstanceState) {
    setTheme(ThemeUtils.getThemeOtherActivity(ModuleRecoveryPasswordActivity.this));
    setContentView(R.layout.activity_recovery_password);
    ButterKnife.bind(this);
    super.onCreate(savedInstanceState);

    setTitleAppBar(R.string.recovery_password_label);

    validator = new Validator(this);
    validator.setValidationListener(this);

    mBtnRecovery.setOnClickListener(v -> validator.validate());
    mRootLayout.setOnClickListener(
        v -> ActionUtils.hideKeyboard(ModuleRecoveryPasswordActivity.this, v));
  }

  @Override public void showSignInActivity() {
    onBackPressed();
    finish();
  }

  @Override public void showDialogMessage(int resId) {
    mDialog = DialogFactory.createSimpleOkDialog(ModuleRecoveryPasswordActivity.this,
        getString(R.string.dialog_title_recovery_password),
        String.format(getString(R.string.dialog_message_recovery_password), getString(resId)),
        (dialog, which) -> showSignInActivity());
    mDialog.setOnCancelListener(dialog -> showSignInActivity());
    mDialog.show();
  }

  @Override public void showAlerter(@StringRes int resId) {
    showAlert(getString(R.string.error_title), getString(resId));
  }

  @Override public void onValidationSucceeded() {
    mBtnRecovery.startAnimation();
    presenter.resetPassword(mLogin.getText().toString());
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
  }

  @Override public void showError(@StringRes int messageResId) {
    mLogin.setError(
        String.format(getString(R.string.error_user_not_found), getString(messageResId)));
  }

  @Override public void stopAnimation() {
    mBtnRecovery.doneLoadingAnimation(R.color.colorAccent,
        BitmapFactory.decodeResource(getResources(), R.drawable.ic_done_white_48dp));
  }

  @Override public void revertAnimation() {
    mBtnRecovery.revertAnimation();
  }

  @Override public void showConnectErrorMessage() {
    showAlert(getString(R.string.error_title_no_internet_connection),
        getString(R.string.no_internet_connection));
  }
}
