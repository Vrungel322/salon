package com.apps.twelve.floor.salon.feature.my_booking.views;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

/**
 * Created by Alexandra on 13.07.2017.
 */

@StateStrategyType(AddToEndSingleStrategy.class) public interface IBookDetailsFragmentView
    extends MvpView {

  void showConfirmationDialog();

  void cancelAlertDialog();

  void showProgressBar();

  void hideProgressBar();

  void closeFragment();

  @StateStrategyType(SkipStrategy.class) void openPostponeFragment();

  void updateTimeInfo(Integer time);
}
