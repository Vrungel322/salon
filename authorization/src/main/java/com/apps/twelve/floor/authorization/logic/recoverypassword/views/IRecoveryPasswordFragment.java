package com.apps.twelve.floor.authorization.logic.recoverypassword.views;

import android.support.annotation.StringRes;
import com.apps.twelve.floor.authorization.base.IBaseMvpView;
import com.apps.twelve.floor.authorization.data.model.CredentialsEntity;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

/**
 * Created by Alexander Svyatetsky on 17.05.2017.
 */

@StateStrategyType(AddToEndSingleStrategy.class) public interface IRecoveryPasswordFragment
    extends IBaseMvpView {

  @StateStrategyType(SkipStrategy.class) void showRecoveryPasswordFragment(
      CredentialsEntity credentialsEntity);

  void showDialogMessage(@StringRes int messageResId);

  void closeDialogMessage();

  @StateStrategyType(SkipStrategy.class) void showVerifyDialog();

  void closeVerifyDialog();

  void showError(@StringRes int messageResId);

  void showVerifyError(String errorMessage);

  void revertVerifyButtonAnimation();

  void stopVerifyButtonAnimation();
}
