package com.apps.twelve.floor.salon.feature.start_point.presenters;

import android.os.Handler;
import com.apps.twelve.floor.salon.App;
import com.apps.twelve.floor.salon.base.BasePresenter;
import com.apps.twelve.floor.salon.feature.start_point.views.IMainActivityView;
import com.arellomobile.mvp.InjectViewState;

/**
 * Created by Vrungel on 25.01.2017.
 */
@InjectViewState public class MainActivityPresenter extends BasePresenter<IMainActivityView> {

  @Override protected void onFirstViewAttach() {
    super.onFirstViewAttach();
    delaySplash();
  }

  @Override protected void inject() {
    App.getAppComponent().inject(this);
  }

  public void delaySplash() {
    final Handler handler = new Handler();
    handler.postDelayed(() -> getViewState().afterSplash(), 1000);
  }
}
