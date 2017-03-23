package com.apps.twelve.floor.salon.mvp.presenters.fragments;

import com.apps.twelve.floor.salon.App;
import com.apps.twelve.floor.salon.mvp.data.DataManager;
import com.apps.twelve.floor.salon.mvp.presenters.BasePresenter;
import com.apps.twelve.floor.salon.mvp.presenters.interfaces.IAllNewsFragmentPresenter;
import com.apps.twelve.floor.salon.mvp.views.IAllNewsFragmentView;
import com.apps.twelve.floor.salon.utils.ThreadSchedulers;
import com.arellomobile.mvp.InjectViewState;
import javax.inject.Inject;
import rx.Subscription;

/**
 * Created by Vrungel on 24.02.2017.
 */

@InjectViewState public class AllNewsFragmentPresenter extends BasePresenter<IAllNewsFragmentView>
    implements IAllNewsFragmentPresenter {

  @Inject DataManager mDataManager;

  @Override protected void inject() {
    App.getAppComponent().inject(this);
  }

  @Override protected void onFirstViewAttach() {
    super.onFirstViewAttach();
    fetchListOfNews();
  }

  @Override public void fetchListOfNews() {
    getViewState().startRefreshingView();
    Subscription subscription = mDataManager.fetchAllNews()
        .compose(ThreadSchedulers.applySchedulers())
        .subscribe(newsEntities -> {
          getViewState().addListOfNews(newsEntities);
          getViewState().stopRefreshingView();
        }, throwable -> {
          throwable.printStackTrace();
          getViewState().stopRefreshingView();
        });
    addToUnsubscription(subscription);
  }
}
