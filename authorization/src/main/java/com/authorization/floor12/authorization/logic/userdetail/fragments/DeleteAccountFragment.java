package com.authorization.floor12.authorization.logic.userdetail.fragments;
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
import com.authorization.floor12.authorization.logic.userdetail.presenters.DeleteAccountFragmentPresenter;
import com.authorization.floor12.authorization.logic.userdetail.views.IDeleteAccountFragment;

public class DeleteAccountFragment extends BaseFragment implements IDeleteAccountFragment {

  @InjectPresenter DeleteAccountFragmentPresenter mPresenter;

  @BindView(R2.id.et_email_or_phone) EditText mPassword;
  @BindView(R2.id.btn_delete) CircularProgressButton mBtnDelete;
  Unbinder unbinder;

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

    ActionBar actionBar = ((UserProfileActivity) getActivity()).getSupportActionBar();
    if (actionBar != null) {
      actionBar.setTitle(getString(R.string.delete_account));
    }
  }

  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    // TODO: inflate a fragment view
    View rootView = super.onCreateView(inflater, container, savedInstanceState);
    unbinder = ButterKnife.bind(this, rootView);
    return rootView;
  }

  @Override public void onDestroyView() {
    super.onDestroyView();
    unbinder.unbind();
  }

  @OnClick(R2.id.btn_delete) public void deleteAccount() {
    mBtnDelete.startAnimation();
    mPresenter.deleteAccount(mPassword.getText().toString());
  }

  @Override public void showError(String message) {
    mPassword.setError(message);
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

  @Override public void closeFragment() {
    mPreferencesHelper.clear();
    mNavigator.startActivityClearStack((AppCompatActivity) getContext(),
        new Intent(getContext(), ModuleSignInActivity.class));
  }

  @Override public void showAlerter(@StringRes int resId) {
    showAlert(getString(R.string.error_title), getString(resId));
  }

  @Override public void showConnectErrorMessage() {
    showAlert(getString(R.string.error_title_no_internet_connection),
        getString(R.string.no_internet_connection));
  }
}
