package com.apps.twelve.floor.salon.feature.my_bonus.presenters;

import com.apps.twelve.floor.salon.App;
import com.apps.twelve.floor.salon.base.BasePresenter;
import com.apps.twelve.floor.salon.feature.my_bonus.views.IBonusHowFragmentView;
import com.arellomobile.mvp.InjectViewState;

/**
 * Created by Alexandra on 29.05.2017.
 */

@InjectViewState public class BonusHowFragmentPresenter
    extends BasePresenter<IBonusHowFragmentView> {

  @Override protected void inject() {
    App.getAppComponent().inject(this);
  }

  @Override protected void onFirstViewAttach() {
    super.onFirstViewAttach();
  }
}