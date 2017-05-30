package com.authorization.floor12.authorization.logic.registration.views;

import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.authorization.floor12.authorization.base.IBaseMvpView;

/**
 * Created by Alexander Svyatetsky on 22.04.2017.
 */

@StateStrategyType(AddToEndSingleStrategy.class) public interface IRegistrationActivityView
    extends IBaseMvpView {

  @StateStrategyType(SkipStrategy.class) void SignIn();

  void showPhoneError(String message);

  void showEmailError(String message);
}
