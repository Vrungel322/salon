package com.apps.twelve.floor.salon.feature.booking.mode.booking_master.views;

import com.apps.twelve.floor.salon.data.model.MasterEntity;
import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import java.util.List;

@StateStrategyType(AddToEndSingleStrategy.class) public interface IChooseMasterMasterView
    extends MvpView {
  void setUpUi();

  void showMasters(List<MasterEntity> masterEntities);

  void hideProgressBar();

  void setSelectedItem(int position);

  void blockedClickRv(boolean status);
}
