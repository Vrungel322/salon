package com.apps.twelve.floor.salon.feature.booking.views;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

/**
 * Created by Vrungel on 20.02.2017.
 */

@StateStrategyType(AddToEndSingleStrategy.class) public interface IBookingFragmentView
    extends MvpView {
}
