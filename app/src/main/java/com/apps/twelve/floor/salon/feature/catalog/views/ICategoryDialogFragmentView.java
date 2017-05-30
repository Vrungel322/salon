package com.apps.twelve.floor.salon.feature.catalog.views;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

/**
 * Created by Vrungel on 29.05.2017.
 */
@StateStrategyType(AddToEndSingleStrategy.class) public interface ICategoryDialogFragmentView
    extends MvpView {
}
