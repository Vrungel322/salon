package com.apps.twelve.floor.salon.feature.main_screen.presenters;

import com.apps.twelve.floor.salon.App;
import com.apps.twelve.floor.salon.base.BasePresenter;
import com.apps.twelve.floor.salon.feature.main_screen.views.ISubNewsFragmentView;
import com.apps.twelve.floor.salon.utils.RxBusHelper;
import com.apps.twelve.floor.salon.utils.ThreadSchedulers;
import com.arellomobile.mvp.InjectViewState;
import rx.Subscription;
import timber.log.Timber;

/**
 * Created by Vrungel on 23.02.2017.
 */

@InjectViewState public class SubNewsFragmentPresenter extends BasePresenter<ISubNewsFragmentView> {

  @Override protected void inject() {
    App.getAppComponent().inject(this);
  }

  @Override protected void onFirstViewAttach() {
    super.onFirstViewAttach();
    fetchNewsEntities();
    //RxBus
    subscribeUpdateNews();
  }

  private void fetchNewsEntities() {
    Subscription subscription = mDataManager.fetchNewsPreview()
        .compose(ThreadSchedulers.applySchedulers())
        .subscribe(previewNewsEntity -> {
          getViewState().updateNewsPreview(previewNewsEntity);
          mRxBus.post(new RxBusHelper.StopRefreshNewsMainFragment());
        }, throwable -> {
          Timber.e(throwable);
          mRxBus.post(new RxBusHelper.StopRefreshNewsMainFragment());
          showMessageConnectException(throwable);
        });
    addToUnsubscription(subscription);
  }

  private void subscribeUpdateNews() {
    Subscription subscription = mRxBus.filteredObservable(RxBusHelper.UpdateNews.class)
        .concatMap(updateNews -> mDataManager.fetchNewsPreview())
        .compose(ThreadSchedulers.applySchedulers())
        .subscribe(newsEntity -> {
          getViewState().updateNewsPreview(newsEntity);
          mRxBus.post(new RxBusHelper.StopRefreshNewsMainFragment());
        }, throwable -> {
          mRxBus.post(new RxBusHelper.StopRefreshNewsMainFragment());
          subscribeUpdateNews();
          Timber.e(throwable);
          showMessageConnectException(throwable);
        });
    addToUnsubscription(subscription);
  }
}
