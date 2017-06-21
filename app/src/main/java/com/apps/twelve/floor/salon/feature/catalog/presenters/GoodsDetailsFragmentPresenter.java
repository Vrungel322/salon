package com.apps.twelve.floor.salon.feature.catalog.presenters;

import com.apps.twelve.floor.salon.App;
import com.apps.twelve.floor.salon.base.BasePresenter;
import com.apps.twelve.floor.salon.feature.catalog.views.IStaffDetailsFragmentView;
import com.apps.twelve.floor.salon.utils.RxBusHelper;
import com.apps.twelve.floor.salon.utils.ThreadSchedulers;
import com.arellomobile.mvp.InjectViewState;
import rx.Subscription;
import timber.log.Timber;

/**
 * Created by Vrungel on 18.05.2017.
 */
@InjectViewState public class GoodsDetailsFragmentPresenter
    extends BasePresenter<IStaffDetailsFragmentView> {

  @Override protected void inject() {
    App.getAppComponent().inject(this);
  }

  public void addFavorite(int idGoods) {
    Subscription subscription = mDataManager.addToFavoriteGoods(idGoods)
        .compose(ThreadSchedulers.applySchedulers())
        .subscribe(voidResponse -> {
          if (voidResponse.code() == 200) {
            getViewState().setStatusFavorite(true);
            mRxBus.post(new RxBusHelper.UpdateGoodsList());
            mRxBus.post(new RxBusHelper.UpdateFavoriteGoodsList());
          }
        }, throwable -> {
          Timber.e(throwable);
          showMessageException(throwable);
        });
    addToUnsubscription(subscription);
  }

  public void deleteFavorite(int idGoods) {
    Subscription subscription = mDataManager.removeFromFavoriteGoods(idGoods)
        .compose(ThreadSchedulers.applySchedulers())
        .subscribe(voidResponse -> {
          if (voidResponse.code() == 204) {
            getViewState().setStatusFavorite(false);
            mRxBus.post(new RxBusHelper.UpdateGoodsList());
            mRxBus.post(new RxBusHelper.UpdateFavoriteGoodsList());
          }
        }, throwable -> {
          Timber.e(throwable);
          showMessageException(throwable);
        });
    addToUnsubscription(subscription);
  }

  public void showAlertDialog() {
    mRxBus.post(new RxBusHelper.ShowAuthDialog());
  }
}
