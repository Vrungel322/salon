package com.apps.twelve.floor.salon.feature.settings.presenters;

import com.apps.twelve.floor.salon.App;
import com.apps.twelve.floor.salon.base.BasePresenter;
import com.apps.twelve.floor.salon.data.DataManager;
import com.apps.twelve.floor.salon.feature.settings.views.IChangeUserInfoFragmentView;
import com.apps.twelve.floor.salon.utils.RxBus;
import com.apps.twelve.floor.salon.utils.RxBusHelper;
import com.apps.twelve.floor.salon.utils.ThreadSchedulers;
import com.arellomobile.mvp.InjectViewState;
import java.util.concurrent.TimeUnit;
import javax.inject.Inject;
import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import timber.log.Timber;

import static com.apps.twelve.floor.salon.utils.Constants.ChangingUserInfoField.EMAIL;
import static com.apps.twelve.floor.salon.utils.Constants.ChangingUserInfoField.LOGIN;
import static com.apps.twelve.floor.salon.utils.Constants.ChangingUserInfoField.NAME;
import static com.apps.twelve.floor.salon.utils.Constants.ChangingUserInfoField.PASSWORD;
import static com.apps.twelve.floor.salon.utils.Constants.ChangingUserInfoField.PHONE;

/**
 * Created by Alexandra on 04.05.2017.
 */

@InjectViewState public class ChangeUserInfoFragmentPresenter
    extends BasePresenter<IChangeUserInfoFragmentView> {

  @Inject DataManager mDataManager;
  @Inject RxBus mRxBus;

  @Override protected void inject() {
    App.getAppComponent().inject(this);
  }

  @Override protected void onFirstViewAttach() {
    super.onFirstViewAttach();
  }

  public void saveInfo(int field, String value) {
    Subscription subscription =
        Observable.just(200).compose(ThreadSchedulers.applySchedulers()).doOnNext(voidResponse -> {
          if (voidResponse == 200) {
            getViewState().stopAnimation();
          } else {
            getViewState().showAlert();
          }
        }).delay(1, TimeUnit.SECONDS, AndroidSchedulers.mainThread()).subscribe(response -> {
          if (response == 200) {
            setUserInfo(field, value);
            mRxBus.post(new RxBusHelper.UpdateUserInfo());
            getViewState().closeFragment();
          } else {
            getViewState().revertAnimation();
          }
        }, throwable -> {
          Timber.e(throwable);
          showMessageConnectException(throwable);
        });
    addToUnsubscription(subscription);
  }

  private void setUserInfo(int field, String value) {
    switch (field) {
      case NAME:
        mDataManager.setProfileName(value);
        break;
      case LOGIN:
        mDataManager.setProfileLogin(value);
        break;
      case PASSWORD:
        mDataManager.setProfilePassword(value);
        break;
      case EMAIL:
        mDataManager.setProfileEmail(value);
        break;
      case PHONE:
        mDataManager.setProfilePhone(value);
        break;
    }
  }
}
