package com.apps.twelve.floor.salon.feature.settings.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.NumberPicker;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;
import com.apps.twelve.floor.salon.R;
import com.apps.twelve.floor.salon.base.BaseFragment;
import com.apps.twelve.floor.salon.feature.settings.activities.SettingsActivity;
import com.apps.twelve.floor.salon.feature.settings.presenters.NotificationSettingsFragmentPresenter;
import com.apps.twelve.floor.salon.feature.settings.views.INotificationSettingsFragmentView;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog;
import java.util.concurrent.TimeUnit;

/**
 * Created by Alexandra on 05.05.2017.
 */

public class NotificationSettingsFragment extends BaseFragment
    implements INotificationSettingsFragmentView, TimePickerDialog.OnTimeSetListener {

  @InjectPresenter NotificationSettingsFragmentPresenter mNotificationSettingsFragmentPresenter;

  @BindView(R.id.switchHourly) android.support.v7.widget.SwitchCompat mSwitchHourly;
  @BindView(R.id.switchDaily) android.support.v7.widget.SwitchCompat mSwitchDaily;
  @BindView(R.id.tvDaily) TextView mTextViewDaily;
  @BindView(R.id.tvHourly) TextView mTextViewHourly;

  private AlertDialog mPickDaysDialog;

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
    mNotificationSettingsFragmentPresenter.setUpStrings();
  }

  @OnCheckedChanged(R.id.switchHourly) void switchHourly(boolean checked) {
    mNotificationSettingsFragmentPresenter.setHourlyNotificationsEnabled(checked);
  }

  @OnCheckedChanged(R.id.switchDaily) void switchDaily(boolean checked) {
    mNotificationSettingsFragmentPresenter.setDailyNotificationsEnabled(checked);
  }

  @OnClick(R.id.rlHourly) void setHourlyEnabled() {
    boolean checked = !mSwitchHourly.isChecked();
    mNotificationSettingsFragmentPresenter.setHourlyNotificationsEnabled(checked);
    mSwitchHourly.setChecked(checked);
  }

  @OnClick(R.id.rlDaily) void setDailyEnabled() {
    boolean checked = !mSwitchDaily.isChecked();
    mNotificationSettingsFragmentPresenter.setDailyNotificationsEnabled(checked);
    mSwitchDaily.setChecked(checked);
  }

  @OnClick(R.id.rlDailyChange) void changeDaily() {
    mNotificationSettingsFragmentPresenter.showPickDayDialog();
  }

  @Override public void showPickDayDialog() {
    final NumberPicker picker = new NumberPicker(getContext());
    picker.setMinValue(1);
    picker.setMaxValue(7);
    picker.setValue(mNotificationSettingsFragmentPresenter.getLastPickedDays());
    picker.setOnValueChangedListener(
        (numberPicker, oldVal, newVal) -> mNotificationSettingsFragmentPresenter.setLastPickedDays(
            newVal));

    final FrameLayout layout = new FrameLayout(getContext());
    layout.addView(picker, new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT,
        FrameLayout.LayoutParams.WRAP_CONTENT, Gravity.CENTER));

    mPickDaysDialog = new AlertDialog.Builder(getContext()).setView(layout)
        .setPositiveButton(R.string.confirm, (dialog, which) -> {
          mNotificationSettingsFragmentPresenter.saveDays();
          mNotificationSettingsFragmentPresenter.cancelPickDayDialog();
        })
        .setNegativeButton(R.string.cancel,
            (dialog, which) -> mNotificationSettingsFragmentPresenter.cancelPickDayDialog())
        .create();
    mPickDaysDialog.show();
  }

  @OnClick(R.id.rlHourlyChange) void changeHourly() {
    TimePickerDialog tpd = TimePickerDialog.newInstance(NotificationSettingsFragment.this,
        (int) TimeUnit.MILLISECONDS.toHours(mNotificationSettingsFragmentPresenter.getHours()),
        (int) (TimeUnit.MILLISECONDS.toMinutes(mNotificationSettingsFragmentPresenter.getHours())
            - TimeUnit.HOURS.toMinutes(
            TimeUnit.MILLISECONDS.toHours(mNotificationSettingsFragmentPresenter.getHours()))),
        true);
    tpd.show(getActivity().getFragmentManager(), "Timepickerdialog");
  }

  @Override public void setUpSwitchers(boolean hourly, boolean daily) {
    mSwitchHourly.setChecked(hourly);
    mSwitchDaily.setChecked(daily);
  }

  @Override public void setUpDaysString(int days) {
    mTextViewDaily.setText(getString(R.string.settings_notifications_daily, days));
  }

  @Override public void setUpHoursString(long hours) {
    mTextViewHourly.setText(
        getString(R.string.settings_notifications_hourly, TimeUnit.MILLISECONDS.toHours(hours),
            TimeUnit.MILLISECONDS.toMinutes(hours) - TimeUnit.HOURS.toMinutes(
                TimeUnit.MILLISECONDS.toHours(hours))));
  }

  @Override public void onTimeSet(TimePickerDialog timePickerDialog, int i, int i1, int i2) {
    mNotificationSettingsFragmentPresenter.setHours(
        TimeUnit.HOURS.toMillis(i) + TimeUnit.MINUTES.toMillis(i1));
  }

  @Override public void cancelPickDayDialog() {
    if (mPickDaysDialog != null) {
      mPickDaysDialog.dismiss();
    }
  }
}
