package com.apps.twelve.floor.salon.mvp.data.remote;

import com.apps.twelve.floor.salon.mvp.data.model.ServiceEntity;
import com.apps.twelve.floor.salon.mvp.data.model.service_tree_item.ParentService;
import com.apps.twelve.floor.salon.mvp.data.model.SalonApi;
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

  public Observable<List<ParentService>> fetchTreeServices(int deep, int count) {
    return api.fetchTreeServices(count);
  }

  //todo fetchAllServices for filtering
  public Observable<List<ServiceEntity>> fetchAllServices(int deep, int count) {
    return null;
  }

  //public Observable<TokenEntity> login(LoginBody credentials) {
  //  return api.login(credentials);
  //}
}
