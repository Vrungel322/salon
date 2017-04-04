package com.apps.twelve.floor.salon.mvp.views;

import com.apps.twelve.floor.salon.mvp.data.model.DataServiceEntity;
import com.apps.twelve.floor.salon.mvp.data.model.WorkStartEndEntity;
import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import java.util.List;

@StateStrategyType(AddToEndSingleStrategy.class) public interface IChooseMasterTimeView
    extends MvpView {

  void setUpUi(List<DataServiceEntity> days);

  void updateWorkSchedule(List<DataServiceEntity> dataServiceEntities);

  void setSelectedTime(int position);

  void setSelectedDay(int position);

  void setTextToDayTv();

  void showTimeBooking();

  void showNotTime();

  void hideProgressBarBookingTime();

}
