package com.apps.twelve.floor.salon.feature.my_bonus.presenters;

import com.apps.twelve.floor.salon.App;
import com.apps.twelve.floor.salon.base.BasePresenter;
import com.apps.twelve.floor.salon.feature.my_bonus.views.IBonusHistoryFragmentView;
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
    //getBonusHistory();
  }

  private void getBonusCount() {
    Subscription subscription = mDataManager.getBonusCountObservable()
        .compose(ThreadSchedulers.applySchedulers())
        .subscribe(count -> getViewState().setBonusCount(count), Timber::e);
    addToUnsubscription(subscription);
  }

  /*private void getBonusHistory() {
    if (mAuthorizationManager.isAuthorized()) {
      Subscription subscription = mDataManager.fetchBonusHistory()
          .doOnNext(count -> mDataManager.setBonusCount(count))
          .compose(ThreadSchedulers.applySchedulers())
          .subscribe(count -> {
            getViewState().setBonusCount(count);
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
  }*/

}
