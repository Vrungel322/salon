package com.apps.twelve.floor.salon.feature.settings.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.util.TypedValue;
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
import com.github.florent37.singledateandtimepicker.dialog.SingleDateAndTimePickerDialog;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Created by Alexandra on 05.05.2017.
 */

public class NotificationSettingsFragment extends BaseFragment
    implements INotificationSettingsFragmentView {

  @InjectPresenter NotificationSettingsFragmentPresenter mNotificationSettingsFragmentPresenter;

  @BindView(R.id.switchHourly) android.support.v7.widget.SwitchCompat mSwitchHourly;
  @BindView(R.id.switchDaily) android.support.v7.widget.SwitchCompat mSwitchDaily;
  @BindView(R.id.tvDaily) TextView mTextViewDaily;
  @BindView(R.id.tvHourly) TextView mTextViewHourly;

  private AlertDialog mPickDaysDialog;
  private SingleDateAndTimePickerDialog.Builder mSingleDateAndTimePickerDialog;

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
    TypedValue value = new TypedValue();
    getActivity().getTheme().resolveAttribute(R.attr.colorAccent, value, true);

    Calendar calendarDefault = Calendar.getInstance();
    calendarDefault.set(Calendar.HOUR,
        (int) TimeUnit.MILLISECONDS.toHours(mNotificationSettingsFragmentPresenter.getHours()));
    calendarDefault.set(Calendar.MINUTE,
        (int) (TimeUnit.MILLISECONDS.toMinutes(mNotificationSettingsFragmentPresenter.getHours())
            - TimeUnit.HOURS.toMinutes(
            TimeUnit.MILLISECONDS.toHours(mNotificationSettingsFragmentPresenter.getHours()))));
    Date dateDefault = calendarDefault.getTime();
    mSingleDateAndTimePickerDialog =
        new SingleDateAndTimePickerDialog.Builder(getActivity()).bottomSheet()
            .curved()
            .mainColor(ContextCompat.getColor(getActivity(), value.resourceId))
            .displayDays(false)
            .defaultDate(dateDefault)
            .listener(date -> {
              Calendar calendar = Calendar.getInstance();
              calendar.setTime(date);
              mNotificationSettingsFragmentPresenter.setHours(
                  TimeUnit.HOURS.toMillis(calendar.get(Calendar.HOUR)) + TimeUnit.MINUTES.toMillis(
                      calendar.get(Calendar.MINUTE)));
            });
    mSingleDateAndTimePickerDialog.display();
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

  @Override public void cancelPickDayDialog() {
    if (mPickDaysDialog != null) {
      mPickDaysDialog.dismiss();
    }
  }
}
