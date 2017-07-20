package com.apps.twelve.floor.authorization.base;

import android.content.Context;
import android.support.annotation.NonNull;
import com.apps.twelve.floor.authorization.data.DataManager;
import com.apps.twelve.floor.authorization.utils.AuthRxBus;
import com.apps.twelve.floor.authorization.utils.AuthRxBusHelper;
import com.arellomobile.mvp.MvpPresenter;
import com.arellomobile.mvp.MvpView;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import javax.inject.Inject;
import retrofit2.Response;
import rx.Observable;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

import static com.apps.twelve.floor.authorization.utils.Constants.Remote.RESPONSE_TOKEN_EXPIRED;
import static com.apps.twelve.floor.authorization.utils.Constants.Remote.RESPONSE_UNAUTHORIZED;

/**
 * Created by Vrungel on 25.01.2017.
 */
public abstract class BasePresenter<V extends MvpView> extends MvpPresenter<V> {

  @Inject protected Context mContext;
  @Inject protected AuthRxBus mAuthRxBus;
  @Inject protected DataManager mDataManager;

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
      mAuthRxBus.post(new AuthRxBusHelper.MessageConnectException());
    } else if (throwable instanceof UnknownHostException) {
      mAuthRxBus.post(new AuthRxBusHelper.MessageConnectException());
    }
  }

  protected <T> Observable<Response<T>> checkToken(Observable<Response<T>> observable) {
    return observable.concatMap(response -> {
      if (response.code() == RESPONSE_TOKEN_EXPIRED) {
        return mDataManager.refreshToken().concatMap(tokenResponse -> {
          if (tokenResponse.isSuccessful()) {
            mDataManager.setToken(tokenResponse.body().getToken());
          } else if (tokenResponse.code() == RESPONSE_UNAUTHORIZED) {
            Response<T> responseError = Response.error(RESPONSE_UNAUTHORIZED, response.errorBody());
            return Observable.just(responseError);
          }
          return Observable.just(response);
        });
      }
      return Observable.just(response);
    });
  }
}

