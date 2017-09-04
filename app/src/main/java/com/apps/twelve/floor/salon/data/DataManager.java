package com.apps.twelve.floor.salon.data;

import com.apps.twelve.floor.authorization.AuthorizationManager;
import com.apps.twelve.floor.salon.data.local.DbHelper;
import com.apps.twelve.floor.salon.data.local.PreferencesHelper;
import com.apps.twelve.floor.salon.data.model.BonusEntity;
import com.apps.twelve.floor.salon.data.model.BonusHistoryEntity;
import com.apps.twelve.floor.salon.data.model.BookingServerEntity;
import com.apps.twelve.floor.salon.data.model.CategoryEntity;
import com.apps.twelve.floor.salon.data.model.DataServiceEntity;
import com.apps.twelve.floor.salon.data.model.GoodsEntity;
import com.apps.twelve.floor.salon.data.model.MasterEntity;
import com.apps.twelve.floor.salon.data.model.NewsEntity;
import com.apps.twelve.floor.salon.data.model.OurWorkEntity;
import com.apps.twelve.floor.salon.data.model.PartnerEntity;
import com.apps.twelve.floor.salon.data.model.PhotoWorksEntity;
import com.apps.twelve.floor.salon.data.model.RemoteStringEntity;
import com.apps.twelve.floor.salon.data.model.ReportProblemResponseEntity;
import com.apps.twelve.floor.salon.data.model.ServiceEntity;
import com.apps.twelve.floor.salon.data.model.booking.LastBookingEntity;
import com.apps.twelve.floor.salon.data.model.category.GoodsCategoryEntity;
import com.apps.twelve.floor.salon.data.remote.RestApi;
import io.realm.RealmObject;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Response;
import rx.Observable;

import static com.apps.twelve.floor.salon.data.local.PreferencesHelper.PREF_LAST_PHONE_FOR_BOOKING;
import static com.apps.twelve.floor.salon.data.local.PreferencesHelper.PREF_NOTIF_DAILY_ENABLED;
import static com.apps.twelve.floor.salon.data.local.PreferencesHelper.PREF_NOTIF_DAYS;
import static com.apps.twelve.floor.salon.data.local.PreferencesHelper.PREF_NOTIF_HOURLY_ENABLED;
import static com.apps.twelve.floor.salon.data.local.PreferencesHelper.PREF_NOTIF_HOURS;
import static com.apps.twelve.floor.salon.data.local.PreferencesHelper.PREF_NOTIF_HOURS_NIGHT_END;
import static com.apps.twelve.floor.salon.data.local.PreferencesHelper.PREF_NOTIF_HOURS_NIGHT_START;
import static com.apps.twelve.floor.salon.data.local.PreferencesHelper.PREF_NOTIF_NIGHT_MODE;

/**
 * Created by Vrungel on 26.01.2017.
 */

public class DataManager {

  private DbHelper mDbHelper;
  private RestApi mRestApi;
  private PreferencesHelper mPref;
  private AuthorizationManager mAuthorizationManager;

  public DataManager(RestApi restApi, PreferencesHelper preferencesHelper,
      AuthorizationManager authorizationManager, DbHelper dbHelper) {
    this.mRestApi = restApi;
    this.mPref = preferencesHelper;
    this.mAuthorizationManager = authorizationManager;
    this.mDbHelper = dbHelper;
  }

  //checkin service.

  public Observable<Response<List<CategoryEntity>>> fetchCategory() {
    return mRestApi.fetchCategory(mPref.getLanguageCode());
  }

  public Observable<Response<List<ServiceEntity>>> fetchAllServices() {
    return mRestApi.fetchAllServices(mPref.getLanguageCode());
  }

  public Observable<Response<List<ServiceEntity>>> fetchAllServicesByMasterId(String masterId) {
    return mRestApi.fetchAllServicesByMasterId(mPref.getLanguageCode(), masterId);
  }

  public Observable<Response<List<ServiceEntity>>> fetchServicesOfCategoryWithId(int id) {
    return mRestApi.fetchServicesOfCategoryWithId(mPref.getLanguageCode(), id);
  }

  public Observable<Response<List<CategoryEntity>>> fetchCategoriesOfCategoryWithId(int parentId) {
    return mRestApi.fetchCategoriesOfCategoryWithId(mPref.getLanguageCode(), parentId);
  }

  public Observable<Response<List<DataServiceEntity>>> fetchDaysData(String serviceId) {
    return mRestApi.fetchDaysData(mPref.getLanguageCode(), serviceId);
  }

  public Observable<Response<List<MasterEntity>>> fetchMasters(String serviceId, String dataID) {
    return mRestApi.fetchMasters(mPref.getLanguageCode(), serviceId, dataID);
  }

  public Observable<Response<List<MasterEntity>>> fetchAllMasters() {
    return mRestApi.fetchAllMasters(mPref.getLanguageCode());
  }

