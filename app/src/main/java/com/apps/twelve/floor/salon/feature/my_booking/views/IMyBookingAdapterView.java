package com.apps.twelve.floor.salon.feature.my_booking.views;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

/**
 * Created by Alexandra on 27.04.2017.
 */

@StateStrategyType(AddToEndSingleStrategy.class) public interface IMyBookingAdapterView
    extends MvpView {

  void showConfirmationDialog(int position);

  void cancelAlertDialog();

  void showProgressBarCancel();

  void hideProgressBarCancel();

  @StateStrategyType(SkipStrategy.class) void openPostponeFragment(int position);
}