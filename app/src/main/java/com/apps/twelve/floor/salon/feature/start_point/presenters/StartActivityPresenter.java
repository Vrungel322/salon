package com.apps.twelve.floor.salon.feature.start_point.presenters;

import com.apps.twelve.floor.salon.App;
import com.apps.twelve.floor.salon.base.BasePresenter;
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

  @Override protected void onFirstViewAttach() {
    super.onFirstViewAttach();
    getViewState().addFragmentMain();
    //RxBus
    getEventFromRxBus();
  }

  @Override protected void inject() {
    App.getAppComponent().inject(this);
  }

  public void share() {
    getViewState().share();
  }

  private void getEventFromRxBus() {
    Subscription subscription = mRxBus.filteredObservable(RxBusHelper.SetBookingItemInMenu.class)
        .compose(ThreadSchedulers.applySchedulers())
        .subscribe(event -> getViewState().setMyBooksItemInMenu(), Timber::e);
    addToUnsubscription(subscription);
    subscription = mRxBus.filteredObservable(RxBusHelper.SetNewsItemInMenu.class)
        .compose(ThreadSchedulers.applySchedulers())
        .subscribe(event -> getViewState().setNewsItemInMenu(), Timber::e);
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

  public void setDrawerIndicator() {
    getViewState().setDrawerIndicator();
  }
}
