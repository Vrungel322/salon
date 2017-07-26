package com.apps.twelve.floor.salon.feature.main_screen.views;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

/**
 * Created by Vrungel on 20.02.2017.
 */

@StateStrategyType(AddToEndSingleStrategy.class) public interface IMainFragmentView
    extends MvpView {

  @StateStrategyType(SkipStrategy.class) void addSubNewsAndBonus();

  void stopRefreshingView();

  void startRefreshingView();

  void hideSubBookingFragment();

  void showSubBookingFragment();
}
