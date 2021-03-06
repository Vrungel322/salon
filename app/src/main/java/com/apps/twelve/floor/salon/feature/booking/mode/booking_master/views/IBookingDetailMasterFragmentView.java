package com.apps.twelve.floor.salon.feature.booking.mode.booking_master.views;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

/**
 * Created by John on 04.05.2017.
 */

@StateStrategyType(AddToEndSingleStrategy.class) public interface IBookingDetailMasterFragmentView
    extends MvpView {
  @StateStrategyType(SkipStrategy.class) void addFirstFragment();

  @StateStrategyType(SkipStrategy.class) void showMessageWarning(int warning);

  void hideKeyboard();

  @StateStrategyType(SkipStrategy.class) void goNextFragment(String fragmentTag);

  void setSelectedTab(String fragmentTag);

  void stateBackBookingMaster();
}
