package com.apps.twelve.floor.salon.feature.settings.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
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

  public static NotificationSettingsFragment newInstance() {
    Bundle args = new Bundle();
    NotificationSettingsFragment fragment = new NotificationSettingsFragment();
    fragment.setArguments(args);
    return fragment;
  }

  public NotificationSettingsFragment() {
    super(R.layout.fragment_notification_settings);
  }

  @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

    ((SettingsActivity) getActivity()).setTitleAppBar(R.string.settings_notifications);
  }
}
