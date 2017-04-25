package com.apps.twelve.floor.salon.feature.main_screen.presenters;

import com.apps.twelve.floor.salon.App;
import com.apps.twelve.floor.salon.base.BasePresenter;
import com.apps.twelve.floor.salon.data.DataManager;
import com.apps.twelve.floor.salon.feature.main_screen.views.IMyLastBookingAdapterView;
import com.apps.twelve.floor.salon.utils.RxBus;
import com.apps.twelve.floor.salon.utils.ThreadSchedulers;
import com.arellomobile.mvp.InjectViewState;
import javax.inject.Inject;
import rx.Subscription;

/**
 * Created by Vrungel on 01.03.2017.
 */

@InjectViewState public class MyLastBookingAdapterPresenter
    extends BasePresenter<IMyLastBookingAdapterView> {
  @Inject DataManager mDataManager;
  @Inject RxBus mRxBus;

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

  public void postponeOrder(Integer serviceId, String masterName) {

  }

  public void showConfirmationDialog(int position) {
    getViewState().showConfirmationDialog(position);
  }

  public void cancelAlertDialog() {
    getViewState().cancelAlertDialog();
  }
}
