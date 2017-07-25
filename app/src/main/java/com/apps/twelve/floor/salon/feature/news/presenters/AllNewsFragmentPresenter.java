package com.apps.twelve.floor.salon.feature.news.presenters;

import com.apps.twelve.floor.salon.App;
import com.apps.twelve.floor.salon.base.BasePresenter;
import com.apps.twelve.floor.salon.feature.news.views.IAllNewsFragmentView;
import com.apps.twelve.floor.salon.utils.RxBusHelper;
import com.apps.twelve.floor.salon.utils.ThreadSchedulers;
import com.arellomobile.mvp.InjectViewState;
import rx.Subscription;
import timber.log.Timber;

import static com.apps.twelve.floor.salon.utils.Constants.StatusCode.RESPONSE_200;
import static com.apps.twelve.floor.salon.utils.Constants.StatusCode.RESPONSE_503;

/**
 * Created by Vrungel on 24.02.2017.
 */

@InjectViewState public class AllNewsFragmentPresenter extends BasePresenter<IAllNewsFragmentView> {

  @Override protected void inject() {
    App.getAppComponent().inject(this);
  }

  @Override protected void onFirstViewAttach() {
    super.onFirstViewAttach();
    fetchListOfNews();
    mRxBus.post(new RxBusHelper.SetNewsItemInMenu());
  }

  public void fetchListOfNews() {
    getViewState().startRefreshingView();
    Subscription subscription = mDataManager.fetchAllNews()
        .compose(ThreadSchedulers.applySchedulers())
        .subscribe(response -> {
          if (response.code() == RESPONSE_200) {
            getViewState().addListOfNews(response.body());
            getViewState().stopRefreshingView();
          }
          if (response.code() == RESPONSE_503) {
            getViewState().showServerErrorMsg();
            getViewState().stopRefreshingView();
          }
        }, throwable -> {
          Timber.e(throwable);
          getViewState().stopRefreshingView();
          showMessageException(throwable);
        });
    addToUnsubscription(subscription);
  }
}
