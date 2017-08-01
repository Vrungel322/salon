package com.apps.twelve.floor.authorization.logic.recoverypassword.fragments;
/*
 * Copyright 2017, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import android.app.Dialog;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import br.com.simplepass.loading_button_lib.customViews.CircularProgressButton;
import butterknife.BindView;
import butterknife.OnClick;
import com.apps.twelve.floor.authorization.R;
import com.apps.twelve.floor.authorization.R2;
import com.apps.twelve.floor.authorization.base.BaseFragment;
import com.apps.twelve.floor.authorization.base.NoChangeBackgroundTextInputLayout;
import com.apps.twelve.floor.authorization.data.model.CredentialsEntity;
import com.apps.twelve.floor.authorization.logic.recoverypassword.presenters.ModuleRecoveryPasswordPresenter;
import com.apps.twelve.floor.authorization.logic.recoverypassword.views.IRecoveryPasswordFragment;
import com.apps.twelve.floor.authorization.utils.ActionUtils;
import com.apps.twelve.floor.authorization.utils.DialogFactory;
import com.apps.twelve.floor.authorization.utils.ValidatorUtils;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.rey.material.app.BottomSheetDialog;

import static com.apps.twelve.floor.authorization.utils.Constants.Regex.REGEX_EMAIL_OR_PHONE;
import static com.basgeekball.awesomevalidation.ValidationStyle.TEXT_INPUT_LAYOUT;

public class RecoveryPasswordFragment extends BaseFragment implements IRecoveryPasswordFragment {

  @InjectPresenter ModuleRecoveryPasswordPresenter mPresenter;

  @BindView(R2.id.btn_recovery) CircularProgressButton mBtnRecovery;
  @BindView(R2.id.til_email_or_phone) NoChangeBackgroundTextInputLayout mTilEmailOrPhone;
  @BindView(R2.id.et_email_or_phone) EditText mLogin;

  //verify dialog
  private EditText mEtVerifyCode;
  private NoChangeBackgroundTextInputLayout mTilVerifyCode;
  private CircularProgressButton mBtnVerifyCode;

  private Dialog mDialog;
  private BottomSheetDialog mVerifyDialog;
  private AwesomeValidation mAwesomeValidation;

  public RecoveryPasswordFragment() {
    super(R.layout.fragment_recovery_password);
  }

  public static RecoveryPasswordFragment newInstance() {
    Bundle args = new Bundle();
    RecoveryPasswordFragment fragment = new RecoveryPasswordFragment();
    fragment.setArguments(args);
    return fragment;
  }

  @Nullable @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    View view = super.onCreateView(inflater, container, savedInstanceState);
    mAwesomeValidation = new AwesomeValidation(TEXT_INPUT_LAYOUT);

    mAwesomeValidation.addValidation(mTilEmailOrPhone, REGEX_EMAIL_OR_PHONE,
        getString(R.string.error_not_regex_field));
    return view;
  }

  @OnClick(R2.id.btn_recovery) public void recoveryPassword() {
    if (TextUtils.isEmpty(mLogin.getText().toString())) {
      mTilEmailOrPhone.setError(getString(R.string.error_empty_name));
      return;
    }
    if (mAwesomeValidation.validate()) {
      mBtnRecovery.startAnimation();
      mPresenter.resetPassword(mLogin.getText().toString());
    }
  }

  @OnClick(R2.id.root_layout) public void onRootLayoutClick(View v) {
    ActionUtils.hideKeyboard(getContext(), v);
  }

  @Override public void showDialogMessage(int resId) {
    mDialog = DialogFactory.createSimpleOkDialog(getContext(),
        getString(R.string.dialog_title_recovery_password),
        String.format(getString(R.string.dialog_message_recovery_password), getString(resId)),
        (dialog, which) -> {
          mPresenter.closeDialogMessage();
          mPresenter.showVerifyDialog();
        });
    mDialog.setOnCancelListener(dialog -> {
      mPresenter.closeDialogMessage();
      mPresenter.showVerifyDialog();
    });
    mDialog.show();
  }

  @Override public void closeDialogMessage() {
    if (mDialog != null) {
      mDialog.dismiss();
    }
  }

  @Override public void showRecoveryPasswordFragment(CredentialsEntity credentialsEntity) {
    mNavigator.addFragmentBackStack((AppCompatActivity) getActivity(), R.id.fragment_container,
        RecoveryChangePasswordFragment.newInstance(credentialsEntity));
  }

  @Override public void showVerifyDialog() {
    mVerifyDialog = new BottomSheetDialog(getContext());

    View v = LayoutInflater.from(getContext()).inflate(R.layout.dialog_verify, null);

    CredentialsEntity credentialsEntity = new CredentialsEntity();

    mTilVerifyCode = v.findViewById(R.id.til_verify_code);
    mEtVerifyCode = v.findViewById(R.id.et_verify_code);
    mBtnVerifyCode = v.findViewById(R.id.btn_verify_code);

    String value = mLogin.getText().toString();

    if (ValidatorUtils.isValidEmail(value)) {
      credentialsEntity.setEmail(value);
    } else if (ValidatorUtils.isValidPhone(value)) {
      credentialsEntity.setPhone(value);
    }

    mBtnVerifyCode.setOnClickListener(view -> {
      credentialsEntity.setVerifyCode(mEtVerifyCode.getText().toString());
      mBtnVerifyCode.startAnimation();
      mPresenter.verifyCode(credentialsEntity);
    });

    mVerifyDialog.applyStyle(mPreferencesHelper.getStyleResId())
        .addContentView(v, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT));
    mVerifyDialog.setOnCancelListener(dialogInterface -> mPresenter.closeVerifyDialog());
    mVerifyDialog.show();
  }

  @Override public void closeVerifyDialog() {
    if (mVerifyDialog != null) {
      mVerifyDialog.dismiss();
    }
  }

  @Override public void showAlerter(@StringRes int resId) {
    showAlert(getString(R.string.error_title), getString(resId));
  }

  @Override public void showError(@StringRes int messageResId) {
    mTilEmailOrPhone.setError(
        String.format(getString(R.string.error_user_not_found), getString(messageResId)));
  }

  @Override public void showVerifyError(String errorMessage) {
    if (mTilVerifyCode != null) {
      mTilVerifyCode.setError(errorMessage);
    }
  }

  @Override public void stopAnimation() {
    TypedValue value = new TypedValue();
    getActivity().getTheme().resolveAttribute(R.attr.colorAccent, value, true);
    mBtnRecovery.doneLoadingAnimation(ContextCompat.getColor(getContext(), value.resourceId),
        BitmapFactory.decodeResource(getResources(), R.drawable.ic_done_white_48dp));
  }

  @Override public void revertAnimation() {
    mBtnRecovery.revertAnimation();
  }

  @Override public void revertVerifyButtonAnimation() {
    if (mBtnVerifyCode != null) {
      mBtnVerifyCode.revertAnimation();
    }
  }

  @Override public void stopVerifyButtonAnimation() {
    if (mBtnVerifyCode != null) {
      TypedValue value = new TypedValue();
      getActivity().getTheme().resolveAttribute(R.attr.colorAccent, value, true);
      mBtnVerifyCode.doneLoadingAnimation(ContextCompat.getColor(getContext(), value.resourceId),
          BitmapFactory.decodeResource(getResources(), R.drawable.ic_done_white_48dp));
    }
  }

  @Override public void showConnectErrorMessage() {
    showAlert(getString(R.string.error_title_no_internet_connection),
        getString(R.string.no_internet_connection));
  }

  @Override public void onDestroy() {
    super.onDestroy();
    if (mVerifyDialog != null) {
      mVerifyDialog.dismiss();
    }
    if (mDialog != null) {
      mDialog.dismiss();
    }
  }
}
