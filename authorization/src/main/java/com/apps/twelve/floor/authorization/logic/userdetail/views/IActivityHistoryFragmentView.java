package com.apps.twelve.floor.authorization.logic.userdetail.views;
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

import com.apps.twelve.floor.authorization.data.model.DeviceInfoEntity;
import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import java.util.List;

@StateStrategyType(AddToEndSingleStrategy.class) public interface IActivityHistoryFragmentView
    extends MvpView {

  void showActivityHistory(List<DeviceInfoEntity> list);

  void showSignInActivity();

  void showLogoutAllDialog();

  void closeLogoutAllDialog();

  void startRefreshingView();

  void stopRefreshingView();

  void showConnectErrorMessage();
}
