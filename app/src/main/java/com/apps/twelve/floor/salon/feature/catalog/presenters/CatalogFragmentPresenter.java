package com.apps.twelve.floor.salon.feature.catalog.presenters;

import com.apps.twelve.floor.salon.App;
import com.apps.twelve.floor.salon.base.BasePresenter;
import com.apps.twelve.floor.salon.data.DataManager;
import com.apps.twelve.floor.salon.feature.catalog.views.ICatalogFragmentView;
import com.apps.twelve.floor.salon.utils.ThreadSchedulers;
import com.arellomobile.mvp.InjectViewState;
import javax.inject.Inject;
import rx.Subscription;
import timber.log.Timber;

/**
 * Created by John on 17.05.2017.
 */

@InjectViewState public class CatalogFragmentPresenter extends BasePresenter<ICatalogFragmentView> {
  @Inject DataManager mDataManager;

  @Override protected void inject() {
    App.getAppComponent().inject(this);
  }

  @Override protected void onFirstViewAttach() {
    super.onFirstViewAttach();
    getViewState().setUpUi();
    fetchGoodsList();
  }

  private void fetchGoodsList() {
    Subscription subscription = mDataManager.fetchGoods()
        .compose(ThreadSchedulers.applySchedulers())
        .subscribe(goodsEntities -> getViewState().updateStaffList(goodsEntities), throwable -> {
          Timber.e(throwable);
          showMessageConnectException(throwable);
        });
    addToUnsubscription(subscription);
  }
}
