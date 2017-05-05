package com.apps.twelve.floor.salon.data.remote;

import com.apps.twelve.floor.salon.data.model.BookingServerEntity;
import com.apps.twelve.floor.salon.data.model.CategoryEntity;
import com.apps.twelve.floor.salon.data.model.DataServiceEntity;
import com.apps.twelve.floor.salon.data.model.LastBookingEntity;
import com.apps.twelve.floor.salon.data.model.MasterEntity;
import com.apps.twelve.floor.salon.data.model.NewsEntity;
import com.apps.twelve.floor.salon.data.model.OurWorkEntity;
import com.apps.twelve.floor.salon.data.model.SalonApi;
import com.apps.twelve.floor.salon.data.model.ServiceEntity;
import java.util.List;
import retrofit2.Response;
import rx.Observable;

/**
 * Created by Vrungel on 26.01.2017.
 */

public class RestApi {
  private final SalonApi api;

  public RestApi(SalonApi api) {
    this.api = api;
  }

  public Observable<List<CategoryEntity>> fetchCategory() {
    return api.fetchCategory();
  }

  //todo fetchAllServices for filtering
  public Observable<List<ServiceEntity>> fetchAllServices() {
    return api.fetchAllServices();
  }

  public Observable<List<ServiceEntity>> fetchServicesOfCategoryWithId(int id) {
    return api.fetchServicesOfCategoryWithId(id);
  }

  public Observable<List<CategoryEntity>> fetchCategoriesOfCategoryWithId(int parentId) {
    return api.fetchCategoriesOfCategoryWithId(parentId);
  }

  public Observable<List<DataServiceEntity>> fetchDaysData(String serviceId) {
    return api.fetchDaysData(serviceId);
  }

  public Observable<List<MasterEntity>> fetchMasters(String serviceId, String dataID) {
    return api.fetchMasters(serviceId, dataID);
  }

  public Observable<List<MasterEntity>> fetchAllMasters() {
    return api.fetchAllMasters();
  }

  public Observable<List<ServiceEntity>> fetchAllServicesByMasterId(String masterId) {
    return api.fetchAllServicesByMasterId(masterId);
  }

  public Observable<List<DataServiceEntity>> fetchDaysDataInMasterMode(String masterId) {
    return api.fetchDaysDataInMasterMode(masterId);
  }

  public Observable<retrofit2.Response<Void>> checkInService(String token,
      BookingServerEntity bookingServerEntity) {
    return api.checkInService(Integer.parseInt(token), bookingServerEntity);
  }

  public Observable<List<LastBookingEntity>> fetchLastBooking(String token) {
    return api.fetchLastBooking(Integer.parseInt(token));
  }

  public Observable<Response<Void>> cancelOrder(Integer serviceId) {
    return api.cancelOrder(String.valueOf(serviceId));
  }

  public Observable<Response<Void>> postponeService(String entryId, String userId, int scheduleId) {
    return api.postponeService(entryId, userId, scheduleId);
  }

  public Observable<List<NewsEntity>> fetchAllNews() {
    return api.fetchAllNews();
  }

  public Observable<NewsEntity> fetchNewsPreview() {
    return api.fetchNewsPreview();
  }

  public Observable<List<OurWorkEntity>> fetchListOfWorks(String token) {
    return api.fetchListOfWorks(token);
  }

  public Observable<Response<Void>> addToFavorite(int photoId, String token) {
    return api.addToFavorite(photoId, token);
  }
  public Observable<Response<Void>> removeFromFavorite(int photoId, String token) {
    return api.removeFromFavorite(photoId, token);
  }

  //public Observable<TokenEntity> login(LoginBody credentials) {
  //  return api.login(credentials);
  //}
}
