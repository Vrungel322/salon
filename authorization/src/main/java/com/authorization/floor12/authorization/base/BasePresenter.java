package com.authorization.floor12.authorization.base;

import android.support.annotation.NonNull;
import com.arellomobile.mvp.MvpPresenter;
import com.arellomobile.mvp.MvpView;
import com.authorization.floor12.authorization.utils.RxBus;
import com.authorization.floor12.authorization.utils.RxBusHelper;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import javax.inject.Inject;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Vrungel on 25.01.2017.
 */
public abstract class BasePresenter<V extends MvpView> extends MvpPresenter<V> {

  @Inject RxBus mRxBus;

  private CompositeSubscription mCompositeSubscription = new CompositeSubscription();

  public BasePresenter() {
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

  protected void showMessageConnectException(Throwable throwable) {
    if (throwable instanceof SocketTimeoutException) {
      mRxBus.post(new RxBusHelper.MessageConnectException());
    } else if (throwable instanceof UnknownHostException) {
      mRxBus.post(new RxBusHelper.MessageConnectException());
    }
  }
}
