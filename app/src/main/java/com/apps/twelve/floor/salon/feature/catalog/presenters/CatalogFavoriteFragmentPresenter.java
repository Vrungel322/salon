package com.apps.twelve.floor.salon.feature.catalog.presenters;

import com.apps.twelve.floor.salon.App;
import com.apps.twelve.floor.salon.base.BasePresenter;
import com.apps.twelve.floor.salon.feature.catalog.views.ICataloFavoriteFragmentView;
import com.apps.twelve.floor.salon.utils.RxBusHelper;
import com.apps.twelve.floor.salon.utils.ThreadSchedulers;
import com.arellomobile.mvp.InjectViewState;
import rx.Subscription;
import timber.log.Timber;

/**
 * Created by John on 06.06.2017.
 */

@InjectViewState public class CatalogFavoriteFragmentPresenter
    extends BasePresenter<ICataloFavoriteFragmentView> {

  @Override protected void inject() {
    App.getAppComponent().inject(this);
  }

  @Override protected void onFirstViewAttach() {
    super.onFirstViewAttach();
    fetchFavoriteGoodsList();
    //RxBus
    subscribeFavoriteGoodsList();
  }

  public void fetchFavoriteGoodsList() {
    Subscription subscription = mDataManager.fetchFavoriteGoods()
        .compose(ThreadSchedulers.applySchedulers())
        .subscribe(listResponse -> {
          if (listResponse.code() != 401) {
            getViewState().stopRefreshingView();
            getViewState().updateGoodsFavoriteList(listResponse.body());
          } else {
            //mAuthorizationManager.refreshToken();
          }
          if (listResponse.code() == 500) {
            mAuthorizationManager.clear();
            getViewState().startLoginActivity();
          }
        }, throwable -> {
          getViewState().stopRefreshingView();
          Timber.e(throwable);
          showMessageException(throwable);
        });
    addToUnsubscription(subscription);
  }

  private void subscribeFavoriteGoodsList() {
    Subscription subscription = mRxBus.filteredObservable(RxBusHelper.UpdateFavoriteGoodsList.class)
        .compose(ThreadSchedulers.applySchedulers())
        .subscribe(updateFavoriteGoodsList -> fetchFavoriteGoodsList(), Timber::e);
    addToUnsubscription(subscription);
  }
}
