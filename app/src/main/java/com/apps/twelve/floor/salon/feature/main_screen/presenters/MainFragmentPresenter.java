package com.apps.twelve.floor.salon.feature.main_screen.presenters;

import com.apps.twelve.floor.salon.App;
import com.apps.twelve.floor.salon.base.BasePresenter;
import com.apps.twelve.floor.salon.feature.main_screen.views.IMainFragmentView;
import com.apps.twelve.floor.salon.utils.RxBusHelper;
import com.apps.twelve.floor.salon.utils.ThreadSchedulers;
import com.arellomobile.mvp.InjectViewState;
import rx.Subscription;
import timber.log.Timber;

/**
 * Created by Vrungel on 20.02.2017.
 */

@InjectViewState public class MainFragmentPresenter extends BasePresenter<IMainFragmentView> {

  @Override protected void inject() {
    App.getAppComponent().inject(this);
  }

  @Override protected void onFirstViewAttach() {
    super.onFirstViewAttach();
    getViewState().addSubNewsAndBonus();
    getViewState().startRefreshingView();
    //RxBus
    subscribeStopRefreshMainFragment();
  }

  public void updateBookingAndNews() {
    getViewState().startRefreshingView();
    mRxBus.post(new RxBusHelper.UpdateNews());
    if (mAuthorizationManager.isAuthorized()) {
      mRxBus.post(new RxBusHelper.UpdateBonusSwipe());
      mRxBus.post(new RxBusHelper.UpdateLastBookingListEvent());
    }
  }

  private void subscribeStopRefreshMainFragment() {
    Subscription subscription =
        mRxBus.filteredObservable(RxBusHelper.StopRefreshNewsMainFragment.class)
            .compose(ThreadSchedulers.applySchedulers())
            .subscribe(stopRefreshNewsMainFragment -> getViewState().stopRefreshingView(),
                throwable -> {
                  Timber.e(throwable);
                  getViewState().stopRefreshingView();
                });
    addToUnsubscription(subscription);
  }
}
