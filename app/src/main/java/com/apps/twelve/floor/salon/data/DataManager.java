package com.apps.twelve.floor.salon.data;

import com.apps.twelve.floor.salon.data.local.PreferencesHelper;
import com.apps.twelve.floor.salon.data.model.BookingServerEntity;
import com.apps.twelve.floor.salon.data.model.CategoryEntity;
import com.apps.twelve.floor.salon.data.model.DataServiceEntity;
import com.apps.twelve.floor.salon.data.model.GoodsEntity;
import com.apps.twelve.floor.salon.data.model.LastBookingEntity;
import com.apps.twelve.floor.salon.data.model.MasterEntity;
import com.apps.twelve.floor.salon.data.model.NewsEntity;
import com.apps.twelve.floor.salon.data.model.OurWorkEntity;
import com.apps.twelve.floor.salon.data.model.ServiceEntity;
import com.apps.twelve.floor.salon.data.model.category.GoodsCategoryEntity;
import com.apps.twelve.floor.salon.data.remote.RestApi;
import com.authorization.floor12.authorization.AuthorizationManager;
import java.util.List;
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

  public Observable<List<CategoryEntity>> fetchCategory() {
    return mRestApi.fetchCategory();
  }

  public Observable<List<ServiceEntity>> fetchAllServices() {
    return mRestApi.fetchAllServices();
  }

  public Observable<List<ServiceEntity>> fetchAllServicesByMasterId(String masterId) {
    return mRestApi.fetchAllServicesByMasterId(masterId);
  }

  public Observable<List<ServiceEntity>> fetchServicesOfCategoryWithId(int id) {
    return mRestApi.fetchServicesOfCategoryWithId(id);
  }

  public Observable<List<CategoryEntity>> fetchCategoriesOfCategoryWithId(int parentId) {
    return mRestApi.fetchCategoriesOfCategoryWithId(parentId);
  }

  public Observable<List<DataServiceEntity>> fetchDaysData(String serviceId) {
    return mRestApi.fetchDaysData(serviceId);
  }

  public Observable<List<MasterEntity>> fetchMasters(String serviceId, String dataID) {
    return mRestApi.fetchMasters(serviceId, dataID);
  }

  public Observable<List<MasterEntity>> fetchAllMasters() {
    return mRestApi.fetchAllMasters();
  }

  public Observable<List<DataServiceEntity>> fetchDaysDataWithMasterId(String masterId) {
    return mRestApi.fetchDaysDataWithMasterId(masterId);
  }

  public Observable<Integer> fetchBonusCount() {
    return Observable.just(100);
  }

  public void setBonusCount(int bonusCount) {
    mPref.setBonusCount(bonusCount);
  }

  public int getBonusCountInt() {
    return mPref.getBonusCounInt();
  }

  public Observable<Integer> getBonusCountObservable() {
    return mPref.getBonusCounObservable();
  }

  public Observable<String> getProfileImage() {
    return mPref.getProfileImage().filter((s) -> !s.isEmpty());
  }

  public void setProfileImage(String uri) {
    mPref.setProfileImage(uri);
  }

  public void setThemeSelected(int themeSelected) {
    mPref.setThemeSelected(themeSelected);
  }

  public int getThemeSelected() {
    return mPref.getThemeSelected();
  }

  public Observable<String> getProfileName() {
    return mPref.getProfileName();
  }

  public void setProfileName(String name) {
    mPref.setProfileName(name);
  }

  public Observable<String> getProfileLogin() {
    return mPref.getProfileLogin();
  }

  public void setProfileLogin(String login) {
    mPref.setProfileLogin(login);
  }

  public Observable<String> getProfilePassword() {
    return mPref.getProfilePassword();
  }

  public void setProfilePassword(String password) {
    mPref.setProfilePassword(password);
  }

  public Observable<String> getProfileEmail() {
    return mPref.getProfileEmail();
  }

  public void setProfileEmail(String email) {
    mPref.setProfileEmail(email);
  }

  public Observable<String> getProfilePhone() {
    return mPref.getProfilePhone();
  }

  public void setProfilePhone(String phone) {
    mPref.setProfilePhone(phone);
  }

  public Observable<Integer> getProfileGender() {
    return mPref.getProfileGender();
  }

  public void setProfileGender(int gender) {
    mPref.setProfileGender(gender);
  }

  public Observable<retrofit2.Response<LastBookingEntity>> checkInService(
      BookingServerEntity bookingServerEntity) {
    return mRestApi.checkInService(mPref.getToken(), bookingServerEntity);
  }

  public Observable<List<LastBookingEntity>> fetchLastBooking() {
    return mRestApi.fetchLastBooking(mPref.getToken());
  }

  public Observable<retrofit2.Response<Void>> cancelOrder(Integer serviceId) {
    return mRestApi.cancelOrder(serviceId, mPref.getToken());
  }

  public Observable<Response<Void>> postponeService(String entryId, int scheduleId) {
    return mRestApi.postponeService(entryId, mPref.getToken(), scheduleId);
  }

  public Observable<List<OurWorkEntity>> fetchListOfWorks() {
    return mRestApi.fetchListOfWorks(mPref.getToken());
  }

  public Observable<NewsEntity> fetchNewsPreview() {
    return mRestApi.fetchNewsPreview();
  }

  public Observable<List<NewsEntity>> fetchAllNews() {
    return mRestApi.fetchAllNews();
  }

  public Observable<Response<Void>> addToFavoritePhoto(int photoId) {
    return mRestApi.addToFavoritePhoto(photoId, mPref.getToken());
  }

  public Observable<Response<Void>> removeFromFavoritePhoto(int photoId) {
    return mRestApi.removeFromFavoritePhoto(photoId, mPref.getToken());
  }

  public Observable<Integer> addToFavoriteGoods(int goodsId) {
    return Observable.just(200);
  }

  public Observable<Integer> removeFromFavoriteGoods(int goodsId) {
    return Observable.just(200);
  }

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

  public Observable<List<GoodsEntity>> fetchGoods() {
    return mRestApi.fetchAllProducts();
  }

  public Observable<List<GoodsCategoryEntity>> fetchCategories() {
    return mRestApi.fetchCategories();
  }

  public Observable<List<GoodsEntity>> fetchGoodsByCatalogId(Integer id) {
    return mRestApi.fetchGoodsByCatalogId(id);
  }

  public boolean isAuthorized() {
    return mAuthorizationManager.isAuthorized();
  }

  public Observable<String> getUserPhoto() {
    return mAuthorizationManager.getObsevableUserPhoto();
  }
}
