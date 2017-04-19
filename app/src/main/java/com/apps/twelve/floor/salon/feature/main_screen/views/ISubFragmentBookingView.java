package com.apps.twelve.floor.salon.feature.main_screen.views;

import com.apps.twelve.floor.salon.data.model.LastBookingEntity;
import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import java.util.List;

/**
 * Created by Vrungel on 28.02.2017.
 */

@StateStrategyType(AddToEndSingleStrategy.class) public interface ISubFragmentBookingView
    extends MvpView {
  void showAllBooking(List<LastBookingEntity> lastBookingEntities);

  void updateRvTiming();
}
