package com.authorization.floor12.authorization.logic.userdetail.presenters;
/*
 * Copyright 2017, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import android.text.TextUtils;
import com.arellomobile.mvp.InjectViewState;
import com.authorization.floor12.authorization.App;
import com.authorization.floor12.authorization.base.BasePresenter;
import com.authorization.floor12.authorization.data.DataManager;
import com.authorization.floor12.authorization.data.local.PreferencesHelper;
import com.authorization.floor12.authorization.data.model.ErrorContentEntity;
import com.authorization.floor12.authorization.logic.userdetail.views.IChangePasswordFragment;
import com.authorization.floor12.authorization.utils.RxBus;
import com.authorization.floor12.authorization.utils.RxBusHelper;
import com.authorization.floor12.authorization.utils.ThreadSchedulers;
import com.google.gson.Gson;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import javax.inject.Inject;
import okhttp3.ResponseBody;
import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import timber.log.Timber;

@InjectViewState public class ChangePasswordPresenter
    extends BasePresenter<IChangePasswordFragment> {

  @Inject RxBus mRxBus;
  @Inject DataManager mDataManager;
  @Inject PreferencesHelper mPreferencesHelper;

  @Override protected void inject() {
    App.getAppComponent().inject(this);
  }

  @Override protected void onFirstViewAttach() {
    super.onFirstViewAttach();
    subscribeConnectException();
  }

  public void changePassword(String password, String newPassword) {

    Subscription subscription =
        mDataManager.changePassword(password, newPassword).concatMap(response -> {
          if (response.code() == 401) {
            return mDataManager.refreshToken().concatMap(tokenResponse -> {
              mPreferencesHelper.setToken(tokenResponse.body().getToken());
              return mDataManager.changePassword(password, newPassword);
            });
          } else {
            return Observable.just(response);
          }
        }).compose(ThreadSchedulers.applySchedulers()).doOnNext(response -> {
          if (response.isSuccessful()) {
            getViewState().stopAnimation();
          }
        }).delay(1, TimeUnit.SECONDS, AndroidSchedulers.mainThread()).subscribe(response -> {
          if (response.isSuccessful()) {
            mPreferencesHelper.setToken(response.body().getToken());
            getViewState().closeFragment();
          } else if (response.code() == 400) {
            getViewState().revertAnimation();
            handleError(response.errorBody());
          } else if (response.code() == 500) {
            getViewState().revertAnimation();
            getViewState().showSignInActivity();
          }
        }, throwable -> {
          showMessageConnectException(throwable);
          getViewState().revertAnimation();
        });

    addToUnsubscription(subscription);
  }

  public void handleError(ResponseBody errorBody) {
    ErrorContentEntity content = null;
    try {
      content = new Gson().fromJson(errorBody.string(), ErrorContentEntity.class);
    } catch (IOException e) {
      e.printStackTrace();
    }
    if (content.getPasswordError() != null && !TextUtils.isEmpty(
        content.getPasswordError().get(0))) {
      getViewState().showPasswordError(content.getPasswordError().get(0));
    }
    if (content.getNewPasswordError() != null && !TextUtils.isEmpty(
        content.getNewPasswordError().get(0))) {
      getViewState().showNewPasswordError(content.getNewPasswordError().get(0));
    }
  }

  private void subscribeConnectException() {
    Subscription subscription = mRxBus.filteredObservable(RxBusHelper.MessageConnectException.class)
        .compose(ThreadSchedulers.applySchedulers())
        .subscribe(event -> getViewState().showConnectErrorMessage(), Timber::e);
    addToUnsubscription(subscription);
  }
}
