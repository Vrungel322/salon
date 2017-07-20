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
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import com.apps.twelve.floor.authorization.R;
import com.apps.twelve.floor.authorization.R2;
import com.apps.twelve.floor.authorization.base.BaseFragment;
import com.apps.twelve.floor.authorization.data.model.DeviceInfoEntity;
import com.apps.twelve.floor.authorization.logic.authorization.activities.ModuleSignInActivity;
import com.apps.twelve.floor.authorization.logic.userdetail.adapters.ActivityHistoryAdapter;
import com.apps.twelve.floor.authorization.logic.userdetail.presenters.ActivityHistoryPresenter;
import com.apps.twelve.floor.authorization.logic.userdetail.views.IActivityHistoryFragmentView;
import com.apps.twelve.floor.authorization.utils.DialogFactory;
import com.arellomobile.mvp.presenter.InjectPresenter;
import java.util.List;

public class ActivityHistoryFragment extends BaseFragment implements IActivityHistoryFragmentView {

  @InjectPresenter ActivityHistoryPresenter mPresenter;

  @BindView(R2.id.recycler_view) RecyclerView mRecyclerView;
  @BindView(R2.id.btn_logout_all) Button mBtnLogoutAll;
  @BindView(R2.id.srlRefreshLayout) SwipeRefreshLayout mSrlRefreshLayout;
  private Unbinder unbinder;

  private Dialog mLogoutAllDialog;
  private ActivityHistoryAdapter mAdapter;

  public ActivityHistoryFragment() {
    super(R.layout.fragment_actvity_history);
  }

  public static ActivityHistoryFragment newInstance() {
    Bundle args = new Bundle();
    ActivityHistoryFragment fragment = new ActivityHistoryFragment();
    fragment.setArguments(args);
    return fragment;
  }

  @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

    setTitleAppBar(R.string.activity_history);

    TypedValue value = new TypedValue();
    getActivity().getTheme().resolveAttribute(R.attr.colorAccent, value, true);
    mSrlRefreshLayout.setColorSchemeResources(value.resourceId);
    mSrlRefreshLayout.setOnRefreshListener(() -> mPresenter.fetchActivityHistory());
  }

  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    View rootView = super.onCreateView(inflater, container, savedInstanceState);
    unbinder = ButterKnife.bind(this, rootView);
    return rootView;
  }

  public void showActivityHistory(List<DeviceInfoEntity> entityList) {
    if (mAdapter == null) {
      mAdapter = new ActivityHistoryAdapter();
    }
    mAdapter.setEntities(entityList);
    mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    mRecyclerView.setAdapter(mAdapter);
    mAdapter.notifyDataSetChanged();
    if (entityList != null && entityList.size() > 1) {
      mBtnLogoutAll.setVisibility(View.VISIBLE);
    } else {
      mBtnLogoutAll.setVisibility(View.GONE);
    }
  }

  @OnClick(R2.id.btn_logout_all) public void logoutAll() {
    mPresenter.showLogoutAllDialog();
  }

  @Override public void showSignInActivity() {
    mPreferencesHelper.clear();
    mNavigator.startActivityClearStack((AppCompatActivity) getContext(),
        new Intent(getContext(), ModuleSignInActivity.class));
  }

  @Override public void startRefreshingView() {
    if (!mSrlRefreshLayout.isRefreshing()) mSrlRefreshLayout.setRefreshing(true);
  }

  @Override public void stopRefreshingView() {
    if (mSrlRefreshLayout.isRefreshing()) mSrlRefreshLayout.setRefreshing(false);
  }

  @Override public void showLogoutAllDialog() {
    mLogoutAllDialog = DialogFactory.createAlertDialogBuilder(getContext(),
        getString(R.string.dialog_title_logout_all), getString(R.string.dialog_message_logout_all))
        .setPositiveButton(R.string.btn_ok, (dialog, which) -> mPresenter.logoutAll())
        .setNegativeButton(R.string.btn_cancel, (dialog, which) -> closeLogoutAllDialog())
        .setCancelable(false)
        .create();
    mLogoutAllDialog.setOnCancelListener(dialog -> mPresenter.closeLogoutAllDialog());
    mLogoutAllDialog.show();
  }

  @Override public void closeLogoutAllDialog() {
    if (mLogoutAllDialog != null) {
      mLogoutAllDialog.dismiss();
    }
  }

  @Override public void showConnectErrorMessage() {
    showAlert(getString(R.string.error_title_no_internet_connection),
        getString(R.string.no_internet_connection));
  }

  @Override public void onDestroy() {
    setTitleAppBar(R.string.profile_title);
    super.onDestroy();
  }

  @Override public void onDestroyView() {
    super.onDestroyView();
    unbinder.unbind();
    if (mLogoutAllDialog != null) {
      mLogoutAllDialog.dismiss();
    }
  }
}
