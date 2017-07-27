package com.apps.twelve.floor.salon.feature.settings.fragments;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.ContextThemeWrapper;
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
import com.apps.twelve.floor.salon.utils.Converters;
import com.arellomobile.mvp.presenter.InjectPresenter;
import java.util.concurrent.TimeUnit;

/**
 * Created by Alexandra on 05.05.2017.
 */

public class NotificationSettingsFragment extends BaseFragment
    implements INotificationSettingsFragmentView {

  @InjectPresenter NotificationSettingsFragmentPresenter mNotificationSettingsFragmentPresenter;

  @BindView(R.id.switchHourly) android.support.v7.widget.SwitchCompat mSwitchHourly;
  @BindView(R.id.switchDaily) android.support.v7.widget.SwitchCompat mSwitchDaily;
  @BindView(R.id.switchNight) android.support.v7.widget.SwitchCompat mSwitchNight;
  @BindView(R.id.tvDaily) TextView mTextViewDaily;
  @BindView(R.id.tvHourly) TextView mTextViewHourly;
  @BindView(R.id.tvNight) TextView mTextViewNight;

  private AlertDialog mPickDaysDialog;

  private TimePickerDialog mPickHourDialog;
  private TimePickerDialog mPickStartNightDialog;
  private TimePickerDialog mPickEndNightDialog;

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

  @Override public void onDestroyView() {
    super.onDestroyView();
    if (mPickDaysDialog != null) {
      mPickDaysDialog.dismiss();
    }
  }

  @OnCheckedChanged(R.id.switchHourly) void switchHourly(boolean checked) {
    mNotificationSettingsFragmentPresenter.setHourlyNotificationsEnabled(checked);
  }

  @OnCheckedChanged(R.id.switchDaily) void switchDaily(boolean checked) {
    mNotificationSettingsFragmentPresenter.setDailyNotificationsEnabled(checked);
  }

  @OnCheckedChanged(R.id.switchNight) void switchNight(boolean checked) {
    mNotificationSettingsFragmentPresenter.setNightModeNotificationsEnabled(checked);
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

  @OnClick(R.id.rlNight) void setNightEnabled() {
    boolean checked = !mSwitchNight.isChecked();
    mNotificationSettingsFragmentPresenter.setNightModeNotificationsEnabled(checked);
    mSwitchNight.setChecked(checked);
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
        .setPositiveButton(R.string.dialog_action_ok, (dialog, which) -> {
          mNotificationSettingsFragmentPresenter.saveDays();
          mNotificationSettingsFragmentPresenter.cancelPickDayDialog();
        })
        .setNegativeButton(R.string.cancel,
            (dialog, which) -> mNotificationSettingsFragmentPresenter.cancelPickDayDialog())
        .create();
    mPickDaysDialog.show();
    mPickDaysDialog.setOnCancelListener(
        dialogInterface -> mNotificationSettingsFragmentPresenter.cancelPickDayDialog());
  }

  @OnClick(R.id.rlHourlyChange) void changeHourly() {
    mNotificationSettingsFragmentPresenter.showPickHourDialog();
  }

  @OnClick(R.id.tvNightChangeStart) void changeNightStart() {
    mNotificationSettingsFragmentPresenter.showPickStartNightDialog();
  }

  @OnClick(R.id.tvNightChangeEnd) void changeNightEnd() {
    mNotificationSettingsFragmentPresenter.showPickEndNightDialog();
  }

  @Override public void showPickHourDialog() {
    mPickHourDialog = new TimePickerDialog(
        new ContextThemeWrapper(getContext(), android.R.style.Theme_Holo_Light_Dialog_MinWidth),
        (timePicker, i, i1) -> {
          mNotificationSettingsFragmentPresenter.setHours(
              TimeUnit.HOURS.toMillis(i) + TimeUnit.MINUTES.toMillis(i1));
          mNotificationSettingsFragmentPresenter.cancelPickHourDialog();
        }, (int) TimeUnit.MILLISECONDS.toHours(mNotificationSettingsFragmentPresenter.getHours()),
        (int) (TimeUnit.MILLISECONDS.toMinutes(mNotificationSettingsFragmentPresenter.getHours())
            - TimeUnit.HOURS.toMinutes(
            TimeUnit.MILLISECONDS.toHours(mNotificationSettingsFragmentPresenter.getHours()))),
        true);
    mPickHourDialog.setOnCancelListener(
        dialogInterface -> mNotificationSettingsFragmentPresenter.cancelPickHourDialog());
    mPickHourDialog.show();
  }

  @Override public void showPickStartNightDialog() {
    mPickStartNightDialog = new TimePickerDialog(
        new ContextThemeWrapper(getContext(), android.R.style.Theme_Holo_Light_Dialog_MinWidth),
        (timePicker, i, i1) -> {
          mNotificationSettingsFragmentPresenter.setHoursNightStart(
              TimeUnit.HOURS.toMillis(i) + TimeUnit.MINUTES.toMillis(i1));
          mNotificationSettingsFragmentPresenter.cancelPickStartNightDialog();
        }, (int) TimeUnit.MILLISECONDS.toHours(
        mNotificationSettingsFragmentPresenter.getHoursNightStart()),
        (int) (TimeUnit.MILLISECONDS.toMinutes(
            mNotificationSettingsFragmentPresenter.getHoursNightStart()) - TimeUnit.HOURS.toMinutes(
            TimeUnit.MILLISECONDS.toHours(
                mNotificationSettingsFragmentPresenter.getHoursNightStart()))), true);
    mPickStartNightDialog.setOnCancelListener(
        dialogInterface -> mNotificationSettingsFragmentPresenter.cancelPickStartNightDialog());
    mPickStartNightDialog.show();
  }

  @Override public void showPickEndNightDialog() {
    mPickEndNightDialog = new TimePickerDialog(
        new ContextThemeWrapper(getContext(), android.R.style.Theme_Holo_Light_Dialog_MinWidth),
        (timePicker, i, i1) -> {
          mNotificationSettingsFragmentPresenter.setHoursNightEnd(
              TimeUnit.HOURS.toMillis(i) + TimeUnit.MINUTES.toMillis(i1));
          mNotificationSettingsFragmentPresenter.cancelPickEndNightDialog();
        }, (int) TimeUnit.MILLISECONDS.toHours(
        mNotificationSettingsFragmentPresenter.getHoursNightEnd()),
        (int) (TimeUnit.MILLISECONDS.toMinutes(
            mNotificationSettingsFragmentPresenter.getHoursNightEnd()) - TimeUnit.HOURS.toMinutes(
            TimeUnit.MILLISECONDS.toHours(
                mNotificationSettingsFragmentPresenter.getHoursNightEnd()))), true);
    mPickStartNightDialog.setOnCancelListener(
        dialogInterface -> mNotificationSettingsFragmentPresenter.cancelPickEndNightDialog());
    mPickEndNightDialog.show();
  }

  @Override public void setUpSwitchers(boolean hourly, boolean daily, boolean night) {
    mSwitchHourly.setChecked(hourly);
    mSwitchDaily.setChecked(daily);
    mSwitchNight.setChecked(night);
  }

  @Override public void setUpDaysString(int days) {
    mTextViewDaily.setText(getString(R.string.settings_notifications_daily, days));
  }

  @Override public void setUpHoursString(long hours) {
    int hour = (int) TimeUnit.MILLISECONDS.toHours(hours);
    if (hour >= 1) {
      mTextViewHourly.setText(getString(R.string.settings_notifications_hourly, hour,
          TimeUnit.MILLISECONDS.toMinutes(hours) - TimeUnit.HOURS.toMinutes(
              TimeUnit.MILLISECONDS.toHours(hours))));
    } else {
      mTextViewHourly.setText(getString(R.string.settings_notifications_minutely,
          TimeUnit.MILLISECONDS.toMinutes(hours) - TimeUnit.HOURS.toMinutes(
              TimeUnit.MILLISECONDS.toHours(hours))));
    }
  }

  @Override public void setUpNightHours(long start, long end) {
    mTextViewNight.setText(getString(R.string.settings_notifications_night,
        Converters.timeFromMilliseconds(getContext(), String.valueOf(start)),
        Converters.timeFromMilliseconds(getContext(), String.valueOf(end))));
  }

  @Override public void cancelPickDayDialog() {
    if (mPickDaysDialog != null) {
      mPickDaysDialog.dismiss();
    }
  }

  @Override public void cancelPickHourDialog() {
    if (mPickHourDialog != null) {
      mPickHourDialog.dismiss();
    }
  }

  @Override public void cancelPickStartNightDialog() {
    if (mPickStartNightDialog != null) {
      mPickStartNightDialog.dismiss();
    }
  }

  @Override public void cancelPickEndNightDialog() {
    if (mPickEndNightDialog != null) {
      mPickEndNightDialog.dismiss();
    }
  }
}
