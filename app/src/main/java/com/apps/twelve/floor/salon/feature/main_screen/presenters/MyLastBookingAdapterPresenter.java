package com.apps.twelve.floor.salon.feature.main_screen.presenters;

import com.apps.twelve.floor.salon.App;
import com.apps.twelve.floor.salon.base.BasePresenter;
import com.apps.twelve.floor.salon.data.DataManager;
import com.apps.twelve.floor.salon.feature.main_screen.views.IMyLastBookingAdapterView;
import com.apps.twelve.floor.salon.utils.ThreadSchedulers;
import com.arellomobile.mvp.InjectViewState;
import javax.inject.Inject;
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
        }, Timber::e);
    addToUnsubscription(subscription);
  }

  public void openPostponeFragment(int position) {
    getViewState().openPostponeFragment(position);
  }

  public void showConfirmationDialog(int position) {
    getViewState().showConfirmationDialog(position);
  }

  public void cancelAlertDialog() {
    getViewState().cancelAlertDialog();
  }
}
