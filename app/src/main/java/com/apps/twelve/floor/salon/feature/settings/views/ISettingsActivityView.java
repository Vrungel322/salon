package com.apps.twelve.floor.salon.feature.settings.views;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

/**
 * Created by Alexandra on 18.04.2017.
 */

@StateStrategyType(AddToEndSingleStrategy.class) public interface ISettingsActivityView
    extends MvpView {

  @StateStrategyType(SkipStrategy.class) void addFragmentSettings();

  @StateStrategyType(SkipStrategy.class) void showConnectErrorMessage();

  @StateStrategyType(SkipStrategy.class) void logoutUser();

  @StateStrategyType(SkipStrategy.class) void startSignInActivity();

  @StateStrategyType(SkipStrategy.class) void showWrongMessage();
}
