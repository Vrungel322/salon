package com.apps.twelve.floor.salon.data.model;

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
import rx.Observable;

/**
 * Created by Vrungel on 26.01.2017.
 */

public interface SalonApi {

  @GET("api/v1/categories/") Observable<List<CategoryEntity>> fetchCategory();

  @GET("api/v1/services/") Observable<List<ServiceEntity>> fetchAllServices();

  @GET("api/v1/categories/{id}/services")
  Observable<List<ServiceEntity>> fetchServicesOfCategoryWithId(@Path("id") int categoryId);

  @GET("api/v1/categories/{id}") Observable<List<CategoryEntity>> fetchCategoriesOfCategoryWithId(
      @Path("id") int parentId);

  @GET("api/v1/schedules/{id}") Observable<List<DataServiceEntity>> fetchDaysData(
      @Path("id") String serviceIdAtData);

  @GET("api/v1/services/{serviceId}/schedules/{dataID}/masters")
  Observable<List<MasterEntity>> fetchMasters(@Path("serviceId") String serviceId,
      @Path("dataID") String dataID);

  @GET("api/v1/masters") Observable<List<MasterEntity>> fetchAllMasters();

  @GET("api/v1/masters/{masterId}") Observable<List<ServiceEntity>> fetchAllServicesByMasterId(
      @Path("masterId") String masterId);

  @GET("api/v1/masters/{masterId}/schedules")
  Observable<List<DataServiceEntity>> fetchDaysDataWithMasterId(@Path("masterId") String masterId);

  @POST("api/v1/entry") Observable<retrofit2.Response<Void>> checkInService(
      @Header("User") int token, @Body BookingServerEntity bookingServerEntity);

  @GET("api/v1/users/entries") Observable<List<LastBookingEntity>> fetchLastBooking(
      @Header("User") int token);

  @DELETE("api/v1/entry/{id}") Observable<Response<Void>> cancelOrder(@Path("id") String serviceId,
      @Header("User") String token);

  @PUT("api/v1/entry/{entryId}") @FormUrlEncoded Observable<Response<Void>> postponeService(
      @Path("entryId") String entryId, @Header("user") String token,
      @Field("schedule_id") int scheduleId);

  @GET("api/v1/pages") Observable<List<NewsEntity>> fetchAllNews();

  @GET("api/v1/pages?last=1") Observable<NewsEntity> fetchNewsPreview();

  @GET("api/v1/galleries") Observable<List<OurWorkEntity>> fetchListOfWorks(
      @Header("User") String token);

  @POST("api/v1/favorite") @FormUrlEncoded Observable<Response<Void>> addToFavorite(
      @Field("photo_id") int photoId, @Header("User") String token);

  @DELETE("api/v1/favorite/{photoId}") Observable<Response<Void>> removeFromFavorite(
      @Path("photoId") int photoId, @Header("User") String token);

  //@POST("signin") Observable<TokenEntity> login(
  //    @Body LoginBody credentials
  //);
}
