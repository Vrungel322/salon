package com.apps.twelve.floor.salon.feature.my_booking.views;

import com.apps.twelve.floor.salon.data.model.DataServiceEntity;
import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import java.util.List;

@StateStrategyType(AddToEndSingleStrategy.class) public interface IPostponeFragmentView
    extends MvpView {

  void showSuccessMessageAndCloseTheFragment();

  void showErrorMessage(String message);

  void setConfirmButtonClickable();

  void setConfirmButtonUnClickable();

  void setUpUi(List<DataServiceEntity> days);

  void setSelectedTime(int position);

  void setSelectedDay(int position);

  void setTextToDayTv();

  void showTimeBooking();

  void showNotTime();

  void hideProgressBarBookingTime();

  void clearSelectedTime();

  void setUpRedSquare(String serviceName, String masterName);

  void timeIsNotAvailable();
}
