package com.apps.twelve.floor.salon.mvp.presenters.fragments;

import com.apps.twelve.floor.salon.App;
import com.apps.twelve.floor.salon.mvp.presenters.BasePresenter;
import com.apps.twelve.floor.salon.mvp.presenters.interfaces.ISubBonusRegestrationFragmentPresenter;
import com.apps.twelve.floor.salon.mvp.views.ISubBonusRegestrationFragmentView;
import com.arellomobile.mvp.InjectViewState;

/**
 * Created by Vrungel on 27.02.2017.
 */

@InjectViewState public class SubBonusRegistrationFragmentPresenter
    extends BasePresenter<ISubBonusRegestrationFragmentView>
    implements ISubBonusRegestrationFragmentPresenter {
  @Override protected void inject() {
    App.getAppComponent().inject(this);
  }
}
