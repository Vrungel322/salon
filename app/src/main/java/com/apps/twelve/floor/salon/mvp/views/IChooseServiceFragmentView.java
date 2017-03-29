package com.apps.twelve.floor.salon.mvp.views;

import com.apps.twelve.floor.salon.mvp.data.model.ServiceEntity;
import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import java.util.List;

/**
 * Created by Vrungel on 29.03.2017.
 */

@StateStrategyType(AddToEndSingleStrategy.class) public interface IChooseServiceFragmentView
    extends MvpView {
  void setUpRvServices();

  void updateRvServices(List<ServiceEntity> serviceEntities);
}
