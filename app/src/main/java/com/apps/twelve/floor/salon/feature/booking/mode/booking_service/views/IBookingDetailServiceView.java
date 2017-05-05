package com.apps.twelve.floor.salon.feature.booking.mode.booking_service.views;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

/**
 * Created by John on 05.05.2017.
 */

@StateStrategyType(AddToEndSingleStrategy.class) public interface IBookingDetailServiceView
    extends MvpView {
}
