package com.apps.twelve.floor.salon.mvp.presenters.fragments;

import com.apps.twelve.floor.salon.App;
import com.apps.twelve.floor.salon.mvp.presenters.BasePresenter;
import com.apps.twelve.floor.salon.mvp.presenters.interfaces.IChooseMasterFragmentPresenter;
import com.apps.twelve.floor.salon.mvp.views.IChooseMasterFragmentView;

/**
 * Created by Vrungel on 28.03.2017.
 */

public class ChooseMasterFragmentPresenter extends BasePresenter<IChooseMasterFragmentView>
    implements IChooseMasterFragmentPresenter {
  @Override protected void inject() {
    App.getAppComponent().inject(this);
  }
}
