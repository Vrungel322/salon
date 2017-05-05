package com.apps.twelve.floor.salon.feature.booking.mode.booking_master.views;

import com.apps.twelve.floor.salon.data.model.ServiceEntity;
import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import java.util.List;

@StateStrategyType(AddToEndSingleStrategy.class) public interface IChooseMasterServiceView
    extends MvpView {
  void setUpRvServices();

  void updateRvServices(List<ServiceEntity> serviceEntities);

  void setItemSelected(int position);

  void hideProgressBar();

  void setMasterName(String masterName);
}
