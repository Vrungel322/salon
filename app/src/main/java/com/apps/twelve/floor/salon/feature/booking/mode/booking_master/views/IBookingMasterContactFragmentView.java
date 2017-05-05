package com.apps.twelve.floor.salon.feature.booking.mode.booking_master.views;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

@StateStrategyType(AddToEndSingleStrategy.class) public interface IBookingMasterContactFragmentView
    extends MvpView {
  void setUpBookingInformation(String serviceName, String serviceTime, String serviceDuration,
      String masterName);

  void closeActivity();

  void showAlert();

  void setButtonClickable();
}