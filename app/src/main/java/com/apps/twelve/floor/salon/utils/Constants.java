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
    public static final String BOOKING_FRAGMENT = "BookingFragment";
    public static final String MY_BOOK_FRAGMENT = "MyBookFragment";
    public static final String MY_BONUS_FRAGMENT = "MyBonusFragment";
    public static final String OUR_WORK_FRAGMENT = "OurWorkFragment";
    public static final String CONTACTS_FRAGMENT = "ContactsFragment";
  }

  public class FragmentsArgumentKeys {
    public static final String NEWS_DETAIL_KEY = "NEWS_DETAIL_KEY";
    public static final String OUR_ENTITY_KEY = "OUR_ENTITY_KEY";
    public static final String BONUS_REGISTRATION_KEY = "BONUS_REGISTRATION_KEY";
    public static final String SERVICE_NAME = "SERVICE_NAME";
  }

  public class FragmentToShow {
    public static final String BONUS = "BONUS";
    public static final String REGISTRATION = "REGISTRATION";
  }
}
