package com.apps.twelve.floor.salon.mvp.presenters;

import com.apps.twelve.floor.salon.App;
import com.apps.twelve.floor.salon.mvp.presenters.interfaces.IStartActivityPresenter;
import com.apps.twelve.floor.salon.mvp.views.IStartActivityView;
import com.arellomobile.mvp.InjectViewState;

/**
 * Created by Vrungel on 20.02.2017.
 */

@InjectViewState public class StartActivityPresenter extends BasePresenter<IStartActivityView>
    implements IStartActivityPresenter {

  @Override protected void onFirstViewAttach() {
    super.onFirstViewAttach();
    getViewState().addFragmentMain();
  }

  @Override protected void inject() {
    App.getAppComponent().inject(this);
  }
}
