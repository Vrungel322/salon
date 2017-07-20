package com.apps.twelve.floor.authorization.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import timber.log.Timber;

/**
 * Created by alexandersvyatetsky on 7/07/17.
 */

public class DateUtils {

  public static String formatServerDate(String date) {
    if (date == null) {
      return null;
    }

    DateFormat originalFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
    DateFormat targetFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);

    Date originalDate = null;
    try {
      originalDate = originalFormat.parse(date);
    } catch (ParseException e) {
      Timber.d(e);
      return null;
    }

    return targetFormat.format(originalDate);
  }

  public static Calendar parseClientDate(String date) {
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
    Calendar calendar = Calendar.getInstance(Locale.getDefault());
    calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR) - 18);
    try {
      calendar.setTime(dateFormat.parse(date));
    } catch (ParseException e) {
      Timber.d(e);
    }

    return calendar;
  }
}
