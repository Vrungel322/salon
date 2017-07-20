package com.apps.twelve.floor.authorization.data.remote;

import com.apps.twelve.floor.authorization.data.model.AuthorizationApi;
import com.apps.twelve.floor.authorization.data.model.CredentialsEntity;
import com.apps.twelve.floor.authorization.data.model.DeviceInfoEntity;
import com.apps.twelve.floor.authorization.data.model.TokenEntity;
import com.apps.twelve.floor.authorization.data.model.UserEntity;
import java.util.List;
import okhttp3.MultipartBody;
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

  public Observable<Response<TokenEntity>> login(String language, CredentialsEntity credentials) {
    return api.login(language, credentials);
  }

  public Observable<Response<TokenEntity>> register(String language, UserEntity userEntity) {
    return api.register(language, userEntity);
  }

  public Observable<Response<Void>> resetPassword(String language,
      CredentialsEntity credentialsEntity) {
    return api.resetPassword(language, credentialsEntity);
  }

  public Observable<Response<Void>> verifyCode(String language,
      CredentialsEntity credentialsEntity) {
    return api.verifyCode(language, credentialsEntity);
  }

  public Observable<Response<Void>> changePassword(String language,
      CredentialsEntity credentialsEntity) {
    return api.changePassword(language, credentialsEntity);
  }

  public Observable<Response<UserEntity>> getUserProfile(String token) {
    return api.getUserProfile(token);
  }

  public Observable<Response<UserEntity>> updateUserProfile(String token, String language,
      UserEntity user) {
    return api.updateUserProfile(token, language, user);
  }

  public Observable<Response<TokenEntity>> refreshTokens(String token) {
    return api.refreshTokens(token);
  }

  public Observable<Response<TokenEntity>> updatePassword(String token, String language,
      String password, String newPassword) {
    return api.updatePassword(token, language, password, newPassword);
  }

  public Observable<Response<Void>> logout(String token) {
    return api.logout(token);
  }

  public Observable<Response<Void>> logoutAll(String token) {
    return api.logoutAll(token);
  }

  public Observable<Response<TokenEntity>> loginBySocialNetwork(UserEntity userEntity) {
    return api.loginBySocialNetwork(userEntity);
  }

  public Observable<Response<Void>> updateEmail1(String token, String language,
      UserEntity userEntity) {
    return api.updateEmail1(token, language, userEntity);
  }

  public Observable<Response<Void>> updateEmail2(String token, String language,
      UserEntity userEntity) {
    return api.updateEmail2(token, language, userEntity);
  }

  public Observable<Response<Void>> updatePhone1(String token, String language,
      UserEntity userEntity) {
    return api.updatePhone1(token, language, userEntity);
  }

  public Observable<Response<Void>> updatePhone2(String token, String language,
      UserEntity userEntity) {
    return api.updatePhone2(token, language, userEntity);
  }

  public Observable<Response<Void>> deleteUserProfile1(String token, String language,
      String password) {
    return api.deleteUserProfile1(token, language, password);
  }

  public Observable<Response<Void>> deleteUserProfile2(String token, String language,
      String verifyCode) {
    return api.deleteUserProfile2(token, language, verifyCode);
  }

  public Observable<Response<UserEntity>> updateUserPicture(String token, MultipartBody.Part file) {
    return api.updateUserPicture(token, file);
  }

  public Observable<Response<List<DeviceInfoEntity>>> getActivityHistory(String token) {
    return api.getActivityHistory(token);
  }

  public Observable<Response<Void>> addAdditionalField(String token, String language, String key,
      String value) {
    return api.addAdditionalField(token, language, key, value);
  }

  public Observable<Response<Void>> updateAdditionalField(String token, String language, String key,
      String value) {
    return api.updateAdditionalField(token, language, key, key, value);
  }
}
