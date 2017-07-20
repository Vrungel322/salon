package com.apps.twelve.floor.authorization.logic.userdetail.views;

import com.apps.twelve.floor.authorization.base.IBaseMvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

/**
 * Created by Alexandra on 04.05.2017.
 */

@StateStrategyType(AddToEndSingleStrategy.class) public interface IChangeUserInfoFragmentView
    extends IBaseMvpView {

  @StateStrategyType(SkipStrategy.class) void closeFragment();

  @StateStrategyType(SkipStrategy.class) void showSignInActivity();

  @StateStrategyType(SkipStrategy.class) void showVerifyDialog();

  void closeVerifyDialog();

  void showPhoneError(String errorMessage);

  void showEmailError(String errorMessage);

  void showPasswordError(String errorMessage);

  void showDialogMessage(String message);

  void closeDialogMessage();

  void revertVerifyButtonAnimation();

  void stopVerifyButtonAnimation();

  void showVerifyError(String errorMessage);
}
