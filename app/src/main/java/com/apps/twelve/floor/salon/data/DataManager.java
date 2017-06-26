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
import com.apps.twelve.floor.salon.data.model.ServiceEntity;
import com.apps.twelve.floor.salon.data.model.category.GoodsCategoryEntity;
import com.apps.twelve.floor.salon.data.remote.RestApi;
import java.util.List;
import java.util.Locale;
import retrofit2.Response;
import rx.Observable;

/**
 * Created by Vrungel on 26.01.2017.
 */

public class DataManager {

  private RestApi mRestApi;
  private PreferencesHelper mPref;
  private AuthorizationManager mAuthorizationManager;

  public DataManager(RestApi restApi, PreferencesHelper preferencesHelper,
      AuthorizationManager authorizationManager) {
    this.mRestApi = restApi;
    this.mPref = preferencesHelper;
    this.mAuthorizationManager = authorizationManager;
  }

  //checkin service.

  public Observable<List<CategoryEntity>> fetchCategory() {
    return mRestApi.fetchCategory(Locale.getDefault().getLanguage());
  }

  public Observable<List<ServiceEntity>> fetchAllServices() {
    return mRestApi.fetchAllServices(Locale.getDefault().getLanguage());
  }

  public Observable<List<ServiceEntity>> fetchAllServicesByMasterId(String masterId) {
    return mRestApi.fetchAllServicesByMasterId(Locale.getDefault().getLanguage(), masterId);
  }

  public Observable<List<ServiceEntity>> fetchServicesOfCategoryWithId(int id) {
    return mRestApi.fetchServicesOfCategoryWithId(Locale.getDefault().getLanguage(), id);
  }

  public Observable<List<CategoryEntity>> fetchCategoriesOfCategoryWithId(int parentId) {
    return mRestApi.fetchCategoriesOfCategoryWithId(Locale.getDefault().getLanguage(), parentId);
  }

  public Observable<List<DataServiceEntity>> fetchDaysData(String serviceId) {
    return mRestApi.fetchDaysData(Locale.getDefault().getLanguage(), serviceId);
  }

  public Observable<List<MasterEntity>> fetchMasters(String serviceId, String dataID) {
    return mRestApi.fetchMasters(Locale.getDefault().getLanguage(), serviceId, dataID);
  }

  public Observable<List<MasterEntity>> fetchAllMasters() {
    return mRestApi.fetchAllMasters(Locale.getDefault().getLanguage());
  }

  public Observable<List<DataServiceEntity>> fetchDaysDataWithMasterId(String masterId) {
    return mRestApi.fetchDaysDataWithMasterId(Locale.getDefault().getLanguage(), masterId);
  }

  public Observable<retrofit2.Response<LastBookingEntity>> checkInService(
      BookingServerEntity bookingServerEntity) {
    return mRestApi.checkInService(Locale.getDefault().getLanguage(),
        mAuthorizationManager.getToken(), bookingServerEntity);
  }

  //bonus

  public Observable<Response<BonusEntity>> fetchBonusCount() {
    return mRestApi.fetchBonusCount(Locale.getDefault().getLanguage(),
        mAuthorizationManager.getToken());
  }

  public Observable<Response<List<BonusHistoryEntity>>> fetchBonusHistory() {
    return mRestApi.fetchBonusHistory(Locale.getDefault().getLanguage(),
        mAuthorizationManager.getToken());
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
    return mRestApi.fetchLastBooking(Locale.getDefault().getLanguage(),
        mAuthorizationManager.getToken());
  }

  public Observable<retrofit2.Response<Void>> cancelOrder(Integer serviceId) {
    return mRestApi.cancelOrder(Locale.getDefault().getLanguage(), serviceId,
        mAuthorizationManager.getToken());
  }

  public Observable<Response<Void>> postponeService(String entryId, int scheduleId) {
    return mRestApi.postponeService(Locale.getDefault().getLanguage(), entryId,
        mAuthorizationManager.getToken(), scheduleId);
  }

  //ourWorks

  public Observable<List<OurWorkEntity>> fetchListOfWorks() {
    return mRestApi.fetchListOfWorks(Locale.getDefault().getLanguage());
  }

  public Observable<Response<List<OurWorkEntity>>> fetchListOfWorksAuth() {
    return mRestApi.fetchListOfWorksAuth(Locale.getDefault().getLanguage(),
        mAuthorizationManager.getToken());
  }

  public Observable<Response<List<PhotoWorksEntity>>> fetchFavoritePhotos() {
    return mRestApi.fetchFavoritePhotos(Locale.getDefault().getLanguage(),
        mAuthorizationManager.getToken());
  }

  //like/dislike ourWork photo

  public Observable<Response<Void>> addToFavoritePhoto(int photoId) {
    return mRestApi.addToFavoritePhoto(Locale.getDefault().getLanguage(), photoId,
        mAuthorizationManager.getToken());
  }

  public Observable<Response<Void>> removeFromFavoritePhoto(int photoId) {
    return mRestApi.removeFromFavoritePhoto(Locale.getDefault().getLanguage(), photoId,
        mAuthorizationManager.getToken());
  }

  //News

  public Observable<NewsEntity> fetchNewsPreview() {
    return mRestApi.fetchNewsPreview(Locale.getDefault().getLanguage());
  }

  public Observable<List<NewsEntity>> fetchAllNews() {
    return mRestApi.fetchAllNews(Locale.getDefault().getLanguage());
  }

  //Goods

  public Observable<List<GoodsEntity>> fetchGoods() {
    return mRestApi.fetchAllProducts(Locale.getDefault().getLanguage(),
        mAuthorizationManager.getToken());
  }

  public Observable<Response<List<GoodsEntity>>> fetchFavoriteGoods() {
    return mRestApi.fetchFavoriteGoods(Locale.getDefault().getLanguage(),
        mAuthorizationManager.getToken());
  }

  public Observable<List<GoodsEntity>> fetchGoodsByCatalogId(Integer id) {
    return mRestApi.fetchGoodsByCatalogId(Locale.getDefault().getLanguage(), id);
  }

  public Observable<List<GoodsCategoryEntity>> fetchCategories() {
    return mRestApi.fetchCategories(Locale.getDefault().getLanguage());
  }

  //like/dislike goods

  public Observable<Response<Void>> addToFavoriteGoods(int goodsId) {
    return mRestApi.addToFavoriteGoods(Locale.getDefault().getLanguage(), goodsId,
        mAuthorizationManager.getToken());
  }

  public Observable<Response<Void>> removeFromFavoriteGoods(int goodsId) {
    return mRestApi.removeFromFavoriteGoods(Locale.getDefault().getLanguage(), goodsId,
        mAuthorizationManager.getToken());
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

  //user

  public void logoutUser() {
    mPref.logoutUser();
    mAuthorizationManager.clear();
  }
}

