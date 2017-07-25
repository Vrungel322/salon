package com.apps.twelve.floor.authorization.data;

import android.support.annotation.StyleRes;
import com.apps.twelve.floor.authorization.data.local.PreferencesHelper;
import com.apps.twelve.floor.authorization.data.model.CredentialsEntity;
import com.apps.twelve.floor.authorization.data.model.DeviceInfoEntity;
import com.apps.twelve.floor.authorization.data.model.TokenEntity;
import com.apps.twelve.floor.authorization.data.model.UserEntity;
import com.apps.twelve.floor.authorization.data.remote.RestApi;
import java.io.File;
import java.util.List;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Response;
import rx.Observable;

/**
 * Created by Alexander Svyatetsky on 04.05.2017.
 */

public class DataManager {

  private final static String RU = "ru";

  private RestApi mRestApi;
  private PreferencesHelper mPreferencesHelper;

  public DataManager(RestApi mRestApi, PreferencesHelper mPreferencesHelper) {
    this.mRestApi = mRestApi;
    this.mPreferencesHelper = mPreferencesHelper;
  }

  /*Api call*/

  public Observable<Response<TokenEntity>> login(CredentialsEntity credentials) {
    return mRestApi.login(RU/*Locale.getDefault().getLanguage()*/, credentials);
  }

  public Observable<Response<TokenEntity>> register(UserEntity user) {
    return mRestApi.register(RU/*Locale.getDefault().getLanguage()*/, user);
  }

  public Observable<Response<Void>> resetPassword(CredentialsEntity credentialsEntity) {
    return mRestApi.resetPassword(RU/*Locale.getDefault().getLanguage()*/, credentialsEntity);
  }

  public Observable<Response<Void>> verifyCode(CredentialsEntity credentialsEntity) {
    return mRestApi.verifyCode(RU, credentialsEntity);
  }

  public Observable<Response<Void>> changePassword(CredentialsEntity credentialsEntity) {
    return mRestApi.changePassword(RU, credentialsEntity);
  }

  public Observable<Response<UserEntity>> getUserProfile() {
    return mRestApi.getUserProfile(mPreferencesHelper.getToken());
  }

  public Observable<Response<UserEntity>> updateUserProfile(UserEntity user) {
    return mRestApi.updateUserProfile(mPreferencesHelper.getToken(), RU/*Locale.getDefault().getLanguage()*/,
        user);
  }

  public Observable<Response<TokenEntity>> refreshToken() {
    return mRestApi.refreshTokens(mPreferencesHelper.getToken());
  }

  public Observable<Response<TokenEntity>> updatePassword(String password, String newPassword) {
    return mRestApi.updatePassword(mPreferencesHelper.getToken(), RU/*Locale.getDefault().getLanguage()*/,
        password, newPassword);
  }

  public Observable<Response<Void>> logout() {
    return mRestApi.logout(mPreferencesHelper.getToken());
  }

  public Observable<Response<Void>> logoutAll() {
    return mRestApi.logoutAll(mPreferencesHelper.getToken());
  }

  public Observable<Response<TokenEntity>> loginBySocialNetwork(UserEntity userEntity) {
    return mRestApi.loginBySocialNetwork(userEntity);
  }

  public Observable<Response<Void>> updateEmail1(UserEntity userEntity) {
    return mRestApi.updateEmail1(mPreferencesHelper.getToken(), RU/*Locale.getDefault().getLanguage()*/,
        userEntity);
  }

  public Observable<Response<Void>> updateEmail2(UserEntity userEntity) {
    return mRestApi.updateEmail2(mPreferencesHelper.getToken(), RU/*Locale.getDefault().getLanguage()*/,
        userEntity);
  }

  public Observable<Response<Void>> updatePhone1(UserEntity userEntity) {
    return mRestApi.updatePhone1(mPreferencesHelper.getToken(), RU/*Locale.getDefault().getLanguage()*/,
        userEntity);
  }

  public Observable<Response<Void>> updatePhone2(UserEntity userEntity) {
    return mRestApi.updatePhone2(mPreferencesHelper.getToken(), RU/*Locale.getDefault().getLanguage()*/,
        userEntity);
  }

  public Observable<Response<Void>> deleteUserProfile1(String password) {
    return mRestApi.deleteUserProfile1(mPreferencesHelper.getToken(), RU/*Locale.getDefault().getLanguage()*/,
        password);
  }

  public Observable<Response<Void>> deleteUserProfile2(String verifyCode) {
    return mRestApi.deleteUserProfile2(mPreferencesHelper.getToken(), RU/*Locale.getDefault().getLanguage()*/,
        verifyCode);
  }

  public Observable<Response<UserEntity>> updateUserPicture(File file) {

    // Создаем RequestBody
    RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);

