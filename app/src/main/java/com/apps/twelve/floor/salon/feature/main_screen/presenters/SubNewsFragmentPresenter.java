package com.apps.twelve.floor.salon.feature.main_screen.presenters;

import com.apps.twelve.floor.salon.App;
import com.apps.twelve.floor.salon.base.BasePresenter;
import com.apps.twelve.floor.salon.data.model.LastBookingEntity;
import com.apps.twelve.floor.salon.data.model.NewsEntity;
import com.apps.twelve.floor.salon.feature.main_screen.views.ISubNewsFragmentView;
import com.apps.twelve.floor.salon.utils.RxBusHelper;
import com.apps.twelve.floor.salon.utils.ThreadSchedulers;
import com.arellomobile.mvp.InjectViewState;
import java.util.ArrayList;
import rx.Subscription;
import timber.log.Timber;

import static com.apps.twelve.floor.salon.utils.Constants.StatusCode.RESPONSE_200;

/**
 * Created by Vrungel on 23.02.2017.
 */

@InjectViewState public class SubNewsFragmentPresenter extends BasePresenter<ISubNewsFragmentView> {

  private ArrayList<NewsEntity> mNewsEntities = new ArrayList<>();

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

    getViewState().updateNewsPreview(mDataManager.getAllElementsFromDB(NewsEntity.class));
    Subscription subscription = mDataManager.fetchAllNews()
        .compose(ThreadSchedulers.applySchedulers())
        .doOnNext(listResponse -> mNewsEntities.addAll(listResponse.body()))
        .subscribe(response -> {
          //cache NewsEntities
          for (int i = 0; i < mNewsEntities.size(); i++) {
            mDataManager.saveObjToDb(mNewsEntities.get(i));
          }
          Timber.e(String.valueOf(mDataManager.getAllElementsFromDB(NewsEntity.class).size()));

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
