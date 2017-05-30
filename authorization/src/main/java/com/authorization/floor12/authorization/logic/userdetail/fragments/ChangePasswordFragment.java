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
package com.authorization.floor12.authorization.logic.userdetail.fragments;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;
import br.com.simplepass.loading_button_lib.customViews.CircularProgressButton;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.authorization.floor12.authorization.R;
import com.authorization.floor12.authorization.R2;
import com.authorization.floor12.authorization.base.BaseFragment;
import com.authorization.floor12.authorization.logic.authorization.activities.ModuleSignInActivity;
import com.authorization.floor12.authorization.logic.userdetail.activities.UserProfileActivity;
import com.authorization.floor12.authorization.logic.userdetail.presenters.ChangePasswordPresenter;
import com.authorization.floor12.authorization.logic.userdetail.views.IChangePasswordFragment;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.ConfirmPassword;
import com.mobsandgeeks.saripaar.annotation.Password;
import java.util.List;

public class ChangePasswordFragment extends BaseFragment
    implements IChangePasswordFragment, Validator.ValidationListener {

  @InjectPresenter ChangePasswordPresenter mPresenter;
  @BindView(R2.id.etPasswordFieldText) EditText mEtPasswordFieldText;

  @Password(min = 6, messageResId = R2.string.error_password_length, scheme = Password.Scheme.ANY)
  @BindView(R2.id.etNewPasswordField) EditText mEtNewPasswordField;

  @ConfirmPassword(messageResId = R2.string.error_password_no_consist)
  @BindView(R2.id.etConfirmPasswordFieldText) EditText mEtConfirmPasswordFieldText;
  @BindView(R2.id.btnSave) CircularProgressButton mBtnSave;
  Unbinder unbinder;

  private Validator validator;

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

    ActionBar actionBar = ((UserProfileActivity) getActivity()).getSupportActionBar();
    actionBar.setTitle(getString(R.string.change_password_info));
  }

  @OnClick(R2.id.btnSave) void save() {
    if (mEtPasswordFieldText.getText()
        .toString()
        .equals(mEtNewPasswordField.getText().toString())) {
      mEtNewPasswordField.setError(getString(R.string.error_password_is_consist));
    } else {
      validator.validate();
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

  @Override public void showPasswordError(String errorMessage) {
    mEtPasswordFieldText.setError(errorMessage);
  }

  @Override public void showNewPasswordError(String errorMessage) {
    mEtNewPasswordField.setError(errorMessage);
  }

  @Override public void onDestroy() {
    ((UserProfileActivity) getActivity()).setTitleAppBar(R.string.menu_settings);
    super.onDestroy();
  }

  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    // TODO: inflate a fragment view

    validator = new Validator(this);
    validator.setValidationListener(this);

    View rootView = super.onCreateView(inflater, container, savedInstanceState);
    unbinder = ButterKnife.bind(this, rootView);
    return rootView;
  }

  @Override public void onDestroyView() {
    super.onDestroyView();
    unbinder.unbind();
  }

  @Override public void onValidationSucceeded() {
    mBtnSave.startAnimation();
    mPresenter.changePassword(mEtPasswordFieldText.getText().toString(),
        mEtNewPasswordField.getText().toString());
  }

  @Override public void onValidationFailed(List<ValidationError> errors) {
    for (ValidationError error : errors) {
      View view = error.getView();
      String message = error.getCollatedErrorMessage(getContext());

      // Display error messages ;)
      if (view instanceof EditText) {
        ((EditText) view).setError(message);
      } else {
        Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
      }
    }
    errors.get(0).getView().requestFocus();
  }

  @Override public void showConnectErrorMessage() {
    showAlert(getString(R.string.error_title_no_internet_connection),
        getString(R.string.no_internet_connection));
  }
}
