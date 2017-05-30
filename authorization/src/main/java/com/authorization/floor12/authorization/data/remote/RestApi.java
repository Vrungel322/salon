package com.authorization.floor12.authorization.data.remote;

import com.authorization.floor12.authorization.data.model.AuthorizationApi;
import com.authorization.floor12.authorization.data.model.CredentialsEntity;
import com.authorization.floor12.authorization.data.model.TokenEntity;
import com.authorization.floor12.authorization.data.model.UserEntity;
import retrofit2.Response;
import rx.Observable;

/**
 * Created by Alexander Svyatetsky on 04.05.2017.
 */

public class RestApi {

  private AuthorizationApi api;

  public RestApi(AuthorizationApi api) {
    this.api = api;
  }

  public Observable<Response<TokenEntity>> login(CredentialsEntity credentials) {
    return api.login(credentials);
  }

  public Observable<Response<TokenEntity>> register(UserEntity userEntity) {
    return api.register(userEntity);
  }

  public Observable<UserEntity> getUserProfile(String token) {
    return api.getUserProfile(token);
  }

  public Observable<Response<UserEntity>> updateUserProfile(String token, UserEntity user) {
    return api.updateUserProfile(token, user);
  }

  public Observable<Response<TokenEntity>> refreshTokens(String token) {
    return api.refreshTokens(token);
  }

  public Observable<Response<TokenEntity>> changePassword(String token, String password,
      String newPassword) {
    return api.changePassword(token, password, newPassword);
  }

  public Observable<Response<Void>> logout(String token) {
    return api.logout(token);
  }

  public Observable<Response<Void>> logoutAll(String token) {
    return api.logoutAll(token);
  }

  public Observable<Response<Void>> resetPassword(CredentialsEntity credentialsEntity) {
    return api.resetPassword(credentialsEntity);
  }

  public Observable<Response<TokenEntity>> loginBySocialNetwork(UserEntity userEntity) {
    return api.loginBySocialNetwork(userEntity);
  }

  public Observable<Response<UserEntity>> updateLogin(String token, UserEntity userEntity) {
    return api.updateLogin(token, userEntity);
  }

  public Observable<Response<Void>> deleteUserProfile(String token, String password) {
    return api.deleteUserProfile(token, password);
  }
}
