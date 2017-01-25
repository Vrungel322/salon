package com.apps.twelve.floor.salon.mvp.presenters;

import com.apps.twelve.floor.salon.App;
import com.apps.twelve.floor.salon.mvp.presenters.BasePresenter;
import com.apps.twelve.floor.salon.mvp.presenters.interfaces.IMainActivityPresenter;
import com.apps.twelve.floor.salon.mvp.views.IMainActivityView;
import com.arellomobile.mvp.InjectViewState;

/**
 * Created by Vrungel on 25.01.2017.
 */
@InjectViewState public class MainActivityPresenter extends BasePresenter<IMainActivityView>
    implements IMainActivityPresenter {

  public MainActivityPresenter() {
    App.getsAppComponent().inject(this);
  }

  @Override protected void onFirstViewAttach() {
    super.onFirstViewAttach();
  }

  @Override public void showTestText(String str) {
    getViewState().showText(str);
  }
}
