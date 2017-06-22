package com.apps.twelve.floor.salon.feature.start_point.presenters;

import com.apps.twelve.floor.authorization.utils.AuthRxBusHelper;
import com.apps.twelve.floor.salon.App;
import com.apps.twelve.floor.salon.base.BasePresenter;
import com.apps.twelve.floor.salon.feature.start_point.views.IStartActivityView;
import com.apps.twelve.floor.salon.utils.RxBusHelper;
import com.apps.twelve.floor.salon.utils.ThreadSchedulers;
import com.arellomobile.mvp.InjectViewState;
import rx.Subscription;
import timber.log.Timber;

/**
 * Created by Vrungel on 20.02.2017.
 */

@InjectViewState public class StartActivityPresenter extends BasePresenter<IStartActivityView> {

  @Override protected void onFirstViewAttach() {
    super.onFirstViewAttach();
    getViewState().addFragmentMain();
    //RxBus
    subscribeOnEvents();
    subscribeConnectException();
    subscribeWrongException();
    subscribeUpdateBonusFromChildren();
    subscribeLogoutUser();
    subscribeShowDialog();
    subscribeUnauthorizedUser();
  }

  @Override protected void inject() {
    App.getAppComponent().inject(this);
  }

  public void share() {
    getViewState().share();
  }

  private void subscribeOnEvents() {
    Subscription subscription = mRxBus.filteredObservable(RxBusHelper.SetBookingItemInMenu.class)
        .compose(ThreadSchedulers.applySchedulers())
        .subscribe(event -> getViewState().setMyBooksItemInMenu(), Timber::e);
    addToUnsubscription(subscription);
    subscription = mRxBus.filteredObservable(RxBusHelper.SetNewsItemInMenu.class)
        .compose(ThreadSchedulers.applySchedulers())
        .subscribe(event -> getViewState().setNewsItemInMenu(), Timber::e);
    addToUnsubscription(subscription);
    subscription = mRxBus.filteredObservable(RxBusHelper.SetBonusItemInMenu.class)
        .compose(ThreadSchedulers.applySchedulers())
        .subscribe(event -> getViewState().setBonusItemInMenu(), Timber::e);
    addToUnsubscription(subscription);
    subscription = mRxBus.filteredObservable(RxBusHelper.HideFloatingButton.class)
        .compose(ThreadSchedulers.applySchedulers())
        .subscribe((event -> getViewState().hideFloatingButton()), Timber::e);
    addToUnsubscription(subscription);
    subscription = mRxBus.filteredObservable(RxBusHelper.ShowFloatingButton.class)
        .compose(ThreadSchedulers.applySchedulers())
        .subscribe((event -> getViewState().showFloatingButton()), Timber::e);
    addToUnsubscription(subscription);
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

  public void fetchBonusCount() {
    if (mAuthorizationManager.isAuthorized()) {
      Subscription subscription = mDataManager.fetchBonusCount()
          .doOnNext(bonusEntity -> mDataManager.setBonusCount(bonusEntity.getBonusesCount()))
          .compose(ThreadSchedulers.applySchedulers())
          .subscribe(bonusEntity -> {
            getViewState().setBonusCount(bonusEntity.getBonusesCount());
            mRxBus.post(new RxBusHelper.UpdateBonusFromParent());
          }, throwable -> {
            Timber.e(throwable);
            getViewState().setBonusCount(mDataManager.getBonusCountInt());
            showMessageException(throwable);
          });
      addToUnsubscription(subscription);
    } else {
      getViewState().setBonusCount(0);
    }
  }

  private void subscribeUpdateBonusFromChildren() {
    Subscription subscription = mRxBus.filteredObservable(RxBusHelper.UpdateBonusFromChildren.class)
        .concatMap(updateBonusSwipe -> mDataManager.getBonusCountObservable())
        .compose(ThreadSchedulers.applySchedulers())
        .subscribe(count -> getViewState().setBonusCount(count), Timber::e);
    addToUnsubscription(subscription);
  }

  private void subscribeShowDialog() {
    Subscription subscription = mRxBus.filteredObservable(RxBusHelper.ShowAuthDialog.class)
        .compose(ThreadSchedulers.applySchedulers())
        .subscribe(show -> showAlertDialog(), Timber::e);
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

  public void setDrawerIndicator() {
    getViewState().setDrawerIndicator();
  }

  public void showAlertDialog() {
    getViewState().showAlertDialog();
  }

  public void cancelAlertDialog() {
    getViewState().cancelAlertDialog();
  }
}
