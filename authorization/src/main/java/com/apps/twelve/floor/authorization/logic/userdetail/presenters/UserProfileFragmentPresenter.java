package com.apps.twelve.floor.authorization.logic.userdetail.presenters;

import android.content.Context;
import android.net.Uri;
import com.apps.twelve.floor.authorization.App;
import com.apps.twelve.floor.authorization.R;
import com.apps.twelve.floor.authorization.base.BasePresenter;
import com.apps.twelve.floor.authorization.data.DataManager;
import com.apps.twelve.floor.authorization.data.model.Genders;
import com.apps.twelve.floor.authorization.data.model.UserEntity;
import com.apps.twelve.floor.authorization.logic.userdetail.views.IUserProfileFragmentView;
import com.apps.twelve.floor.authorization.utils.AuthRxBus;
import com.apps.twelve.floor.authorization.utils.AuthRxBusHelper;
import com.apps.twelve.floor.authorization.utils.DateUtils;
import com.apps.twelve.floor.authorization.utils.ThreadSchedulers;
import com.arellomobile.mvp.InjectViewState;
import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import javax.inject.Inject;
import rx.Observable;
import rx.Subscription;
import timber.log.Timber;

import static com.apps.twelve.floor.authorization.utils.Constants.Remote.RESPONSE_CONTENT_ERROR;
import static com.apps.twelve.floor.authorization.utils.Constants.Remote.RESPONSE_TOKEN_EXPIRED;
import static com.apps.twelve.floor.authorization.utils.Constants.Remote.RESPONSE_UNAUTHORIZED;

/**
 * Created by Alexandra on 18.04.2017.
 */

