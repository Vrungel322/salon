package com.apps.twelve.floor.salon.utils;

/**
 * Created by Vrungel on 26.01.2017.
 */

public final class Constants {
  public class Remote {
    private static final String DOMEN = "beauty.api.floor12apps.com";
    public static final String BASE_URL = "https://" + DOMEN + "/";
  }

  public class FragmentTag {
    public static final String MAIN_FRAGMENT = "MainFragment";
    public static final String BOOKING_DETAIL_MASTER_FRAGMENT = "BookingDetailMasterFragment";
    public static final String BOOKING_DETAIL_SERVICE_FRAGMENT = "BookingDetailServiceFragment";
    public static final String MY_BOOK_FRAGMENT = "MyBookFragment";
    public static final String MY_BONUS_FRAGMENT = "MyBonusFragment";
    public static final String BONUS_HOW_WORKS_FRAGMENT = "BonusHowWorksFragment";
    public static final String OUR_WORK_FRAGMENT = "OurWorkFragment";
    public static final String CONTACTS_FRAGMENT = "ContactsFragment";
    public static final String CONTACTS_ABOUT_FRAGMENT = "ContactsAboutFragment";
    public static final String ALL_NEWS_FRAGMENT = "AllNewsFragment";
    public static final String CATALOG_FRAGMENT = "CatalogFragment";
    public static final String SUB_BOOKING_FRAGMENT = "SubBookingFragment";

    public static final String CHOOSE_MASTER_MASTER_FRAGMENT = "ChooseMasterMasterFragment";
    public static final String CHOOSE_MASTER_SERVICE_FRAGMENT = "ChooseMasterServiceFragment";
    public static final String CHOOSE_MASTER_TIME_FRAGMENT = "ChooseMasterTimeFragment";
    public static final String CHOOSE_MASTER_CONTACT_FRAGMENT = "ChooseMasterContactFragment";

    public static final String CHOOSE_SERVICE_MASTER_FRAGMENT = "ChooseServiceMasterFragment";
    public static final String CHOOSE_SERVICE_SERVICE_FRAGMENT = "ChooseServiceServiceFragment";
    public static final String CHOOSE_SERVICE_TIME_FRAGMENT = "ChooseServiceTimeFragment";
    public static final String CHOOSE_SERVICE_CONTACT_FRAGMENT = "ChooseServiceContactFragment";
  }

  public class FragmentsArgumentKeys {
    public static final String NEWS_DETAIL_KEY = "NEWS_DETAIL_KEY";
    public static final String ALL_NEWS_DETAIL_KEY = "ALL_NEWS_DETAIL_KEY";
    public static final String POSITION = "POSITION";
    public static final String OUR_ENTITY_KEY = "OUR_ENTITY_KEY";
    public static final String GOODS_ENTITY_KEY = "GOODS_ENTITY_KEY";
    public static final String BONUS_REGISTRATION_KEY = "BONUS_REGISTRATION_KEY";
    public static final String SERVICE_NAME = "SERVICE_NAME";
    public static final String MASTER_NAME = "MASTER_NAME";
    public static final String MASTER_ID = "MASTER_ID";
    public static final String ENTITY_ID = "ENTITY_ID";
    public static final String CHANGING_FIELD = "CHANGING_FIELD";
    public static final String CHANGING_FIELD_VALUE = "CHANGING_FIELD_VALUE";
  }

  public class Notifications {
    public static final String NOTIFICATION_TYPE = "NOTIFICATION_TYPE";
    public static final String DAILY = "DAILY";
    public static final String HOURLY = "HOURLY";
    public static final String SERVICE = "SERVICE";
    public static final String DATE = "DATE";
    public static final String TIME = "TIME";
  }

  public class StatusCode {
    public static final int RESPONSE_200 = 200;
    public static final int RESPONSE_204 = 204;
    public static final int RESPONSE_400 = 400;
    public static final int RESPONSE_404 = 404;
  }

  public class Theme {
    public static final int PINK = 0;
    public static final int BLUE = 1;
    public static final int GREEN = 2;
    public static final int YELLOW = 3;
    public static final int GRAY = 4;
    public static final int PURPLE = 5;
    public static final int RED = 6;
  }

  public class Rotation {
    public static final int PORTRAIT = 2;
    public static final int LANDSCAPE = 3;
  }

  public class Other {
    public static final String MASTER_MALE_PLACEHOLDER = "male";
    public static final String SERVER_ANSWER_EMPTY_STRING = "---";
  }
}
