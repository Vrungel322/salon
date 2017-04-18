package com.apps.twelve.floor.salon.feature.main_screen.presenters;

import com.apps.twelve.floor.salon.App;
import com.apps.twelve.floor.salon.data.DataManager;
import com.apps.twelve.floor.salon.base.BasePresenter;
import com.apps.twelve.floor.salon.feature.main_screen.views.ISubNewsFragmentView;
import com.apps.twelve.floor.salon.utils.ThreadSchedulers;
import com.arellomobile.mvp.InjectViewState;
import javax.inject.Inject;
import rx.Subscription;
import timber.log.Timber;

/**
 * Created by Vrungel on 23.02.2017.
 */

@InjectViewState public class SubNewsFragmentPresenter extends BasePresenter<ISubNewsFragmentView> {
  @Inject DataManager mDataManager;

  @Override protected void inject() {
    App.getAppComponent().inject(this);
  }

  @Override protected void onFirstViewAttach() {
    super.onFirstViewAttach();
    fetchNewsEntities();
  }

  public void fetchNewsEntities() {
    Subscription subscription = mDataManager.fetchNewsPreview()
        .compose(ThreadSchedulers.applySchedulers())
        .subscribe(previewNewsEntity -> getViewState().updateNewsPreview(previewNewsEntity),
            Timber::e);
    addToUnsubscription(subscription);
  }
}
