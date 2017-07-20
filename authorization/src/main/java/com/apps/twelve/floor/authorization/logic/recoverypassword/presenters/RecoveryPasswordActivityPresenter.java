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
import com.apps.twelve.floor.authorization.logic.recoverypassword.views.IRecoveryPasswordActivity;
import com.apps.twelve.floor.authorization.utils.AuthRxBus;
import com.apps.twelve.floor.authorization.utils.AuthRxBusHelper;
import com.apps.twelve.floor.authorization.utils.ThreadSchedulers;
import com.arellomobile.mvp.InjectViewState;
import javax.inject.Inject;
import rx.Subscription;
import timber.log.Timber;

@InjectViewState public class RecoveryPasswordActivityPresenter
    extends BasePresenter<IRecoveryPasswordActivity> {

  @Inject protected AuthRxBus mAuthRxBus;

  @Override protected void inject() {
    App.getAppComponent().inject(this);
  }

  @Override protected void onFirstViewAttach() {
    super.onFirstViewAttach();
    subscribeChangePasswordSuccess();
    getViewState().addRecoveryPasswordFragment();
  }

  private void subscribeChangePasswordSuccess() {
    Subscription subscription =
        mAuthRxBus.filteredObservable(AuthRxBusHelper.ChangePasswordSuccess.class)
            .compose(ThreadSchedulers.applySchedulers())
            .subscribe(changePasswordSuccess -> getViewState().finishActivity(), Timber::e);
    addToUnsubscription(subscription);
  }
}
