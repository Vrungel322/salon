package com.authorization.floor12.authorization;

import android.app.Application;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import com.authorization.floor12.authorization.data.DataManager;
import com.authorization.floor12.authorization.data.local.PreferencesHelper;
import com.authorization.floor12.authorization.data.model.TokenEntity;
import com.authorization.floor12.authorization.data.model.UserEntity;
import com.authorization.floor12.authorization.di.components.AppComponent;
import com.authorization.floor12.authorization.di.components.DaggerAppComponent;
import com.authorization.floor12.authorization.di.modules.AppModule;
import com.authorization.floor12.authorization.di.modules.RetrofitModule;
import com.jaychang.slm.SocialLoginManager;
import javax.inject.Inject;
import retrofit2.Response;
import rx.Observable;

/**
 * Created by Alexander Svyatetsky on 04.05.2017.
 */
public class AuthorizationManager {

  @Inject DataManager mDataManager;
  @Inject PreferencesHelper mPreferencesHelper;

  private static AuthorizationManager instance;
  protected static AppComponent sAppComponent;

  public static void init(@NonNull Application application, String baseUrl) {
    SocialLoginManager.init(application);
    sAppComponent = DaggerAppComponent.builder()
        .appModule(new AppModule(application))
        .retrofitModule(new RetrofitModule(baseUrl))
        .build();
  }

  protected AuthorizationManager() {
    sAppComponent.inject(this);
  }

  public static AuthorizationManager getInstance() {
    if (instance == null) {
      instance = new AuthorizationManager();
    }
    return instance;
  }

  /*Api*/
  public Observable<UserEntity> getUserProfile() {
    return mDataManager.getUserProfile();
  }

  public Observable<Response<UserEntity>> updateUserInfo(UserEntity user) {
    return mDataManager.updateUserProfile(user);
  }

  public Observable<Response<UserEntity>> updateEmail(String email, String password) {
    UserEntity userEntity = new UserEntity();
    userEntity.setEmail(email);
    userEntity.setPassword(password);
    return mDataManager.updateLogin(userEntity);
  }

  public Observable<Response<UserEntity>> updatePhone(String phone, String password) {
    UserEntity userEntity = new UserEntity();
    userEntity.setPhone(phone);
    userEntity.setPassword(password);
    return mDataManager.updateLogin(userEntity);
  }

  public Observable<Response<TokenEntity>> changePassword(String password, String newPassword) {
    return mDataManager.changePassword(password, newPassword);
  }

  public Observable<Response<TokenEntity>> refreshToken() {
    return mDataManager.refreshToken();
  }

  public Observable<Response<Void>> deleteUserProfile(String password) {
    return mDataManager.deleteUserProfile(password);
  }

  public Observable<Response<Void>> logout() {
    return mDataManager.logout();
  }

  public Observable<Response<Void>> logoutAll() {
    return mDataManager.logoutAll();
  }

  /*SharedPreference*/
  public Observable<String> getToken() {
    return mDataManager.getToken();
  }

  public Observable<String> getUserName() {
    return mDataManager.getUserName();
  }

  public Observable<String> getUserPhone() {
    return mDataManager.getUserPhone();
  }

  public Observable<String> getUserEmail() {
    return mDataManager.getUserEmail();
  }

  public Observable<String> getUserPhoto() {
    return mDataManager.getUserPhoto();
  }

  public Observable<String> getUserGender() {
    return mDataManager.getUserGender();
  }

  public void setToken(String token) {
    mPreferencesHelper.setToken(token);
  }

  public void setUserName(String userName) {
    mPreferencesHelper.setUserName(userName);
  }

  public void setUserPhone(String userPhone) {
    mPreferencesHelper.setUserPhone(userPhone);
  }

  public void setUserEmail(String userEmail) {
    mPreferencesHelper.setUserEmail(userEmail);
  }

  public void setUserPhoto(String userPhoto) {
    mPreferencesHelper.setUserPhoto(userPhoto);
  }

  public void setUserGender(String userGender) {
    mPreferencesHelper.setUserGender(userGender);
  }

  public boolean isAuthorized() {
    return !TextUtils.isEmpty(mPreferencesHelper.getToken());
  }
}


