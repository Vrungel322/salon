package com.apps.twelve.floor.salon.utils;

/**
 * Created by Vrungel on 26.01.2017.
 */

public final class Constants {
  public class Remote {
    private static final String DOMEN = "beauty.api.floor12apps.com";
    // TODO: do not forget to paste base url
    public static final String BASE_URL = "http://" + DOMEN + "/";
  }

  public class FragmentTag {
    public static final String MAIN_FRAGMENT = "MainFragment";
    public static final String BOOKING_SERVICES_FRAGMENT = "BookingServicesFragment";
    public static final String BOOKING_MASTERS_FRAGMENT = "BookingMastersFragment";
    public static final String MY_BOOK_FRAGMENT = "MyBookFragment";
    public static final String MY_BONUS_FRAGMENT = "MyBonusFragment";
    public static final String OUR_WORK_FRAGMENT = "OurWorkFragment";
    public static final String CONTACTS_FRAGMENT = "ContactsFragment";
    public static final String ALL_NEWS_FRAGMENT = "AllNewsFragment";
  }

  public class FragmentsArgumentKeys {
    public static final String NEWS_DETAIL_KEY = "NEWS_DETAIL_KEY";
    public static final String OUR_ENTITY_KEY = "OUR_ENTITY_KEY";
    public static final String BONUS_REGISTRATION_KEY = "BONUS_REGISTRATION_KEY";
    public static final String SERVICE_NAME = "SERVICE_NAME";
    public static final String BOOKING_SCREEN_TO_START = "BOOKING_SCREEN_TO_START";
    public static final String MASTER_NAME = "MASTER_NAME";
    public static final String SERVICE_ID = "SERVICE_ID";
    public static final String ENTITY_ID = "ENTITY_ID";
    public static final String CHANGING_FIELD = "CHANGING_FIELD";
    public static final String CHANGING_FIELD_VALUE = "CHANGING_FIELD_VALUE";
  }

  public class FragmentToShow {
    public static final String BONUS = "BONUS";
    public static final String REGISTRATION = "REGISTRATION";

    public static final String CHOOSE_MASTER = "MASTER";
    public static final String CHOOSE_SERVICE = "SERVICE";
  }

  public class ChangingUserInfoField {
    public static final int NAME = 0;
    public static final int LOGIN = 1;
    public static final int PASSWORD = 2;
    public static final int EMAIL = 3;
    public static final int PHONE = 4;
  }

  public class BookingMode {
    public static final int START_WITH_MASTER = 1;
    public static final int START_WITH_SERVICE = 2;
  }
}
