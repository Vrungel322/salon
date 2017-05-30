package com.apps.twelve.floor.salon.feature.catalog.views;

import com.apps.twelve.floor.salon.data.model.GoodsEntity;
import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import java.util.List;

/**
 * Created by John on 17.05.2017.
 */

@StateStrategyType(AddToEndSingleStrategy.class) public interface ICatalogFragmentView
    extends MvpView {
  void setUpUi();

  void updateGoodsList(List<GoodsEntity> goodsEntities);
}
