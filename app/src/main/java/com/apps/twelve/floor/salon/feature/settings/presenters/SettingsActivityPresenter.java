package com.apps.twelve.floor.salon.feature.settings.presenters;

import com.apps.twelve.floor.authorization.utils.AuthRxBusHelper;
import com.apps.twelve.floor.salon.App;
import com.apps.twelve.floor.salon.base.BasePresenter;
import com.apps.twelve.floor.salon.feature.settings.views.ISettingsActivityView;
import com.apps.twelve.floor.salon.utils.RxBusHelper;
import com.apps.twelve.floor.salon.utils.ThreadSchedulers;
import com.arellomobile.mvp.InjectViewState;
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

  @Override protected void onFirstViewAttach() {
    super.onFirstViewAttach();
    getViewState().addFragmentSettings();
    //RxBus
    subscribeConnectException();
    subscribeWrongException();
    subscribeLogoutUser();
    subscribeUnauthorizedUser();
  }

  private void subscribeConnectException() {
    Subscription subscription = mRxBus.filteredObservable(RxBusHelper.MessageConnectException.class)
        .compose(ThreadSchedulers.applySchedulers())
        .subscribe(event -> getViewState().showConnectErrorMessage(), Timber::e);
    addToUnsubscription(subscription);
  }

  private void subscribeWrongException() {
    Subscription subscription = mRxBus.filteredObservable(RxBusHelper.MessageWrongException.class)
        .compose(ThreadSchedulers.applySchedulers())
        .subscribe(event -> getViewState().showWrongMessage(), Timber::e);
    addToUnsubscription(subscription);
  }

  private void subscribeLogoutUser() {
    Subscription subscription = mAuthorizationManager.getAuthRxBus()
        .filteredObservable(AuthRxBusHelper.LogoutEvent.class)
        .compose(ThreadSchedulers.applySchedulers())
        .subscribe(event -> {
          getViewState().logoutUser();
          mDataManager.logoutUser();
        }, Timber::e);
    addToUnsubscription(subscription);
  }

  private void subscribeUnauthorizedUser() {
    Subscription subscription = mAuthorizationManager.getAuthRxBus()
        .filteredObservable(AuthRxBusHelper.UnauthorizedEvent.class)
        .compose(ThreadSchedulers.applySchedulers())
        .subscribe(event -> getViewState().startSignInActivity(), Timber::e);
    addToUnsubscription(subscription);
  }
}
