package com.apps.twelve.floor.salon.data;

import android.net.Uri;
import com.apps.twelve.floor.salon.data.local.PreferencesHelper;
import com.apps.twelve.floor.salon.data.model.BookingServerEntity;
import com.apps.twelve.floor.salon.data.model.CategoryEntity;
import com.apps.twelve.floor.salon.data.model.DataServiceEntity;
import com.apps.twelve.floor.salon.data.model.LastBookingEntity;
import com.apps.twelve.floor.salon.data.model.MasterEntity;
import com.apps.twelve.floor.salon.data.model.NewsEntity;
import com.apps.twelve.floor.salon.data.model.OurWorkEntity;
import com.apps.twelve.floor.salon.data.model.PhotoWorksEntity;
import com.apps.twelve.floor.salon.data.model.ServiceEntity;
import com.apps.twelve.floor.salon.data.remote.RestApi;
import java.util.ArrayList;
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

  public Observable<List<DataServiceEntity>> fetchDaysDataInMasterMode(String masterId) {
    return mRestApi.fetchDaysDataInMasterMode(masterId);
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

  public Observable<retrofit2.Response<Void>> checkInService(
      BookingServerEntity bookingServerEntity) {
    return mRestApi.checkInService(mPref.getToken(), bookingServerEntity);
  }

  public Observable<List<LastBookingEntity>> fetchLastBooking() {
    return mRestApi.fetchLastBooking(mPref.getToken());
  }

  public Observable<retrofit2.Response<Void>> cancelOrder(Integer serviceId) {
    return mRestApi.cancelOrder(serviceId);
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

  public Observable<Response<Void>> addToFavorite(int photoId){
    return mRestApi.addToFavorite(photoId, mPref.getToken());
  }

  public Observable<Response<Void>> removeFromFavorite(int photoId){
    return mRestApi.removeFromFavorite(photoId, mPref.getToken());
  }

  //public Observable<List<MasterEntity>> fetchMasters(String dataID) {
  //  ArrayList<MasterEntity> arrayList = new ArrayList<>();
  //  for (int i = 0; i < 7; i++) {
  //    arrayList.add(new MasterEntity("Master " + i,
  //        "https://s-media-cache-ak0.pinimg.com/736x/9a/34/cb/9a34cb759887396a7e46b62e39dfc60d.jpg",
  //        "Lorem ipsum dolore sit amet", "" + i));
  //  }
  //  return Observable.just(arrayList);
  //}
}
