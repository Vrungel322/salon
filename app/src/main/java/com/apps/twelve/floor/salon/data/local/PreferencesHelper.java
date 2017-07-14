package com.apps.twelve.floor.salon.data.local;

import android.content.Context;
import android.content.SharedPreferences;
import com.apps.twelve.floor.salon.data.model.LastBookingEntity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.util.List;
import rx.Observable;

/**
 * Created by John on 26.01.2017.
 */

public class PreferencesHelper {

  public static final String PREF_FILE_NAME = "com.salon.Salon";

  private static final String PREF_BONUS_COUNT = "PREF_BONUS_COUNT";
  public static final String PREF_THEME_SELECTED = "PREF_THEME_SELECTED";
  private static final String PREF_NOTIF_HOURLY_ENABLED = "PREF_NOTIF_HOURLY_ENABLED";
  private static final String PREF_NOTIF_DAILY_ENABLED = "PREF_NOTIF_DAILY_ENABLED";
  private static final String PREF_NOTIF_HOURS = "PREF_NOTIF_HOURS";
  private static final String PREF_NOTIF_HOURS_NIGHT_START = "PREF_NOTIF_HOURS_NIGHT_START";
  private static final String PREF_NOTIF_HOURS_NIGHT_END = "PREF_NOTIF_HOURS_NIGHT_END";
  private static final String PREF_NOTIF_DAYS = "PREF_NOTIF_DAYS";
  private static final String PREF_NOTIF_NIGHT_MODE = "PREF_NOTIF_NIGHT_MODE";
  private static final String PREF_LAST_PHONE_FOR_BOOKING = "PREF_LAST_PHONE_FOR_BOOKING";
  private static final String PREF_BOOKING = "PREF_BOOKING";

  private final SharedPreferences mPreferences;
  private Gson mGson;

  public PreferencesHelper(Context context, Gson gson) {
    mPreferences = context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE);
    mGson = gson;
  }

  public void clear() {
    mPreferences.edit().clear().apply();
  }

  public void setThemeSelected(int themeSelected) {
    mPreferences.edit().putInt(PREF_THEME_SELECTED, themeSelected).apply();
  }

  public int getThemeSelected() {
    return mPreferences.getInt(PREF_THEME_SELECTED, 0);
  }

  public int getBonusCountInt() {
    return mPreferences.getInt(PREF_BONUS_COUNT, 0);
  }

  public Observable<Integer> getBonusCountObservable() {
    return Observable.just(mPreferences.getInt(PREF_BONUS_COUNT, 0));
  }

  public void setBonusCount(int bonusCount) {
    mPreferences.edit().putInt(PREF_BONUS_COUNT, bonusCount).apply();
  }

  public void setLastPhoneForBooking(String lastPhone) {
    mPreferences.edit().putString(PREF_LAST_PHONE_FOR_BOOKING, lastPhone).apply();
  }

  public String getLastPhoneForBooking() {
    return mPreferences.getString(PREF_LAST_PHONE_FOR_BOOKING, "");
  }

  public boolean isHourlyNotificationsEnabled() {
    return mPreferences.getBoolean(PREF_NOTIF_HOURLY_ENABLED, true);
  }

  public void setHourlyNotificationsEnabled(boolean enabled) {
    mPreferences.edit().putBoolean(PREF_NOTIF_HOURLY_ENABLED, enabled).apply();
  }

  public boolean isDailyNotificationsEnabled() {
    return mPreferences.getBoolean(PREF_NOTIF_DAILY_ENABLED, true);
  }

  public void setDailyNotificationsEnabled(boolean enabled) {
    mPreferences.edit().putBoolean(PREF_NOTIF_DAILY_ENABLED, enabled).apply();
  }

  public int getNotificationDays() {
    return mPreferences.getInt(PREF_NOTIF_DAYS, 1);
  }

  public void setNotificationDays(int days) {
    mPreferences.edit().putInt(PREF_NOTIF_DAYS, days).apply();
  }

  public long getNotificationHours() {
    return mPreferences.getLong(PREF_NOTIF_HOURS, 3600000);
  }

  public long getNotificationHoursNightStart() {
    return mPreferences.getLong(PREF_NOTIF_HOURS_NIGHT_START, 82800000);
  }

  public long getNotificationHoursNightEnd() {
    return mPreferences.getLong(PREF_NOTIF_HOURS_NIGHT_END, 25200000);
  }

  public void setNotificationHours(long millis) {
    mPreferences.edit().putLong(PREF_NOTIF_HOURS, millis).apply();
  }

  public void setNotificationHoursNightStart(long millis) {
    mPreferences.edit().putLong(PREF_NOTIF_HOURS_NIGHT_START, millis).apply();
  }

  public void setNotificationHoursNightEnd(long millis) {
    mPreferences.edit().putLong(PREF_NOTIF_HOURS_NIGHT_END, millis).apply();
  }

  public boolean isNightMode() {
    return mPreferences.getBoolean(PREF_NOTIF_NIGHT_MODE, true);
  }

  public void setNightMode(boolean enabled) {
    mPreferences.edit().putBoolean(PREF_NOTIF_NIGHT_MODE, enabled).apply();
  }

  public void putBooking(List<LastBookingEntity> bookingEntities) {
    mPreferences.edit().putString(PREF_BOOKING, mGson.toJson(bookingEntities)).apply();
  }

  public List<LastBookingEntity> getBooking() {
    String bookingEntities = mPreferences.getString(PREF_BOOKING, null);
    if (bookingEntities != null) {
      return mGson.fromJson(bookingEntities, new TypeToken<List<LastBookingEntity>>() {
      }.getType());
    }
    return null;
  }

  public void logoutUser() {
    setBonusCount(0);
    setLastPhoneForBooking("");
  }
}
