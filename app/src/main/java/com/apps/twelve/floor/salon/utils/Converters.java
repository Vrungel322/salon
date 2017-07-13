package com.apps.twelve.floor.salon.utils;

import android.net.Uri;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Created by Vrungel on 13.04.2017.
 */

public final class Converters {

  private static Locale mLocale = new Locale("ru"); //java.util.Locale.getDefault();

  public static String dateNow() {
    String dateResult;
    SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy", mLocale);
    Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
    calendar.getTimeInMillis();
    dateResult = formatter.format(calendar.getTime());
    return dateResult;
  }

  public static String timeFromMilliseconds(String date) {
    if (!date.equals("")) {
      String dateResult;
      SimpleDateFormat formatter = new SimpleDateFormat("HH:mm", mLocale);
      Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
      long d = Long.valueOf(date);
      calendar.setTimeInMillis(d);
      dateResult = formatter.format(calendar.getTime());

      return dateResult;
    }
    return "";
  }

  public static int timeHoursFromMilliseconds(String date) {
    if (!date.equals("")) {
      String dateResult;
      SimpleDateFormat formatter = new SimpleDateFormat("HH", mLocale);
      Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
      long d = Long.valueOf(date);
      calendar.setTimeInMillis(d);
      dateResult = formatter.format(calendar.getTime());

      return Integer.parseInt(dateResult);
    }
    return 0;
  }

  public static String timeFromSeconds(String date) {
    if (!date.equals("")) {
      String dateResult;
      SimpleDateFormat formatter = new SimpleDateFormat("HH:mm", mLocale);
      Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
      long d = Long.valueOf(date) * 1000L;
      calendar.setTimeInMillis(d);
      dateResult = formatter.format(calendar.getTime());

      return dateResult;
    }
    return "";
  }

  public static String dateFromSeconds(String date) {
    if (!date.equals("")) {
      String dateResult;
      SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy", mLocale);
      Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
      long d = Long.valueOf(date) * 1000L;
      calendar.setTimeInMillis(d);
      dateResult = formatter.format(calendar.getTime());

      return dateResult;
    }
    return "";
  }

  public static String fullDateWithTimeFromSeconds(String date) {
    if (!date.equals("")) {
      String dateResult;
      SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy (HH:mm)", mLocale);
      Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
      long d = Long.valueOf(date) * 1000L;
      calendar.setTimeInMillis(d);
      dateResult = formatter.format(calendar.getTime());

      return dateResult;
    }
    return "";
  }

  public static String dayFromSeconds(String date) {
    if (!date.equals("")) {
      String dateResult;
      SimpleDateFormat formatter = new SimpleDateFormat("dd EEE", mLocale);
      Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
      long d = Long.valueOf(date) * 1000L;
      calendar.setTimeInMillis(d);
      dateResult = formatter.format(calendar.getTime());

      return dateResult;
    }
    return "";
  }

  public static String detailDayFromSeconds(String date) {
    if (!date.equals("")) {
      String dateResult;
      SimpleDateFormat formatter = new SimpleDateFormat("dd MMMM (E)", mLocale);
      Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
      long d = Long.valueOf(date) * 1000L;
      calendar.setTimeInMillis(d);
      dateResult = formatter.format(calendar.getTime());
      return dateResult;
    }
    return "";
  }

  public static String getUrl(int res) {
    return Uri.parse("android.resource://com.apps.twelve.floor.salon/" + res).toString();
  }
}
