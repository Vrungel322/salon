package com.apps.twelve.floor.salon.mvp.views;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

/**
 * Created by Alexandra on 28.03.2017.
 */

@StateStrategyType(AddToEndSingleStrategy.class) public interface IBookingContactFragmentView
    extends MvpView {
}
