package com.apps.twelve.floor.salon.feature.catalog.presenters;

import com.apps.twelve.floor.salon.App;
import com.apps.twelve.floor.salon.base.BasePresenter;
import com.apps.twelve.floor.salon.data.DataManager;
import com.apps.twelve.floor.salon.feature.catalog.views.ICatalogFragmentView;
import com.apps.twelve.floor.salon.utils.RxBus;
import com.apps.twelve.floor.salon.utils.RxBusHelper;
import com.apps.twelve.floor.salon.utils.ThreadSchedulers;
import com.arellomobile.mvp.InjectViewState;
import javax.inject.Inject;
import rx.Observable;
import rx.Subscription;
import timber.log.Timber;

/**
 * Created by John on 17.05.2017.
 */

@InjectViewState public class CatalogFragmentPresenter extends BasePresenter<ICatalogFragmentView> {

  @Inject DataManager mDataManager;
  @Inject RxBus mRxBus;

  @Override protected void inject() {
    App.getAppComponent().inject(this);
  }

  @Override protected void onFirstViewAttach() {
    super.onFirstViewAttach();
    fetchGoodsList();
    //RxBus
    subscribeGoodsList();
    getRxBusInfoToReloadListByCetegory();
  }

  private void getRxBusInfoToReloadListByCetegory() {
    Subscription subscription = mRxBus.filteredObservable(RxBusHelper.ReloadCatalogByCategory.class)
        .compose(ThreadSchedulers.applySchedulers())
        .concatMap(reloadCatalogByCategory -> mDataManager.fetchGoodsByCatalogId(
            reloadCatalogByCategory.id)
            .compose(ThreadSchedulers.applySchedulers())
            .concatMap(Observable::just))
        .subscribe(goodsEntities -> {
          getViewState().updateGoodsList(goodsEntities);
        });
    addToUnsubscription(subscription);
  }

  public void fetchGoodsList() {
    getViewState().startRefreshingView();
    Subscription subscription = mDataManager.fetchGoods()
        .compose(ThreadSchedulers.applySchedulers())
        .subscribe(goodsEntities -> {
          getViewState().updateGoodsList(goodsEntities);
          getViewState().stopRefreshingView();
        }, throwable -> {
          getViewState().stopRefreshingView();
          Timber.e(throwable);
          showMessageConnectException(throwable);
        });
    addToUnsubscription(subscription);
  }

  private void subscribeGoodsList() {
    Subscription subscription = mRxBus.filteredObservable(RxBusHelper.UpdateGoodsList.class)
        .compose(ThreadSchedulers.applySchedulers())
        .subscribe(goodsEntities -> fetchGoodsList(), Timber::e);
    addToUnsubscription(subscription);
  }
}
