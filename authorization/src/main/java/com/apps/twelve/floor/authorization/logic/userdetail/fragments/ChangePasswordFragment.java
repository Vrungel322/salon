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
package com.apps.twelve.floor.authorization.logic.userdetail.fragments;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import br.com.simplepass.loading_button_lib.customViews.CircularProgressButton;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import com.apps.twelve.floor.authorization.R;
import com.apps.twelve.floor.authorization.R2;
import com.apps.twelve.floor.authorization.base.BaseFragment;
import com.apps.twelve.floor.authorization.base.NoChangeBackgroundTextInputLayout;
import com.apps.twelve.floor.authorization.logic.authorization.activities.ModuleSignInActivity;
import com.apps.twelve.floor.authorization.logic.userdetail.presenters.ChangePasswordPresenter;
import com.apps.twelve.floor.authorization.logic.userdetail.views.IChangePasswordFragment;
import com.apps.twelve.floor.authorization.utils.ActionUtils;
import com.apps.twelve.floor.authorization.utils.DialogFactory;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.basgeekball.awesomevalidation.AwesomeValidation;

import static com.apps.twelve.floor.authorization.utils.Constants.Regex.REGEX_PASSWORD_LENGTH;
import static com.basgeekball.awesomevalidation.ValidationStyle.TEXT_INPUT_LAYOUT;

public class ChangePasswordFragment extends BaseFragment implements IChangePasswordFragment {

  @InjectPresenter ChangePasswordPresenter mPresenter;

  @BindView(R2.id.etPasswordFieldText) EditText mEtPasswordFieldText;
  @BindView(R2.id.etNewPasswordField) EditText mEtNewPasswordField;
  @BindView(R2.id.etConfirmPasswordFieldText) EditText mEtConfirmPasswordFieldText;
  @BindView(R2.id.btnSave) CircularProgressButton mBtnSave;
  @BindView(R2.id.til_password) NoChangeBackgroundTextInputLayout mTilPassword;
  @BindView(R2.id.til_new_password) NoChangeBackgroundTextInputLayout mTilNewPassword;
  @BindView(R2.id.til_confirm_password) NoChangeBackgroundTextInputLayout mTilConfirmPassword;

  private Dialog mDialog;
  private Unbinder unbinder;
  private AwesomeValidation mAwesomeValidation;

  public ChangePasswordFragment() {
    super(R.layout.fragment_change_password);
  }

  public static ChangePasswordFragment newInstance() {
    Bundle args = new Bundle();
    ChangePasswordFragment fragment = new ChangePasswordFragment();
    fragment.setArguments(args);
    return fragment;
  }

  @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    setTitleAppBar(R.string.change_password_info);
  }

  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    View rootView = super.onCreateView(inflater, container, savedInstanceState);
    unbinder = ButterKnife.bind(this, rootView);

    mAwesomeValidation = new AwesomeValidation(TEXT_INPUT_LAYOUT);

    mAwesomeValidation.addValidation(mTilPassword, REGEX_PASSWORD_LENGTH,
        getString(R.string.error_password_length));
    mAwesomeValidation.addValidation(mTilNewPassword, REGEX_PASSWORD_LENGTH,
        getString(R.string.error_password_length));
    mAwesomeValidation.addValidation(getActivity(), R.id.etConfirmPasswordFieldText,
        R.id.etNewPasswordField, R.string.error_password_no_consist);

    return rootView;
  }

  @OnClick(R2.id.root_layout) public void onRootLayoutClick(View view) {
    ActionUtils.hideKeyboard(getContext(), view);
  }

  @OnClick(R2.id.btnSave) void save() {
    if (mAwesomeValidation.validate()) {
      if (mEtPasswordFieldText.getText()
          .toString()
          .equals(mEtNewPasswordField.getText().toString())) {
        mTilNewPassword.setError(getString(R.string.error_password_is_consist));
      } else if (!mEtNewPasswordField.getText()
          .toString()
          .equals(mEtConfirmPasswordFieldText.getText().toString())) {
        mTilConfirmPassword.setError(getString(R.string.error_password_no_consist));
      } else {
        mBtnSave.startAnimation();
        mPresenter.changePassword(mEtPasswordFieldText.getText().toString(),
            mEtNewPasswordField.getText().toString());
      }
    }
  }

  @Override public void stopAnimation() {
    TypedValue value = new TypedValue();
    getActivity().getTheme().resolveAttribute(R.attr.colorAccent, value, true);
    mBtnSave.doneLoadingAnimation(ContextCompat.getColor(getContext(), value.resourceId),
        BitmapFactory.decodeResource(getResources(), R.drawable.ic_done_white_48dp));
  }

  @Override public void revertAnimation() {
    mBtnSave.revertAnimation();
  }

  @Override public void closeFragment() {
    getActivity().onBackPressed();
  }

  @Override public void showAlerter(@StringRes int resId) {
    showAlert(getString(R.string.error_title), getString(resId));
  }

  @Override public void showSignInActivity() {
    mPreferencesHelper.clear();
    mNavigator.startActivityClearStack((AppCompatActivity) getContext(),
        new Intent(getContext(), ModuleSignInActivity.class));
  }

  @Override public void showDialogMessage() {
    mDialog = DialogFactory.createSimpleOkDialog(getContext(),
        getString(R.string.dialog_title_change_password),
        getString(R.string.dialog_message_change_password),
        (dialog, which) -> mPresenter.closeFragment());
    mDialog.setOnCancelListener(dialog -> mPresenter.closeFragment());
    mDialog.show();
  }

  @Override public void showPasswordError(String errorMessage) {
    mTilPassword.setError(errorMessage);
  }

  @Override public void showNewPasswordError(String errorMessage) {
    mTilNewPassword.setError(errorMessage);
  }

  @Override public void onDestroy() {
    setTitleAppBar(R.string.menu_settings);
    if (mDialog != null) {
      mDialog.dismiss();
    }
    super.onDestroy();
  }

  @Override public void onDestroyView() {
    super.onDestroyView();
    unbinder.unbind();
    ActionUtils.hideKeyboard(getContext());
  }

  @Override public void showConnectErrorMessage() {
    showAlert(getString(R.string.error_title_no_internet_connection),
        getString(R.string.no_internet_connection));
  }
}
