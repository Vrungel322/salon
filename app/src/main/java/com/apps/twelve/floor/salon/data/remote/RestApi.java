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

  public Observable<List<CategoryEntity>> fetchCategory(String language) {
    return api.fetchCategory(language);
  }

  public Observable<List<ServiceEntity>> fetchAllServices(String language) {
    return api.fetchAllServices(language);
  }

  public Observable<List<ServiceEntity>> fetchServicesOfCategoryWithId(String language, int id) {
    return api.fetchServicesOfCategoryWithId(language, id);
  }

  public Observable<List<CategoryEntity>> fetchCategoriesOfCategoryWithId(String language,
      int parentId) {
    return api.fetchCategoriesOfCategoryWithId(language, parentId);
  }

  public Observable<List<DataServiceEntity>> fetchDaysData(String language, String serviceId) {
    return api.fetchDaysData(language, serviceId);
  }

  public Observable<List<MasterEntity>> fetchMasters(String language, String serviceId,
      String dataID) {
    return api.fetchMasters(language, serviceId, dataID);
  }

  public Observable<List<MasterEntity>> fetchAllMasters(String language) {
    return api.fetchAllMasters(language);
  }

  public Observable<List<ServiceEntity>> fetchAllServicesByMasterId(String language,
      String masterId) {
    return api.fetchAllServicesByMasterId(language, masterId);
  }

  public Observable<List<DataServiceEntity>> fetchDaysDataWithMasterId(String language,
      String masterId) {
    return api.fetchDaysDataWithMasterId(language, masterId);
  }

  public Observable<retrofit2.Response<LastBookingEntity>> checkInService(String language,
      String token, BookingServerEntity bookingServerEntity) {
    return api.checkInService(language, token, bookingServerEntity);
  }

  public Observable<List<LastBookingEntity>> fetchLastBooking(String language, String token) {
    return api.fetchLastBooking(language, token);
  }

  public Observable<Response<Void>> cancelOrder(String language, Integer serviceId, String token) {
    return api.cancelOrder(language, String.valueOf(serviceId), token);
  }

  public Observable<Response<Void>> postponeService(String language, String entryId, String userId,
      int scheduleId) {
    return api.postponeService(language, entryId, userId, scheduleId);
  }

  public Observable<List<NewsEntity>> fetchAllNews(String language) {
    return api.fetchAllNews(language);
  }

  public Observable<NewsEntity> fetchNewsPreview(String language) {
    return api.fetchNewsPreview(language);
  }

  public Observable<List<OurWorkEntity>> fetchListOfWorks(String language, String token) {
    return api.fetchListOfWorks(language, token);
  }

  public Observable<Response<Void>> addToFavoritePhoto(String language, int photoId, String token) {
    return api.addToFavoritePhoto(language, photoId, token);
  }

  public Observable<Response<Void>> removeFromFavoritePhoto(String language, int photoId,
      String token) {
    return api.removeFromFavoritePhoto(language, photoId, token);
  }

  public Observable<List<GoodsEntity>> fetchAllProducts(String language, String token) {
    return api.fetchAllProducts(language, token);
  }

  public Observable<List<GoodsCategoryEntity>> fetchCategories(String language) {
    return api.fetchCategories(language);
  }

  public Observable<List<GoodsEntity>> fetchGoodsByCatalogId(String language, Integer id) {
    return api.fetchGoodsByCatalogId(language, id);
  }

  public Observable<Response<List<PhotoWorksEntity>>> fetchFavoritePhotos(String language,
      String token) {
    return api.fetchFavoritePhotos(language, token);
  }

  public Observable<Response<List<GoodsEntity>>> fetchFavoriteGoods(String language, String token) {
    return api.fetchFavoriteGoods(language, token);
  }

  public Observable<Response<Void>> addToFavoriteGoods(String language, int goodsId, String token) {
    return api.addToFavoriteGoods(language, goodsId, token);
  }

  public Observable<Response<Void>> removeFromFavoriteGoods(String language, int goodsId,
      String token) {
    return api.removeFromFavoriteGoods(language, goodsId, token);
  }
}
