package com.apps.twelve.floor.salon.mvp.data.remote;

import com.apps.twelve.floor.salon.mvp.data.model.SalonApi;

/**
 * Created by Vrungel on 26.01.2017.
 */

public class RestApi {
  private final SalonApi api;

  public RestApi(SalonApi api) {
    this.api = api;
  }

  //public Observable<TokenEntity> login(LoginBody credentials) {
  //  return api.login(credentials);
  //}
}
