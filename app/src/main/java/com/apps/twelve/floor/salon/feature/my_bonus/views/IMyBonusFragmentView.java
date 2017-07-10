package com.apps.twelve.floor.salon.feature.my_bonus.views;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

/**
 * Created by Vrungel on 21.02.2017.
 */

@StateStrategyType(AddToEndSingleStrategy.class) public interface IMyBonusFragmentView
    extends MvpView {

  void setBonusCount(Integer count);

  void startRefreshingView();

  void stopRefreshingView();
}
