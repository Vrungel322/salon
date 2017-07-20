package com.apps.twelve.floor.salon.feature.booking.mode.booking_master.views;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

@StateStrategyType(AddToEndSingleStrategy.class) public interface IChooseMasterContactFragmentView
    extends MvpView {
  void setUpBookingInformation(String serviceName, String serviceTime, String serviceDuration,
      String masterName);

  void startAnimation();

  void stopAnimation();

  void revertAnimation();

  void showEmptyPhoneError(boolean show);

  void showEmptyNameError(boolean show);

  void showErrorMessage(int message);

  @StateStrategyType(SkipStrategy.class) void moveToBookingListActivity();

  void setLastPhone(String s);
}
