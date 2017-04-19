package com.apps.twelve.floor.salon.feature.news.presenters;

import com.apps.twelve.floor.salon.App;
import com.apps.twelve.floor.salon.base.BasePresenter;
import com.apps.twelve.floor.salon.data.DataManager;
import com.apps.twelve.floor.salon.feature.news.views.IAllNewsFragmentView;
import com.apps.twelve.floor.salon.utils.ThreadSchedulers;
import com.arellomobile.mvp.InjectViewState;
import javax.inject.Inject;
import rx.Subscription;
import timber.log.Timber;

/**
 * Created by Vrungel on 24.02.2017.
 */

@InjectViewState public class AllNewsFragmentPresenter extends BasePresenter<IAllNewsFragmentView> {

  @Inject DataManager mDataManager;

  @Override protected void inject() {
    App.getAppComponent().inject(this);
  }

  @Override protected void onFirstViewAttach() {
    super.onFirstViewAttach();
    fetchListOfNews();
  }

  public void fetchListOfNews() {
    getViewState().startRefreshingView();
    Subscription subscription = mDataManager.fetchAllNews()
        .compose(ThreadSchedulers.applySchedulers())
        .subscribe(newsEntities -> {
          getViewState().addListOfNews(newsEntities);
          getViewState().stopRefreshingView();
        }, throwable -> {
          Timber.e(throwable);
          getViewState().stopRefreshingView();
        });
    addToUnsubscription(subscription);
  }
}
