package com.apps.twelve.floor.salon.mvp.views;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import java.util.List;

/**
 * Created by Vrungel on 27.03.2017.
 */

@StateStrategyType(AddToEndSingleStrategy.class) public interface IChooseTimeFragmentView
    extends MvpView {

  void setUpUi(List<String> days);
}
