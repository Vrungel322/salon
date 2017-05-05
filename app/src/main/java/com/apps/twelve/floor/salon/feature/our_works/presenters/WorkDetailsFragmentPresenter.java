package com.apps.twelve.floor.salon.feature.our_works.presenters;

import com.apps.twelve.floor.salon.App;
import com.apps.twelve.floor.salon.base.BasePresenter;
import com.apps.twelve.floor.salon.data.DataManager;
import com.apps.twelve.floor.salon.feature.our_works.views.IWorkDetailsFragmentView;
import com.apps.twelve.floor.salon.utils.RxBus;
import com.apps.twelve.floor.salon.utils.RxBusHelper;
import com.apps.twelve.floor.salon.utils.ThreadSchedulers;
import com.arellomobile.mvp.InjectViewState;
import javax.inject.Inject;
import rx.Subscription;
import timber.log.Timber;

/**
 * Created by Alexandra on 23.02.2017.
 */

@InjectViewState public class WorkDetailsFragmentPresenter
    extends BasePresenter<IWorkDetailsFragmentView> {
  @Inject DataManager mDataManager;
  @Inject RxBus mRxBus;

  @Override protected void inject() {
    App.getAppComponent().inject(this);
  }

  public void addFavorite(int idPhoto) {
    Subscription subscription = mDataManager.addToFavorite(idPhoto)
        .compose(ThreadSchedulers.applySchedulers())
        .subscribe(voidResponse -> {
          if (voidResponse.code() == 200){
            getViewState().setStatusFavorite(true);
            mRxBus.post(new RxBusHelper.UpdateOurWorkList());
          }
        }, Timber::e);
    addToUnsubscription(subscription);
  }

  public void deleteFavorite(int idPhoto) {
    Subscription subscription = mDataManager.removeFromFavorite(idPhoto)
        .compose(ThreadSchedulers.applySchedulers())
        .subscribe(voidResponse -> {
          if (voidResponse.code() == 200){
            getViewState().setStatusFavorite(false);
            mRxBus.post(new RxBusHelper.UpdateOurWorkList());
          }
        }, Timber::e);
    addToUnsubscription(subscription);
  }
}
