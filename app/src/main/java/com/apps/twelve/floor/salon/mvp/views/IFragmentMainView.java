package com.apps.twelve.floor.salon.mvp.views;

import com.apps.twelve.floor.salon.mvp.views.IFragmentView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

/**
 * Created by Vrungel on 20.02.2017.
 */

@StateStrategyType(AddToEndSingleStrategy.class) public interface IFragmentMainView extends IFragmentView {
}
