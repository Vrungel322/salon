package com.apps.twelve.floor.salon.mvp.views;

import com.apps.twelve.floor.salon.mvp.data.model.ServiceEntity;
import com.apps.twelve.floor.salon.mvp.data.model.service_tree_item.ParentService;
import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import java.util.List;

/**
 * Created by Vrungel on 29.03.2017.
 */

@StateStrategyType(AddToEndSingleStrategy.class) public interface IChooseServiceFragmentView
    extends MvpView {
  void setUpRvAllServices();

  void updateRvAllServices(List<ServiceEntity> serviceEntities);

  void updateRvTreeServices(List<ParentService> serviceEntities);

  void hideLLAllServices();

  void setItemSelected(int position);

  void showProgressBarAllServices();

  void hideProgressBarAllServices();

  void showErrorMsg(String s);

  void hideProgressBar();

  void showProgressBar();

  void setUpRvTreeServices();

  void showLLTreeServices();

  void showLLAllServices();

  void hideLLTreeServices();
}
