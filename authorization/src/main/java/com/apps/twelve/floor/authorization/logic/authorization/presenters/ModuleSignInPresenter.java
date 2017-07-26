package com.apps.twelve.floor.authorization.logic.authorization.presenters;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import com.apps.twelve.floor.authorization.App;
import com.apps.twelve.floor.authorization.R;
import com.apps.twelve.floor.authorization.base.BasePresenter;
import com.apps.twelve.floor.authorization.data.DataManager;
import com.apps.twelve.floor.authorization.data.model.CredentialsEntity;
import com.apps.twelve.floor.authorization.data.model.DeviceInfoEntity;
import com.apps.twelve.floor.authorization.data.model.ErrorContentEntity;
import com.apps.twelve.floor.authorization.data.model.UserEntity;
import com.apps.twelve.floor.authorization.logic.authorization.views.IModuleSignInActivityView;
import com.apps.twelve.floor.authorization.social.SocialLoginManager;
import com.apps.twelve.floor.authorization.utils.AuthRxBus;
import com.apps.twelve.floor.authorization.utils.AuthRxBusHelper;
import com.apps.twelve.floor.authorization.utils.DateUtils;
import com.apps.twelve.floor.authorization.utils.ThreadSchedulers;
import com.apps.twelve.floor.authorization.utils.ValidatorUtils;
import com.arellomobile.mvp.InjectViewState;
import com.google.gson.Gson;
import java.io.IOException;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;
import javax.inject.Inject;
import okhttp3.ResponseBody;
import org.json.JSONException;
import org.json.JSONObject;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import timber.log.Timber;

import static com.apps.twelve.floor.authorization.utils.Constants.Genders.FEMALE;
import static com.apps.twelve.floor.authorization.utils.Constants.Genders.MALE;
import static com.apps.twelve.floor.authorization.utils.Constants.Remote.RESPONSE_CONTENT_ERROR;
import static com.apps.twelve.floor.authorization.utils.Constants.Remote.RESPONSE_UNAUTHORIZED;

/**
 * Created by Alexander Svyatetsky on 22.04.2017.
 */

