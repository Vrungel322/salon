package com.apps.twelve.floor.authorization.data.model;

import java.util.List;
import okhttp3.MultipartBody;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by Alexander Svyatetsky on 04.05.2017.
 */

public interface AuthorizationApi {

  //LOGIN
  @POST("api/v1/login") Observable<Response<TokenEntity>> login(@Header("lng") String language,
      @Body CredentialsEntity credentials);

  //LOGIN BY SOCIAL NETWORK
  @POST("api/v1/login/social") Observable<Response<TokenEntity>> loginBySocialNetwork(
      @Body UserEntity userEntity);

  //REGISTRATION
  @POST("api/v1/users") Observable<Response<TokenEntity>> register(@Header("lng") String language,
      @Body UserEntity user);

  //RECOVERY PASSWORD
  @POST("api/v1/password/reset") Observable<Response<Void>> resetPassword(
      @Header("lng") String language, @Body CredentialsEntity credentials);

  //VERIFY CODE
  @POST("api/v1/password/reset/verify") Observable<Response<Void>> verifyCode(
      @Header("lng") String language, @Body CredentialsEntity credentials);

  //CHANGE PASSWORD
  @POST("api/v1/password/reset/change") Observable<Response<Void>> changePassword(
      @Header("lng") String language, @Body CredentialsEntity credentials);

  //GET USER PROFILE
  @GET("api/v1/users/me") Observable<Response<UserEntity>> getUserProfile(
      @Header("authorization") String token);

  //UPDATE USER PROFILE
  @PUT("api/v1/users/me") Observable<Response<UserEntity>> updateUserProfile(
      @Header("authorization") String token, @Header("lng") String language, @Body UserEntity user);

  //UPDATE EMAIL STEP 1
  @POST("api/v1/users/me/email") Observable<Response<Void>> updateEmail1(
      @Header("authorization") String token, @Header("lng") String language, @Body UserEntity user);

  //UPDATE EMAIL STEP 2
  @POST("api/v1/users/me/change_email") Observable<Response<Void>> updateEmail2(
      @Header("authorization") String token, @Header("lng") String language, @Body UserEntity user);

  //UPDATE PHONE STEP 1
  @POST("api/v1/users/me/phone") Observable<Response<Void>> updatePhone1(
      @Header("authorization") String token, @Header("lng") String language, @Body UserEntity user);

  //UPDATE PHONE STEP 2
  @POST("api/v1/users/me/change_phone") Observable<Response<Void>> updatePhone2(
      @Header("authorization") String token, @Header("lng") String language, @Body UserEntity user);

  //UPDATE USER PICTURE
  @Multipart @POST("api/v1/users/me/picture") Observable<Response<UserEntity>> updateUserPicture(
      @Header("authorization") String token, @Part MultipartBody.Part file);

  //GET ACTIVITY HISTORY
  @GET("api/v1/users/me/devices") Observable<Response<List<DeviceInfoEntity>>> getActivityHistory(
      @Header("authorization") String token);

  //REFRESH TOKEN
  @POST("api/v1/refresh") Observable<Response<TokenEntity>> refreshTokens(
      @Header("authorization") String token);

  //CHANGE PASSWORD
  @FormUrlEncoded @POST("api/v1/users/me/change_password")
  Observable<Response<TokenEntity>> updatePassword(@Header("authorization") String token,
      @Header("lng") String language, @Field("password") String password,
      @Field("new_password") String newPassword);

  //LOGOUT
  @POST("api/v1/logout") Observable<Response<Void>> logout(@Header("authorization") String token);

  //LOGOUT ALL
  @POST("api/v1/logout/all") Observable<Response<Void>> logoutAll(
      @Header("authorization") String token);

  //DELETE ACCOUNT STEP 1
  @FormUrlEncoded @POST("api/v1/users/me/delete") Observable<Response<Void>> deleteUserProfile1(
      @Header("authorization") String token, @Header("lng") String language,
      @Field("password") String password);

  @FormUrlEncoded @HTTP(method = "DELETE", path = "api/v1/users/me", hasBody = true)
  Observable<Response<Void>> deleteUserProfile2(@Header("authorization") String token,
      @Header("lng") String language, @Field("verify_code") String verifyCode);

  //CREATE ADDITIONAL FIELDS
  @FormUrlEncoded @POST("api/v1/users/me/additionals")
  Observable<Response<Void>> addAdditionalField(@Header("authorization") String token,
      @Header("lng") String language, @Field("key") String key, @Field("value") String value);

  //UPDATE ADDITIONAL FIELDS
  @FormUrlEncoded @PUT("api/v1/users/me/additionals/{key}")
  Observable<Response<Void>> updateAdditionalField(@Header("authorization") String token,
      @Header("lng") String language, @Path("key") String keyPath, @Field("key") String key,
      @Field("value") String value);
}
