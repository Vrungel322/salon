package com.authorization.floor12.authorization.logic.recoverypassword.presenters;

import android.support.annotation.StringRes;
import com.arellomobile.mvp.InjectViewState;
import com.authorization.floor12.authorization.App;
import com.authorization.floor12.authorization.R;
import com.authorization.floor12.authorization.base.BasePresenter;
import com.authorization.floor12.authorization.data.DataManager;
import com.authorization.floor12.authorization.data.model.CredentialsEntity;
import com.authorization.floor12.authorization.logic.recoverypassword.views.IRecoveryPasswordActivity;
import com.authorization.floor12.authorization.utils.RxBus;
import com.authorization.floor12.authorization.utils.RxBusHelper;
import com.authorization.floor12.authorization.utils.ThreadSchedulers;
import com.authorization.floor12.authorization.utils.ValidatiorUtils;
import java.util.concurrent.TimeUnit;
import javax.inject.Inject;
import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import timber.log.Timber;

/**
 * Created by Alexander Svyatetsky on 17.05.2017.
 */
@InjectViewState public class RecoveryPasswordPresenter
    extends BasePresenter<IRecoveryPasswordActivity> {

  @Inject RxBus mRxBus;
  @Inject DataManager mDataManager;

  @StringRes private int resId;

  @Override protected void inject() {
    App.getAppComponent().inject(this);
  }

  @Override protected void onFirstViewAttach() {
    super.onFirstViewAttach();
    subscribeConnectException();
  }

  public void resetPassword(String login) {

    CredentialsEntity credentialsEntity = new CredentialsEntity();

    if (ValidatiorUtils.EmailValidator(login)) {
      credentialsEntity.setEmail(login);
      resId = R.string.email;
    } else {
      credentialsEntity.setPhone(login);
      resId = R.string.phone;
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
            getViewState().showDialogMessage(resId);
          } else {
            getViewState().revertAnimation();
            getViewState().showError(resId);
          }
        }, throwable -> {
          showMessageConnectException(throwable);
          getViewState().revertAnimation();
        });
    addToUnsubscription(subscription);
  }

  private void subscribeConnectException() {
    Subscription subscription = mRxBus.filteredObservable(RxBusHelper.MessageConnectException.class)
        .compose(ThreadSchedulers.applySchedulers())
        .subscribe(event -> getViewState().showConnectErrorMessage(), Timber::e);
    addToUnsubscription(subscription);
  }
}
