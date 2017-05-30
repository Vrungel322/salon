package com.authorization.floor12.authorization.logic.recoverypassword.views;

import android.support.annotation.StringRes;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.authorization.floor12.authorization.base.IBaseMvpView;

/**
 * Created by Alexander Svyatetsky on 17.05.2017.
 */

@StateStrategyType(AddToEndSingleStrategy.class) public interface IRecoveryPasswordActivity
    extends IBaseMvpView {

  void showSignInActivity();

  void showDialogMessage(@StringRes int messageResId);

  void showError(@StringRes int messageResId);
}
