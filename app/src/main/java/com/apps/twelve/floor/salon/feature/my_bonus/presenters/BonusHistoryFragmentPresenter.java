package com.apps.twelve.floor.salon.feature.my_bonus.presenters;

import com.apps.twelve.floor.salon.App;
import com.apps.twelve.floor.salon.base.BasePresenter;
import com.apps.twelve.floor.salon.feature.my_bonus.views.IBonusHistoryFragmentView;
import com.apps.twelve.floor.salon.utils.RxBusHelper;
import com.apps.twelve.floor.salon.utils.ThreadSchedulers;
import com.arellomobile.mvp.InjectViewState;
import rx.Subscription;
import timber.log.Timber;

/**
 * Created by Alexandra on 30.05.2017.
 */

@InjectViewState public class BonusHistoryFragmentPresenter
    extends BasePresenter<IBonusHistoryFragmentView> {

  @Override protected void inject() {
    App.getAppComponent().inject(this);
  }

  @Override protected void onFirstViewAttach() {
    super.onFirstViewAttach();
    getBonusCount();
    getBonusHistory();
  }

  public void getBonusCount() {
    Subscription subscription = mDataManager.getBonusCountObservable()
        .compose(ThreadSchedulers.applySchedulers())
        .subscribe(count -> getViewState().setBonusCount(count), Timber::e);
    addToUnsubscription(subscription);
  }

  public void getBonusHistory() {
    getViewState().startRefreshingView();
    if (mAuthorizationManager.isAuthorized()) {
      Subscription subscription = mDataManager.fetchBonusHistory()
          .compose(ThreadSchedulers.applySchedulers()).subscribe(bonusHistoryEntity -> {
            getViewState().addBonusHistoryList(bonusHistoryEntity);
            mRxBus.post(new RxBusHelper.UpdateBonusFromParent());
            getViewState().stopRefreshingView();
          }, throwable -> {
            Timber.e(throwable);
            showMessageException(throwable);
            getViewState().stopRefreshingView();
          });
      addToUnsubscription(subscription);
    } else {
      getViewState().setHistoryNotAuth();
      getViewState().stopRefreshingView();
    }
  }

}
