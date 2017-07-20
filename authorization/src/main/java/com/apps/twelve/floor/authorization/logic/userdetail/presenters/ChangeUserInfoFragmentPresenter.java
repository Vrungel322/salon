package com.apps.twelve.floor.authorization.logic.userdetail.presenters;

import android.text.TextUtils;
import com.apps.twelve.floor.authorization.App;
import com.apps.twelve.floor.authorization.R;
import com.apps.twelve.floor.authorization.base.BasePresenter;
import com.apps.twelve.floor.authorization.data.model.ErrorContentEntity;
import com.apps.twelve.floor.authorization.data.model.UserEntity;
import com.apps.twelve.floor.authorization.logic.userdetail.views.IChangeUserInfoFragmentView;
import com.apps.twelve.floor.authorization.utils.AuthRxBusHelper;
import com.apps.twelve.floor.authorization.utils.ThreadSchedulers;
import com.arellomobile.mvp.InjectViewState;
import com.google.gson.Gson;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import okhttp3.ResponseBody;
import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import timber.log.Timber;

import static com.apps.twelve.floor.authorization.utils.Constants.ChangingUserInfoField.EMAIL;
import static com.apps.twelve.floor.authorization.utils.Constants.ChangingUserInfoField.NAME;
import static com.apps.twelve.floor.authorization.utils.Constants.ChangingUserInfoField.PHONE;
import static com.apps.twelve.floor.authorization.utils.Constants.Remote.RESPONSE_CONTENT_ERROR;
import static com.apps.twelve.floor.authorization.utils.Constants.Remote.RESPONSE_TOKEN_EXPIRED;
import static com.apps.twelve.floor.authorization.utils.Constants.Remote.RESPONSE_UNAUTHORIZED;

/**
 * Created by Alexandra on 04.05.2017.
 */

