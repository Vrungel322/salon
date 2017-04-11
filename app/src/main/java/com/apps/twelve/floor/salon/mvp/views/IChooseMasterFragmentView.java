package com.apps.twelve.floor.salon.mvp.views;

import com.apps.twelve.floor.salon.mvp.data.model.MasterEntity;
import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import java.util.List;

/**
 * Created by Vrungel on 28.03.2017.
 */

@StateStrategyType(AddToEndSingleStrategy.class) public interface IChooseMasterFragmentView
    extends MvpView {
  void setUpUi();

  void showMasters(List<MasterEntity> masterEntities);

  void hideProgressBar();

  void setSelectedItem(int position);

  void blockedClickRv(boolean status);

  void setUpRedSquare(String serviceName, String serviceTime);
}
