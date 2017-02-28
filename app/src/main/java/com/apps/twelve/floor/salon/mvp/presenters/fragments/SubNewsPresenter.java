package com.apps.twelve.floor.salon.mvp.presenters.fragments;

import com.apps.twelve.floor.salon.App;
import com.apps.twelve.floor.salon.mvp.data.DataManager;
import com.apps.twelve.floor.salon.mvp.presenters.BasePresenter;
import com.apps.twelve.floor.salon.mvp.presenters.interfaces.ISubNewsFragmentPresenter;
import com.apps.twelve.floor.salon.mvp.views.ISubNewsFragmentView;
import com.apps.twelve.floor.salon.utils.ThreadSchedulers;
import com.arellomobile.mvp.InjectViewState;
import javax.inject.Inject;
import rx.Subscription;

/**
 * Created by Vrungel on 23.02.2017.
 */

@InjectViewState public class SubNewsPresenter extends BasePresenter<ISubNewsFragmentView>
    implements ISubNewsFragmentPresenter {
  @Inject DataManager mDataManager;

  @Override protected void inject() {
    App.getAppComponent().inject(this);
  }

  @Override protected void onFirstViewAttach() {
    super.onFirstViewAttach();
    Subscription subscription = mDataManager.fetchNewsPreview()
        .compose(ThreadSchedulers.applySchedulers())
        .subscribe(previewNewsEntity -> getViewState().updateNewsPreview(previewNewsEntity),
            Throwable::printStackTrace);
    addToUnsubscription(subscription);
  }
}