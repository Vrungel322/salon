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
          showMessageConnectException(throwable);
        });
    addToUnsubscription(subscription);
  }

  private void fetchUserPhoto() {
    Subscription subscription = mDataManager.getUserPhoto()
        .compose(ThreadSchedulers.applySchedulers())
        .subscribe(photo -> getViewState().setUserPhoto(photo), throwable -> {
          Timber.e(throwable);
          showMessageConnectException(throwable);
        });
    addToUnsubscription(subscription);
  }

  public void openRegistrationOrBonus() {
    getViewState().openRegistrationOrBonus(mDataManager.isAuthorized());
  }

  public void showCardBonusOrRegistration() {
    if (!mDataManager.isAuthorized()) {
      getViewState().showCardRegistration();
      getViewState().setUserPhoto("");
    } else {
      fetchBonusCount();
      fetchUserPhoto();
    }
  }

  private void subscribeUpdateBonusSwipe() {
    Subscription subscription = mRxBus.filteredObservable(RxBusHelper.UpdateBonusSwipe.class)
        .concatMap(updateBonusSwipe -> mDataManager.fetchBonusCount())
        .doOnNext(count -> mDataManager.setBonusCount(count))
        .compose(ThreadSchedulers.applySchedulers())
        .subscribe(count -> {
          getViewState().setBonusCount(String.valueOf(count));
          mRxBus.post(new RxBusHelper.UpdateBonusFromChildren());
        }, throwable -> {
          getViewState().setBonusCount(String.valueOf(mDataManager.getBonusCountInt()));
          subscribeUpdateBonusFromParent();
          Timber.e(throwable);
          showMessageConnectException(throwable);
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
