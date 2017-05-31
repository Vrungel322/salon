package com.apps.twelve.floor.salon.feature.start_point.presenters;

import com.apps.twelve.floor.salon.App;
import com.apps.twelve.floor.salon.base.BasePresenter;
import com.apps.twelve.floor.salon.data.DataManager;
import com.apps.twelve.floor.salon.feature.start_point.views.IStartActivityView;
import com.apps.twelve.floor.salon.utils.RxBus;
import com.apps.twelve.floor.salon.utils.RxBusHelper;
import com.apps.twelve.floor.salon.utils.ThreadSchedulers;
import com.arellomobile.mvp.InjectViewState;
import javax.inject.Inject;
import rx.Subscription;
import timber.log.Timber;

/**
 * Created by Vrungel on 20.02.2017.
 */

@InjectViewState public class StartActivityPresenter extends BasePresenter<IStartActivityView> {

  @Inject RxBus mRxBus;
  @Inject DataManager mDataManager;

  @Override protected void onFirstViewAttach() {
    super.onFirstViewAttach();
    getViewState().addFragmentMain();
    //RxBus
    subscribeOnEvents();
    subscribeConnectException();
    subscribeUpdateBonusFromChildren();
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

  public void fetchBonusCount() {
    if (mDataManager.isAuthorized()) {
      Subscription subscription = mDataManager.fetchBonusCount()
          .doOnNext(count -> mDataManager.setBonusCount(count))
          .compose(ThreadSchedulers.applySchedulers())
          .subscribe(count -> {
            getViewState().setBonusCount(count);
            mRxBus.post(new RxBusHelper.UpdateBonusFromParent());
          }, throwable -> {
            Timber.e(throwable);
            getViewState().setBonusCount(mDataManager.getBonusCountInt());
            showMessageConnectException(throwable);
          });
      addToUnsubscription(subscription);
    }
  }

  private void subscribeUpdateBonusFromChildren() {
    Subscription subscription = mRxBus.filteredObservable(RxBusHelper.UpdateBonusFromChildren.class)
        .concatMap(updateBonusSwipe -> mDataManager.getBonusCountObservable())
        .compose(ThreadSchedulers.applySchedulers())
        .subscribe(count -> getViewState().setBonusCount(count), Timber::e);
    addToUnsubscription(subscription);
  }

  public void setDrawerIndicator() {
    getViewState().setDrawerIndicator();
  }
}
