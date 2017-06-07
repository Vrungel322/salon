package com.apps.twelve.floor.salon.feature.main_screen.presenters;

import com.apps.twelve.floor.salon.App;
import com.apps.twelve.floor.salon.base.BasePresenter;
import com.apps.twelve.floor.salon.feature.main_screen.views.IMyLastBookingAdapterView;
import com.apps.twelve.floor.salon.utils.RxBusHelper;
import com.apps.twelve.floor.salon.utils.ThreadSchedulers;
import com.apps.twelve.floor.salon.utils.jobs.JobsCreator;
import com.arellomobile.mvp.InjectViewState;
import javax.inject.Inject;
import rx.Subscription;
import timber.log.Timber;

/**
 * Created by Vrungel on 01.03.2017.
 */

@InjectViewState public class MyLastBookingAdapterPresenter
    extends BasePresenter<IMyLastBookingAdapterView> {

  @Inject JobsCreator mJobsCreator;

  @Override protected void inject() {
    App.getAppComponent().inject(this);
  }

  public void cancelOrder(Integer entityId) {
    Subscription subscription = mDataManager.cancelOrder(entityId)
        .compose(ThreadSchedulers.applySchedulers())
        .subscribe(voidResponse -> {
          if (voidResponse.code() == 204) {
            mRxBus.post(new RxBusHelper.UpdateLastBookingListEvent());
            mJobsCreator.cancelJob(String.valueOf(entityId));
          }
        }, throwable -> {
          Timber.e(throwable);
          showMessageConnectException(throwable);
        });
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
