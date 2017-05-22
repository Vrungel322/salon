package com.apps.twelve.floor.salon.feature.settings.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import butterknife.BindView;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;
import com.apps.twelve.floor.salon.R;
import com.apps.twelve.floor.salon.base.BaseFragment;
import com.apps.twelve.floor.salon.feature.settings.activities.SettingsActivity;
import com.apps.twelve.floor.salon.feature.settings.presenters.NotificationSettingsFragmentPresenter;
import com.apps.twelve.floor.salon.feature.settings.views.INotificationSettingsFragmentView;
import com.arellomobile.mvp.presenter.InjectPresenter;

/**
 * Created by Alexandra on 05.05.2017.
 */

public class NotificationSettingsFragment extends BaseFragment
    implements INotificationSettingsFragmentView {

  @InjectPresenter NotificationSettingsFragmentPresenter mNotificationSettingsFragmentPresenter;

  @BindView(R.id.switchHourly) android.support.v7.widget.SwitchCompat mSwitchHourly;
  @BindView(R.id.switchDaily) android.support.v7.widget.SwitchCompat mSwitchDaily;

  public NotificationSettingsFragment() {
    super(R.layout.fragment_notification_settings);
  }

  public static NotificationSettingsFragment newInstance() {
    Bundle args = new Bundle();
    NotificationSettingsFragment fragment = new NotificationSettingsFragment();
    fragment.setArguments(args);
    return fragment;
  }

  @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

    ((SettingsActivity) getActivity()).setTitleAppBar(R.string.settings_notifications);

    mNotificationSettingsFragmentPresenter.setUpSwitches();
  }

  @OnCheckedChanged(R.id.switchHourly) void switchHourly(boolean checked) {
    mNotificationSettingsFragmentPresenter.setHourlyNotificationsEnabled(checked);
  }

  @OnCheckedChanged(R.id.switchDaily) void switchDaily(boolean checked) {
    mNotificationSettingsFragmentPresenter.setDailyNotificationsEnabled(checked);
  }

  @OnClick(R.id.rlHourly) void setHourly() {
    boolean checked = !mSwitchHourly.isChecked();
    mNotificationSettingsFragmentPresenter.setHourlyNotificationsEnabled(checked);
    mSwitchHourly.setChecked(checked);
  }

  @OnClick(R.id.rlDaily) void setDaily() {
    boolean checked = !mSwitchDaily.isChecked();
    mNotificationSettingsFragmentPresenter.setDailyNotificationsEnabled(checked);
    mSwitchDaily.setChecked(checked);
  }

  @Override public void setUpSwitchers(boolean hourly, boolean daily) {
    mSwitchHourly.setChecked(hourly);
    mSwitchDaily.setChecked(daily);
  }
}
