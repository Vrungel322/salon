package com.apps.twelve.floor.salon.feature.my_booking.presenters;

import com.apps.twelve.floor.authorization.utils.AuthRxBusHelper;
import com.apps.twelve.floor.salon.App;
import com.apps.twelve.floor.salon.base.BasePresenter;
import com.apps.twelve.floor.salon.feature.my_booking.views.IMyBookingAdapterView;
import com.apps.twelve.floor.salon.utils.RxBusHelper;
import com.apps.twelve.floor.salon.utils.ThreadSchedulers;
import com.apps.twelve.floor.salon.utils.jobs.JobsCreator;
import com.arellomobile.mvp.InjectViewState;
import javax.inject.Inject;
import rx.Observable;
import rx.Subscription;
import timber.log.Timber;

import static com.apps.twelve.floor.authorization.utils.Constants.Remote.RESPONSE_TOKEN_EXPIRED;
import static com.apps.twelve.floor.authorization.utils.Constants.Remote.RESPONSE_UNAUTHORIZED;
import static com.apps.twelve.floor.salon.utils.Constants.StatusCode.RESPONSE_204;

/**
 * Created by Alexandra on 27.04.2017.
 */

@InjectViewState public class MyBookingAdapterPresenter
    extends BasePresenter<IMyBookingAdapterView> {

  @Inject JobsCreator mJobsCreator;

  @Override protected void inject() {
    App.getAppComponent().inject(this);
  }

  public void cancelOrder(Integer entityId) {
    getViewState().showProgressBarCancel();
    Subscription subscription =
        mAuthorizationManager.checkToken(mDataManager.cancelOrder(entityId)).concatMap(response -> {
          if (response.code() == RESPONSE_TOKEN_EXPIRED) {
            return mAuthorizationManager.checkToken(mDataManager.cancelOrder(entityId));
          }
          return Observable.just(response);
        }).compose(ThreadSchedulers.applySchedulers()).subscribe(response -> {
          getViewState().hideProgressBarCancel();
          switch (response.code()) {
            case RESPONSE_204:
              mRxBus.post(new RxBusHelper.UpdateLastBookingListEvent());
              mJobsCreator.cancelJob(String.valueOf(entityId));
              break;
            case RESPONSE_UNAUTHORIZED:
              mAuthorizationManager.getAuthRxBus().post(new AuthRxBusHelper.UnauthorizedEvent());
              break;
            default:
              showMessageException();
              break;
          }
        }, throwable -> {
          Timber.e(throwable);
          showMessageException(throwable);
          getViewState().hideProgressBarCancel();
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
