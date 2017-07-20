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

import com.apps.twelve.floor.authorization.App;
import com.apps.twelve.floor.authorization.base.BasePresenter;
import com.apps.twelve.floor.authorization.logic.userdetail.views.IActivityHistoryFragmentView;
import com.apps.twelve.floor.authorization.utils.AuthRxBusHelper;
import com.apps.twelve.floor.authorization.utils.ThreadSchedulers;
import com.arellomobile.mvp.InjectViewState;
import rx.Observable;
import rx.Subscription;
import timber.log.Timber;

import static com.apps.twelve.floor.authorization.utils.Constants.Remote.RESPONSE_TOKEN_EXPIRED;
import static com.apps.twelve.floor.authorization.utils.Constants.Remote.RESPONSE_UNAUTHORIZED;

@InjectViewState public class ActivityHistoryPresenter
    extends BasePresenter<IActivityHistoryFragmentView> {

  @Override protected void inject() {
    App.getAppComponent().inject(this);
  }

  @Override protected void onFirstViewAttach() {
    super.onFirstViewAttach();

    fetchActivityHistory();
    subscribeConnectException();
  }

  public void fetchActivityHistory() {
    getViewState().startRefreshingView();
    Subscription subscription =
        checkToken(mDataManager.getActivityHistory()).concatMap(response -> {
          if (response.code() == RESPONSE_TOKEN_EXPIRED) {
            return checkToken(mDataManager.getActivityHistory());
          }
          return Observable.just(response);
        }).compose(ThreadSchedulers.applySchedulers()).subscribe(response -> {
          if (response.isSuccessful()) {
            getViewState().stopRefreshingView();
            getViewState().showActivityHistory(response.body());
          } else if (response.code() == RESPONSE_UNAUTHORIZED) {
            getViewState().stopRefreshingView();
            mAuthRxBus.post(new AuthRxBusHelper.UnauthorizedEvent());
          } else {
            getViewState().stopRefreshingView();
          }
        }, throwable -> {
          getViewState().stopRefreshingView();
          showMessageConnectException(throwable);
        });
    addToUnsubscription(subscription);
  }

  public void logoutAll() {
    // TODO: 5/07/17 mb cache trouble
    Subscription subscription = mDataManager.logoutAll()
        .concatMap(response -> mDataManager.getActivityHistory())
        .compose(ThreadSchedulers.applySchedulers())
        .subscribe(response -> {
          if (response.isSuccessful()) {
            getViewState().closeLogoutAllDialog();
            getViewState().showActivityHistory(response.body());
          }
        }, this::showMessageConnectException);
    addToUnsubscription(subscription);
  }

  public void showLogoutAllDialog() {
    getViewState().showLogoutAllDialog();
  }

  public void closeLogoutAllDialog() {
    getViewState().closeLogoutAllDialog();
  }

  private void subscribeConnectException() {
    Subscription subscription =
        mAuthRxBus.filteredObservable(AuthRxBusHelper.MessageConnectException.class)
            .compose(ThreadSchedulers.applySchedulers())
            .subscribe(event -> getViewState().showConnectErrorMessage(), Timber::e);
    addToUnsubscription(subscription);
  }
}
