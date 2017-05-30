package com.authorization.floor12.authorization.logic.userdetail.presenters;

import android.content.Context;
import android.net.Uri;
import com.arellomobile.mvp.InjectViewState;
import com.authorization.floor12.authorization.App;
import com.authorization.floor12.authorization.base.BasePresenter;
import com.authorization.floor12.authorization.data.DataManager;
import com.authorization.floor12.authorization.data.local.PreferencesHelper;
import com.authorization.floor12.authorization.data.model.UserEntity;
import com.authorization.floor12.authorization.logic.userdetail.views.IUserProfileFragmentView;
import com.authorization.floor12.authorization.utils.RxBus;
import com.authorization.floor12.authorization.utils.RxBusHelper;
import com.authorization.floor12.authorization.utils.ThreadSchedulers;
import javax.inject.Inject;
import rx.Observable;
import rx.Subscription;
import timber.log.Timber;

/**
 * Created by Alexandra on 18.04.2017.
 */

@InjectViewState public class UserProfileFragmentPresenter
    extends BasePresenter<IUserProfileFragmentView> {

  @Inject Context mContext;
  @Inject PreferencesHelper mPreferencesHelper;
  @Inject DataManager mDataManager;
  @Inject RxBus mRxBus;

  @Override protected void inject() {
    App.getAppComponent().inject(this);
  }

  @Override protected void onFirstViewAttach() {
    super.onFirstViewAttach();
    setUpPhoto();
    setUserInfo();
    //RxBus
    subscribeUpdateUserInfo();
    subscribeConnectException();
  }

  private void setUpPhoto() {
    Subscription subscription = mDataManager.getUserPhoto()
        .compose(ThreadSchedulers.applySchedulers())
        .subscribe(s -> getViewState().setUserPhoto(Uri.parse(s)), Timber::e);
    addToUnsubscription(subscription);
  }

  public void savePhoto(String uri) {
    mDataManager.setProfileImage(uri);
    setUpPhoto();
  }

  public void saveGender(String gender) {

    UserEntity user = new UserEntity();
    user.setGender(gender);

    Subscription subscription = mDataManager.updateUserProfile(user).concatMap(response -> {
      if (response.code() == 401) {
        return mDataManager.refreshToken().concatMap(tokenResponse -> {
          mPreferencesHelper.setToken(tokenResponse.body().getToken());
          return mDataManager.updateUserProfile(user);
        });
      } else {
        return Observable.just(response);
      }
    }).subscribe(response -> {
      if (response.isSuccessful()) {
        mDataManager.setUserGender(gender);
      } else if (response.code() == 500) {
        getViewState().showSignIn();
      }
    }, throwable -> {
      showMessageConnectException(throwable);
    });

    addToUnsubscription(subscription);
  }

  private void setUserInfo() {
    Subscription subscription = mDataManager.getUserName()
        .compose(ThreadSchedulers.applySchedulers())
        .subscribe(s -> getViewState().setUserName(s), Timber::e);
    addToUnsubscription(subscription);
    subscription = mDataManager.getUserEmail()
        .compose(ThreadSchedulers.applySchedulers())
        .subscribe(s -> getViewState().setUserEmail(s), Timber::e);
    addToUnsubscription(subscription);
    subscription = mDataManager.getUserPhone()
        .compose(ThreadSchedulers.applySchedulers())
        .subscribe(s -> getViewState().setUserPhone(s), Timber::e);
    addToUnsubscription(subscription);
    subscription = mDataManager.getUserGender()
        .compose(ThreadSchedulers.applySchedulers())
        .subscribe(s -> getViewState().setUserGender(s), Timber::e);
    addToUnsubscription(subscription);
  }

  private void subscribeUpdateUserInfo() {
    Subscription subscription = mRxBus.filteredObservable(RxBusHelper.UpdateUserInfo.class)
        .concatMap(updateUserInfo -> mDataManager.getUserName())
        .compose(ThreadSchedulers.applySchedulers())
        .subscribe(s -> getViewState().setUserName(s), Timber::e);
    addToUnsubscription(subscription);
    subscription = mRxBus.filteredObservable(RxBusHelper.UpdateUserInfo.class)
        .concatMap(updateUserInfo -> mDataManager.getUserEmail())
        .compose(ThreadSchedulers.applySchedulers())
        .subscribe(s -> getViewState().setUserEmail(s), Timber::e);
    addToUnsubscription(subscription);
    subscription = mRxBus.filteredObservable(RxBusHelper.UpdateUserInfo.class)
        .concatMap(updateUserInfo -> mDataManager.getUserPhone())
        .compose(ThreadSchedulers.applySchedulers())
        .subscribe(s -> getViewState().setUserPhone(s), Timber::e);
    addToUnsubscription(subscription);
    subscription = mRxBus.filteredObservable(RxBusHelper.UpdateUserInfo.class)
        .concatMap(updateUserInfo -> mDataManager.getUserGender())
        .compose(ThreadSchedulers.applySchedulers())
        .subscribe(s -> getViewState().setUserGender(s), Timber::e);
    addToUnsubscription(subscription);
  }

  public void logout() {
    mDataManager.logout()
        .compose(ThreadSchedulers.applySchedulers())
        .subscribe(response -> getViewState().showSignIn());
  }

  public void logoutAll() {
    mDataManager.logoutAll()
        .compose(ThreadSchedulers.applySchedulers())
        .subscribe(response -> getViewState().showSignIn());
  }

  private void subscribeConnectException() {
    Subscription subscription = mRxBus.filteredObservable(RxBusHelper.MessageConnectException.class)
        .compose(ThreadSchedulers.applySchedulers())
        .subscribe(event -> getViewState().showConnectErrorMessage(), Timber::e);
    addToUnsubscription(subscription);
  }
}
