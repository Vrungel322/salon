package com.apps.twelve.floor.salon.feature.my_bonus.presenters;

import com.apps.twelve.floor.salon.App;
import com.apps.twelve.floor.salon.base.BasePresenter;
import com.apps.twelve.floor.salon.feature.my_bonus.views.IMyBonusFragmentView;
import com.arellomobile.mvp.InjectViewState;

/**
 * Created by Vrungel on 21.02.2017.
 */

@InjectViewState public class MyBonusFragmentPresenter extends BasePresenter<IMyBonusFragmentView> {

  @Override protected void inject() {
    App.getAppComponent().inject(this);
  }
}
