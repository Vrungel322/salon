package com.authorization.floor12.authorization.logic.authorization.views;

import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.authorization.floor12.authorization.base.IBaseMvpView;
import com.authorization.floor12.authorization.data.model.UserEntity;

/**
 * Created by Alexander Svyatetsky on 22.04.2017.
 */

@StateStrategyType(AddToEndSingleStrategy.class) public interface ISignInActivityView
    extends IBaseMvpView {

  void showUserProfile();

  void showRegistrationActivity(UserEntity user);

  void finishActivity();
}
