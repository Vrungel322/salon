package com.apps.twelve.floor.salon.utils;

/**
 * Created by Vrungel on 26.01.2017.
 */

public final class Constants {
  public class Remote {
    private static final String DOMEN = "beauty.api.floor12apps.com";
    // TODO: do not forget to paste base url
    public static final String BASE_URL = "https://" + DOMEN + "/";
  }

  public class FragmentTag {
    public static final String MAIN_FRAGMENT = "MainFragment";
    public static final String BOOKING_DETAIL_MASTER_FRAGMENT = "BookingDetailMasterFragment";
    public static final String BOOKING_DETAIL_SERVICE_FRAGMENT = "BookingDetailServiceFragment";
    public static final String MY_BOOK_FRAGMENT = "MyBookFragment";
    public static final String MY_BONUS_FRAGMENT = "MyBonusFragment";
    public static final String OUR_WORK_FRAGMENT = "OurWorkFragment";
    public static final String CONTACTS_FRAGMENT = "ContactsFragment";
    public static final String ALL_NEWS_FRAGMENT = "AllNewsFragment";
    public static final String CATALOG_FRAGMENT = "CatalogFragment";

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
  }

  public class ChangingUserInfoField {
    public static final int NAME = 0;
    public static final int LOGIN = 1;
    public static final int PASSWORD = 2;
    public static final int EMAIL = 3;
    public static final int PHONE = 4;
  }
}
