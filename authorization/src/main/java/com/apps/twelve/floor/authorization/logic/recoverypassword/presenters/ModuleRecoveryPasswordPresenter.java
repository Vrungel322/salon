package com.apps.twelve.floor.authorization.logic.recoverypassword.presenters;

import android.support.annotation.StringRes;
import android.text.TextUtils;
import com.apps.twelve.floor.authorization.App;
import com.apps.twelve.floor.authorization.R;
import com.apps.twelve.floor.authorization.base.BasePresenter;
import com.apps.twelve.floor.authorization.data.DataManager;
import com.apps.twelve.floor.authorization.data.model.CredentialsEntity;
import com.apps.twelve.floor.authorization.data.model.ErrorContentEntity;
import com.apps.twelve.floor.authorization.logic.recoverypassword.views.IRecoveryPasswordFragment;
import com.apps.twelve.floor.authorization.utils.AuthRxBus;
import com.apps.twelve.floor.authorization.utils.AuthRxBusHelper;
import com.apps.twelve.floor.authorization.utils.ThreadSchedulers;
import com.apps.twelve.floor.authorization.utils.ValidatorUtils;
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

/**
 * Created by Alexander Svyatetsky on 17.05.2017.
 */
@InjectViewState public class ModuleRecoveryPasswordPresenter
    extends BasePresenter<IRecoveryPasswordFragment> {

  @Inject protected AuthRxBus mAuthRxBus;
  @Inject protected DataManager mDataManager;

  @StringRes private int resId;
  @StringRes private int resErrorId;

  @Override protected void inject() {
    App.getAppComponent().inject(this);
  }

  @Override protected void onFirstViewAttach() {
    super.onFirstViewAttach();
    subscribeConnectException();
  }

  public void resetPassword(String login) {

    CredentialsEntity credentialsEntity = new CredentialsEntity();

    if (ValidatorUtils.isValidEmail(login)) {
      credentialsEntity.setEmail(login);
      resId = R.string.auth_email;
      resErrorId = R.string.auth_email;
    } else {
      credentialsEntity.setPhone(login);
      resId = R.string.auth_phone;
      resErrorId = R.string.telephone;
    }

    Subscription subscription = mDataManager.resetPassword(credentialsEntity)
        .compose(ThreadSchedulers.applySchedulers())
        .concatMap(response -> {
          if (response.isSuccessful()) {
            getViewState().stopAnimation();
          }
          return Observable.just(response);
        })
        .delay(1, TimeUnit.SECONDS, AndroidSchedulers.mainThread())
        .subscribe(voidResponse -> {
          if (voidResponse.isSuccessful()) {
            getViewState().revertAnimation();
            getViewState().showDialogMessage(resId);
          } else {
            getViewState().revertAnimation();
            getViewState().showError(resErrorId);
          }
        }, throwable -> {
          showMessageConnectException(throwable);
          getViewState().revertAnimation();
        });
    addToUnsubscription(subscription);
  }

  private void subscribeConnectException() {
    Subscription subscription =
        mAuthRxBus.filteredObservable(AuthRxBusHelper.MessageConnectException.class)
            .compose(ThreadSchedulers.applySchedulers())
            .subscribe(event -> getViewState().showConnectErrorMessage(), Timber::e);
    addToUnsubscription(subscription);
  }

  public void verifyCode(CredentialsEntity credentialsEntity) {
    Subscription subscription = mDataManager.verifyCode(credentialsEntity)
        .compose(ThreadSchedulers.applySchedulers())
        .concatMap(response -> {
          if (response.isSuccessful()) {
            getViewState().stopVerifyButtonAnimation();
          }
          return Observable.just(response);
        })
        .delay(1, TimeUnit.SECONDS, AndroidSchedulers.mainThread())
        .subscribe(response -> {
          if (response.isSuccessful()) {
            getViewState().closeVerifyDialog();
            getViewState().showRecoveryPasswordFragment(credentialsEntity);
          } else if (response.code() == RESPONSE_CONTENT_ERROR) {
            getViewState().revertVerifyButtonAnimation();
            handleVerifyError(response.errorBody());
          }
        }, throwable -> {
          Timber.e(throwable);
          getViewState().revertVerifyButtonAnimation();
          showMessageConnectException(throwable);
        });
    addToUnsubscription(subscription);
  }

  public void showVerifyDialog() {
    getViewState().showVerifyDialog();
  }

  public void closeDialogMessage() {
    getViewState().closeDialogMessage();
  }

  public void closeVerifyDialog() {
    getViewState().closeVerifyDialog();
  }

  private void handleVerifyError(ResponseBody errorBody) {
    ErrorContentEntity content = null;
    try {
      content = new Gson().fromJson(errorBody.string(), ErrorContentEntity.class);
    } catch (IOException e) {
      e.printStackTrace();
    }
    if (content != null && content.getError() != null && !TextUtils.isEmpty(
        content.getError().get(0))) {
      getViewState().showVerifyError(content.getError().get(0));
    }
  }
}
