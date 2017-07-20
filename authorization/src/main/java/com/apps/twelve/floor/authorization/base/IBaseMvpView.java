package com.apps.twelve.floor.authorization.base;

import android.support.annotation.StringRes;
import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

/**
 * Created by alexander on 28.05.17.
 */

@StateStrategyType(AddToEndSingleStrategy.class) public interface IBaseMvpView extends MvpView {
  void showConnectErrorMessage();

  void showAlerter(@StringRes int resId);

  void stopAnimation();

  void revertAnimation();
}
