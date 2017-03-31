package com.apps.twelve.floor.salon.mvp.data.model;

import com.apps.twelve.floor.salon.mvp.data.model.service_tree_item.ParentService;
import java.util.List;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Vrungel on 26.01.2017.
 */

public interface SalonApi {

  @GET("v1/services/") Observable<List<ParentService>> fetchTreeServices(
      @Query("count") int count);

  //@POST("signin") Observable<TokenEntity> login(
  //    @Body LoginBody credentials
  //);
}
