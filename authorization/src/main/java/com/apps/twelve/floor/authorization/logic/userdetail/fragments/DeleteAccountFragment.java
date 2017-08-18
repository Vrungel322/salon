package com.apps.twelve.floor.authorization.logic.userdetail.fragments;
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
import android.text.TextUtils;
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
import com.apps.twelve.floor.authorization.logic.userdetail.presenters.DeleteAccountFragmentPresenter;
import com.apps.twelve.floor.authorization.logic.userdetail.views.IDeleteAccountFragment;
import com.apps.twelve.floor.authorization.utils.ActionUtils;
import com.apps.twelve.floor.authorization.utils.DialogFactory;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.rey.material.app.BottomSheetDialog;

import static com.apps.twelve.floor.authorization.utils.Constants.Regex.REGEX_PASSWORD_LENGTH;
import static com.basgeekball.awesomevalidation.ValidationStyle.TEXT_INPUT_LAYOUT;

public class DeleteAccountFragment extends BaseFragment implements IDeleteAccountFragment {

  @InjectPresenter DeleteAccountFragmentPresenter mPresenter;

  @BindView(R2.id.et_password) EditText mPassword;
  @BindView(R2.id.btn_delete) CircularProgressButton mBtnDelete;

  @BindView(R2.id.til_password) NoChangeBackgroundTextInputLayout mTilPassword;

  private Unbinder unbinder;
  private Dialog mDialog;
  private BottomSheetDialog mVerifyDialog;
  private AwesomeValidation mAwesomeValidation;

  //verify dialog
  private EditText mEtVerifyCode;
  private NoChangeBackgroundTextInputLayout mTilVerifyCode;
  private CircularProgressButton mBtnVerifyCode;

  public DeleteAccountFragment() {
    super(R.layout.fragment_delete_account);
  }

  public static DeleteAccountFragment newInstance() {
    Bundle args = new Bundle();
    DeleteAccountFragment fragment = new DeleteAccountFragment();
    fragment.setArguments(args);
    return fragment;
  }

  @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

    setTitleAppBar(R.string.delete_account);
  }

  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    View rootView = super.onCreateView(inflater, container, savedInstanceState);
    unbinder = ButterKnife.bind(this, rootView);

    mAwesomeValidation = new AwesomeValidation(TEXT_INPUT_LAYOUT);
    mAwesomeValidation.addValidation(mTilPassword, REGEX_PASSWORD_LENGTH,
        getString(R.string.error_password_length));

    return rootView;
  }

  @Override public void onDestroyView() {
    super.onDestroyView();
    unbinder.unbind();
    ActionUtils.hideKeyboard(getContext());
  }

  @OnClick(R2.id.root_layout) public void onRootLayoutClick() {
    ActionUtils.hideKeyboard(getContext());
  }

  @OnClick(R2.id.btn_delete) public void deleteAccount() {
    clearErrors();
    if (TextUtils.isEmpty(mPassword.getText().toString())) {
      mTilPassword.setError(getString(R.string.error_required_field));
    } else if (mPassword.getText().toString().length() < 6) {
      mTilPassword.setError(getString(R.string.error_password_length));
    } else {
      mBtnDelete.startAnimation();
      mPresenter.deleteAccount1(mPassword.getText().toString());
    }
  }

  @Override public void showDialogMessage() {
    mDialog = DialogFactory.createSimpleOkDialog(getContext(),
        getString(R.string.dialog_title_delete_account),
        getString(R.string.dialog_message_delete_account), (dialog, which) -> {
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

  @Override public void showVerifyDialog() {
    mVerifyDialog = new BottomSheetDialog(getContext());

    View v = LayoutInflater.from(getContext()).inflate(R.layout.dialog_verify, null);

    mTilVerifyCode = v.findViewById(R.id.til_verify_code);
    mEtVerifyCode = v.findViewById(R.id.et_verify_code);
    mBtnVerifyCode = v.findViewById(R.id.btn_verify_code);

    mBtnVerifyCode.setOnClickListener(view -> {
      mBtnVerifyCode.startAnimation();
      mPresenter.verifyCode(mEtVerifyCode.getText().toString());
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

  @Override public void showError(String message) {
    mTilPassword.setError(message);
  }

  @Override public void showVerifyError(String errorMessage) {
    if (mTilVerifyCode != null) {
      mTilVerifyCode.setError(errorMessage);
    }
  }

  @Override public void stopAnimation() {
    TypedValue value = new TypedValue();
    getActivity().getTheme().resolveAttribute(R.attr.colorAccent, value, true);
    mBtnDelete.doneLoadingAnimation(ContextCompat.getColor(getContext(), value.resourceId),
        BitmapFactory.decodeResource(getResources(), R.drawable.ic_done_white_48dp));
  }

  @Override public void revertAnimation() {
    mBtnDelete.revertAnimation();
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

  @Override public void logout() {
    mPreferencesHelper.clear();
  }

  @Override public void onDestroy() {
    setTitleAppBar(R.string.menu_settings);
    super.onDestroy();
  }

  @Override public void showAlerter(@StringRes int resId) {
    showAlert(getString(R.string.error_title), getString(resId));
  }

  @Override public void showConnectErrorMessage() {
    showAlert(getString(R.string.error_title_no_internet_connection),
        getString(R.string.no_internet_connection));
  }
}
