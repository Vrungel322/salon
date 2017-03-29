package com.apps.twelve.floor.salon.mvp.data.model;

import java.util.List;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Vrungel on 26.01.2017.
 */

public interface SalonApi {

  @GET("v1/services/") Observable<List<ServiceEntity>> fetchServices(@Query("service") int deep,
      @Query("count") int count);

  //@POST("signin") Observable<TokenEntity> login(
  //    @Body LoginBody credentials
  //);
}
