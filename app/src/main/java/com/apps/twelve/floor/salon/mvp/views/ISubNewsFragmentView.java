package com.apps.twelve.floor.salon.mvp.views;

import com.apps.twelve.floor.salon.mvp.data.model.NewsEntity;
import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

/**
 * Created by Vrungel on 23.02.2017.
 */

@StateStrategyType(AddToEndSingleStrategy.class) public interface ISubNewsFragmentView
    extends MvpView {
  void updateNewsPreview(NewsEntity newsEntity);
}
