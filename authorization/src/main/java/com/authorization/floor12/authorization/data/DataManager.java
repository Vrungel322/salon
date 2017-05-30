package com.authorization.floor12.authorization.data;

import com.authorization.floor12.authorization.data.local.PreferencesHelper;
import com.authorization.floor12.authorization.data.model.CredentialsEntity;
import com.authorization.floor12.authorization.data.model.TokenEntity;
import com.authorization.floor12.authorization.data.model.UserEntity;
import com.authorization.floor12.authorization.data.remote.RestApi;
import retrofit2.Response;
import rx.Observable;

/**
 * Created by Alexander Svyatetsky on 04.05.2017.
 */

public class DataManager {

  private RestApi restApi;
  private PreferencesHelper preferencesHelper;

  public DataManager(RestApi restApi, PreferencesHelper preferencesHelper) {
    this.restApi = restApi;
    this.preferencesHelper = preferencesHelper;
  }

  /*Api call*/

  public Observable<Response<TokenEntity>> login(CredentialsEntity credentials) {
    return restApi.login(credentials);
  }

  public Observable<Response<TokenEntity>> register(UserEntity user) {
    return restApi.register(user);
  }

  public Observable<UserEntity> getUserProfile() {
    return restApi.getUserProfile(preferencesHelper.getToken());
  }

  public Observable<Response<UserEntity>> updateUserProfile(UserEntity user) {
    return restApi.updateUserProfile(preferencesHelper.getToken(), user);
  }

  public Observable<Response<TokenEntity>> refreshToken() {
    return restApi.refreshTokens(preferencesHelper.getToken());
  }

  public Observable<Response<TokenEntity>> changePassword(String password, String newPassword) {
    return restApi.changePassword(preferencesHelper.getToken(), password, newPassword);
  }

  public Observable<Response<Void>> logout() {
    return restApi.logout(preferencesHelper.getToken());
  }

  public Observable<Response<Void>> logoutAll() {
    return restApi.logoutAll(preferencesHelper.getToken());
  }

  public Observable<Response<Void>> resetPassword(CredentialsEntity credentialsEntity) {
    return restApi.resetPassword(credentialsEntity);
  }

  public Observable<Response<TokenEntity>> loginBySocialNetwork(UserEntity userEntity) {
    return restApi.loginBySocialNetwork(userEntity);
  }

  public Observable<Response<UserEntity>> updateLogin(UserEntity userEntity) {
    return restApi.updateLogin(preferencesHelper.getToken(), userEntity);
  }

  public Observable<Response<Void>> deleteUserProfile(String password) {
    return restApi.deleteUserProfile(preferencesHelper.getToken(), password);
  }

  /*SharedPreferences*/

  public Observable<String> getToken() {
    return Observable.just(preferencesHelper.getToken());
  }

  public Observable<String> getUserPhoto() {
    return Observable.just(preferencesHelper.getUserPhoto()).filter((s) -> !s.isEmpty());
  }

  public void setProfileImage(String uri) {
    preferencesHelper.setUserPhoto(uri);
  }

  public Observable<String> getUserName() {
    return Observable.just(preferencesHelper.getUserName());
  }

  public void setUserName(String name) {
    preferencesHelper.setUserName(name);
  }

  public Observable<String> getUserEmail() {
    return Observable.just(preferencesHelper.getUserEmail());
  }

  public void setUserEmail(String email) {
    preferencesHelper.setUserEmail(email);
  }

  public Observable<String> getUserPhone() {
    return Observable.just(preferencesHelper.getUserPhone());
  }

  public void setUserPhone(String phone) {
    preferencesHelper.setUserPhone(phone);
  }

  public Observable<String> getUserGender() {
    return Observable.just(preferencesHelper.getUserGender());
  }

  public void setUserGender(String gender) {
    preferencesHelper.setUserGender(gender);
  }
}
