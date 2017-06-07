package com.apps.twelve.floor.salon.feature.catalog.presenters;

import com.apps.twelve.floor.salon.App;
import com.apps.twelve.floor.salon.base.BasePresenter;
import com.apps.twelve.floor.salon.feature.catalog.views.ICataloFavoriteFragmentView;
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
  }

  private void fetchFavoriteGoodsList() {
    Subscription subscription = mDataManager.fetchFavoriteGoods()
        .compose(ThreadSchedulers.applySchedulers())
        .subscribe(listResponse -> {
          if (listResponse.code() != 401) {
            getViewState().updateGoodsFavoriteList(listResponse.body());
          } else {
            Timber.e("no Auth or need to refresh token");
            mAuthorizationManager.refreshToken();
          }
          if (listResponse.code() == 500) {
            mAuthorizationManager.clear();
            getViewState().startLoginActivity();
          }
        }, throwable -> {
          getViewState().stopProgressBar();
          Timber.e(throwable);
          showMessageConnectException(throwable);
        });
    addToUnsubscription(subscription);
  }
}
