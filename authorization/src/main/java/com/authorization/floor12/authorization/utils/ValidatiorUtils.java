package com.authorization.floor12.authorization.utils;

import android.util.Patterns;

/**
 * Created by Alexander Svyatetsky on 12.05.2017.
 */

public class ValidatiorUtils {
  public static boolean EmailValidator(String login) {
    return Patterns.EMAIL_ADDRESS.matcher(login).matches();
  }
}
