package com.apps.twelve.floor.salon.mvp.views;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

/**
 * Created by Alexandra on 23.02.2017.
 */

@StateStrategyType(AddToEndSingleStrategy.class) public interface IWorkDetailsFragmentView
    extends MvpView {

  @StateStrategyType(AddToEndStrategy.class) void setStatusFavorite(boolean statusFavorite);
}
