package com.apps.twelve.floor.salon.feature.settings.presenters;

import android.net.Uri;
import com.apps.twelve.floor.salon.App;
import com.apps.twelve.floor.salon.base.BasePresenter;
import com.apps.twelve.floor.salon.data.DataManager;
import com.apps.twelve.floor.salon.feature.settings.views.ISettingsActivityView;
import com.apps.twelve.floor.salon.utils.ThreadSchedulers;
import com.arellomobile.mvp.InjectViewState;
import javax.inject.Inject;
import rx.Subscription;
import timber.log.Timber;

/**
 * Created by Alexandra on 18.04.2017.
 */

@InjectViewState public class SettingsActivityPresenter
    extends BasePresenter<ISettingsActivityView> {

  @Override protected void inject() {
    App.getAppComponent().inject(this);
  }

  @Inject DataManager mDataManager;

  @Override protected void onFirstViewAttach() {
    super.onFirstViewAttach();
    setUpPhoto();
    setUpUserInfo();
  }

  private void setUpPhoto() {
    Subscription subscription = mDataManager.getProfileImage()
        .compose(ThreadSchedulers.applySchedulers())
        .subscribe(s -> getViewState().setUserPhoto(Uri.parse(s)), Timber::e);
    addToUnsubscription(subscription);
  }

  public void savePhoto(String uri) {
    mDataManager.setProfileImage(uri);
    setUpPhoto();
  }

  public void setUpUserInfo() {
    setUpUserName();
    setUpUserLogin();
    setUpUserPassword();
    setUpUserEmail();
    setUpUserPhone();
    setUpUserGender();
  }

  public void saveGender(int gender) {
    mDataManager.setProfileGender(gender);
  }

  private void setUpUserName() {
    Subscription subscription = mDataManager.getProfileName()
        .compose(ThreadSchedulers.applySchedulers())
        .subscribe(s -> getViewState().setUserName(s), Timber::e);
    addToUnsubscription(subscription);
  }

  private void setUpUserLogin() {
    Subscription subscription = mDataManager.getProfileLogin()
        .compose(ThreadSchedulers.applySchedulers())
        .subscribe(s -> getViewState().setUserLogin(s), Timber::e);
    addToUnsubscription(subscription);
  }

  private void setUpUserPassword() {
    Subscription subscription = mDataManager.getProfilePassword()
        .compose(ThreadSchedulers.applySchedulers())
        .subscribe(s -> getViewState().setUserPassword(s), Timber::e);
    addToUnsubscription(subscription);
  }

  private void setUpUserEmail() {
    Subscription subscription = mDataManager.getProfileEmail()
        .compose(ThreadSchedulers.applySchedulers())
        .subscribe(s -> getViewState().setUserEmail(s), Timber::e);
    addToUnsubscription(subscription);
  }

  private void setUpUserPhone() {
    Subscription subscription = mDataManager.getProfilePhone()
        .compose(ThreadSchedulers.applySchedulers())
        .subscribe(s -> getViewState().setUserPhone(s), Timber::e);
    addToUnsubscription(subscription);
  }

  private void setUpUserGender() {
    Subscription subscription = mDataManager.getProfileGender()
        .compose(ThreadSchedulers.applySchedulers())
        .subscribe(s -> getViewState().setUserGender(s), Timber::e);
    addToUnsubscription(subscription);
  }
}
