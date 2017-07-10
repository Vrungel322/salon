package com.apps.twelve.floor.salon.feature.settings.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RelativeLayout;
import butterknife.BindView;
import butterknife.OnClick;
import com.apps.twelve.floor.authorization.AuthorizationManager;
import com.apps.twelve.floor.salon.R;
import com.apps.twelve.floor.salon.base.BaseFragment;
import com.apps.twelve.floor.salon.feature.settings.presenters.SettingsFragmentPresenter;
import com.apps.twelve.floor.salon.feature.settings.views.ISettingsFragmentView;
import com.arellomobile.mvp.presenter.InjectPresenter;

public class SettingsFragment extends BaseFragment implements ISettingsFragmentView {

  @InjectPresenter SettingsFragmentPresenter mSettingsFragmentPresenter;

  @BindView(R.id.rlNotifications) RelativeLayout mRelativeLayoutNotifications;

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

    if (!mAuthorizationManager.isAuthorized()) {
      mRelativeLayoutNotifications.setVisibility(View.GONE);
    }
  }

  @Override public void openUserProfileFragment() {
    mNavigator.addChildFragmentBackStack(this, R.id.container_info_user,
        AuthorizationManager.getInstance().openUserProfileFragment(R.id.container_settings));
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
    ThemeDialogFragment themeDialog = new ThemeDialogFragment();
    themeDialog.show(getActivity().getFragmentManager(), "");
  }
}
