package com.apps.twelve.floor.authorization.logic.registration.views;

import com.apps.twelve.floor.authorization.base.IBaseMvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

/**
 * Created by Alexander Svyatetsky on 22.04.2017.
 */

@StateStrategyType(AddToEndSingleStrategy.class) public interface IModuleRegistrationActivityView
    extends IBaseMvpView {

  @StateStrategyType(SkipStrategy.class) void finishActivity();

  void showPhoneError(String message);

  void showEmailError(String message);
}
