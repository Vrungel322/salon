package com.apps.twelve.floor.salon.mvp.views;

import com.apps.twelve.floor.salon.mvp.data.model.PreviewNewsEntity;
import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

/**
 * Created by Vrungel on 23.02.2017.
 */

@StateStrategyType(AddToEndSingleStrategy.class) public interface ISubFragmentNewsView
    extends MvpView {
  @StateStrategyType(SkipStrategy.class) void updateNewsPreview(PreviewNewsEntity previewNewsEntity);
}