    // MultipartBody.Part используется, чтобы передать имя файла
    MultipartBody.Part body =
        MultipartBody.Part.createFormData("picture", file.getName(), requestFile);

    return mRestApi.updateUserPicture(mPreferencesHelper.getToken(), body);
  }

  public Observable<Response<List<DeviceInfoEntity>>> getActivityHistory() {
    return mRestApi.getActivityHistory(mPreferencesHelper.getToken());
  }

  public Observable<Response<Void>> addAdditionalField(String key, String value) {
    return mRestApi.addAdditionalField(mPreferencesHelper.getToken(), RU, key, value);
  }

  public Observable<Response<Void>> updateAdditionalField(String key, String value) {
    return mRestApi.updateAdditionalField(mPreferencesHelper.getToken(), RU, key, value);
  }

  /*SharedPreferences*/

  public String getToken() {
    return mPreferencesHelper.getToken();
  }

  public void setToken(String token) {
    mPreferencesHelper.setToken(token);
  }

  public long getUserId() {
    return mPreferencesHelper.getUserId();
  }

  public void setUserId(long userId) {
    mPreferencesHelper.setUserId(userId);
  }

  public String getUserName() {
    return mPreferencesHelper.getUserName();
  }

  public void setUserName(String userName) {
    mPreferencesHelper.setUserName(userName);
  }

  public String getUserPhone() {
    return mPreferencesHelper.getUserPhone();
  }

  public void setUserPhone(String userPhone) {
    mPreferencesHelper.setUserPhone(userPhone);
  }

  public String getUserEmail() {
    return mPreferencesHelper.getUserEmail();
  }

  public void setUserEmail(String userEmail) {
    mPreferencesHelper.setUserEmail(userEmail);
  }

  public String getUserImage() {
    return mPreferencesHelper.getUserImage();
  }

  public void setUserImage(String userPhotoUrl) {
    mPreferencesHelper.setUserImage(userPhotoUrl);
  }

  public String getUserGender() {
    return mPreferencesHelper.getUserGender();
  }

  public void setUserGender(String gender) {
    mPreferencesHelper.setUserGender(gender);
  }

  public String getUserBirhDay() {
    return mPreferencesHelper.getUserBirthDay();
  }

  public void setUserBirthDay(String date) {
    mPreferencesHelper.setUserBirthDay(date);
  }

  public int getStyleResId() {
    return mPreferencesHelper.getStyleResId();
  }

  public void setStyleResId(@StyleRes int styleResId) {
    mPreferencesHelper.setStyleResId(styleResId);
  }

  public String getAdditionalField(String key, String defaultValue) {
    return mPreferencesHelper.getAdditionalField(key, defaultValue);
  }

  public void setAdditionalField(String key, String value) {
    mPreferencesHelper.setAdditionalField(key, value);
  }

  public int getAdditionalField(String key, int defaultValue) {
    return mPreferencesHelper.getAdditionalField(key, defaultValue);
  }

  public void setAdditionalField(String key, int value) {
    mPreferencesHelper.setAdditionalField(key, value);
  }

  public long getAdditionalField(String key, long defaultValue) {
    return mPreferencesHelper.getAdditionalField(key, defaultValue);
  }

  public void setAdditionalField(String key, long value) {
    mPreferencesHelper.setAdditionalField(key, value);
  }

  public boolean getAdditionalField(String key, boolean defaultValue) {
    return mPreferencesHelper.getAdditionalField(key, defaultValue);
  }

  public void setAdditionalField(String key, boolean value) {
    mPreferencesHelper.setAdditionalField(key, value);
  }

  public boolean isExistAdditionalField(String key) {
    return mPreferencesHelper.isExistAdditionalField(key);
  }

  public void clear() {
    mPreferencesHelper.clear();
  }

  //Observable
  public Observable<String> getObservableToken() {
    return Observable.just(mPreferencesHelper.getToken());
  }

  public Observable<String> getObservableUserImage() {
    return Observable.just(mPreferencesHelper.getUserImage());
  }

  public Observable<String> getObservableUserName() {
    return Observable.just(mPreferencesHelper.getUserName());
  }

  public Observable<String> getObservableUserEmail() {
    return Observable.just(mPreferencesHelper.getUserEmail());
  }

  public Observable<String> getObservableUserPhone() {
    return Observable.just(mPreferencesHelper.getUserPhone());
  }

  public Observable<String> getObservableUserGender() {
    return Observable.just(mPreferencesHelper.getUserGender());
  }

  public Observable<String> getObservableUserBirthDay() {
    return Observable.just(mPreferencesHelper.getUserBirthDay());
  }

  public void setLanguage(String language) {
    mPreferencesHelper.setLanguage(language);
  }
}
