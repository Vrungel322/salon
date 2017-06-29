package com.apps.twelve.floor.salon.feature.main_screen.views;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

/**
 * Created by Vrungel on 01.03.2017.
 */

@StateStrategyType(AddToEndSingleStrategy.class) public interface IMyLastBookingAdapterView
    extends MvpView {

  @StateStrategyType(AddToEndSingleStrategy.class) void showConfirmationDialog(int position);

  @StateStrategyType(AddToEndSingleStrategy.class) void cancelAlertDialog();

  void showProgressBarCancel();

  void hideProgressBarCancel();

  @StateStrategyType(SkipStrategy.class) void openPostponeFragment(int position);
}
