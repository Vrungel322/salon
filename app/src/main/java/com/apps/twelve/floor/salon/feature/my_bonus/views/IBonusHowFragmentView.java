package com.apps.twelve.floor.salon.feature.my_bonus.views;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

/**
 * Created by Alexandra on 29.05.2017.
 */

@StateStrategyType(AddToEndSingleStrategy.class) public interface IBonusHowFragmentView
    extends MvpView {

  void showStringBody(String text);
}