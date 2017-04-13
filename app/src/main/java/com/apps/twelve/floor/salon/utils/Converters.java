package com.apps.twelve.floor.salon.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Vrungel on 13.04.2017.
 */

public class Converters {

  public static String dateFromMilliseconds(String date) {
    if (!date.equals("")) {
      String dateResult;
      SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy", java.util.Locale.getDefault());
      Calendar calendar = Calendar.getInstance();
      long d = Long.valueOf(date) * 1000L;
      calendar.setTimeInMillis(d);
      dateResult = formatter.format(calendar.getTime());

      return dateResult;
    }
    return "";
  }

  public static String dateNow() {
    String dateResult;
    SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy", java.util.Locale.getDefault());
    Calendar calendar = Calendar.getInstance();
    calendar.getTimeInMillis();
    dateResult = formatter.format(calendar.getTime());
    return dateResult;
  }


  public static String timeFromMilliseconds(String date) {
    if (!date.equals("")) {
      String dateResult;
      SimpleDateFormat formatter = new SimpleDateFormat("HH:mm", java.util.Locale.getDefault());
      Calendar calendar = Calendar.getInstance();
      long d = Long.valueOf(date) * 1000L;
      calendar.setTimeInMillis(d);
      dateResult = formatter.format(calendar.getTime());

      return dateResult;
    }
    return "";
  }

  public static String timeFromSeconds(String date) {
    if (!date.equals("")) {
      String dateResult;
      SimpleDateFormat formatter = new SimpleDateFormat("HH:mm", java.util.Locale.getDefault());
      Calendar calendar = Calendar.getInstance();
      long d = Long.valueOf(date);
      calendar.setTimeInMillis(d);
      dateResult = formatter.format(calendar.getTime());

      return dateResult;
    }
    return "";
  }
}
