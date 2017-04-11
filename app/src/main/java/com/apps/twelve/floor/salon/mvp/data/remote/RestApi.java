package com.apps.twelve.floor.salon.mvp.data.remote;

import com.apps.twelve.floor.salon.mvp.data.model.CategoryEntity;
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

  public Observable<List<CategoryEntity>> fetchCategory() {
    return api.fetchCategory();
  }

  //todo fetchAllServices for filtering
  public Observable<List<ServiceEntity>> fetchAllServices() {
    return api.fetchAllServices();
  }

  public Observable<List<ServiceEntity>> fetchServicesOfCategoryWithId(int id) {
    return api.fetchServicesOfCategoryWithId(id);
  }

  public Observable<List<CategoryEntity>> fetchCategoriesOfCategoryWithId(int parentId) {
    return api.fetchCategoriesOfCategoryWithId(parentId);
  }

  //public Observable<TokenEntity> login(LoginBody credentials) {
  //  return api.login(credentials);
  //}
}
