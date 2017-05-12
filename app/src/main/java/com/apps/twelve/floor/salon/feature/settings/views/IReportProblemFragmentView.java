package com.apps.twelve.floor.salon.feature.settings.views;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

/**
 * Created by Alexandra on 05.05.2017.
 */

@StateStrategyType(AddToEndSingleStrategy.class) public interface IReportProblemFragmentView
    extends MvpView {

  @StateStrategyType(SkipStrategy.class) void closeFragment();

  void showAlert();

  void stopAnimation();
}
