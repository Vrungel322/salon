package com.apps.twelve.floor.salon.feature.catalog.presenters;

import com.apps.twelve.floor.salon.App;
import com.apps.twelve.floor.salon.base.BasePresenter;
import com.apps.twelve.floor.salon.feature.catalog.views.IStaffDetailsFragmentView;
import com.arellomobile.mvp.InjectViewState;

/**
 * Created by Vrungel on 18.05.2017.
 */
@InjectViewState
public class StaffDetailsFragmentPresenter extends BasePresenter<IStaffDetailsFragmentView> {
  @Override protected void inject() {
    App.getAppComponent().inject(this);
  }
}
