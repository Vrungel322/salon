package com.apps.twelve.floor.salon.feature.start_point.views;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

/**
 * Created by Vrungel on 20.02.2017.
 */

@StateStrategyType(AddToEndSingleStrategy.class) public interface IStartActivityView
    extends MvpView {

  @StateStrategyType(SkipStrategy.class) void addFragmentMain();

  @StateStrategyType(SkipStrategy.class) void setMyBooksItemInMenu();

  @StateStrategyType(AddToEndSingleStrategy.class) void hideFloatingButton();

  @StateStrategyType(AddToEndSingleStrategy.class) void showFloatingButton();

  @StateStrategyType(SkipStrategy.class) void share();

  void setDrawerIndicator();

  @StateStrategyType(SkipStrategy.class) void setNewsItemInMenu();

  @StateStrategyType(SkipStrategy.class) void setBonusItemInMenu();

  void setBonusCount(int integer);

  @StateStrategyType(SkipStrategy.class) void showConnectErrorMessage();

  @StateStrategyType(SkipStrategy.class) void showWrongMessage();

  @StateStrategyType(SkipStrategy.class) void logoutUser();

  @StateStrategyType(AddToEndSingleStrategy.class) void showAlertDialog();

  @StateStrategyType(AddToEndSingleStrategy.class) void cancelAlertDialog();

  @StateStrategyType(SkipStrategy.class) void startSignInActivity();
}
