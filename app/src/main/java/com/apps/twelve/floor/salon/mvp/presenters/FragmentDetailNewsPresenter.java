package com.apps.twelve.floor.salon.mvp.presenters;

import com.apps.twelve.floor.salon.App;
import com.apps.twelve.floor.salon.mvp.presenters.interfaces.IFragmentNewsDetailPresenter;
import com.apps.twelve.floor.salon.mvp.views.IFragmentNewsDetailView;
import com.arellomobile.mvp.InjectViewState;

/**
 * Created by Vrungel on 23.02.2017.
 */

@InjectViewState public class FragmentDetailNewsPresenter
    extends BasePresenter<IFragmentNewsDetailView> implements IFragmentNewsDetailPresenter {
  @Override protected void inject() {
    App.getAppComponent().inject(this);
  }
}
