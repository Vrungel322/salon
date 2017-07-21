package com.apps.twelve.floor.authorization;

import android.app.Application;
import android.content.Intent;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.StyleRes;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import com.apps.twelve.floor.authorization.data.DataManager;
import com.apps.twelve.floor.authorization.di.components.AppComponent;
import com.apps.twelve.floor.authorization.di.components.DaggerAppComponent;
import com.apps.twelve.floor.authorization.di.modules.AppModule;
import com.apps.twelve.floor.authorization.di.modules.RetrofitModule;
import com.apps.twelve.floor.authorization.logic.authorization.activities.ModuleSignInActivity;
import com.apps.twelve.floor.authorization.logic.userdetail.activities.UserProfileActivity;
import com.apps.twelve.floor.authorization.logic.userdetail.fragments.UserProfileFragment;
import com.apps.twelve.floor.authorization.social.SocialLoginManager;
import com.apps.twelve.floor.authorization.utils.AuthRxBus;
import javax.inject.Inject;
import retrofit2.Response;
import rx.Observable;

import static com.apps.twelve.floor.authorization.utils.Constants.Remote.RESPONSE_TOKEN_EXPIRED;
import static com.apps.twelve.floor.authorization.utils.Constants.Remote.RESPONSE_UNAUTHORIZED;

/**
 * Created by Alexander Svyatetsky on 04.05.2017.
 */
public class AuthorizationManager {

  static AppComponent sAppComponent;
  private static AuthorizationManager instance;
  @Inject protected AuthRxBus mAuthRxBus;
  @Inject protected DataManager mDataManager;

  AuthorizationManager() {
    sAppComponent.inject(this);
  }

  public static void init(@NonNull Application application, String baseUrl) {
    SocialLoginManager.init(application);
    sAppComponent = DaggerAppComponent.builder()
        .appModule(new AppModule(application))
        .retrofitModule(new RetrofitModule(baseUrl))
        .build();
  }

  public static AuthorizationManager getInstance() {
    if (instance == null) {
      instance = new AuthorizationManager();
    }
    return instance;
  }

  public UserProfileFragment openUserProfileFragment(@IdRes int resId) {
    return UserProfileFragment.newInstance(resId);
  }

  /*AuthRxBus*/
  public AuthRxBus getAuthRxBus() {
    return mAuthRxBus;
  }

  /*Navigation*/
  public void startSignInActivity(AppCompatActivity activity) {
    activity.startActivity(new Intent(activity, ModuleSignInActivity.class));
  }

  public void startSignInActivity(AppCompatActivity activity, @StyleRes int resIdStyle) {
    mDataManager.setStyleResId(resIdStyle);
    activity.startActivity(new Intent(activity, ModuleSignInActivity.class));
  }

  public void startUserProfileActivity(AppCompatActivity activity) {
    activity.startActivity(new Intent(activity, UserProfileActivity.class));
  }

  public void startUserProfileActivity(AppCompatActivity activity, @StyleRes int resIdStyle) {
    mDataManager.setStyleResId(resIdStyle);
    activity.startActivity(new Intent(activity, UserProfileActivity.class));
  }

  public <T> Observable<Response<T>> checkToken(Observable<Response<T>> observable) {
    return observable.concatMap(response -> {
      if (response.code() == RESPONSE_TOKEN_EXPIRED) {
        return mDataManager.refreshToken().concatMap(tokenResponse -> {
          if (tokenResponse.isSuccessful()) {
            mDataManager.setToken(tokenResponse.body().getToken());
          } else if (tokenResponse.code() == RESPONSE_UNAUTHORIZED) {
            Response<T> responseError = Response.error(RESPONSE_UNAUTHORIZED, response.errorBody());
            return Observable.just(responseError);
          }
          return Observable.just(response);
        });
      }
      return Observable.just(response);
    });
  }

  /*SharedPreference*/

  public String getToken() {
    return mDataManager.getToken();
  }

  public String getUserName() {
    return mDataManager.getUserName();
  }

  public String getUserPhone() {
    return mDataManager.getUserPhone();
  }

  public String getUserEmail() {
    return mDataManager.getUserEmail();
  }

  public String getUserPhoto() {
    return mDataManager.getUserImage();
  }

  public String getUserGender() {
    return mDataManager.getUserGender();
  }

  public void saveToken(String token) {
    mDataManager.setToken(token);
  }

  public void saveUserName(String userName) {
    mDataManager.setUserName(userName);
  }

  public void saveUserPhone(String userPhone) {
    mDataManager.setUserPhone(userPhone);
  }

  public void saveUserEmail(String userEmail) {
    mDataManager.setUserEmail(userEmail);
  }

  public void saveUserPhoto(String userPhoto) {
    mDataManager.setUserImage(userPhoto);
  }

  public void saveUserGender(String userGender) {
    mDataManager.setUserGender(userGender);
  }

  public boolean isAuthorized() {
    return !TextUtils.isEmpty(mDataManager.getToken());
  }

  public void clear() {
    mDataManager.clear();
  }

  /*Additional Fields*/

  public String getAdditionalField(String key, String defaultValue) {
    return mDataManager.getAdditionalField(key, defaultValue);
  }

  public void setAdditionalField(String key, String value) {
    mDataManager.setAdditionalField(key, value);
  }

  public void setAdditionalField(String key, int value) {
    mDataManager.setAdditionalField(key, value);
  }

  public long getAdditionalField(String key, long defaultValue) {
    return mDataManager.getAdditionalField(key, defaultValue);
  }

  public void setAdditionalField(String key, long value) {
    mDataManager.setAdditionalField(key, value);
  }

  public boolean getAdditionalField(String key, boolean defaultValue) {
    return mDataManager.getAdditionalField(key, defaultValue);
  }

  public void setAdditionalField(String key, boolean value) {
    mDataManager.setAdditionalField(key, value);
  }

  public Observable<Response<Void>> populateAdditionalField(String key, String value) {
    if (!mDataManager.isExistAdditionalField(key)) {
      return mDataManager.addAdditionalField(key, value).concatMap(response -> {
        if (response.isSuccessful()) {
          mDataManager.setAdditionalField(key, value);
        }
        return Observable.just(response);
      });
    } else {
      return mDataManager.updateAdditionalField(key, value).concatMap(response -> {
        if (response.isSuccessful()) {
          mDataManager.setAdditionalField(key, value);
        }
        return Observable.just(response);
      });
    }
  }

  public Observable<Response<Void>> populateAdditionalField(String key, int value) {
    return populateAdditionalField(key, String.valueOf(value));
  }

  public Observable<Response<Void>> populateAdditionalField(String key, long value) {
    return populateAdditionalField(key, String.valueOf(value));
  }

  public Observable<Response<Void>> populateAdditionalField(String key, boolean value) {
    return populateAdditionalField(key, String.valueOf(value));
  }

}


