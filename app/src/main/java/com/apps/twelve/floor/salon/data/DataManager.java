package com.apps.twelve.floor.salon.data;

import com.apps.twelve.floor.authorization.AuthorizationManager;
import com.apps.twelve.floor.salon.data.local.PreferencesHelper;
import com.apps.twelve.floor.salon.data.model.BonusEntity;
import com.apps.twelve.floor.salon.data.model.BonusHistoryEntity;
import com.apps.twelve.floor.salon.data.model.BookingServerEntity;
import com.apps.twelve.floor.salon.data.model.CategoryEntity;
import com.apps.twelve.floor.salon.data.model.DataServiceEntity;
import com.apps.twelve.floor.salon.data.model.GoodsEntity;
import com.apps.twelve.floor.salon.data.model.LastBookingEntity;
import com.apps.twelve.floor.salon.data.model.MasterEntity;
import com.apps.twelve.floor.salon.data.model.NewsEntity;
import com.apps.twelve.floor.salon.data.model.OurWorkEntity;
import com.apps.twelve.floor.salon.data.model.PhotoWorksEntity;
import com.apps.twelve.floor.salon.data.model.ReportProblemResponseEntity;
import com.apps.twelve.floor.salon.data.model.ServiceEntity;
import com.apps.twelve.floor.salon.data.model.category.GoodsCategoryEntity;
import com.apps.twelve.floor.salon.data.remote.RestApi;
import java.util.List;
import retrofit2.Response;
import rx.Observable;

import static com.apps.twelve.floor.salon.utils.Constants.Remote.LOCAL;

/**
 * Created by Vrungel on 26.01.2017.
 */

public class DataManager {

  private RestApi mRestApi;
  private PreferencesHelper mPref;
  private AuthorizationManager mAuthorizationManager;

  private String mLanguage = LOCAL; // = Locale.getDefault().getLanguage();

  public DataManager(RestApi restApi, PreferencesHelper preferencesHelper,
      AuthorizationManager authorizationManager) {
    this.mRestApi = restApi;
    this.mPref = preferencesHelper;
    this.mAuthorizationManager = authorizationManager;
  }

  //checkin service.

  public Observable<Response<List<CategoryEntity>>> fetchCategory() {
    return mRestApi.fetchCategory(mLanguage);
  }

  public Observable<Response<List<ServiceEntity>>> fetchAllServices() {
    return mRestApi.fetchAllServices(mLanguage);
  }

  public Observable<Response<List<ServiceEntity>>> fetchAllServicesByMasterId(String masterId) {
    return mRestApi.fetchAllServicesByMasterId(mLanguage, masterId);
  }

  public Observable<Response<List<ServiceEntity>>> fetchServicesOfCategoryWithId(int id) {
    return mRestApi.fetchServicesOfCategoryWithId(mLanguage, id);
  }

  public Observable<Response<List<CategoryEntity>>> fetchCategoriesOfCategoryWithId(int parentId) {
    return mRestApi.fetchCategoriesOfCategoryWithId(mLanguage, parentId);
  }

  public Observable<Response<List<DataServiceEntity>>> fetchDaysData(String serviceId) {
    return mRestApi.fetchDaysData(mLanguage, serviceId);
  }

  public Observable<Response<List<MasterEntity>>> fetchMasters(String serviceId, String dataID) {
    return mRestApi.fetchMasters(mLanguage, serviceId, dataID);
  }

  public Observable<Response<List<MasterEntity>>> fetchAllMasters() {
    return mRestApi.fetchAllMasters(mLanguage);
  }

  public Observable<Response<List<DataServiceEntity>>> fetchDaysDataWithMasterId(String masterId) {
    return mRestApi.fetchDaysDataWithMasterId(mLanguage, masterId);
  }

  public Observable<retrofit2.Response<LastBookingEntity>> checkInService(
      BookingServerEntity bookingServerEntity) {
    return mRestApi.checkInService(mLanguage, mAuthorizationManager.getToken(),
        bookingServerEntity);
  }

  //bonus

  public Observable<Response<BonusEntity>> fetchBonusCount() {
    return mRestApi.fetchBonusCount(mLanguage, mAuthorizationManager.getToken());
  }

