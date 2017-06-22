package com.apps.twelve.floor.salon.feature.main_screen.presenters;

import com.apps.twelve.floor.salon.App;
import com.apps.twelve.floor.salon.base.BasePresenter;
import com.apps.twelve.floor.salon.feature.main_screen.views.ISubBonusRegestrationFragmentView;
import com.apps.twelve.floor.salon.utils.RxBusHelper;
import com.apps.twelve.floor.salon.utils.ThreadSchedulers;
import com.arellomobile.mvp.InjectViewState;
import rx.Subscription;
import timber.log.Timber;

/**
 * Created by Vrungel on 27.02.2017.
 */

@InjectViewState public class SubBonusRegistrationFragmentPresenter
    extends BasePresenter<ISubBonusRegestrationFragmentView> {

  @Override protected void inject() {
    App.getAppComponent().inject(this);
  }

  @Override protected void onFirstViewAttach() {
    super.onFirstViewAttach();
    //RxBus
    subscribeUpdateBonusFromParent();
    subscribeUpdateBonusSwipe();
  }

  private void fetchBonusCount() {
    Subscription subscription = mDataManager.getBonusCountObservable()
        .compose(ThreadSchedulers.applySchedulers())
        .subscribe(count -> getViewState().setBonusCount(String.valueOf(count)), throwable -> {
          Timber.e(throwable);
          showMessageException(throwable);
        });
    addToUnsubscription(subscription);
  }

  public void showCardBonusOrRegistration() {
    if (!mAuthorizationManager.isAuthorized()) {
      getViewState().showCardRegistration();
    } else {
      fetchBonusCount();
    }
  }

  private void subscribeUpdateBonusSwipe() {
    Subscription subscription = mRxBus.filteredObservable(RxBusHelper.UpdateBonusSwipe.class)
        .concatMap(updateBonusSwipe -> mDataManager.fetchBonusCount())
        .doOnNext(bonusEntity -> mDataManager.setBonusCount(bonusEntity.getBonusesCount()))
        .compose(ThreadSchedulers.applySchedulers())
        .subscribe(count -> {
          getViewState().setBonusCount(String.valueOf(count));
          mRxBus.post(new RxBusHelper.UpdateBonusFromChildren());
        }, throwable -> {
          getViewState().setBonusCount(String.valueOf(mDataManager.getBonusCountInt()));
          subscribeUpdateBonusFromParent();
          Timber.e(throwable);
          showMessageException(throwable);
        });
    addToUnsubscription(subscription);
  }

  private void subscribeUpdateBonusFromParent() {
    Subscription subscription = mRxBus.filteredObservable(RxBusHelper.UpdateBonusFromParent.class)
        .concatMap(updateBonusSwipe -> mDataManager.getBonusCountObservable())
        .compose(ThreadSchedulers.applySchedulers())
        .subscribe(count -> getViewState().setBonusCount(String.valueOf(count)), Timber::e);
    addToUnsubscription(subscription);
  }
}
