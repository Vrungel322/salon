package com.apps.twelve.floor.salon.feature.booking.views;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

/**
 * Created by Vrungel on 23.03.2017.
 */

@StateStrategyType(AddToEndSingleStrategy.class) public interface IBookingDetailFragmentView
    extends MvpView {
  void setUpViewPager();

  void goNext(int position);

  void goPrev(int position);

  void hideKeyboard();

  void replaceTitleNextButton(boolean state);

  @StateStrategyType(SkipStrategy.class) void showMessageWarning(int warning);

  @StateStrategyType(SkipStrategy.class) void stateBooking();
}
