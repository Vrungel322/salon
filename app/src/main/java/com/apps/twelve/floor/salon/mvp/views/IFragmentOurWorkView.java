package com.apps.twelve.floor.salon.mvp.views;

import com.apps.twelve.floor.salon.mvp.data.model.OurWorkEntity;
import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import java.util.List;

/**
 * Created by Vrungel on 21.02.2017.
 */

@StateStrategyType(AddToEndSingleStrategy.class) public interface IFragmentOurWorkView
    extends MvpView {
  void updateListOfWorks(List<OurWorkEntity> ourWorkEntities);
}
