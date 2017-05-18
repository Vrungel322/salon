package com.apps.twelve.floor.salon.feature.settings.presenters;

import android.net.Uri;
import com.apps.twelve.floor.salon.App;
import com.apps.twelve.floor.salon.base.BasePresenter;
import com.apps.twelve.floor.salon.data.DataManager;
import com.apps.twelve.floor.salon.feature.settings.views.ISettingsFragmentView;
import com.apps.twelve.floor.salon.utils.RxBus;
import com.apps.twelve.floor.salon.utils.RxBusHelper;
import com.apps.twelve.floor.salon.utils.ThreadSchedulers;
import com.arellomobile.mvp.InjectViewState;
import javax.inject.Inject;
import rx.Subscription;
import timber.log.Timber;

/**
 * Created by Alexandra on 18.04.2017.
 */

@InjectViewState public class SettingsFragmentPresenter
    extends BasePresenter<ISettingsFragmentView> {

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

  public void saveGender(int gender) {
    mDataManager.setProfileGender(gender);
  }

  private void setUserInfo() {
    Subscription subscription = mDataManager.getProfileName()
        .compose(ThreadSchedulers.applySchedulers())
        .subscribe(s -> getViewState().setUserName(s), Timber::e);
    addToUnsubscription(subscription);
    subscription = mDataManager.getProfileLogin()
        .compose(ThreadSchedulers.applySchedulers())
        .subscribe(s -> getViewState().setUserLogin(s), Timber::e);
    addToUnsubscription(subscription);
    subscription = mDataManager.getProfilePassword()
        .compose(ThreadSchedulers.applySchedulers())
        .subscribe(s -> getViewState().setUserPassword(s), Timber::e);
    addToUnsubscription(subscription);
    subscription = mDataManager.getProfileEmail()
        .compose(ThreadSchedulers.applySchedulers())
        .subscribe(s -> getViewState().setUserEmail(s), Timber::e);
    addToUnsubscription(subscription);
    subscription = mDataManager.getProfilePhone()
        .compose(ThreadSchedulers.applySchedulers())
        .subscribe(s -> getViewState().setUserPhone(s), Timber::e);
    addToUnsubscription(subscription);
    subscription = mDataManager.getProfileGender()
        .compose(ThreadSchedulers.applySchedulers())
        .subscribe(s -> getViewState().setUserGender(s), Timber::e);
    addToUnsubscription(subscription);
  }

  private void subscribeUpdateUserInfo() {
    Subscription subscription = mRxBus.filteredObservable(RxBusHelper.UpdateUserInfo.class)
        .concatMap(updateUserInfo -> mDataManager.getProfileName())
        .compose(ThreadSchedulers.applySchedulers())
        .subscribe(s -> getViewState().setUserName(s), Timber::e);
    addToUnsubscription(subscription);
    subscription = mRxBus.filteredObservable(RxBusHelper.UpdateUserInfo.class)
        .concatMap(updateUserInfo -> mDataManager.getProfileLogin())
        .compose(ThreadSchedulers.applySchedulers())
        .subscribe(s -> getViewState().setUserLogin(s), Timber::e);
    addToUnsubscription(subscription);
    subscription = mRxBus.filteredObservable(RxBusHelper.UpdateUserInfo.class)
        .concatMap(updateUserInfo -> mDataManager.getProfilePassword())
        .compose(ThreadSchedulers.applySchedulers())
        .subscribe(s -> getViewState().setUserPassword(s), Timber::e);
    addToUnsubscription(subscription);
    subscription = mRxBus.filteredObservable(RxBusHelper.UpdateUserInfo.class)
        .concatMap(updateUserInfo -> mDataManager.getProfileEmail())
        .compose(ThreadSchedulers.applySchedulers())
        .subscribe(s -> getViewState().setUserEmail(s), Timber::e);
    addToUnsubscription(subscription);
    subscription = mRxBus.filteredObservable(RxBusHelper.UpdateUserInfo.class)
        .concatMap(updateUserInfo -> mDataManager.getProfilePhone())
        .compose(ThreadSchedulers.applySchedulers())
        .subscribe(s -> getViewState().setUserPhone(s), Timber::e);
    addToUnsubscription(subscription);
    subscription = mRxBus.filteredObservable(RxBusHelper.UpdateUserInfo.class)
        .concatMap(updateUserInfo -> mDataManager.getProfileGender())
        .compose(ThreadSchedulers.applySchedulers())
        .subscribe(s -> getViewState().setUserGender(s), Timber::e);
    addToUnsubscription(subscription);
  }
}
