package com.apps.twelve.floor.salon.feature.main_screen.presenters;

import com.apps.twelve.floor.salon.App;
import com.apps.twelve.floor.salon.base.BasePresenter;
import com.apps.twelve.floor.salon.data.DataManager;
import com.apps.twelve.floor.salon.feature.main_screen.views.IMyLastBookingAdapterView;
import com.apps.twelve.floor.salon.utils.ThreadSchedulers;
import com.arellomobile.mvp.InjectViewState;
import javax.inject.Inject;
import rx.Observable;
import rx.Subscription;
import timber.log.Timber;

/**
 * Created by Vrungel on 01.03.2017.
 */

@InjectViewState public class MyLastBookingAdapterPresenter
    extends BasePresenter<IMyLastBookingAdapterView> {
  @Inject DataManager mDataManager;

  @Override protected void inject() {
    App.getAppComponent().inject(this);
  }

  public void cancelOrder(int position, Integer serviceId) {
    Subscription subscription = mDataManager.cancelOrder(serviceId)
        .compose(ThreadSchedulers.applySchedulers())
        .subscribe(voidResponse -> {
          if (voidResponse.code() == 200) {
            getViewState().removeBookedServiceFromList(position);
          }
        });
    addToUnsubscription(subscription);
  }

  public void postponeOrder(int position) {
  }

  public void removeItemBooking(int position) {
    Subscription subscription = Observable.just(position)
        .compose(ThreadSchedulers.applySchedulers())
        .subscribe(integer -> getViewState().removeItemBooking(integer), Timber::e);
    addToUnsubscription(subscription);
  }
}
