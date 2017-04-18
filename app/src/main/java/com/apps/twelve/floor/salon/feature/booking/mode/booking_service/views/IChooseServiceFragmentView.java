package com.apps.twelve.floor.salon.feature.booking.mode.booking_service.views;

import com.apps.twelve.floor.salon.data.model.CategoryEntity;
import com.apps.twelve.floor.salon.data.model.ServiceEntity;
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

  void updateRvCategory(List<CategoryEntity> serviceEntities);

  void hideLLAllServices();

  void setItemSelected(int position);

  void showProgressBarAllServices();

  void hideProgressBarAllServices();

  void showErrorMsg(String s);

  void hideProgressBar();

  void showProgressBar();

  void setUpRvCategory();

  void showLLTreeServices();

  void showLLAllServices();

  void hideLLTreeServices();

  void setServicesWithParentId(List<ServiceEntity> serviceEntities);

  void setCategoriesWithParentId(List<CategoryEntity> categoryEntities);

  void showTextPath(String text);

  void hideTextPath();

  void stateCategoriesServices(boolean state);
}
