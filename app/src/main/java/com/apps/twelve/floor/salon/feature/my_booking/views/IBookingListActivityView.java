package com.apps.twelve.floor.salon.feature.my_booking.views;

import com.apps.twelve.floor.salon.data.model.LastBookingEntity;
import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

/**
 * Created by Vrungel on 20.07.2017.
 */
@StateStrategyType(AddToEndSingleStrategy.class) public interface IBookingListActivityView
    extends MvpView {
  void showMyBookFragment(LastBookingEntity lastBookingEntity);
}