@InjectViewState public class ModuleSignInPresenter
    extends BasePresenter<IModuleSignInActivityView> {

  @Inject protected AuthRxBus mAuthRxBus;
  @Inject protected Context mContext;
  @Inject protected DataManager mDataManager;

  @Override protected void inject() {
    App.getAppComponent().inject(this);
  }

  @Override protected void onFirstViewAttach() {
    super.onFirstViewAttach();
    subscribeRegistrationSuccess();
    subscribeConnectException();
  }

  public void login(String login, String password) {

    CredentialsEntity credentialsEntity = new CredentialsEntity();
    credentialsEntity.setPassword(password);

    if (ValidatorUtils.isValidEmail(login)) {
      credentialsEntity.setEmail(login);
    } else {
      credentialsEntity.setPhone(login);
    }

    DeviceInfoEntity deviceInfoEntity = new DeviceInfoEntity();
    deviceInfoEntity.setName(Build.MODEL);
    deviceInfoEntity.setOs(
        mContext.getString(R.string.device_info_os_name) + Build.VERSION.RELEASE);
    credentialsEntity.setDeviceInfo(new Gson().toJson(deviceInfoEntity));

    Subscription subscription = mDataManager.login(credentialsEntity)
        .compose(ThreadSchedulers.applySchedulers())
        .doOnNext(response -> {
          if (response.isSuccessful()) {
            mDataManager.setToken(response.body().getToken());
            getViewState().stopAnimation();
          } else if (response.code() == RESPONSE_CONTENT_ERROR) {
            getViewState().revertAnimation();
            handleError(response.errorBody());
          } else if (response.code() == RESPONSE_UNAUTHORIZED) {
            getViewState().revertAnimation();
            getViewState().showPasswordError();
          }
        })
        .concatMap(response -> mDataManager.getUserProfile())
        .delay(1, TimeUnit.SECONDS, AndroidSchedulers.mainThread())
        .subscribe(response -> {
          saveUser(response.body());
          getViewState().finishActivity();
        }, throwable -> {
          showMessageConnectException(throwable);
          getViewState().revertAnimation();
        });
    addToUnsubscription(subscription);
  }

  public void loginByFacebook() {
    SocialLoginManager.getInstance(mContext)
        .facebook()
        .login()
        .compose(ThreadSchedulers.applySchedulers())
        .subscribe(socialUser -> {
          UserEntity userEntity = new UserEntity();
          userEntity.setProviderId(socialUser.userId);
          userEntity.setFirstName(socialUser.profile.firstName);
          userEntity.setLastName(socialUser.profile.lastName);
          userEntity.setEmail(socialUser.profile.email);
          userEntity.setPictureUrl(socialUser.photoUrl);
          userEntity.setProvider(mContext.getString(R.string.sign_in_facebook));
          if (!TextUtils.isEmpty(socialUser.profile.email)) {
            loginBySocialNetwork(userEntity);
            getViewState().showProgressDialog();
          } else {
            getViewState().showRegistrationActivity(userEntity);
          }
        }, this::showMessageConnectException);
  }

  public void loginByGoogle() {
    SocialLoginManager.getInstance(mContext)
        .google(mContext.getString(R.string.default_web_client_id))
        .login()
        .compose(ThreadSchedulers.applySchedulers())
        .subscribe(socialUser -> {
          UserEntity userEntity = new UserEntity();
          userEntity.setProviderId(socialUser.userId);
          userEntity.setFirstName(socialUser.profile.firstName);
          userEntity.setLastName(socialUser.profile.lastName);
          userEntity.setEmail(socialUser.profile.email);
          userEntity.setPictureUrl(socialUser.photoUrl);
          userEntity.setProvider(mContext.getString(R.string.sign_in_google));

          if (!TextUtils.isEmpty(socialUser.profile.email)) {
            loginBySocialNetwork(userEntity);
            getViewState().showProgressDialog();
          } else {
            getViewState().showRegistrationActivity(userEntity);
          }
        }, this::showMessageConnectException);
  }

  private void loginBySocialNetwork(UserEntity userEntity) {

    DeviceInfoEntity deviceInfoEntity = new DeviceInfoEntity();
    deviceInfoEntity.setName(Build.MODEL);
    deviceInfoEntity.setOs(
        mContext.getString(R.string.device_info_os_name) + Build.VERSION.RELEASE);
    userEntity.setDeviceInfo(new Gson().toJson(deviceInfoEntity));

    Subscription subscription = mDataManager.loginBySocialNetwork(userEntity)
        .compose(ThreadSchedulers.applySchedulers())
        .doOnNext(response -> {
          if (response.isSuccessful()) {
            mDataManager.setToken(response.body().getToken());
          }
        })
        .concatMap(response -> mDataManager.getUserProfile())
        .subscribe(response -> {
          if (response.isSuccessful()) {
            saveUser(response.body());
            getViewState().closeProgressDialog();
            getViewState().finishActivity();
          }
        }, throwable -> {
          getViewState().closeProgressDialog();
          showMessageConnectException(throwable);
        });

    addToUnsubscription(subscription);
  }

  private void saveUser(UserEntity user) {
    mDataManager.setUserId(user.getId());
    mDataManager.setUserName(user.getFullName());
    mDataManager.setUserEmail(user.getEmail());
    mDataManager.setUserPhone(user.getPhone());
    mDataManager.setUserImage(user.getPicture());
    mDataManager.setUserGenderPosition(getGenderPosition(user.getGender()));
    mDataManager.setUserBirthDay(DateUtils.formatServerDate(user.getBirthday()));
    saveAdditionalFields(user.getAdditionalFields());
  }

  private int getGenderPosition(String gender) {
    if (gender == null) return 0;
    switch (gender) {
      case MALE:
        return 1;
      case FEMALE:
        return 2;
      default:
        return 0;
    }
  }

  private void saveAdditionalFields(String additionalFields) {
    if (!TextUtils.isEmpty(additionalFields)) {
      JSONObject jsonObject = null;
      try {
        jsonObject = new JSONObject(additionalFields);
      } catch (JSONException e) {
        Timber.e(e);
      }
      if (jsonObject != null) {
        Iterator<?> keys = jsonObject.keys();
        while (keys.hasNext()) {
          String key = (String) keys.next();
          mDataManager.setAdditionalField(key, jsonObject.optString(key));
        }
      }
    }
  }

  private void subscribeRegistrationSuccess() {
    Subscription subscription =
        mAuthRxBus.filteredObservable(AuthRxBusHelper.CloseActivityEvent.class)
            .compose(ThreadSchedulers.applySchedulers())
            .subscribe(event -> getViewState().finishActivity(), Timber::e);
    addToUnsubscription(subscription);
  }

  private void subscribeConnectException() {
    Subscription subscription =
        mAuthRxBus.filteredObservable(AuthRxBusHelper.MessageConnectException.class)
            .compose(ThreadSchedulers.applySchedulers())
            .subscribe(event -> getViewState().showConnectErrorMessage(), Timber::e);
    addToUnsubscription(subscription);
  }

  private void handleError(ResponseBody errorBody) {
    ErrorContentEntity content = null;
    try {
      content = new Gson().fromJson(errorBody.string(), ErrorContentEntity.class);
    } catch (IOException e) {
      e.printStackTrace();
    }
    if (content != null) {
      if (content.getPhoneError() != null && !TextUtils.isEmpty(content.getPhoneError().get(0))) {
        getViewState().showLoginError(content.getPhoneError().get(0));
      }
      if (content.getEmailError() != null && !TextUtils.isEmpty(content.getEmailError().get(0))) {
        getViewState().showLoginError(content.getEmailError().get(0));
      }
    }
  }
}
