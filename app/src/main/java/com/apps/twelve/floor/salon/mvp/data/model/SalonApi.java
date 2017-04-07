package com.apps.twelve.floor.salon.mvp.data.model;

import java.util.List;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Vrungel on 26.01.2017.
 */

public interface SalonApi {

  @GET("api/v1/categories/") Observable<List<CategoryEntity>> fetchCategory();

  @GET("api/v1/services/") Observable<List<ServiceEntity>> fetchAllServices(@Query("id") int id);

  //@POST("signin") Observable<TokenEntity> login(
  //    @Body LoginBody credentials
  //);
}
