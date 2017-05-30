package com.authorization.floor12.authorization.data.model;

import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import rx.Observable;

/**
 * Created by Alexander Svyatetsky on 04.05.2017.
 */

public interface AuthorizationApi {

  //LOGIN
  @POST("api/v1/login") Observable<Response<TokenEntity>> login(
      @Body CredentialsEntity credentials);

  //LOGIN BY SOCIAL NETWORK
  @POST("api/v1/login/social") Observable<Response<TokenEntity>> loginBySocialNetwork(
      @Body UserEntity userEntity);

  //REGISTRATION
  @POST("api/v1/users") Observable<Response<TokenEntity>> register(@Body UserEntity user);

  //GET USER PROFILE
  @GET("api/v1/users/me") Observable<UserEntity> getUserProfile(
      @Header("authorization") String token);

  //UPDATE USER PROFILE
  @PUT("api/v1/users/me") Observable<Response<UserEntity>> updateUserProfile(
      @Header("authorization") String token, @Body UserEntity user);

  //UPDATE EMAIL OR PHONE
  @PUT("api/v1/users/me/change_phoneOrEmail") Observable<Response<UserEntity>> updateLogin(
      @Header("authorization") String token, @Body UserEntity user);

  //REFRESH TOKEN
  @POST("api/v1/refresh") Observable<Response<TokenEntity>> refreshTokens(
      @Header("authorization") String token);

  //CHANGE PASSWORD
  @FormUrlEncoded @PUT("api/v1/users/me/change_password")
  Observable<Response<TokenEntity>> changePassword(@Header("authorization") String token,
      @Field("password") String password, @Field("new_password") String newPassword);

  //LOGOUT
  @POST("api/v1/logout") Observable<Response<Void>> logout(@Header("authorization") String token);

  //LOGOUT ALL
  @POST("api/v1/logout/all") Observable<Response<Void>> logoutAll(
      @Header("authorization") String token);

  //RECOVERY PASSWORD
  @POST("api/v1/password/reset") Observable<Response<Void>> resetPassword(
      @Body CredentialsEntity credentials);

  //DELETE ACCOUNT
  @FormUrlEncoded @HTTP(method = "DELETE", path = "api/v1/users", hasBody = true)
  //@DELETE ("api/v1/users")
  Observable<Response<Void>> deleteUserProfile(@Header("authorization") String token,
      @Field("password") String password);
}
