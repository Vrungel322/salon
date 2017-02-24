package com.apps.twelve.floor.salon.mvp.presenters.base;

import android.support.annotation.NonNull;
import com.arellomobile.mvp.MvpPresenter;
import com.arellomobile.mvp.MvpView;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by John on 24.02.2017.
 */

public abstract class ActivityBasePresenter<V extends MvpView> extends MvpPresenter<V> {

  private CompositeSubscription mCompositeSubscription = new CompositeSubscription();

  public ActivityBasePresenter() {
    inject();
  }

   protected void addToUnsubscription(@NonNull Subscription subscription) {
    mCompositeSubscription.add(subscription);
  }

  @Override public void onDestroy() {
    super.onDestroy();
    mCompositeSubscription.clear();
  }

  protected abstract void inject();
}
