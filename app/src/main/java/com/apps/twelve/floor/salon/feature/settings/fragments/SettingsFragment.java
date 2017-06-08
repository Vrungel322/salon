package com.apps.twelve.floor.salon.feature.settings.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import butterknife.OnClick;
import com.apps.twelve.floor.salon.R;
import com.apps.twelve.floor.salon.base.BaseFragment;
import com.apps.twelve.floor.salon.feature.settings.presenters.SettingsFragmentPresenter;
import com.apps.twelve.floor.salon.feature.settings.views.ISettingsFragmentView;
import com.apps.twelve.floor.salon.feature.start_point.activities.StartActivity;
import com.apps.twelve.floor.salon.utils.DialogFactory;
import com.arellomobile.mvp.presenter.InjectPresenter;

public class SettingsFragment extends BaseFragment implements ISettingsFragmentView {

  @InjectPresenter SettingsFragmentPresenter mSettingsFragmentPresenter;

  private AlertDialog mChooseThemeDialog;

  public SettingsFragment() {
    super(R.layout.fragment_settings);
  }

  public static SettingsFragment newInstance() {
    Bundle args = new Bundle();
    SettingsFragment fragment = new SettingsFragment();
    fragment.setArguments(args);
    return fragment;
  }

  @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
  }

  @Override public void showSetThemeDialog(int position) {
    String[] themes = getResources().getStringArray(R.array.dialog_theme);
    mChooseThemeDialog = DialogFactory.createAlertDialogBuilder(getActivity(),
        getString(R.string.dialog_title_select_theme))
        .setSingleChoiceItems(themes, position, (dialog, which) -> {
          mSettingsFragmentPresenter.setThemeApp(which);
          mNavigator.startActivityClearStack((AppCompatActivity) getActivity(),
              new Intent(getActivity(), StartActivity.class));
        })
        .create();
    mChooseThemeDialog.show();
  }

  @Override public void closeSetThemeDialog() {
    if (mChooseThemeDialog != null) {
      mChooseThemeDialog.dismiss();
    }
  }

  @Override public void openUserProfileFragment() {
    //mAuthorizationManager......
  }

  @OnClick(R.id.rlNotifications) void editNotifications() {
    mNavigator.addFragmentBackStack((AppCompatActivity) getActivity(), R.id.container_settings,
        NotificationSettingsFragment.newInstance());
  }

  @OnClick(R.id.rlProblem) void problems() {
    mNavigator.addFragmentBackStack((AppCompatActivity) getActivity(), R.id.container_settings,
        ReportProblemFragment.newInstance());
  }

  @OnClick(R.id.rlTheme) void changeTheme() {
    mSettingsFragmentPresenter.showSetThemeDialog();
  }

  @Override public void onDestroy() {
    super.onDestroy();
    if (mChooseThemeDialog != null) {
      mChooseThemeDialog.dismiss();
    }
  }
}
