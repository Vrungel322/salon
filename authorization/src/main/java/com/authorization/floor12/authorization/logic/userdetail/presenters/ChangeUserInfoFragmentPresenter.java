package com.authorization.floor12.authorization.logic.userdetail.presenters;

import android.text.TextUtils;
import com.arellomobile.mvp.InjectViewState;
import com.authorization.floor12.authorization.App;
import com.authorization.floor12.authorization.base.BasePresenter;
import com.authorization.floor12.authorization.data.DataManager;
import com.authorization.floor12.authorization.data.local.PreferencesHelper;
import com.authorization.floor12.authorization.data.model.ErrorContentEntity;
import com.authorization.floor12.authorization.data.model.UserEntity;
import com.authorization.floor12.authorization.logic.userdetail.views.IChangeUserInfoFragmentView;
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

import static com.authorization.floor12.authorization.utils.Constants.ChangingUserInfoField.EMAIL;
import static com.authorization.floor12.authorization.utils.Constants.ChangingUserInfoField.NAME;
import static com.authorization.floor12.authorization.utils.Constants.ChangingUserInfoField.PHONE;

/**
 * Created by Alexandra on 04.05.2017.
 */

@InjectViewState public class ChangeUserInfoFragmentPresenter
    extends BasePresenter<IChangeUserInfoFragmentView> {

  @Inject PreferencesHelper mPreferencesHelper;
  @Inject DataManager mDataManager;
  @Inject RxBus mRxBus;

  @Override protected void inject() {
    App.getAppComponent().inject(this);
  }

  @Override protected void onFirstViewAttach() {
    super.onFirstViewAttach();
    subscribeConnectException();
  }

  public void saveInfo(int field, String value) {

    UserEntity user = addToWrapper(field, value);

    Subscription subscription = mDataManager.updateUserProfile(user).concatMap(response -> {
      if (response.code() == 401) {
        return mDataManager.refreshToken().concatMap(tokenResponse -> {
          mPreferencesHelper.setToken(tokenResponse.body().getToken());
          return mDataManager.updateUserProfile(user);
        });
      } else {
        return Observable.just(response);
      }
    }).compose(ThreadSchedulers.applySchedulers()).doOnNext(response -> {
      if (response.isSuccessful()) {
        getViewState().stopAnimation();
      }
    }).delay(1, TimeUnit.SECONDS, AndroidSchedulers.mainThread()).subscribe(response -> {
      if (response.isSuccessful()) {
        setUserInfo(field, value);
        mRxBus.post(new RxBusHelper.UpdateUserInfo());
        getViewState().closeFragment();
      } else if (response.code() == 400) {
        getViewState().revertAnimation();
        handleError(response.errorBody());
      } else if (response.code() == 500) {
        getViewState().revertAnimation();
        getViewState().showSignInActivity();
      }
    }, throwable -> {
      showMessageConnectException(throwable);
      getViewState().revertAnimation();
    });

    addToUnsubscription(subscription);
  }

  public void updateLogin(int field, UserEntity user) {

    Subscription subscription = mDataManager.updateLogin(user).concatMap(response -> {
      if (response.code() == 401) {
        return mDataManager.refreshToken().concatMap(tokenResponse -> {
          mPreferencesHelper.setToken(tokenResponse.body().getToken());
          return mDataManager.updateUserProfile(user);
        });
      } else {
        return Observable.just(response);
      }
    }).compose(ThreadSchedulers.applySchedulers()).doOnNext(response -> {
      if (response.isSuccessful()) {
        getViewState().stopAnimation();
      }
    }).delay(1, TimeUnit.SECONDS, AndroidSchedulers.mainThread()).subscribe(response -> {
      if (response.isSuccessful()) {
        String value;
        if (!TextUtils.isEmpty(user.getEmail())) {
          value = user.getEmail();
        } else {
          value = user.getPhone();
        }
        setUserInfo(field, value);
        mRxBus.post(new RxBusHelper.UpdateUserInfo());
        getViewState().closeFragment();
      } else if (response.code() == 400) {
        getViewState().revertAnimation();
        handleError(response.errorBody());
      } else if (response.code() == 500) {
        getViewState().revertAnimation();
        getViewState().showSignInActivity();
      }
    }, throwable -> {
      showMessageConnectException(throwable);
      getViewState().revertAnimation();
    });
    addToUnsubscription(subscription);
  }

  private UserEntity addToWrapper(int field, String value) {
    UserEntity userEntity = new UserEntity();
    switch (field) {
      case NAME:
        userEntity.setName(value);
        return userEntity;
      case EMAIL:
        userEntity.setEmail(value);
        return userEntity;
      case PHONE:
        userEntity.setPhone(value);
        return userEntity;
      default:
        return userEntity;
    }
  }

  private void setUserInfo(int field, String value) {
    switch (field) {
      case NAME:
        mDataManager.setUserName(value);
        break;
      case EMAIL:
        mDataManager.setUserEmail(value);
        break;
      case PHONE:
        mDataManager.setUserPhone(value);
        break;
    }
  }

  public void handleError(ResponseBody body) {
    ErrorContentEntity content = null;
    try {
      content = new Gson().fromJson(body.string(), ErrorContentEntity.class);
    } catch (IOException e) {
      e.printStackTrace();
    }
    if (content.getPhoneError() != null && !TextUtils.isEmpty(content.getPhoneError().get(0))) {
      getViewState().showPhoneError(content.getPhoneError().get(0));
    }
    if (content.getEmailError() != null && !TextUtils.isEmpty(content.getEmailError().get(0))) {
      getViewState().showEmailError(content.getEmailError().get(0));
    }
    if (content.getError() != null && !TextUtils.isEmpty(content.getError().get(0))) {
      getViewState().showPasswordError(content.getError().get(0));
    }
  }

  private void subscribeConnectException() {
    Subscription subscription = mRxBus.filteredObservable(RxBusHelper.MessageConnectException.class)
        .compose(ThreadSchedulers.applySchedulers())
        .subscribe(event -> getViewState().showConnectErrorMessage(), Timber::e);
    addToUnsubscription(subscription);
  }
}