  public Observable<Response<List<DataServiceEntity>>> fetchDaysDataWithMasterId(String masterId) {
    return mRestApi.fetchDaysDataWithMasterId(mPref.getLanguageCode(), masterId);
  }

  public Observable<retrofit2.Response<LastBookingEntity>> checkInService(
      BookingServerEntity bookingServerEntity) {
    return mRestApi.checkInService(mPref.getLanguageCode(), mAuthorizationManager.getToken(),
        bookingServerEntity);
  }

  //bonus

  public Observable<Response<BonusEntity>> fetchBonusCount() {
    return mRestApi.fetchBonusCount(mPref.getLanguageCode(), mAuthorizationManager.getToken());
  }

  public Observable<Response<List<BonusHistoryEntity>>> fetchBonusHistory() {
    return mRestApi.fetchBonusHistory(mPref.getLanguageCode(), mAuthorizationManager.getToken());
  }

  public void setBonusCount(int bonusCount) {
    mPref.setBonusCount(bonusCount);
  }

  public int getBonusCountInt() {
    return mPref.getBonusCountInt();
  }

  public Observable<Integer> getBonusCountObservable() {
    return mPref.getBonusCountObservable();
  }

  //theme

  public int getThemeSelected() {
    return mPref.getThemeSelected();
  }

  public void setThemeSelected(int themeSelected) {
    mPref.setThemeSelected(themeSelected);
  }

  //main screen

  public Observable<Response<List<LastBookingEntity>>> fetchLastBooking() {
    return mRestApi.fetchLastBooking(mPref.getLanguageCode(), mAuthorizationManager.getToken());
  }

  public Observable<Response<List<LastBookingEntity>>> fetchLastBookingHistory() {
    return mRestApi.fetchLastBookingHistory(mPref.getLanguageCode(),
        mAuthorizationManager.getToken());
  }

  public Observable<retrofit2.Response<Void>> cancelOrder(Integer serviceId) {
    return mRestApi.cancelOrder(mPref.getLanguageCode(), serviceId,
        mAuthorizationManager.getToken());
  }

  public Observable<Response<Void>> postponeService(String entryId, int scheduleId) {
    return mRestApi.postponeService(mPref.getLanguageCode(), entryId,
        mAuthorizationManager.getToken(), scheduleId);
  }

  //ourWorks

  public Observable<Response<List<OurWorkEntity>>> fetchListOfWorks() {
    return mRestApi.fetchListOfWorks(mPref.getLanguageCode());
  }

  public Observable<Response<List<OurWorkEntity>>> fetchListOfWorksAuth() {
    return mRestApi.fetchListOfWorksAuth(mPref.getLanguageCode(), mAuthorizationManager.getToken());
  }

  public Observable<Response<List<PhotoWorksEntity>>> fetchFavoritePhotos() {
    return mRestApi.fetchFavoritePhotos(mPref.getLanguageCode(), mAuthorizationManager.getToken());
  }

  //like/dislike ourWork photo

  public Observable<Response<Void>> addToFavoritePhoto(int photoId) {
    return mRestApi.addToFavoritePhoto(mPref.getLanguageCode(), photoId,
        mAuthorizationManager.getToken());
  }

  public Observable<Response<Void>> removeFromFavoritePhoto(int photoId) {
    return mRestApi.removeFromFavoritePhoto(mPref.getLanguageCode(), photoId,
        mAuthorizationManager.getToken());
  }

  //News

  public Observable<Response<List<NewsEntity>>> fetchAllNews() {
    return mRestApi.fetchAllNews(mPref.getLanguageCode());
  }

  //Goods

  public Observable<Response<List<GoodsEntity>>> fetchAllProducts() {
    return mRestApi.fetchAllProducts(mPref.getLanguageCode(), mAuthorizationManager.getToken());
  }

  public Observable<Response<List<GoodsEntity>>> fetchFavoriteGoods() {
    return mRestApi.fetchFavoriteGoods(mPref.getLanguageCode(), mAuthorizationManager.getToken());
  }

  public Observable<Response<List<GoodsEntity>>> fetchGoodsByCatalogId(Integer id) {
    return mRestApi.fetchGoodsByCatalogId(mPref.getLanguageCode(), id);
  }

  public Observable<Response<List<GoodsCategoryEntity>>> fetchCategories() {
    return mRestApi.fetchCategories(mPref.getLanguageCode());
  }

  //like/dislike goods

  public Observable<Response<Void>> addToFavoriteGoods(int goodsId) {
    return mRestApi.addToFavoriteGoods(mPref.getLanguageCode(), goodsId,
        mAuthorizationManager.getToken());
  }

