package com.apps.twelve.floor.salon.mvp.presenters.activities;

import android.os.Handler;
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

  @Override protected void onFirstViewAttach() {
    super.onFirstViewAttach();
    delaySplash();
  }

  @Override protected void inject() {
    App.getAppComponent().inject(this);
  }

  @Override public void delaySplash() {
    final Handler handler = new Handler();
    handler.postDelayed(() -> getViewState().afterSplash(), 1);
  }
}
