package com.apps.twelve.floor.authorization.utils;

import android.util.Patterns;

/**
 * Created by Alexander Svyatetsky on 12.05.2017.
 */

public class ValidatorUtils {
  public static boolean isValidEmail(String value) {
    return Patterns.EMAIL_ADDRESS.matcher(value).matches();
  }

  public static boolean isValidPhone(String value) {
    return Patterns.PHONE.matcher(value).matches();
  }
}
