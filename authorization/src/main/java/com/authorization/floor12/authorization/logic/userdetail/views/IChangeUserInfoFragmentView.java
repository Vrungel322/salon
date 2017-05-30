package com.authorization.floor12.authorization.logic.userdetail.views;

import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.authorization.floor12.authorization.base.IBaseMvpView;

/**
 * Created by Alexandra on 04.05.2017.
 */

@StateStrategyType(AddToEndSingleStrategy.class) public interface IChangeUserInfoFragmentView
    extends IBaseMvpView {

  @StateStrategyType(SkipStrategy.class) void closeFragment();

  @StateStrategyType(SkipStrategy.class) void showSignInActivity();

  void showPhoneError(String errorMessage);

  void showEmailError(String errorMessage);

  void showPasswordError(String errorMessage);
}
