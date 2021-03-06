package com.apps.twelve.floor.salon.feature.my_booking.views;

import com.apps.twelve.floor.salon.data.model.LastBookingEntity;
import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import java.util.List;

/**
 * Created by Vrungel on 21.02.2017.
 */

@StateStrategyType(AddToEndSingleStrategy.class) public interface IMyBookFragmentView
    extends MvpView {

  void showAllBooking(List<LastBookingEntity> bookingEntities);

  void startRefreshingView();

  void stopRefreshingView();
}
