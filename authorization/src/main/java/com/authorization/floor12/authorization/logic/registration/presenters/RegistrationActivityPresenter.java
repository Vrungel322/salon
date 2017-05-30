package com.authorization.floor12.authorization.logic.registration.presenters;

import android.text.TextUtils;
import com.arellomobile.mvp.InjectViewState;
import com.authorization.floor12.authorization.App;
import com.authorization.floor12.authorization.base.BasePresenter;
import com.authorization.floor12.authorization.data.DataManager;
import com.authorization.floor12.authorization.data.local.PreferencesHelper;
import com.authorization.floor12.authorization.data.model.ErrorContentEntity;
import com.authorization.floor12.authorization.data.model.UserEntity;
import com.authorization.floor12.authorization.logic.registration.views.IRegistrationActivityView;
import com.authorization.floor12.authorization.utils.RxBus;
import com.authorization.floor12.authorization.utils.RxBusHelper;
import com.authorization.floor12.authorization.utils.ThreadSchedulers;
import com.google.gson.Gson;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import javax.inject.Inject;
import okhttp3.ResponseBody;
import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import timber.log.Timber;

/**
 * Created by Alexander Svyatetsky on 22.04.2017.
 */

@InjectViewState public class RegistrationActivityPresenter
    extends BasePresenter<IRegistrationActivityView> {

  @Inject RxBus mRxBus;
  @Inject DataManager mDataManager;
  @Inject PreferencesHelper mPreferencesHelper;

  @Override protected void inject() {
    App.getAppComponent().inject(this);
  }

  @Override protected void onFirstViewAttach() {
    super.onFirstViewAttach();
    subscribeConnectException();
  }

  public void register(UserEntity userEntity) {

    Subscription subscription = mDataManager.register(userEntity)
        .compose(ThreadSchedulers.applySchedulers())
        .concatMap(response -> {
          if (response.isSuccessful()) {
            getViewState().stopAnimation();
            mPreferencesHelper.setToken(response.body().getToken());
          }
          return Observable.just(response);
        })
        .delay(1, TimeUnit.SECONDS, AndroidSchedulers.mainThread())
        .subscribe(response -> {
          if (response.isSuccessful()) {
            saveUser(userEntity);
            getViewState().SignIn();
          } else if (response.code() == 400) {
            getViewState().revertAnimation();
            handleError(response.errorBody());
          }
        }, throwable -> {
          getViewState().revertAnimation();
          showMessageConnectException(throwable);
        });

    addToUnsubscription(subscription);
  }

  public void saveUser(UserEntity user) {
    mPreferencesHelper.setUserName(user.getName());
    mPreferencesHelper.setUserEmail(user.getEmail());
    mPreferencesHelper.setUserPhone(user.getPhone());
  }

  public void handleError(ResponseBody errorBody) {
    ErrorContentEntity content = null;
    try {
      content = new Gson().fromJson(errorBody.string(), ErrorContentEntity.class);
    } catch (IOException e) {
      e.printStackTrace();
    }
    if (content.getPhoneError() != null && !TextUtils.isEmpty(content.getPhoneError().get(0))) {
      getViewState().showPhoneError(content.getPhoneError().get(0));
    }
    if (content.getEmailError() != null && !TextUtils.isEmpty(content.getEmailError().get(0))) {
      getViewState().showEmailError(content.getEmailError().get(0));
    }
    getViewState().revertAnimation();
  }

  private void subscribeConnectException() {
    Subscription subscription = mRxBus.filteredObservable(RxBusHelper.MessageConnectException.class)
        .compose(ThreadSchedulers.applySchedulers())
        .subscribe(event -> getViewState().showConnectErrorMessage(), Timber::e);
    addToUnsubscription(subscription);
  }
}
