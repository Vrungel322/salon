package com.apps.twelve.floor.authorization.logic.authorization.views;

import com.apps.twelve.floor.authorization.base.IBaseMvpView;
import com.apps.twelve.floor.authorization.data.model.UserEntity;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

/**
 * Created by Alexander Svyatetsky on 22.04.2017.
 */

@StateStrategyType(AddToEndSingleStrategy.class) public interface IModuleSignInActivityView
    extends IBaseMvpView {

  void showRegistrationActivity(UserEntity user);

  void finishActivity();

  void showProgressDialog();

  void closeProgressDialog();

  void showLoginError(String errorMessage);

  void showPasswordError();
}
