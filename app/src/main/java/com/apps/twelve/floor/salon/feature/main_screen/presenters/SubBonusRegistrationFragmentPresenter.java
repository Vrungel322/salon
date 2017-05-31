package com.apps.twelve.floor.salon.feature.main_screen.presenters;

import com.apps.twelve.floor.salon.App;
import com.apps.twelve.floor.salon.base.BasePresenter;
import com.apps.twelve.floor.salon.data.DataManager;
import com.apps.twelve.floor.salon.feature.main_screen.views.ISubBonusRegestrationFragmentView;
import com.apps.twelve.floor.salon.utils.ThreadSchedulers;
import com.arellomobile.mvp.InjectViewState;
import javax.inject.Inject;
import rx.Subscription;
import timber.log.Timber;

/**
 * Created by Vrungel on 27.02.2017.
 */

@InjectViewState public class SubBonusRegistrationFragmentPresenter
    extends BasePresenter<ISubBonusRegestrationFragmentView> {

  @Inject DataManager mDataManager;

  @Override protected void inject() {
    App.getAppComponent().inject(this);
  }

  private void fetchBonusCount() {
    Subscription subscription = mDataManager.fetchBonusCount()
        .compose(ThreadSchedulers.applySchedulers())
        .subscribe(count -> getViewState().setBonusCount(String.valueOf(count)), throwable -> {
          Timber.e(throwable);
          showMessageConnectException(throwable);
        });
    addToUnsubscription(subscription);
  }

  private void fetchUserPhoto() {
    Subscription subscription = mDataManager.getUserPhoto()
        .compose(ThreadSchedulers.applySchedulers())
        .subscribe(photo -> getViewState().setUserPhoto(photo), throwable -> {
          Timber.e(throwable);
          showMessageConnectException(throwable);
        });
    addToUnsubscription(subscription);
  }

  public void openRegistrationOrBonus() {
    getViewState().openRegistrationOrBonus(mDataManager.isAuthorized());
  }

  public void showCardBonusOrRegistration() {
    if (!mDataManager.isAuthorized()) {
      getViewState().showCardRegistration();
    } else {
      fetchBonusCount();
      fetchUserPhoto();
    }
  }
}
