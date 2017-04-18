package com.apps.twelve.floor.salon.mvp.views;

import android.net.Uri;
import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

/**
 * Created by Alexandra on 18.04.2017.
 */

@StateStrategyType(AddToEndSingleStrategy.class) public interface ISettingsActivityView
    extends MvpView {

  public void setUserPhoto(Uri uri);
}
