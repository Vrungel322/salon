package com.apps.twelve.floor.salon.feature.catalog.presenters;

import com.apps.twelve.floor.salon.App;
import com.apps.twelve.floor.salon.R;
import com.apps.twelve.floor.salon.base.BasePresenter;
import com.apps.twelve.floor.salon.data.model.GoodsEntity;
import com.apps.twelve.floor.salon.feature.catalog.views.ICatalogFragmentView;
import com.apps.twelve.floor.salon.utils.Converters;
import com.apps.twelve.floor.salon.utils.RxBusHelper;
import com.apps.twelve.floor.salon.utils.ThreadSchedulers;
import com.arellomobile.mvp.InjectViewState;
import rx.Subscription;
import timber.log.Timber;

/**
 * Created by John on 17.05.2017.
 */

@InjectViewState public class CatalogFragmentPresenter extends BasePresenter<ICatalogFragmentView> {

  private String mTitle;

  @Override protected void inject() {
    App.getAppComponent().inject(this);
  }

  @Override protected void onFirstViewAttach() {
    super.onFirstViewAttach();
    fetchGoodsList();
    //RxBus
    subscribeGoodsList();
    subscribeReloadListByCategory();
  }

  public void showAlertDialog() {
    mRxBus.post(new RxBusHelper.ShowAuthDialog());
  }

  private void subscribeReloadListByCategory() {
    Subscription subscription = mRxBus.filteredObservable(RxBusHelper.ReloadCatalogByCategory.class)
        .doOnNext(reloadCatalogByCategory -> getViewState().startRefreshingView())
        .concatMap(reloadCatalogByCategory -> {
          mTitle = reloadCatalogByCategory.title;
          return mDataManager.fetchGoodsByCatalogId(reloadCatalogByCategory.id);
        })
        .compose(ThreadSchedulers.applySchedulers())
        .subscribe(goodsEntities -> {
          goodsEntities.add(0,
              new GoodsEntity(0, mContext.getString(R.string.menu_favourite), "", "", "", "", 0, "",
                  "",
                  Converters.getUrl(R.drawable.ic_favorite_catalog_32dp), 0, null, false, false,
                  false));
          getViewState().updateGoodsList(goodsEntities);
          getViewState().stopRefreshingView();
          getViewState().setCategoryTitle(mTitle);
        }, throwable -> {
          getViewState().stopRefreshingView();
          Timber.e(throwable);
          showMessageException(throwable);
        });
    addToUnsubscription(subscription);
  }

  public void fetchGoodsList() {
    getViewState().startRefreshingView();
    Subscription subscription = mDataManager.fetchGoods()
        .compose(ThreadSchedulers.applySchedulers())
        .subscribe(goodsEntities -> {
          goodsEntities.add(0,
              new GoodsEntity(0, mContext.getString(R.string.menu_favourite), "", "", "", "", 0, "",
                  "",
                  Converters.getUrl(R.drawable.ic_favorite_catalog_32dp), 0, null, false, false,
                  false));
          getViewState().updateGoodsList(goodsEntities);
          getViewState().stopRefreshingView();
          getViewState().setButtonDefaultText();
        }, throwable -> {
          getViewState().stopRefreshingView();
          Timber.e(throwable);
          showMessageException(throwable);
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
