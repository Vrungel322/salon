package com.apps.twelve.floor.salon.feature.booking.mode.booking_service.views;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

/**
 * Created by Alexandra on 28.03.2017.
 */

@StateStrategyType(AddToEndSingleStrategy.class) public interface IChooseServiceContactFragmentView
    extends MvpView {
  void setUpBookingInformation(String serviceName, String serviceTime, String serviceDuration,
      String masterName);

  void startAnimation();

  void stopAnimation();

  void revertAnimation();

  void showEmptyPhoneError();

  void setLastPhone(String lastPhone);

  void showErrorMessage(int message);

  @StateStrategyType(SkipStrategy.class) void closeBooking();
}
