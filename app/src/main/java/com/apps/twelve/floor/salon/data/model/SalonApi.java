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

  @GET("api/v1/categories") Observable<Response<List<CategoryEntity>>> fetchCategory(
      @Header("lng") String language);

  @GET("api/v1/services") Observable<Response<List<ServiceEntity>>> fetchAllServices(
      @Header("lng") String language);

  @GET("api/v1/categories/{id}/services")
  Observable<Response<List<ServiceEntity>>> fetchServicesOfCategoryWithId(
      @Header("lng") String language, @Path("id") int categoryId);

  @GET("api/v1/categories/{id}")
  Observable<Response<List<CategoryEntity>>> fetchCategoriesOfCategoryWithId(
      @Header("lng") String language, @Path("id") int parentId);

  @GET("api/v1/schedules/{id}") Observable<Response<List<DataServiceEntity>>> fetchDaysData(
      @Header("lng") String language, @Path("id") String serviceIdAtData);

  @GET("api/v1/services/{serviceId}/schedules/{dataID}/masters")
  Observable<Response<List<MasterEntity>>> fetchMasters(@Header("lng") String language,
      @Path("serviceId") String serviceId, @Path("dataID") String dataID);

  @GET("api/v1/masters") Observable<Response<List<MasterEntity>>> fetchAllMasters(
      @Header("lng") String language);

  @GET("api/v1/masters/{masterId}")
  Observable<Response<List<ServiceEntity>>> fetchAllServicesByMasterId(
      @Header("lng") String language, @Path("masterId") String masterId);

  @GET("api/v1/masters/{masterId}/schedules")
  Observable<Response<List<DataServiceEntity>>> fetchDaysDataWithMasterId(
      @Header("lng") String language, @Path("masterId") String masterId);

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

  @GET("api/v1/pages") Observable<Response<List<NewsEntity>>> fetchAllNews(
      @Header("lng") String language);

  @GET("api/v1/galleries") Observable<Response<List<OurWorkEntity>>> fetchListOfWorks(
      @Header("lng") String language);

  @GET("api/v1/galleries") Observable<Response<List<OurWorkEntity>>> fetchListOfWorksAuth(
      @Header("lng") String language, @Header("authorization") String token);

  @POST("api/v1/users/favorite/photos") @FormUrlEncoded
  Observable<Response<Void>> addToFavoritePhoto(@Header("lng") String language,
      @Field("photo_id") int photoId, @Header("authorization") String token);

  @DELETE("api/v1/users/favorite/photos/{photoId}")
  Observable<Response<Void>> removeFromFavoritePhoto(@Header("lng") String language,
      @Path("photoId") int photoId, @Header("authorization") String token);

  @GET("api/v1/products") Observable<Response<List<GoodsEntity>>> fetchAllProducts(
      @Header("lng") String language, @Header("authorization") String token);

  @GET("api/v1/products/categories")
  Observable<Response<List<GoodsCategoryEntity>>> fetchCategories(@Header("lng") String language);

  @GET("api/v1/products") Observable<Response<List<GoodsEntity>>> fetchGoodsByCatalogId(
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
  Observable<Response<List<BonusHistoryEntity>>> fetchBonusHistory(@Header("lng") String language,
      @Header("authorization") String token);

  @POST("api/v1/feedback") @FormUrlEncoded
  Observable<Response<ReportProblemResponseEntity>> sendReportProblem(
      @Field("message") String problemBody, @Header("authorization") String token);

  @POST("api/v1/users/me/invited") @FormUrlEncoded Observable<Response<Void>> sendFriendsCode(
      @Field("invite_code") String friendsCode, @Header("authorization") String token,
      @Header("lng") String language);

  @GET("api/v1/texts/{string}") Observable<RemoteStringEntity> fetchString(
      @Path("string") String remoteStringType, @Header("lng") String language);
}
