package com.apps.twelve.floor.salon.data;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import com.apps.twelve.floor.salon.data.local.PreferencesHelper;
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
import com.apps.twelve.floor.salon.utils.ThemeUtils;
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

  //---------checkin service.

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

  public Observable<retrofit2.Response<LastBookingEntity>> checkInService(
      BookingServerEntity bookingServerEntity) {
    return mRestApi.checkInService(mAuthorizationManager.getToken(), bookingServerEntity);
  }

  //---------bonus

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

  public Observable<String> getUserPhoto() {
    return mAuthorizationManager.getObsevableUserPhoto();
  }

  //---------settings

  public Observable<String> getProfileImage() {
    return mAuthorizationManager.getObsevableUserPhoto();
  }

  public void setProfileImage(String uri) {
    mAuthorizationManager.saveUserPhoto(uri);
  }

  public Observable<String> getProfileName() {
    return mAuthorizationManager.getObsevableUserName();
  }

  public void setProfileName(String name) {
    mAuthorizationManager.saveUserName(name);
  }

  public void updatePassword(String oldPassword, String newPassword) {
    mAuthorizationManager.changePassword(oldPassword, newPassword);
  }

  public Observable<String> getProfileEmail() {
    return mAuthorizationManager.getObsevableUserEmail();
  }

  public void setProfileEmail(String email) {
    mAuthorizationManager.saveUserEmail(email);
  }

  public Observable<String> getProfilePhone() {
    return mAuthorizationManager.getObsevableUserPhone();
  }

  public void setProfilePhone(String phone) {
    mAuthorizationManager.saveUserPhone(phone);
  }

  public Observable<String> getProfileGender() {
    return mAuthorizationManager.getObsevableUserGender();
  }

  public void setProfileGender(int gender) {
    mAuthorizationManager.saveUserGender(String.valueOf(gender));
  }

  //---------theme

  public void setThemeSelected(int themeSelected) {
    mPref.setThemeSelected(themeSelected);
  }

  public int getThemeSelected() {
    return mPref.getThemeSelected();
  }

  //---------main screen

  public Observable<List<LastBookingEntity>> fetchLastBooking() {
    return mRestApi.fetchLastBooking(mAuthorizationManager.getToken());
  }

  public Observable<retrofit2.Response<Void>> cancelOrder(Integer serviceId) {
    return mRestApi.cancelOrder(serviceId, mAuthorizationManager.getToken());
  }

  public Observable<Response<Void>> postponeService(String entryId, int scheduleId) {
    return mRestApi.postponeService(entryId, mAuthorizationManager.getToken(), scheduleId);
  }

  //---------ourWorks

  public Observable<List<OurWorkEntity>> fetchListOfWorks() {
    return mRestApi.fetchListOfWorks();
  }

  public Observable<Response<List<PhotoWorksEntity>>> fetchFavoritePhotos() {
    return mRestApi.fetchFavoritePhotos(mAuthorizationManager.getToken());
    //return mRestApi.fetchFavoritePhotos(
    //    "bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOjksImlzcyI6Imh0dHBzOlwvXC9iZWF1dHkuYXBpLmZsb29yMTJhcHBzLmNvbVwvYXBpXC92MVwvdXNlcnMiLCJpYXQiOjE0OTY3MzU4NjEsImV4cCI6MTUwODczNTg2MSwibmJmIjoxNDk2NzM1ODYxLCJqdGkiOiJPdURCa050TEt0VWw0Q2RMIn0.GZwHT_o22OWVT6gSm1PFoLqhMAzx1RwK5IOUB1LS3gA");
  }

  //--------- like/dislike ourWork photo

  public Observable<Response<Void>> addToFavoritePhoto(int photoId) {
    return mRestApi.addToFavoritePhoto(photoId, mAuthorizationManager.getToken());
  }

  public Observable<Response<Void>> removeFromFavoritePhoto(int photoId) {
    return mRestApi.removeFromFavoritePhoto(photoId, mAuthorizationManager.getToken());
  }

  //---------News

  public Observable<NewsEntity> fetchNewsPreview() {
    return mRestApi.fetchNewsPreview();
  }

  public Observable<List<NewsEntity>> fetchAllNews() {
    return mRestApi.fetchAllNews();
  }

  //---------Goods

  public Observable<List<GoodsEntity>> fetchGoods() {
    return mRestApi.fetchAllProducts(mAuthorizationManager.getToken());
    //return mRestApi.fetchAllProducts("bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOjksImlzcyI6Imh0dHBzOlwvXC9iZWF1dHkuYXBpLmZsb29yMTJhcHBzLmNvbVwvYXBpXC92MVwvdXNlcnMiLCJpYXQiOjE0OTY3MzU4NjEsImV4cCI6MTUwODczNTg2MSwibmJmIjoxNDk2NzM1ODYxLCJqdGkiOiJPdURCa050TEt0VWw0Q2RMIn0.GZwHT_o22OWVT6gSm1PFoLqhMAzx1RwK5IOUB1LS3gA");
  }

  public Observable<Response<List<GoodsEntity>>> fetchFavoriteGoods() {
    return mRestApi.fetchFavoriteGoods(mAuthorizationManager.getToken());
    //return mRestApi.fetchFavoriteGoods("bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOjksImlzcyI6Imh0dHBzOlwvXC9iZWF1dHkuYXBpLmZsb29yMTJhcHBzLmNvbVwvYXBpXC92MVwvdXNlcnMiLCJpYXQiOjE0OTY3MzU4NjEsImV4cCI6MTUwODczNTg2MSwibmJmIjoxNDk2NzM1ODYxLCJqdGkiOiJPdURCa050TEt0VWw0Q2RMIn0.GZwHT_o22OWVT6gSm1PFoLqhMAzx1RwK5IOUB1LS3gA");
  }

  public Observable<List<GoodsEntity>> fetchGoodsByCatalogId(Integer id) {
    return mRestApi.fetchGoodsByCatalogId(id);
  }

  public Observable<List<GoodsCategoryEntity>> fetchCategories() {
    return mRestApi.fetchCategories();
  }

  //---------like/dislike goods

  public Observable<Response<Void>> addToFavoriteGoods(int goodsId) {
    return mRestApi.addToFavoriteGoods(goodsId,mAuthorizationManager.getToken());
  }

  public Observable<Response<Void>> removeFromFavoriteGoods(int goodsId) {
    return mRestApi.removeFromFavoriteGoods(goodsId,mAuthorizationManager.getToken());
  }

  //---------Notification

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

  //---------Auth

  public boolean isAuthorized() {
    return mAuthorizationManager.isAuthorized();
  }

  public void refreshToken() {
    mAuthorizationManager.refreshToken1();
  }

  public void clearToken() {
    mAuthorizationManager.clear();
  }

  public void startSignInActivity(AppCompatActivity activity, Context context) {
    mAuthorizationManager.startSignInActivity(activity, ThemeUtils.getThemeActionBar(context));
  }

  public void logout() {
    mPref.clear();
    mAuthorizationManager.clear();
    mAuthorizationManager.logout();
  }
}

