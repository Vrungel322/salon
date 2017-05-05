package com.apps.twelve.floor.salon.feature.booking.views;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

/**
 * Created by John on 23.03.2017.
 */

@StateStrategyType(AddToEndSingleStrategy.class) public interface IBookingActivityView
    extends MvpView {

  @StateStrategyType(SkipStrategy.class) void addFragmentBooking();
}
