package com.apps.twelve.floor.salon.mvp.views;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

/**
 * Created by Vrungel on 01.03.2017.
 */

@StateStrategyType(AddToEndSingleStrategy.class) public interface IMyLastBookingAdapterView
    extends MvpView {
  @StateStrategyType(AddToEndStrategy.class) void removeBookedServiceFromList(int position);
}
