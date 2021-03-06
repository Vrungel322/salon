package com.apps.twelve.floor.salon.feature.main_screen.presenters;

import com.apps.twelve.floor.salon.App;
import com.apps.twelve.floor.salon.base.BasePresenter;
import com.apps.twelve.floor.salon.feature.main_screen.views.ISubNewsFragmentView;
import com.apps.twelve.floor.salon.utils.RxBusHelper;
import com.apps.twelve.floor.salon.utils.ThreadSchedulers;
import com.arellomobile.mvp.InjectViewState;
import rx.Subscription;
import timber.log.Timber;

import static com.apps.twelve.floor.salon.utils.Constants.StatusCode.RESPONSE_200;

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
        .subscribe(response -> {
          if (response.code() == RESPONSE_200) {
            getViewState().updateNewsPreview(response.body());
            mRxBus.post(new RxBusHelper.StopRefreshNewsMainFragment());
          }
        }, throwable -> {
          Timber.e(throwable);
          mRxBus.post(new RxBusHelper.StopRefreshNewsMainFragment());
          showMessageException(throwable);
        });
    addToUnsubscription(subscription);
  }

  private void subscribeUpdateNews() {
    Subscription subscription = mRxBus.filteredObservable(RxBusHelper.UpdateNews.class)
        .compose(ThreadSchedulers.applySchedulers())
        .subscribe(newsEntity -> fetchNewsEntities(), throwable -> {
          mRxBus.post(new RxBusHelper.StopRefreshNewsMainFragment());
          subscribeUpdateNews();
          Timber.e(throwable);
          showMessageException(throwable);
        });
    addToUnsubscription(subscription);
  }
}