@InjectViewState public class ChangeUserInfoFragmentPresenter
    extends BasePresenter<IChangeUserInfoFragmentView> {

  @Override protected void inject() {
    App.getAppComponent().inject(this);
  }

  @Override protected void onFirstViewAttach() {
    super.onFirstViewAttach();
    subscribeConnectException();
  }

  public void saveInfo(int field, String value) {
    UserEntity user = addToWrapper(field, value);
    Subscription subscription =
        checkToken(mDataManager.updateUserProfile(user)).concatMap(response -> {
          if (response.code() == RESPONSE_TOKEN_EXPIRED) {
            return checkToken(mDataManager.updateUserProfile(user));
          }
          return Observable.just(response);
        }).compose(ThreadSchedulers.applySchedulers()).doOnNext(response -> {
          if (response.isSuccessful()) {
            getViewState().stopAnimation();
          }
        }).delay(1, TimeUnit.SECONDS, AndroidSchedulers.mainThread()).subscribe(response -> {
          if (response.isSuccessful()) {
            setUserInfo(field, value);
            mAuthRxBus.post(new AuthRxBusHelper.UpdateUserInfo());
            getViewState().closeFragment();
          } else if (response.code() == RESPONSE_CONTENT_ERROR) {
            getViewState().revertAnimation();
            handleError(response.errorBody());
          } else if (response.code() == RESPONSE_UNAUTHORIZED) {
            getViewState().revertAnimation();
            mAuthRxBus.post(new AuthRxBusHelper.UnauthorizedEvent());
          }
        }, throwable -> {
          showMessageConnectException(throwable);
          getViewState().revertAnimation();
        });

    addToUnsubscription(subscription);
  }

  public void updateEmail1(UserEntity user) {
    // TODO: 15.06.2017 response code
    Subscription subscription = checkToken(mDataManager.updateEmail1(user)).concatMap(response -> {
      if (response.code() == RESPONSE_TOKEN_EXPIRED) {
        return checkToken(mDataManager.updateEmail1(user));
      }
      return Observable.just(response);
    }).compose(ThreadSchedulers.applySchedulers()).doOnNext(response -> {
      if (response.isSuccessful()) {
        getViewState().stopAnimation();
      }
    }).delay(1, TimeUnit.SECONDS, AndroidSchedulers.mainThread()).subscribe(response -> {
      if (response.isSuccessful()) {
        getViewState().showDialogMessage(
            mContext.getString(R.string.dialog_message_recovery_password,
                mContext.getString(R.string.auth_email)));
      } else if (response.code() == RESPONSE_CONTENT_ERROR) {
        getViewState().revertAnimation();
        handleError(response.errorBody());
      } else if (response.code() == RESPONSE_UNAUTHORIZED) {
        getViewState().revertAnimation();
        mAuthRxBus.post(new AuthRxBusHelper.UnauthorizedEvent());
      }
    }, throwable -> {
      showMessageConnectException(throwable);
      getViewState().revertAnimation();
    });
    addToUnsubscription(subscription);
  }

  public void updateEmail2(UserEntity user) {
    Subscription subscription = checkToken(mDataManager.updateEmail2(user)).concatMap(response -> {
      if (response.code() == RESPONSE_TOKEN_EXPIRED) {
        return checkToken(mDataManager.updateEmail2(user));
      }
      return Observable.just(response);
    }).compose(ThreadSchedulers.applySchedulers()).concatMap(response -> {
      if (response.isSuccessful()) {
        getViewState().stopVerifyButtonAnimation();
      }
      return Observable.just(response);
    }).delay(1, TimeUnit.SECONDS, AndroidSchedulers.mainThread()).subscribe(response -> {
      if (response.isSuccessful()) {
        setUserInfo(EMAIL, user.getEmail());
        mAuthRxBus.post(new AuthRxBusHelper.UpdateUserInfo());
        getViewState().closeVerifyDialog();
        getViewState().closeFragment();
      } else if (response.code() == RESPONSE_CONTENT_ERROR) {
        getViewState().revertVerifyButtonAnimation();
        handleVerifyError(response.errorBody());
      } else if (response.code() == RESPONSE_UNAUTHORIZED) {
        getViewState().revertVerifyButtonAnimation();
        mAuthRxBus.post(new AuthRxBusHelper.UnauthorizedEvent());
      }
    }, throwable -> {
      Timber.e(throwable);
      showMessageConnectException(throwable);
      getViewState().revertVerifyButtonAnimation();
    });
    addToUnsubscription(subscription);
  }

  public void updatePhone1(UserEntity user) {
    Subscription subscription = checkToken(mDataManager.updatePhone1(user)).concatMap(response -> {
      if (response.code() == RESPONSE_TOKEN_EXPIRED) {
        return checkToken(mDataManager.updatePhone1(user));
      }
      return Observable.just(response);
    }).compose(ThreadSchedulers.applySchedulers()).doOnNext(response -> {
      if (response.isSuccessful()) {
        getViewState().stopAnimation();
      }
    }).delay(1, TimeUnit.SECONDS, AndroidSchedulers.mainThread()).subscribe(response -> {
      if (response.isSuccessful()) {
        getViewState().showDialogMessage(
            mContext.getString(R.string.dialog_message_recovery_password,
                mContext.getString(R.string.auth_phone)));
      } else if (response.code() == RESPONSE_CONTENT_ERROR) {
        getViewState().revertAnimation();
        handleError(response.errorBody());
      } else if (response.code() == RESPONSE_UNAUTHORIZED) {
        getViewState().revertAnimation();
        mAuthRxBus.post(new AuthRxBusHelper.UnauthorizedEvent());
      }
    }, throwable -> {
      showMessageConnectException(throwable);
      getViewState().revertAnimation();
    });
    addToUnsubscription(subscription);
  }

  public void updatePhone2(UserEntity user) {
    Subscription subscription = checkToken(mDataManager.updatePhone2(user)).concatMap(response -> {
      if (response.code() == RESPONSE_TOKEN_EXPIRED) {
        return checkToken(mDataManager.updatePhone2(user));
      }
      return Observable.just(response);
    }).compose(ThreadSchedulers.applySchedulers()).concatMap(response -> {
      if (response.isSuccessful()) {
        getViewState().stopVerifyButtonAnimation();
      }
      return Observable.just(response);
    }).delay(1, TimeUnit.SECONDS, AndroidSchedulers.mainThread()).subscribe(response -> {
      if (response.isSuccessful()) {
        setUserInfo(PHONE, user.getPhone());
        mAuthRxBus.post(new AuthRxBusHelper.UpdateUserInfo());
        getViewState().closeVerifyDialog();
        getViewState().closeFragment();
      } else if (response.code() == RESPONSE_CONTENT_ERROR) {
        getViewState().revertVerifyButtonAnimation();
        handleVerifyError(response.errorBody());
      } else if (response.code() == RESPONSE_UNAUTHORIZED) {
        getViewState().revertVerifyButtonAnimation();
        mAuthRxBus.post(new AuthRxBusHelper.UnauthorizedEvent());
      }
    }, throwable -> {
      Timber.e(throwable);
      showMessageConnectException(throwable);
      getViewState().revertVerifyButtonAnimation();
    });
    addToUnsubscription(subscription);
  }

  private UserEntity addToWrapper(int field, String value) {
    UserEntity userEntity = new UserEntity();
    switch (field) {
      case NAME:
        userEntity.setFirstName(value);
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

  private void handleError(ResponseBody body) {
    ErrorContentEntity content = null;
    try {
      content = new Gson().fromJson(body.string(), ErrorContentEntity.class);
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
      if (content.getPasswordError() != null && !TextUtils.isEmpty(
          content.getPasswordError().get(0))) {
        getViewState().showPasswordError(content.getPasswordError().get(0));
      }
      if (content.getError() != null && !TextUtils.isEmpty(content.getError().get(0))) {
        getViewState().showPasswordError(content.getError().get(0));
      }
    }
  }

  private void handleVerifyError(ResponseBody body) {
    ErrorContentEntity content = null;
    try {
      content = new Gson().fromJson(body.string(), ErrorContentEntity.class);
    } catch (IOException e) {
      e.printStackTrace();
    }
    if (content != null && content.getError() != null && !TextUtils.isEmpty(
        content.getError().get(0))) {
      getViewState().showVerifyError(content.getError().get(0));
    }
  }

  private void subscribeConnectException() {
    Subscription subscription =
        mAuthRxBus.filteredObservable(AuthRxBusHelper.MessageConnectException.class)
            .compose(ThreadSchedulers.applySchedulers())
            .subscribe(event -> getViewState().showConnectErrorMessage(), Timber::e);
    addToUnsubscription(subscription);
  }

  public void closeDialogMessage() {
    getViewState().closeDialogMessage();
  }

  public void showVerifyDialog() {
    getViewState().showVerifyDialog();
  }

  public void closeVerifyDialog() {
    getViewState().closeVerifyDialog();
  }
}
