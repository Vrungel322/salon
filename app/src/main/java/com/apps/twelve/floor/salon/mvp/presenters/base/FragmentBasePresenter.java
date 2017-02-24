package com.apps.twelve.floor.salon.mvp.presenters.base;

import android.support.annotation.NonNull;
import com.apps.twelve.floor.salon.utils.RxBus;
import com.apps.twelve.floor.salon.utils.RxBusHelper;
import com.arellomobile.mvp.MvpPresenter;
import com.arellomobile.mvp.MvpView;
import javax.inject.Inject;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Vrungel on 25.01.2017.
 */
public abstract class FragmentBasePresenter<V extends MvpView> extends MvpPresenter<V> {

  @Inject RxBus mRxBus;
  private CompositeSubscription mCompositeSubscription = new CompositeSubscription();

  @Override protected void onFirstViewAttach() {
    super.onFirstViewAttach();
    mRxBus.post(new RxBusHelper.ShowAppBarLayout());
  }

  public FragmentBasePresenter() {
    inject();
  }

  protected void addToUnsubscription(@NonNull Subscription subscription) {
    mCompositeSubscription.add(subscription);
  }

  @Override public void onDestroy() {
    super.onDestroy();
    mCompositeSubscription.clear();
    mRxBus.post(new RxBusHelper.ShowAppBarLayout());
  }

  protected abstract void inject();
}
