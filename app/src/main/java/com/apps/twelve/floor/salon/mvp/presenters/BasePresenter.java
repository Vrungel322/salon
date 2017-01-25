package com.apps.twelve.floor.salon.mvp.presenters;

import android.support.annotation.NonNull;
import com.arellomobile.mvp.MvpPresenter;
import com.arellomobile.mvp.MvpView;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Vrungel on 25.01.2017.
 */
public abstract class BasePresenter<V extends MvpView> extends MvpPresenter<V> {

  private CompositeSubscription compositeSubscription = new CompositeSubscription();

  protected void addToUnsubscription(@NonNull Subscription subscription) {
    compositeSubscription.add(subscription);
  }

  @Override public void onDestroy() {
    super.onDestroy();
    compositeSubscription.clear();
  }
}
