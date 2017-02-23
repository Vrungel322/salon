package com.apps.twelve.floor.salon.mvp.presenters;

import com.apps.twelve.floor.salon.App;
import com.apps.twelve.floor.salon.mvp.presenters.interfaces.ISubFragmentNewsPresenter;
import com.apps.twelve.floor.salon.mvp.views.ISubFragmentNewsView;
import com.arellomobile.mvp.InjectViewState;

/**
 * Created by Vrungel on 23.02.2017.
 */

@InjectViewState public class SubFragmentNewsPresenter extends BasePresenter<ISubFragmentNewsView>
    implements ISubFragmentNewsPresenter {
  @Override protected void inject() {
    App.getAppComponent().inject(this);
  }
}
