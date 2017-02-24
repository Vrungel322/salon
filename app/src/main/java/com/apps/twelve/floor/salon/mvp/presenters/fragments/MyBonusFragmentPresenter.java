package com.apps.twelve.floor.salon.mvp.presenters.fragments;

import com.apps.twelve.floor.salon.App;
import com.apps.twelve.floor.salon.mvp.presenters.base.FragmentBasePresenter;
import com.apps.twelve.floor.salon.mvp.presenters.interfaces.IMyBonusFragmentPresenter;
import com.apps.twelve.floor.salon.mvp.views.IMyBonusFragmentView;
import com.arellomobile.mvp.InjectViewState;

/**
 * Created by Vrungel on 21.02.2017.
 */

@InjectViewState public class MyBonusFragmentPresenter extends FragmentBasePresenter<IMyBonusFragmentView>
    implements IMyBonusFragmentPresenter {
  @Override protected void inject() {
    App.getAppComponent().inject(this);
  }
}
