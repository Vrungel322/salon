package com.apps.twelve.floor.salon.feature.settings.views;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

/**
 * Created by alexandersvyatetsky on 4/08/17.
 */

@StateStrategyType(AddToEndSingleStrategy.class) public interface IAboutApplicationDialog
    extends MvpView {
}
