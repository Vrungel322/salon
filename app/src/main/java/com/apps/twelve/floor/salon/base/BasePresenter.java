package com.apps.twelve.floor.salon.base;

import android.content.Context;
import android.support.annotation.NonNull;
import com.apps.twelve.floor.authorization.AuthorizationManager;
import com.apps.twelve.floor.salon.data.DataManager;
import com.apps.twelve.floor.salon.data.model.error.IErrorResponse;
import com.apps.twelve.floor.salon.utils.RxBus;
import com.apps.twelve.floor.salon.utils.RxBusHelper;
import com.arellomobile.mvp.MvpPresenter;
import com.arellomobile.mvp.MvpView;
import com.google.gson.Gson;
import io.realm.RealmObject;
import java.io.IOException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.List;
import javax.inject.Inject;
import okhttp3.ResponseBody;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Vrungel on 25.01.2017.
 */
public abstract class BasePresenter<V extends MvpView> extends MvpPresenter<V> {

  @Inject protected RxBus mRxBus;
  @Inject protected DataManager mDataManager;
  @Inject protected Context mContext;
  @Inject protected AuthorizationManager mAuthorizationManager;

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

  protected void showMessageException(Throwable throwable) {
    if (throwable instanceof SocketTimeoutException) {
      mRxBus.post(new RxBusHelper.MessageConnectException());
    } else {
      if (throwable instanceof UnknownHostException) {
        mRxBus.post(new RxBusHelper.MessageConnectException());
      } else {
        mRxBus.post(new RxBusHelper.MessageWrongException());
      }
    }
  }

  protected <T extends RealmObject> void cacheEntities(List<T> body, Class<T> clazz) {
    //cache Entities
    if (body != null) {
      mDataManager.dropRealmTable(clazz);
      for (int i = 0; i < body.size(); i++) {
        mDataManager.saveObjToDb(body.get(i));
      }
    }
  }

  protected <T extends RealmObject> void cacheEntity(T body) {
    //cache Entities
    mDataManager.saveObjToDb(body);
  }

  public <T extends IErrorResponse> String handleError(ResponseBody errorBody, Class<T> clazz) {
    T errorResponse = null;
    StringBuilder sb = new StringBuilder();
    try {
      errorResponse = new Gson().fromJson(errorBody.string(), clazz);
    } catch (IOException e) {
      e.printStackTrace();
    }
    if (errorResponse != null) {
      for (int i = 0; i < errorResponse.getError().size(); i++) {
        sb.append(errorResponse.getError().get(i));
      }
    }
    return sb.toString();
  }

  protected void showMessageException() {
    mRxBus.post(new RxBusHelper.MessageWrongException());
  }
}
