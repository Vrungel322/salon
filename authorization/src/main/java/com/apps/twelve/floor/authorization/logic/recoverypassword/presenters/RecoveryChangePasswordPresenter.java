package com.apps.twelve.floor.authorization.logic.recoverypassword.presenters;
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
import com.apps.twelve.floor.authorization.data.DataManager;
import com.apps.twelve.floor.authorization.data.model.CredentialsEntity;
import com.apps.twelve.floor.authorization.logic.recoverypassword.views.IRecoveryChangePasswordFragment;
import com.apps.twelve.floor.authorization.utils.AuthRxBus;
import com.apps.twelve.floor.authorization.utils.AuthRxBusHelper;
import com.apps.twelve.floor.authorization.utils.ThreadSchedulers;
import com.arellomobile.mvp.InjectViewState;
import java.util.concurrent.TimeUnit;
import javax.inject.Inject;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import timber.log.Timber;

@InjectViewState public class RecoveryChangePasswordPresenter
    extends BasePresenter<IRecoveryChangePasswordFragment> {

  @Inject protected AuthRxBus mAuthRxBus;
  @Inject protected DataManager mDataManager;

  @Override protected void inject() {
    App.getAppComponent().inject(this);
  }

  public void changePassword(CredentialsEntity credentialsEntity) {
    mDataManager.changePassword(credentialsEntity)
        .compose(ThreadSchedulers.applySchedulers())
        .concatMap(response -> {
          if (response.isSuccessful()) {
            getViewState().stopAnimation();
          }
          return Observable.just(response);
        })
        .delay(1, TimeUnit.SECONDS, AndroidSchedulers.mainThread())
        .subscribe(response -> {
          if (response.isSuccessful()) {
            mAuthRxBus.post(new AuthRxBusHelper.ChangePasswordSuccess());
          }
        }, throwable -> {
          Timber.e(throwable);
          getViewState().revertAnimation();
          showMessageConnectException(throwable);
        });
  }
}