  public Observable<Response<List<BonusHistoryEntity>>> fetchBonusHistory() {
    return mRestApi.fetchBonusHistory(mLanguage, mAuthorizationManager.getToken());
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

  public void setThemeSelected(int themeSelected) {
    mPref.setThemeSelected(themeSelected);
  }

  public int getThemeSelected() {
    return mPref.getThemeSelected();
  }

  //main screen

  public Observable<Response<List<LastBookingEntity>>> fetchLastBooking() {
    return mRestApi.fetchLastBooking(mLanguage, mAuthorizationManager.getToken());
  }

  public Observable<retrofit2.Response<Void>> cancelOrder(Integer serviceId) {
    return mRestApi.cancelOrder(mLanguage, serviceId, mAuthorizationManager.getToken());
  }

  public Observable<Response<Void>> postponeService(String entryId, int scheduleId) {
    return mRestApi.postponeService(mLanguage, entryId, mAuthorizationManager.getToken(),
        scheduleId);
  }

  //ourWorks

  public Observable<Response<List<OurWorkEntity>>> fetchListOfWorks() {
    return mRestApi.fetchListOfWorks(mLanguage);
  }

  public Observable<Response<List<OurWorkEntity>>> fetchListOfWorksAuth() {
    return mRestApi.fetchListOfWorksAuth(mLanguage, mAuthorizationManager.getToken());
  }

  public Observable<Response<List<PhotoWorksEntity>>> fetchFavoritePhotos() {
    return mRestApi.fetchFavoritePhotos(mLanguage, mAuthorizationManager.getToken());
  }

  //like/dislike ourWork photo

  public Observable<Response<Void>> addToFavoritePhoto(int photoId) {
    return mRestApi.addToFavoritePhoto(mLanguage, photoId, mAuthorizationManager.getToken());
  }

  public Observable<Response<Void>> removeFromFavoritePhoto(int photoId) {
    return mRestApi.removeFromFavoritePhoto(mLanguage, photoId, mAuthorizationManager.getToken());
  }

  //News

  public Observable<Response<List<NewsEntity>>> fetchAllNews() {
    return mRestApi.fetchAllNews(mLanguage);
  }

  //Goods

  public Observable<Response<List<GoodsEntity>>> fetchAllProducts() {
    return mRestApi.fetchAllProducts(mLanguage, mAuthorizationManager.getToken());
  }

  public Observable<Response<List<GoodsEntity>>> fetchFavoriteGoods() {
    return mRestApi.fetchFavoriteGoods(mLanguage, mAuthorizationManager.getToken());
  }

  public Observable<Response<List<GoodsEntity>>> fetchGoodsByCatalogId(Integer id) {
    return mRestApi.fetchGoodsByCatalogId(mLanguage, id);
  }

  public Observable<Response<List<GoodsCategoryEntity>>> fetchCategories() {
    return mRestApi.fetchCategories(mLanguage);
  }

  //like/dislike goods

  public Observable<Response<Void>> addToFavoriteGoods(int goodsId) {
    return mRestApi.addToFavoriteGoods(mLanguage, goodsId, mAuthorizationManager.getToken());
  }

  public Observable<Response<Void>> removeFromFavoriteGoods(int goodsId) {
    return mRestApi.removeFromFavoriteGoods(mLanguage, goodsId, mAuthorizationManager.getToken());
  }

  //Notification

  public boolean isHourlyNotificationsEnabled() {
    return mPref.isHourlyNotificationsEnabled();
  }

  public void setHourlyNotificationsEnabled(boolean enabled) {
    mPref.setHourlyNotificationsEnabled(enabled);
  }

  public boolean isDailyNotificationsEnabled() {
    return mPref.isDailyNotificationsEnabled();
  }

  public void setDailyNotificationsEnabled(boolean enabled) {
    mPref.setDailyNotificationsEnabled(enabled);
  }

  public int getNotificationDays() {
    return mPref.getNotificationDays();
  }

  public void setNotificationDays(int days) {
    mPref.setNotificationDays(days);
  }

  public long getNotificationHours() {
    return mPref.getNotificationHours();
  }

  public void setNotificationHours(long millis) {
    mPref.setNotificationHours(millis);
  }

  public void setNotificationHoursNightStart(long millis) {
    mPref.setNotificationHoursNightStart(millis);
  }

  public void setNotificationHoursNightEnd(long millis) {
    mPref.setNotificationHoursNightEnd(millis);
  }

  public long getNotificationHoursNightStart() {
    return mPref.getNotificationHoursNightStart();
  }

  public long getNotificationHoursNightEnd() {
    return mPref.getNotificationHoursNightEnd();
  }

  public boolean isNightMode() {
    return mPref.isNightMode();
  }

  public void setNightMode(boolean enabled) {
    mPref.setNightMode(enabled);
  }

  //Cache
  public void putBooking(List<LastBookingEntity> bookingEntities) {
    mPref.putBooking(bookingEntities);
  }

  public List<LastBookingEntity> getBooking() {
    return mPref.getBooking();
  }

  //Report problen
  public Observable<Response<ReportProblemResponseEntity>> sendReportProblem(String problemBody){
    return mRestApi.sendReportProblem(problemBody, mAuthorizationManager.getToken());
  }

  //user

  public void setLastPhoneForBooking(String lastPhone) {
    mPref.setLastPhoneForBooking(lastPhone);
  }

  public String getLastPhoneForBooking() {
    return mPref.getLastPhoneForBooking();
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
}

