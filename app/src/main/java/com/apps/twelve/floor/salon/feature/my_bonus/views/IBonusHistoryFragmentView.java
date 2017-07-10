package com.apps.twelve.floor.salon.feature.my_bonus.views;

import com.apps.twelve.floor.salon.data.model.BonusHistoryEntity;
import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import java.util.List;

/**
 * Created by Alexandra on 30.05.2017.
 */

@StateStrategyType(AddToEndSingleStrategy.class) public interface IBonusHistoryFragmentView
    extends MvpView {

  void setBonusCount(Integer count);

  void addBonusHistoryList(List<BonusHistoryEntity> entities);

  void startRefreshingView();

  void stopRefreshingView();
}