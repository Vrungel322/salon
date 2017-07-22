package com.apps.twelve.floor.salon.utils;

import android.content.Context;
import android.net.Uri;
import com.apps.twelve.floor.salon.data.local.LocaleHelper;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

import static com.apps.twelve.floor.salon.utils.Constants.Remote.LOCAL;

/**
 * Created by Vrungel on 13.04.2017.
 */

public final class Converters {

  private static Locale mLocale = new Locale(LOCAL); //java.util.Locale.getDefault();

  public static String dateNow() {
    String dateResult;
    SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy", mLocale);
    Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
    calendar.getTimeInMillis();
    dateResult = formatter.format(calendar.getTime());
    return dateResult;
  }

  public static String timeFromMilliseconds(Context context, String date) {
    if (!date.equals("")) {
      String dateResult;
      SimpleDateFormat formatter =
          new SimpleDateFormat("HH:mm", new Locale(LocaleHelper.getLanguage(context)));
      Calendar calendar = Calendar.getInstance(mLocale);
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

  public static String timeFromSeconds(Context context, String date) {
    if (!date.equals("")) {
      String dateResult;
      SimpleDateFormat formatter =
          new SimpleDateFormat("HH:mm", new Locale(LocaleHelper.getLanguage(context)));
      Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
      long d = Long.valueOf(date) * 1000L;
      calendar.setTimeInMillis(d);
      dateResult = formatter.format(calendar.getTime());

      return dateResult;
    }
    return "";
  }

  public static String dateFromSeconds(Context context, String date) {
    if (!date.equals("")) {
      String dateResult;
      SimpleDateFormat formatter =
          new SimpleDateFormat("dd.MM.yyyy", new Locale(LocaleHelper.getLanguage(context)));
      Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
      long d = Long.valueOf(date) * 1000L;
      calendar.setTimeInMillis(d);
      dateResult = formatter.format(calendar.getTime());

      return dateResult;
    }
    return "";
  }

  public static String fullDateWithTimeFromSeconds(Context context, String date) {
    if (!date.equals("")) {
      String dateResult;
      SimpleDateFormat formatter =
          new SimpleDateFormat("dd.MM.yyyy (HH:mm)", new Locale(LocaleHelper.getLanguage(context)));
      Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
      long d = Long.valueOf(date) * 1000L;
      calendar.setTimeInMillis(d);
      dateResult = formatter.format(calendar.getTime());

      return dateResult;
    }
    return "";
  }

  public static String dayFromSeconds(Context context, String date) {
    if (!date.equals("")) {
      String dateResult;
      SimpleDateFormat formatter =
          new SimpleDateFormat("dd EEE", new Locale(LocaleHelper.getLanguage(context)));
      Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
      long d = Long.valueOf(date) * 1000L;
      calendar.setTimeInMillis(d);
      dateResult = formatter.format(calendar.getTime());

      return dateResult;
    }
    return "";
  }

  public static String detailDayFromSeconds(Context context, String date) {
    if (!date.equals("")) {
      String dateResult;
      SimpleDateFormat formatter =
          new SimpleDateFormat("dd MMMM (E)", new Locale(LocaleHelper.getLanguage(context)));
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
