package com.apps.twelve.floor.salon.feature.news.views;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

/**
 * Created by John on 11.07.2017.
 */
@StateStrategyType(AddToEndSingleStrategy.class) public interface IListNewsDetailFragmentView
    extends MvpView {
}
