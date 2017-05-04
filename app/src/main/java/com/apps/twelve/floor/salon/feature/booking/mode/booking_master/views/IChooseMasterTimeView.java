package com.apps.twelve.floor.salon.feature.booking.mode.booking_master.views;

import com.apps.twelve.floor.salon.data.model.DataServiceEntity;
import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import java.util.List;

@StateStrategyType(AddToEndSingleStrategy.class) public interface IChooseMasterTimeView
    extends MvpView {

  void setUpUi(List<DataServiceEntity> days);

  void setSelectedTime(int position);

  void setSelectedDay(int position);

  void setTextToDayTv();

  void showTimeBooking();

  void showNotTime();

  void hideProgressBarBookingTime();

  void setUpRedSquare(String serviceName, String masterName);

  void timeIsNotAvailable();
}
