package com.authorization.floor12.authorization.logic.userdetail.views;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

/**
 * Created by Alexander Svyatetsky on 05.05.2017.
 */

@StateStrategyType(AddToEndSingleStrategy.class) public interface IUserProfileActivityView
    extends MvpView {

  @StateStrategyType(SkipStrategy.class) void addFragmentSettings();
}
