package com.apps.twelve.floor.authorization.utils;

/**
 * Created by Vrungel on 26.01.2017.
 */

public final class Constants {

  public static final String PREF_THEME_SELECTED = "PREF_THEME_SELECTED";
  public static final String EXTRA_THEME_RES_ID = "EXTRA_STYLE_RES_ID";

  public class Remote {
    public static final String BASE_URL = "https://jwt.floor12apps.com/";
    public static final int RESPONSE_CONTENT_ERROR = 400;
    public static final int RESPONSE_UNAUTHORIZED = 401;
    public static final int RESPONSE_TOKEN_EXPIRED = 403;
  }

  public class Regex {
    public static final String REGEX_EMAIL =
        "[a-zA-Z0-9_\\.\\+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-\\.]+";
    public static final String REGEX_PHONE = "^$|^[+][0-9]{12}$";
    public static final String REGEX_PHONE_NOT_EMPTY = "^[+][0-9]{12}$";
    public static final String REGEX_OR_CONDITION = "|";
    public static final String REGEX_EMAIL_OR_PHONE =
        "[a-zA-Z0-9_\\.\\+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-\\.]+|^\\+?\\d{12}$";
    public static final String REGEX_PASSWORD_LENGTH = "^[a-zA-Z0-9]{6,}$";
    public static final String REGEX_NOT_EMPTY = "^(?=\\s*\\S).*$";
    public static final String REGEX_NOT_EQUAL = "^(?!%s$).*$";
  }

  public class Login {
    public static final String EXTRA_LOGIN = "extra_login";
  }

  public class UserProfile {
    public static final String MALE = "male";
    public static final String FEMALE = "female";
    public static final String ARG_CONTAINER_RES_ID = "arg_container_res_id";
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
    public static final int EMAIL = 1;
    public static final int PHONE = 2;
    public static final int PASSWORD = 3;
  }

  public class RecoveryPassword {
    public static final String ARG_LOGIN_TYPE = "arg_login_type";
    public static final String ARG_LOGIN_VALUE = "arg_login_value";
    public static final String ARG_VERIFY_CODE = "arg_verify_code";
    public static final int EMAIL = 1;
    public static final int PHONE = 2;
  }

  public class Date {
    public static final int DEFAULT_YEAR = 1999;
  }

  public class Language {
    public static final String UK = "uk";
    public static final String RU = "ru";
  }

  public class Genders {
    public static final String UNKNOWN = "unknown";
    public static final String MALE = "male";
    public static final String FEMALE = "female";
  }
}
