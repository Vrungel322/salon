package com.authorization.floor12.authorization.logic.authorization.presenters;

import android.content.Context;
import android.text.TextUtils;
import com.arellomobile.mvp.InjectViewState;
import com.authorization.floor12.authorization.App;
import com.authorization.floor12.authorization.R;
import com.authorization.floor12.authorization.base.BasePresenter;
import com.authorization.floor12.authorization.data.DataManager;
import com.authorization.floor12.authorization.data.local.PreferencesHelper;
import com.authorization.floor12.authorization.data.model.CredentialsEntity;
import com.authorization.floor12.authorization.data.model.UserEntity;
import com.authorization.floor12.authorization.logic.authorization.views.ISignInActivityView;
import com.authorization.floor12.authorization.utils.RxBus;
import com.authorization.floor12.authorization.utils.RxBusHelper;
import com.authorization.floor12.authorization.utils.ThreadSchedulers;
import com.authorization.floor12.authorization.utils.ValidatiorUtils;
import com.jaychang.slm.SocialLoginManager;
import java.util.concurrent.TimeUnit;
import javax.inject.Inject;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import timber.log.Timber;

/**
 * Created by Alexander Svyatetsky on 22.04.2017.
 */

@InjectViewState public class SignInActivityPresenter extends BasePresenter<ISignInActivityView> {

  @Inject RxBus mRxBus;
  @Inject Context mContext;
  @Inject DataManager mDataManager;
  @Inject PreferencesHelper mPreferencesHelper;

  @Override protected void inject() {
    App.getAppComponent().inject(this);
  }

  @Override protected void onFirstViewAttach() {
    super.onFirstViewAttach();
    subscribeConnectException();
  }

  public void login(String login, String password) {

    CredentialsEntity credentialsEntity = new CredentialsEntity();
    credentialsEntity.setPassword(password);

    if (ValidatiorUtils.EmailValidator(login)) {
      credentialsEntity.setEmail(login);
    } else {
      credentialsEntity.setPhone(login);
    }

    Subscription subscription = mDataManager.login(credentialsEntity)
        .compose(ThreadSchedulers.applySchedulers())
        .doOnNext(response -> {
          if (response.isSuccessful()) {
            mPreferencesHelper.setToken(response.body().getToken());
            getViewState().stopAnimation();
          } else if (response.code() == 401) {
            getViewState().showAlerter(R.string.login_error);
          }
        })
        .concatMap(response -> mDataManager.getUserProfile())
        .delay(1, TimeUnit.SECONDS, AndroidSchedulers.mainThread())
        .subscribe(user -> {
          saveUser(user);
          getViewState().finishActivity();
        }, throwable -> {
          showMessageConnectException(throwable);
          getViewState().revertAnimation();
        });
    addToUnsubscription(subscription);
  }

  public void loginByFacebook() {
    SocialLoginManager.getInstance(mContext).facebook().login().subscribe(socialUser -> {

      UserEntity userEntity = new UserEntity();
      userEntity.setProviderId(socialUser.userId);
      userEntity.setName(socialUser.profile.name);
      userEntity.setEmail(socialUser.profile.email);
      userEntity.setPicture(socialUser.photoUrl);
      userEntity.setProvider("facebook");

      if (!TextUtils.isEmpty(socialUser.profile.email)) {
        loginBySocialNetwork(userEntity);
      } else {
        getViewState().showRegistrationActivity(userEntity);
      }
    }, error -> {
      showMessageConnectException(error);
    });
  }

  public void loginByGoogle() {
    SocialLoginManager.getInstance(mContext)
        .google(/*mContext.getString(R.string.default_web_client_id)*/"123123123")
        .login()
        .subscribe(socialUser -> {
          UserEntity userEntity = new UserEntity();
          userEntity.setProviderId(socialUser.userId);
          userEntity.setName(socialUser.profile.name);
          userEntity.setEmail(socialUser.profile.email);
          userEntity.setPicture(socialUser.photoUrl);
          userEntity.setProvider("google");

          if (!TextUtils.isEmpty(socialUser.profile.email)) {
            loginBySocialNetwork(userEntity);
          } else {
            getViewState().showRegistrationActivity(userEntity);
          }
        }, error -> {
          showMessageConnectException(error);
        });
  }

  private void loginBySocialNetwork(UserEntity userEntity) {

    Subscription subscription = mDataManager.loginBySocialNetwork(userEntity)
        .compose(ThreadSchedulers.applySchedulers())
        .doOnNext(response -> {
          if (response.isSuccessful()) {
            mPreferencesHelper.setToken(response.body().getToken());
          }
        })
        .concatMap(response -> mDataManager.getUserProfile())
        .subscribe(user -> {
          saveUser(user);
          getViewState().finishActivity();
        }, throwable -> {
          showMessageConnectException(throwable);
        });

    addToUnsubscription(subscription);
  }

  private void saveUser(UserEntity user) {
    mPreferencesHelper.setUserId(user.getId());
    mPreferencesHelper.setUserName(user.getName());
    mPreferencesHelper.setUserEmail(user.getEmail());
    mPreferencesHelper.setUserPhone(user.getPhone());
    mPreferencesHelper.setUserPhoto(user.getPicture());
    mPreferencesHelper.setUserGender(user.getGender());
  }

  private void subscribeConnectException() {
    Subscription subscription = mRxBus.filteredObservable(RxBusHelper.MessageConnectException.class)
        .compose(ThreadSchedulers.applySchedulers())
        .subscribe(event -> getViewState().showConnectErrorMessage(), Timber::e);
    addToUnsubscription(subscription);
  }
}
