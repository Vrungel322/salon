package com.apps.twelve.floor.salon.data.model;

import com.apps.twelve.floor.salon.data.model.category.GoodsCategoryEntity;
import java.util.List;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Vrungel on 26.01.2017.
 */

public interface SalonApi {

  @GET("api/v1/categories/") Observable<List<CategoryEntity>> fetchCategory(
      @Header("lng") String language);

  @GET("api/v1/services/") Observable<List<ServiceEntity>> fetchAllServices(
      @Header("lng") String language);

  @GET("api/v1/categories/{id}/services")
  Observable<List<ServiceEntity>> fetchServicesOfCategoryWithId(@Header("lng") String language,
      @Path("id") int categoryId);

  @GET("api/v1/categories/{id}") Observable<List<CategoryEntity>> fetchCategoriesOfCategoryWithId(
      @Header("lng") String language, @Path("id") int parentId);

  @GET("api/v1/schedules/{id}") Observable<List<DataServiceEntity>> fetchDaysData(
      @Header("lng") String language, @Path("id") String serviceIdAtData);

  @GET("api/v1/services/{serviceId}/schedules/{dataID}/masters")
  Observable<List<MasterEntity>> fetchMasters(@Header("lng") String language,
      @Path("serviceId") String serviceId, @Path("dataID") String dataID);

  @GET("api/v1/masters") Observable<List<MasterEntity>> fetchAllMasters(
      @Header("lng") String language);

  @GET("api/v1/masters/{masterId}") Observable<List<ServiceEntity>> fetchAllServicesByMasterId(
      @Header("lng") String language, @Path("masterId") String masterId);

  @GET("api/v1/masters/{masterId}/schedules")
  Observable<List<DataServiceEntity>> fetchDaysDataWithMasterId(@Header("lng") String language,
      @Path("masterId") String masterId);

  @POST("api/v1/entry") Observable<Response<LastBookingEntity>> checkInService(
      @Header("lng") String language, @Header("authorization") String token,
      @Body BookingServerEntity bookingServerEntity);

  @GET("api/v1/users/entries") Observable<Response<List<LastBookingEntity>>> fetchLastBooking(
      @Header("lng") String language, @Header("authorization") String token);

  @DELETE("api/v1/entry/{id}") Observable<Response<Void>> cancelOrder(
      @Header("lng") String language, @Path("id") String serviceId,
      @Header("authorization") String token);

  @PUT("api/v1/entry/{entryId}") @FormUrlEncoded Observable<Response<Void>> postponeService(
      @Header("lng") String language, @Path("entryId") String entryId,
      @Header("authorization") String token, @Field("schedule_id") int scheduleId);

  @GET("api/v1/pages") Observable<List<NewsEntity>> fetchAllNews(@Header("lng") String language);

  @GET("api/v1/pages?last=1") Observable<NewsEntity> fetchNewsPreview(
      @Header("lng") String language);

  @GET("api/v1/galleries") Observable<List<OurWorkEntity>> fetchListOfWorks(
      @Header("lng") String language);

  @GET("api/v1/galleries") Observable<Response<List<OurWorkEntity>>> fetchListOfWorksAuth(
      @Header("lng") String language, @Header("authorization") String token);

  @POST("api/v1/users/favorite/photos") @FormUrlEncoded
  Observable<Response<Void>> addToFavoritePhoto(@Header("lng") String language,
      @Field("photo_id") int photoId, @Header("authorization") String token);

  @DELETE("api/v1/users/favorite/photos/{photoId}")
  Observable<Response<Void>> removeFromFavoritePhoto(@Header("lng") String language,
      @Path("photoId") int photoId, @Header("authorization") String token);

  @GET("api/v1/products") Observable<List<GoodsEntity>> fetchAllProducts(
      @Header("lng") String language, @Header("authorization") String token);

  @GET("api/v1/products/categories") Observable<List<GoodsCategoryEntity>> fetchCategories(
      @Header("lng") String language);

  @GET("api/v1/products") Observable<List<GoodsEntity>> fetchGoodsByCatalogId(
      @Header("lng") String language, @Query("category") Integer id);

  @GET("api/v1/users/favorite/photos")
  Observable<Response<List<PhotoWorksEntity>>> fetchFavoritePhotos(@Header("lng") String language,
      @Header("authorization") String token);

  @GET("api/v1/users/favorite/products") Observable<Response<List<GoodsEntity>>> fetchFavoriteGoods(
      @Header("lng") String language, @Header("authorization") String token);

  @POST("api/v1/users/favorite/products") @FormUrlEncoded
  Observable<Response<Void>> addToFavoriteGoods(@Header("lng") String language,
      @Field("product_id") int goodsId, @Header("authorization") String token);

  @DELETE("api/v1/users/favorite/products/{product_id}")
  Observable<Response<Void>> removeFromFavoriteGoods(@Header("lng") String language,
      @Path("product_id") int goodsId, @Header("authorization") String token);

  @GET("api/v1/users/me/bonuses") Observable<Response<BonusEntity>> fetchBonusCount(
      @Header("lng") String language, @Header("authorization") String token);

  @GET("api/v1/users/me/bonuses_history")
  Observable<Response<List<BonusHistoryEntity>>> fetchBonusHistory(
      @Header("lng") String language, @Header("authorization") String token);
}
