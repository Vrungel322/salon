package com.apps.twelve.floor.salon.mvp.data.model;

import java.util.List;
import retrofit2.http.GET;
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

  @GET("api/v1/schedules/{id}") Observable<List<DataServiceEntity>> fetchDaysData(@Path("id") String serviceIdAtData);

  //@POST("signin") Observable<TokenEntity> login(
  //    @Body LoginBody credentials
  //);
}
