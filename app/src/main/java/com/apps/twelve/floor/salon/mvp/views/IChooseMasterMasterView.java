package com.apps.twelve.floor.salon.mvp.views;

import com.apps.twelve.floor.salon.mvp.data.model.MasterEntity;
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
