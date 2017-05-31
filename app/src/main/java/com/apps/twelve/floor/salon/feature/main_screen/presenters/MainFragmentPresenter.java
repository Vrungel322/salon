package com.apps.twelve.floor.salon.feature.main_screen.presenters;

import com.apps.twelve.floor.salon.App;
import com.apps.twelve.floor.salon.base.BasePresenter;
import com.apps.twelve.floor.salon.data.DataManager;
import com.apps.twelve.floor.salon.feature.main_screen.views.IMainFragmentView;
import com.apps.twelve.floor.salon.utils.RxBus;
import com.apps.twelve.floor.salon.utils.RxBusHelper;
import com.apps.twelve.floor.salon.utils.ThreadSchedulers;
import com.arellomobile.mvp.InjectViewState;
import javax.inject.Inject;
import rx.Observable;
import rx.Subscription;
import timber.log.Timber;

/**
 * Created by Vrungel on 20.02.2017.
 */

@InjectViewState public class MainFragmentPresenter extends BasePresenter<IMainFragmentView> {

  @Inject RxBus mRxBus;
  @Inject DataManager mDataManager;

  @Override protected void inject() {
    App.getAppComponent().inject(this);
  }

  @Override protected void onFirstViewAttach() {
    super.onFirstViewAttach();
    getViewState().addSubFragments();
    getViewState().startRefreshingView();
    //RxBus
    subscribeStopRefreshMainFragment();
  }

  public void updateBookingAndNews() {
    getViewState().startRefreshingView();
    mRxBus.post(new RxBusHelper.UpdateLastBookingListEvent());
    mRxBus.post(new RxBusHelper.UpdateNews());
    if (mDataManager.isAuthorized()) {
      mRxBus.post(new RxBusHelper.UpdateBonusSwipe());
    }
  }

  private void subscribeStopRefreshMainFragment() {
    Subscription subscription =
        Observable.zip(mRxBus.filteredObservable(RxBusHelper.StopRefreshBookingMainFragment.class),
            mRxBus.filteredObservable(RxBusHelper.StopRefreshNewsMainFragment.class),
            (stopRefreshBookingMainFragment, stopRefreshNewsMainFragment) -> true)
            .compose(ThreadSchedulers.applySchedulers())
            .subscribe(aBoolean -> getViewState().stopRefreshingView(), throwable -> {
              Timber.e(throwable);
              getViewState().stopRefreshingView();
            });
    addToUnsubscription(subscription);
  }
}
