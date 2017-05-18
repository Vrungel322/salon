package com.apps.twelve.floor.salon.feature.settings.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Switch;
import butterknife.BindView;
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

  @BindView(R.id.switchHourly) Switch mSwithHourly;
  @BindView(R.id.switchDaily) Switch mSwitchDaily;

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
  }
}
