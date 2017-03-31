package com.apps.twelve.floor.salon.mvp.views;

import com.apps.twelve.floor.salon.mvp.data.model.ServiceEntity;
import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import java.util.List;

@StateStrategyType(AddToEndSingleStrategy.class) public interface IChooseMasterServiceView
    extends MvpView {
  void setUpRvServices();

  void updateRvServices(List<ServiceEntity> serviceEntities);

  void showRvAllServices();

  void hideRvAllServices();

  void setItemSelected(int position);

  void showProgressBarAllServices();

  void hideProgressBarAllServices();

  void showErrorMsg(String s);

  void hideProgressBar();
}
