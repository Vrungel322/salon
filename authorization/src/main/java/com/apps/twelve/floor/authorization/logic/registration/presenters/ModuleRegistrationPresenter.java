package com.apps.twelve.floor.authorization.logic.registration.presenters;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import com.apps.twelve.floor.authorization.App;
import com.apps.twelve.floor.authorization.R;
import com.apps.twelve.floor.authorization.base.BasePresenter;
import com.apps.twelve.floor.authorization.data.DataManager;
import com.apps.twelve.floor.authorization.data.model.DeviceInfoEntity;
import com.apps.twelve.floor.authorization.data.model.ErrorContentEntity;
import com.apps.twelve.floor.authorization.data.model.UserEntity;
import com.apps.twelve.floor.authorization.logic.registration.views.IModuleRegistrationActivityView;
import com.apps.twelve.floor.authorization.utils.AuthRxBus;
import com.apps.twelve.floor.authorization.utils.AuthRxBusHelper;
import com.apps.twelve.floor.authorization.utils.ThreadSchedulers;
import com.arellomobile.mvp.InjectViewState;
import com.google.gson.Gson;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import javax.inject.Inject;
import okhttp3.ResponseBody;
import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import timber.log.Timber;

import static com.apps.twelve.floor.authorization.utils.Constants.Remote.RESPONSE_CONTENT_ERROR;
import static com.apps.twelve.floor.authorization.utils.Constants.Remote.RESPONSE_SUCCESS;

/**
 * Created by Alexander Svyatetsky on 22.04.2017.
 */

@InjectViewState public class ModuleRegistrationPresenter
    extends BasePresenter<IModuleRegistrationActivityView> {

  @Inject protected Context mContext;
  @Inject protected AuthRxBus mAuthRxBus;
  @Inject protected DataManager mDataManager;

  @Override protected void inject() {
    App.getAppComponent().inject(this);
  }

  @Override protected void onFirstViewAttach() {
    super.onFirstViewAttach();
    subscribeConnectException();
  }

  public void register(UserEntity userEntity) {

    DeviceInfoEntity deviceInfoEntity = new DeviceInfoEntity();
    deviceInfoEntity.setName(Build.MODEL);
    deviceInfoEntity.setOs(
        mContext.getString(R.string.device_info_os_name) + Build.VERSION.RELEASE);
    userEntity.setDeviceInfo(new Gson().toJson(deviceInfoEntity));

    Subscription subscription = mDataManager.register(userEntity)
        .compose(ThreadSchedulers.applySchedulers())
        .concatMap(response -> {
          switch (response.code()) {
            case RESPONSE_SUCCESS:
              getViewState().stopAnimation();
              mDataManager.setToken(response.body().getToken());
              return Observable.just(response);
            case RESPONSE_CONTENT_ERROR:
              handleError(response.errorBody());
              return Observable.error(new Exception("Response content error"));
          }
          return Observable.just(response);
        })
        .delay(1, TimeUnit.SECONDS, AndroidSchedulers.mainThread())
        .concatMap(response -> mDataManager.getUserProfile())
        .subscribe(response -> {
          if (response.isSuccessful()) {
            saveUser(response.body());
            mAuthRxBus.post(new AuthRxBusHelper.CloseActivityEvent());
            getViewState().finishActivity();
          }
        }, throwable -> {
          getViewState().revertAnimation();
          showMessageConnectException(throwable);
        });

    addToUnsubscription(subscription);
  }

  private void saveUser(UserEntity user) {
    mDataManager.setUserId(user.getId());
    mDataManager.setUserName(user.getFullName());
    mDataManager.setUserEmail(user.getEmail());
    mDataManager.setUserPhone(user.getPhone());
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
        getViewState().showPhoneError(content.getPhoneError().get(0));
      }
      if (content.getEmailError() != null && !TextUtils.isEmpty(content.getEmailError().get(0))) {
        getViewState().showEmailError(content.getEmailError().get(0));
      }
    }
  }

  private void subscribeConnectException() {
    Subscription subscription =
        mAuthRxBus.filteredObservable(AuthRxBusHelper.MessageConnectException.class)
            .compose(ThreadSchedulers.applySchedulers())
            .subscribe(event -> getViewState().showConnectErrorMessage(), Timber::e);
    addToUnsubscription(subscription);
  }
}
