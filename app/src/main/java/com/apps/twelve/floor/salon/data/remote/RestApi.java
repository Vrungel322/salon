package com.apps.twelve.floor.salon.data.remote;

import com.apps.twelve.floor.salon.data.model.BookingServerEntity;
import com.apps.twelve.floor.salon.data.model.CategoryEntity;
import com.apps.twelve.floor.salon.data.model.DataServiceEntity;
import com.apps.twelve.floor.salon.data.model.GoodsEntity;
import com.apps.twelve.floor.salon.data.model.LastBookingEntity;
import com.apps.twelve.floor.salon.data.model.MasterEntity;
import com.apps.twelve.floor.salon.data.model.NewsEntity;
import com.apps.twelve.floor.salon.data.model.OurWorkEntity;
import com.apps.twelve.floor.salon.data.model.PhotoWorksEntity;
import com.apps.twelve.floor.salon.data.model.SalonApi;
import com.apps.twelve.floor.salon.data.model.ServiceEntity;
import com.apps.twelve.floor.salon.data.model.category.GoodsCategoryEntity;
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

  public Observable<List<DataServiceEntity>> fetchDaysDataWithMasterId(String masterId) {
    return api.fetchDaysDataWithMasterId(masterId);
  }

  public Observable<retrofit2.Response<LastBookingEntity>> checkInService(String token,
      BookingServerEntity bookingServerEntity) {
    return api.checkInService(token, bookingServerEntity);
  }

  public Observable<List<LastBookingEntity>> fetchLastBooking(String token) {
    return api.fetchLastBooking(token);
  }

  public Observable<Response<Void>> cancelOrder(Integer serviceId, String token) {
    return api.cancelOrder(String.valueOf(serviceId), token);
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

  public Observable<List<OurWorkEntity>> fetchListOfWorks() {
    return api.fetchListOfWorks();
  }

  public Observable<Response<Void>> addToFavoritePhoto(int photoId, String token) {
    return api.addToFavoritePhoto(photoId, token);
  }

  public Observable<Response<Void>> removeFromFavoritePhoto(int photoId, String token) {
    return api.removeFromFavoritePhoto(photoId, token);
  }

  public Observable<List<GoodsEntity>> fetchAllProducts(String token) {
    return api.fetchAllProducts(token);
  }

  public Observable<List<GoodsCategoryEntity>> fetchCategories() {
    return api.fetchCategories();
  }

  public Observable<List<GoodsEntity>> fetchGoodsByCatalogId(Integer id) {
    return api.fetchGoodsByCatalogId(id);
  }

  public Observable<Response<List<PhotoWorksEntity>>> fetchFavoritePhotos(String token) {
    return api.fetchFavoritePhotos(token);
  }

  public Observable<Response<List<GoodsEntity>>> fetchFavoriteGoods(String token) {
    return api.fetchFavoriteGoods(token);
  }

  public Observable<Response<Void>> addToFavoriteGoods(int goodsId,String token) {
    return api.addToFavoriteGoods(goodsId, token);
  }

  public Observable<Response<Void> > removeFromFavoriteGoods(int goodsId, String token) {
    return api.removeFromFavoriteGoods(goodsId, token);
  }

  //public Observable<TokenEntity> login(LoginBody credentials) {
  //  return api.login(credentials);
  //}
}
