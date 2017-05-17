package com.apps.twelve.floor.salon.data;

import com.apps.twelve.floor.salon.data.local.PreferencesHelper;
import com.apps.twelve.floor.salon.data.model.BookingServerEntity;
import com.apps.twelve.floor.salon.data.model.CategoryEntity;
import com.apps.twelve.floor.salon.data.model.DataServiceEntity;
import com.apps.twelve.floor.salon.data.model.LastBookingEntity;
import com.apps.twelve.floor.salon.data.model.MasterEntity;
import com.apps.twelve.floor.salon.data.model.NewsEntity;
import com.apps.twelve.floor.salon.data.model.OurWorkEntity;
import com.apps.twelve.floor.salon.data.model.ServiceEntity;
import com.apps.twelve.floor.salon.data.remote.RestApi;
import java.util.List;
import retrofit2.Response;
import rx.Observable;

/**
 * Created by Vrungel on 26.01.2017.
 */

public class DataManager {

  private RestApi mRestApi;
  private PreferencesHelper mPref;

  public DataManager(RestApi restApi, PreferencesHelper preferencesHelper) {
    this.mRestApi = restApi;
    this.mPref = preferencesHelper;
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

  public Observable<String> getProfileImage() {
    return mPref.getProfileImage().filter((s) -> !s.isEmpty());
  }

  public void setProfileImage(String uri) {
    mPref.setProfileImage(uri);
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

  public Observable<Response<Void>> addToFavorite(int photoId) {
    return mRestApi.addToFavorite(photoId, mPref.getToken());
  }

  public Observable<Response<Void>> removeFromFavorite(int photoId) {
    return mRestApi.removeFromFavorite(photoId, mPref.getToken());
  }
}
