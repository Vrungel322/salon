package com.apps.twelve.floor.salon.mvp.presenters.pr_fragments;

import com.apps.twelve.floor.salon.App;
import com.apps.twelve.floor.salon.mvp.data.DataManager;
import com.apps.twelve.floor.salon.mvp.presenters.BasePresenter;
import com.apps.twelve.floor.salon.mvp.views.IChooseMasterFragmentView;
import com.apps.twelve.floor.salon.utils.ThreadSchedulers;
import com.arellomobile.mvp.InjectViewState;
import javax.inject.Inject;
import rx.Subscription;

/**
 * Created by Vrungel on 28.03.2017.
 */

@InjectViewState public class ChooseMasterFragmentPresenter
    extends BasePresenter<IChooseMasterFragmentView> {
  @Inject DataManager mDataManager;

  @Override protected void inject() {
    App.getAppComponent().inject(this);
  }

  @Override protected void onFirstViewAttach() {
    super.onFirstViewAttach();
    getViewState().setUpUi();
    fetchMasters();
  }

  private void fetchMasters() {
    Subscription subscription = mDataManager.fetchMasters()
        .compose(ThreadSchedulers.applySchedulers()).subscribe(masterEntities -> {
          getViewState().showMasters(masterEntities);
          getViewState().hideProgressBar();
        });
    addToUnsubscription(subscription);
  }
}
