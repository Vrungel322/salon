package com.apps.twelve.floor.salon.mvp.data.remote;

import com.apps.twelve.floor.salon.mvp.data.model.SalonApi;
import com.apps.twelve.floor.salon.mvp.data.model.ServiceEntity;
import java.util.List;
import rx.Observable;

/**
 * Created by Vrungel on 26.01.2017.
 */

public class RestApi {
  private final SalonApi api;

  public RestApi(SalonApi api) {
    this.api = api;
  }

  public Observable<List<ServiceEntity>> fetchServices(int deep, int count) {
    return api.fetchServices(deep, count);
  }

  //public Observable<TokenEntity> login(LoginBody credentials) {
  //  return api.login(credentials);
  //}
}