@InjectViewState public class UserProfileFragmentPresenter
    extends BasePresenter<IUserProfileFragmentView> {

  @Inject protected Context mContext;
  @Inject protected DataManager mDataManager;
  @Inject protected AuthRxBus mAuthRxBus;

  @Override protected void inject() {
    App.getAppComponent().inject(this);
  }

  @Override protected void onFirstViewAttach() {
    super.onFirstViewAttach();
    setUpPhoto();
    setUserInfo();

    //AuthRxBus
    subscribeUpdateUserInfo();
    subscribeConnectException();
  }

  private void setUpPhoto() {
    Subscription subscription = mDataManager.getObservableUserImage()
        .compose(ThreadSchedulers.applySchedulers())
        .subscribe(s -> getViewState().setUserPhoto(Uri.parse(s)), Timber::e);
    addToUnsubscription(subscription);
  }

  public void savePhoto(String uri) {
    try {
      File uploadFile = new File(new URI(uri));
      Subscription subscription =
          checkToken(mDataManager.updateUserPicture(uploadFile)).concatMap(response -> {
            if (response.code() == RESPONSE_TOKEN_EXPIRED) {
              return checkToken(mDataManager.updateUserPicture(uploadFile));
            }
            return Observable.just(response);
          }).compose(ThreadSchedulers.applySchedulers()).subscribe(response -> {
            if (response.isSuccessful()) {
              mDataManager.setUserImage(uri);
              setUpPhoto();
            } else if (response.code() == RESPONSE_CONTENT_ERROR) {
              getViewState().showToastMessage(R.string.error_invalid_picture);
            } else if (response.code() == RESPONSE_UNAUTHORIZED) {
              mAuthRxBus.post(new AuthRxBusHelper.UnauthorizedEvent());
            }
          }, throwable -> {
            Timber.e(throwable);
            showMessageConnectException(throwable);
          });
      addToUnsubscription(subscription);
    } catch (URISyntaxException e) {
      e.printStackTrace();
    }
  }

  public void saveGender(String gender) {
    UserEntity user = new UserEntity();
    String[] genders = mContext.getResources().getStringArray(R.array.gender);

    if (gender.equals(genders[0])) {
      user.setGender(mContext.getString(R.string.gender_unknown));
    } else if (gender.equals(genders[1])) {
      user.setGender(mContext.getString(R.string.gender_male));
    } else if (gender.equals(genders[2])) {
      user.setGender(mContext.getString(R.string.gender_female));
    }

    Subscription subscription =
        checkToken(mDataManager.updateUserProfile(user)).concatMap(response -> {
          if (response.code() == RESPONSE_TOKEN_EXPIRED) {
            return checkToken(mDataManager.updateUserProfile(user));
          }
          return Observable.just(response);
        }).compose(ThreadSchedulers.applySchedulers()).subscribe(response -> {
          if (response.isSuccessful()) {
            mDataManager.setUserGender(gender);
            mAuthRxBus.post(new AuthRxBusHelper.UpdateUserInfo());
            getViewState().closeChangeGenderDialog();
          } else if (response.code() == RESPONSE_UNAUTHORIZED) {
            mAuthRxBus.post(new AuthRxBusHelper.UnauthorizedEvent());
          }
        }, this::showMessageConnectException);

    addToUnsubscription(subscription);
  }

  public void saveUserBirthDay(String date) {
    UserEntity user = new UserEntity();
    user.setBirthday(date);

    Subscription subscription =
        checkToken(mDataManager.updateUserProfile(user)).concatMap(response -> {
          if (response.code() == RESPONSE_TOKEN_EXPIRED) {
            return checkToken(mDataManager.updateUserProfile(user));
          }
          return Observable.just(response);
        }).compose(ThreadSchedulers.applySchedulers()).subscribe(response -> {
          if (response.isSuccessful()) {
            mDataManager.setUserBirthDay(DateUtils.formatServerDate(date));
            mAuthRxBus.post(new AuthRxBusHelper.UpdateUserInfo());
          } else if (response.code() == RESPONSE_UNAUTHORIZED) {
            mAuthRxBus.post(new AuthRxBusHelper.UnauthorizedEvent());
          }
        }, this::showMessageConnectException);
    addToUnsubscription(subscription);
  }

  private void setUserInfo() {
    Subscription subscription = mDataManager.getObservableUserName()
        .compose(ThreadSchedulers.applySchedulers())
        .subscribe(s -> getViewState().setUserName(s), Timber::e);
    addToUnsubscription(subscription);
    subscription = mDataManager.getObservableUserEmail()
        .compose(ThreadSchedulers.applySchedulers())
        .subscribe(s -> getViewState().setUserEmail(s), Timber::e);
    addToUnsubscription(subscription);
    subscription = mDataManager.getObservableUserPhone()
        .compose(ThreadSchedulers.applySchedulers())
        .subscribe(s -> getViewState().setUserPhone(s), Timber::e);
    addToUnsubscription(subscription);
    subscription = mDataManager.getObservableUserGender()
        .compose(ThreadSchedulers.applySchedulers())
        .subscribe(s -> getViewState().setUserGender(s), Timber::e);
    addToUnsubscription(subscription);
    subscription = mDataManager.getObservableUserBirthDay()
        .compose(ThreadSchedulers.applySchedulers())
        .subscribe(s -> getViewState().setUserBirthDay(s), Timber::e);
    addToUnsubscription(subscription);
  }

  private void subscribeUpdateUserInfo() {
    Subscription subscription = mAuthRxBus.filteredObservable(AuthRxBusHelper.UpdateUserInfo.class)
        .concatMap(updateUserInfo -> mDataManager.getObservableUserName())
        .compose(ThreadSchedulers.applySchedulers())
        .subscribe(s -> getViewState().setUserName(s), Timber::e);
    addToUnsubscription(subscription);
    subscription = mAuthRxBus.filteredObservable(AuthRxBusHelper.UpdateUserInfo.class)
        .concatMap(updateUserInfo -> mDataManager.getObservableUserEmail())
        .compose(ThreadSchedulers.applySchedulers())
        .subscribe(s -> getViewState().setUserEmail(s), Timber::e);
    addToUnsubscription(subscription);
    subscription = mAuthRxBus.filteredObservable(AuthRxBusHelper.UpdateUserInfo.class)
        .concatMap(updateUserInfo -> mDataManager.getObservableUserPhone())
        .compose(ThreadSchedulers.applySchedulers())
        .subscribe(s -> getViewState().setUserPhone(s), Timber::e);
    addToUnsubscription(subscription);
    subscription = mAuthRxBus.filteredObservable(AuthRxBusHelper.UpdateUserInfo.class)
        .concatMap(updateUserInfo -> mDataManager.getObservableUserGender())
        .compose(ThreadSchedulers.applySchedulers())
        .subscribe(s -> getViewState().setUserGender(s), Timber::e);
    addToUnsubscription(subscription);
    subscription = mAuthRxBus.filteredObservable(AuthRxBusHelper.UpdateUserInfo.class)
        .concatMap(updateUserInfo -> mDataManager.getObservableUserBirthDay())
        .compose(ThreadSchedulers.applySchedulers())
        .subscribe(s -> getViewState().setUserBirthDay(s), Timber::e);
    addToUnsubscription(subscription);
  }

  public void logout() {
    closeExitDialog();
    //showExitProgressDialog();
    mDataManager.logout().compose(ThreadSchedulers.applySchedulers()).subscribe(response -> {
      //closeExitProgressDialog();
      mAuthRxBus.post(new AuthRxBusHelper.LogoutEvent());
    }, throwable -> {
      Timber.e(throwable);
      //closeExitProgressDialog();
      mAuthRxBus.post(new AuthRxBusHelper.LogoutEvent());
    });
  }

  private void subscribeConnectException() {
    Subscription subscription =
        mAuthRxBus.filteredObservable(AuthRxBusHelper.MessageConnectException.class)
            .compose(ThreadSchedulers.applySchedulers())
            .subscribe(event -> getViewState().showConnectErrorMessage(), Timber::e);
    addToUnsubscription(subscription);
  }

  public int getGenderPosition() {
    return Genders.getGenderPosition(mDataManager.getUserGender());
  }

  public void showChangeGenderDialog() {
    getViewState().showChangeGenderDialog();
  }

  public void closeChangeGenderDialog() {
    getViewState().closeChangeGenderDialog();
  }

  public void showExitDialog() {
    getViewState().showExitDialog();
  }

  public void showExitProgressDialog() {
    getViewState().showExitProgressDialog();
  }

  public void closeExitDialog() {
    getViewState().closeExitDialog();
  }

  public void closeExitProgressDialog() {
    getViewState().closeExitProgressDialog();
  }
}
