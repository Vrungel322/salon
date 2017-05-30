package com.apps.twelve.floor.salon.feature.main_screen.presenters;

import com.apps.twelve.floor.salon.App;
import com.apps.twelve.floor.salon.base.BasePresenter;
import com.apps.twelve.floor.salon.data.DataManager;
import com.apps.twelve.floor.salon.feature.main_screen.views.ISubBonusRegestrationFragmentView;
import com.apps.twelve.floor.salon.utils.ThreadSchedulers;
import com.arellomobile.mvp.InjectViewState;
import com.authorization.floor12.authorization.AuthorizationManager;
import javax.inject.Inject;
import rx.Subscription;
import timber.log.Timber;

/**
 * Created by Vrungel on 27.02.2017.
 */

@InjectViewState public class SubBonusRegistrationFragmentPresenter
    extends BasePresenter<ISubBonusRegestrationFragmentView> {

  @Inject AuthorizationManager mAuthManager;
  @Inject DataManager mDataManager;

  @Override protected void inject() {
    App.getAppComponent().inject(this);
  }

  public void fetchBonusCount() {
    Subscription subscription = mDataManager.fetchBonusCount()
        .compose(ThreadSchedulers.applySchedulers())
        .subscribe(count -> getViewState().setBonusCount(String.valueOf(count)), throwable -> {
          Timber.e(throwable);
          showMessageConnectException(throwable);
        });
    addToUnsubscription(subscription);
  }

  public void fetchUserPhoto() {
    Subscription subscription = mAuthManager.getUserPhoto()
        .compose(ThreadSchedulers.applySchedulers())
        .subscribe(photo -> getViewState().setUserPhoto(photo), throwable -> {
          Timber.e(throwable);
          showMessageConnectException(throwable);
        });
    addToUnsubscription(subscription);
  }

}
