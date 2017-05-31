package com.apps.twelve.floor.salon.feature.main_screen.views;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

/**
 * Created by Vrungel on 27.02.2017.
 */

@StateStrategyType(AddToEndSingleStrategy.class) public interface ISubBonusRegestrationFragmentView
    extends MvpView {

  void setBonusCount(String bonusCount);

  void setUserPhoto(String photoUri);

  @StateStrategyType(SkipStrategy.class) void openRegistrationOrBonus(boolean authorized);

  void showCardRegistration();
}
