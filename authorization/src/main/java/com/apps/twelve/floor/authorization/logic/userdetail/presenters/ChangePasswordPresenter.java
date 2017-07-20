package com.apps.twelve.floor.authorization.logic.userdetail.presenters;
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
import com.apps.twelve.floor.authorization.App;
import com.apps.twelve.floor.authorization.base.BasePresenter;
import com.apps.twelve.floor.authorization.data.model.ErrorContentEntity;
import com.apps.twelve.floor.authorization.logic.userdetail.views.IChangePasswordFragment;
import com.apps.twelve.floor.authorization.utils.AuthRxBusHelper;
import com.apps.twelve.floor.authorization.utils.ThreadSchedulers;
import com.arellomobile.mvp.InjectViewState;
import com.google.gson.Gson;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import okhttp3.ResponseBody;
import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import timber.log.Timber;

import static com.apps.twelve.floor.authorization.utils.Constants.Remote.RESPONSE_CONTENT_ERROR;
import static com.apps.twelve.floor.authorization.utils.Constants.Remote.RESPONSE_TOKEN_EXPIRED;
import static com.apps.twelve.floor.authorization.utils.Constants.Remote.RESPONSE_UNAUTHORIZED;

@InjectViewState public class ChangePasswordPresenter
    extends BasePresenter<IChangePasswordFragment> {

  @Override protected void inject() {
    App.getAppComponent().inject(this);
  }

  @Override protected void onFirstViewAttach() {
    super.onFirstViewAttach();
    subscribeConnectException();
  }

  public void changePassword(String password, String newPassword) {

    Subscription subscription =
        checkToken(mDataManager.updatePassword(password, newPassword)).concatMap(response -> {
          if (response.code() == RESPONSE_TOKEN_EXPIRED) {
            return checkToken(mDataManager.updatePassword(password, newPassword));
          }
          return Observable.just(response);
        }).compose(ThreadSchedulers.applySchedulers()).doOnNext(response -> {
          if (response.isSuccessful()) {
            getViewState().stopAnimation();
          }
        }).delay(1, TimeUnit.SECONDS, AndroidSchedulers.mainThread()).subscribe(response -> {
          if (response.isSuccessful()) {
            mDataManager.setToken(response.body().getToken());
            getViewState().showDialogMessage();
          } else if (response.code() == RESPONSE_CONTENT_ERROR) {
            getViewState().revertAnimation();
            handleError(response.errorBody());
          } else if (response.code() == RESPONSE_UNAUTHORIZED) {
            getViewState().revertAnimation();
            mAuthRxBus.post(new AuthRxBusHelper.UnauthorizedEvent());
          }
        }, throwable -> {
          showMessageConnectException(throwable);
          getViewState().revertAnimation();
        });

    addToUnsubscription(subscription);
  }

  public void closeFragment() {
    getViewState().closeFragment();
  }

  private void handleError(ResponseBody errorBody) {
    ErrorContentEntity content = null;
    try {
      content = new Gson().fromJson(errorBody.string(), ErrorContentEntity.class);
    } catch (IOException e) {
      e.printStackTrace();
    }
    if (content != null) {
      if (content.getPasswordError() != null && !TextUtils.isEmpty(
          content.getPasswordError().get(0))) {
        getViewState().showPasswordError(content.getPasswordError().get(0));
      }
      if (content.getNewPasswordError() != null && !TextUtils.isEmpty(
          content.getNewPasswordError().get(0))) {
        getViewState().showNewPasswordError(content.getNewPasswordError().get(0));
      }
    }
  }

  private void subscribeConnectException() {
    Subscription subscription =
        mAuthRxBus.filteredObservable(AuthRxBusHelper.MessageConnectException.class)
            .compose(ThreadSchedulers.applySchedulers())
            .subscribe(event -> getViewState().showConnectErrorMessage(), Timber::e);
    addToUnsubscription(subscription);
  }
}
