package com.apps.twelve.floor.salon.feature.settings.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import com.apps.twelve.floor.authorization.AuthorizationManager;
import com.apps.twelve.floor.salon.R;
import com.apps.twelve.floor.salon.base.BaseFragment;
import com.apps.twelve.floor.salon.feature.settings.presenters.SettingsFragmentPresenter;
import com.apps.twelve.floor.salon.feature.settings.views.ISettingsFragmentView;
import com.apps.twelve.floor.salon.feature.start_point.activities.StartActivity;
import com.apps.twelve.floor.salon.utils.DialogFactory;
import com.arellomobile.mvp.presenter.InjectPresenter;

public class SettingsFragment extends BaseFragment implements ISettingsFragmentView {

  @InjectPresenter SettingsFragmentPresenter mSettingsFragmentPresenter;

  @BindView(R.id.rlNotifications) RelativeLayout mRelativeLayoutNotifications;
  Unbinder unbinder;

  private AlertDialog mChangeLanguageDialog;
  private String mSelectedLanguage;

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
    themeDialog.setCancelable(false);
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
    if (mChangeLanguageDialog != null) {
      mChangeLanguageDialog.dismiss();
    }
  }

  @OnClick(R.id.tvLanguage) public void onViewClicked() {
    String[] languages = getResources().getStringArray(R.array.dialog_language);
    mSelectedLanguage = languages[mSettingsFragmentPresenter.getLanguagePosition()];
    mChangeLanguageDialog = DialogFactory.createAlertDialogBuilder(getContext(),
        getString(R.string.dialog_title_select_language))
        .setSingleChoiceItems(languages, mSettingsFragmentPresenter.getLanguagePosition(),
            (dialog, which) -> mSelectedLanguage = languages[which])
        .setPositiveButton(R.string.btn_ok, (dialog, which) -> {
          mSettingsFragmentPresenter.saveLanguage(mSelectedLanguage);
          mNavigator.startActivityClearStack((AppCompatActivity) getActivity(),
              new Intent(getActivity(), StartActivity.class));
        })
        .setCancelable(false)
        .create();
    mChangeLanguageDialog.setOnCancelListener(
        dialog -> mSettingsFragmentPresenter.closeChangeLanguageDialog());
    mChangeLanguageDialog.show();
  }

  @Override public void closeChangeLanguageDialog() {
    if (mChangeLanguageDialog != null) {
      mChangeLanguageDialog.dismiss();
    }
  }
}
