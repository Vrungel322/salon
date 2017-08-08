package com.apps.twelve.floor.salon.feature.my_booking.views;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

/**
 * Created by alexandersvyatetsky on 28/07/17.
 */

@StateStrategyType(AddToEndSingleStrategy.class) public interface IBookingDetailActivity
    extends MvpView {
  @StateStrategyType(SkipStrategy.class) void addBookingDetailFragment();
}