  public Observable<Response<Void>> removeFromFavoriteGoods(int goodsId) {
    return mRestApi.removeFromFavoriteGoods(mPref.getLanguageCode(), goodsId,
        mAuthorizationManager.getToken());
  }

  //Partners
  public Observable<List<PartnerEntity>> fetchListOfPartners() {
    return mRestApi.fetchListOfPartners(mPref.getLanguageCode());
  }

  //Notification

  public boolean isHourlyNotificationsEnabled() {
    return mAuthorizationManager.getAdditionalField(PREF_NOTIF_HOURLY_ENABLED, true);
  }

  public void setHourlyNotificationsEnabled(boolean enabled) {
    mPref.setHourlyNotificationsEnabled(enabled);
  }

  public boolean isDailyNotificationsEnabled() {
    return mAuthorizationManager.getAdditionalField(PREF_NOTIF_DAILY_ENABLED, true);
  }

  public void setDailyNotificationsEnabled(boolean enabled) {
    mPref.setDailyNotificationsEnabled(enabled);
  }

  public int getNotificationDays() {
    return (int) mAuthorizationManager.getAdditionalField(PREF_NOTIF_DAYS, 1);
  }

  public void setNotificationDays(int days) {
    mPref.setNotificationDays(days);
  }

  public long getNotificationHours() {
    return mAuthorizationManager.getAdditionalField(PREF_NOTIF_HOURS, 3600000);
  }

  public void setNotificationHours(long millis) {
    mPref.setNotificationHours(millis);
  }

  public long getNotificationHoursNightStart() {
    return mAuthorizationManager.getAdditionalField(PREF_NOTIF_HOURS_NIGHT_START, 82800000);
  }

  public void setNotificationHoursNightStart(long millis) {
    mPref.setNotificationHoursNightStart(millis);
  }

  public long getNotificationHoursNightEnd() {
    return mAuthorizationManager.getAdditionalField(PREF_NOTIF_HOURS_NIGHT_END, 25200000);
  }

  public void setNotificationHoursNightEnd(long millis) {
    mPref.setNotificationHoursNightEnd(millis);
  }

  public boolean isNightMode() {
    return mAuthorizationManager.getAdditionalField(PREF_NOTIF_NIGHT_MODE, true);
  }

  public void setNightMode(boolean enabled) {
    mPref.setNightMode(enabled);
  }

  //Cache
  @Deprecated public void putBooking(List<LastBookingEntity> bookingEntities) {
    mPref.putEntityToSHP(PreferencesHelper.PREF_BOOKING, bookingEntities);
  }

  @Deprecated public List<LastBookingEntity> getBooking() {
    return mPref.getBooking();
  }

  //Report problen
  public Observable<Response<ReportProblemResponseEntity>> sendReportProblem(String problemBody) {
    return mRestApi.sendReportProblem(problemBody, mAuthorizationManager.getToken());
  }

  //user

  public String getLastPhoneForBooking() {
    return mAuthorizationManager.getAdditionalField(PREF_LAST_PHONE_FOR_BOOKING,
        mPref.getLastPhoneForBooking());
  }

  public void setLastPhoneForBooking(String lastPhone) {
    mPref.setLastPhoneForBooking(lastPhone);
  }

  public void logoutUser() {
    mPref.logoutUser();
    mAuthorizationManager.clear();
  }

  //language
  public String getSelectedLanguage() {
    return mPref.getSelectedLanguage();
  }

  public void setSelectedLanguage(String selectedLanguage) {
    mPref.setSelectedLanguage(selectedLanguage);
  }

  public Observable<Response<Void>> sendFriendsCode(String friendsCode) {
    return mRestApi.sendFriendsCode(friendsCode, mAuthorizationManager.getToken(),
        mPref.getLanguageCode());
  }

  //db
  public <T extends RealmObject> void saveObjToDb(T object) {
    mDbHelper.save(object);
  }

  public <T extends RealmObject> List<T> getAllElementsFromDB(Class<T> clazz) {
    return mDbHelper.getAll(clazz);
  }

  public <T extends RealmObject> List<T> getElementsFromDBByQuery(Class<T> clazz, String field,
      String value) {
    return mDbHelper.getElementsFromDBByQuery(clazz, field, value);
  }

  public <T extends RealmObject> T getElementById(Class<T> clazz, int id) {
    return mDbHelper.getElementById(clazz, id);
  }

  public <T extends RealmObject> void dropRealmTable(Class<T> clazz) {
    mDbHelper.dropRealmTable(clazz);
  }

  //remote strings
  public Observable<RemoteStringEntity> fetchString(String remoteStringType) {
    return mRestApi.fetchString(remoteStringType, mPref.getLanguageCode());
  }

  public void storeStringEntity(String bonus, String text) {
    mPref.putEntityToSHP(bonus, text);
  }

  public String getStringEntity(String bonus) {
    return mPref.getStringEntity(bonus);
  }

}

