package com.authorization.floor12.authorization.utils;

/**
 * Created by Vrungel on 26.01.2017.
 */

public final class Constants {

  public static final String PREF_FILE_NAME = "com.salon.Salon";
  public static final String PREF_THEME_SELECTED = "PREF_THEME_SELECTED";

  public static final String EXTRA_THEME_RES_ID = "EXTRA_STYLE_RES_ID";

  public class Remote {
    public static final String BASE_URL = "https://jwt.floor12apps.com/";
  }

  public class Login {
    public static final String REGEX_EMAIL =
        "[a-zA-Z0-9_\\.\\+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-\\.]+";
    public static final String REGEX_PHONE = "^$|^\\+?\\d{12}$";
    public static final String REGEX_OR_CONDITION = "|";
    public static final int REQUEST_CODE = 100;
  }

  public class UserProfile {
    public static final String BEARER = "bearer";
    public static final String USER = "user_detail";
    public static final String MALE = "male";
    public static final String FEMALE = "female";
  }

  public class Registration {
    public static final String EXTRA_USER = "extra_user";
  }

  public class FragmentsArgumentKeys {
    public static final String CHANGING_FIELD = "CHANGING_FIELD";
    public static final String CHANGING_FIELD_VALUE = "CHANGING_FIELD_VALUE";
  }

  public class ChangingUserInfoField {
    public static final int NAME = 0;
    public static final int LOGIN = 1;
    public static final int PASSWORD = 2;
    public static final int EMAIL = 3;
    public static final int PHONE = 4;
  }
}
