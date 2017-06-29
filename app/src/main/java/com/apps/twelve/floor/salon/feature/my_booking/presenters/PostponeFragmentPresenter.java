package com.apps.twelve.floor.salon.feature.my_booking.presenters;

import com.apps.twelve.floor.authorization.utils.AuthRxBusHelper;
import com.apps.twelve.floor.salon.App;
import com.apps.twelve.floor.salon.R;
import com.apps.twelve.floor.salon.base.BasePresenter;
import com.apps.twelve.floor.salon.data.model.DataServiceEntity;
import com.apps.twelve.floor.salon.feature.my_booking.views.IPostponeFragmentView;
import com.apps.twelve.floor.salon.utils.RxBusHelper;
import com.apps.twelve.floor.salon.utils.ThreadSchedulers;
import com.apps.twelve.floor.salon.utils.jobs.JobsCreator;
import com.arellomobile.mvp.InjectViewState;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.inject.Inject;
import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import timber.log.Timber;

import static com.apps.twelve.floor.authorization.utils.Constants.Remote.RESPONSE_TOKEN_EXPIRED;
import static com.apps.twelve.floor.authorization.utils.Constants.Remote.RESPONSE_UNAUTHORIZED;
import static com.apps.twelve.floor.salon.utils.Constants.StatusCode.RESPONSE_200;
import static com.apps.twelve.floor.salon.utils.Constants.StatusCode.RESPONSE_400;
import static com.apps.twelve.floor.salon.utils.Constants.StatusCode.RESPONSE_404;

/**
 * Created by Alexandra on 25.04.2017.
 */

@InjectViewState public class PostponeFragmentPresenter
    extends BasePresenter<IPostponeFragmentView> {

  @Inject JobsCreator mJobsCreator;

  private List<DataServiceEntity> mDataServiceEntity;
  private int dayPosition;
  private int timePosition = -1;

  public PostponeFragmentPresenter(String masterId) {
    getAvailableTime(masterId);
  }

  @Override protected void inject() {
    App.getAppComponent().inject(this);
  }

  @Override protected void onFirstViewAttach() {
    super.onFirstViewAttach();
    mRxBus.post(new RxBusHelper.HideFloatingButton());
  }

  @SuppressWarnings("ConstantConditions") private void getAvailableTime(String masterId) {
    Subscription subscription = mDataManager.fetchDaysDataWithMasterId(masterId)
        .compose(ThreadSchedulers.applySchedulers())
        .subscribe(response -> {
          if (response.code() == RESPONSE_200) {
            mDataServiceEntity = response.body();
            getViewState().hideProgressBarBookingTime();
            if (!response.body().isEmpty()) {
              getViewState().setUpUi(response.body());
              setDateToTv();
              getViewState().showTimeBooking();
            } else {
              getViewState().showNotTime();
            }
          }
        }, throwable -> {
          Timber.e(throwable);
          showMessageException(throwable);
        });
    addToUnsubscription(subscription);
  }

  public void saveNewTime(String entryId, String serviceName) {
    if (timePosition != -1) {
      Subscription subscription = mAuthorizationManager.checkToken(
          mDataManager.postponeService(entryId, Integer.parseInt(
              mDataServiceEntity.get(dayPosition).getScheduleEntities().get(timePosition).getId())))
          .concatMap(response -> {
            if (response.code() == RESPONSE_TOKEN_EXPIRED) {
              return mAuthorizationManager.checkToken(mDataManager.postponeService(entryId,
                  Integer.parseInt(mDataServiceEntity.get(dayPosition)
                      .getScheduleEntities()
                      .get(timePosition)
                      .getId())));
            }
            return Observable.just(response);
          })
          .compose(ThreadSchedulers.applySchedulers())
          .doOnNext(response -> {
            if (response.code() == RESPONSE_200) {
              getViewState().stopAnimation();
            }
          })
          .delay(1, TimeUnit.SECONDS, AndroidSchedulers.mainThread())
          .subscribe(response -> {
            switch (response.code()) {
              case RESPONSE_200:
                mRxBus.post(new RxBusHelper.UpdateLastBookingListEvent());
                mJobsCreator.cancelJob(entryId);
                mJobsCreator.createNotification(entryId, Integer.parseInt(
                    mDataServiceEntity.get(dayPosition)
                        .getScheduleEntities()
                        .get(timePosition)
                        .getTimeInSec()) * 1000L - System.currentTimeMillis(), serviceName);
                getViewState().closeTheFragment();
                break;
              case RESPONSE_UNAUTHORIZED:
                mAuthorizationManager.getAuthRxBus().post(new AuthRxBusHelper.UnauthorizedEvent());
                getViewState().revertAnimation();
                break;
              case RESPONSE_400:
                getViewState().showErrorMessage(R.string.error_time_not_available);
                getViewState().revertAnimation();
                break;
              case RESPONSE_404:
                getViewState().showErrorMessage(R.string.error_booking_entity_not_exist);
                getViewState().revertAnimation();
                break;
              default:
                showMessageException();
                getViewState().revertAnimation();
                break;
            }
          }, throwable -> {
            Timber.e(throwable);
            showMessageException(throwable);
          });
      addToUnsubscription(subscription);
    } else {
      getViewState().showErrorMessage(R.string.error_empty_date);
      Subscription subscription = Observable.just(true).delay(1, TimeUnit.SECONDS)
          .compose(ThreadSchedulers.applySchedulers())
          .subscribe(o -> getViewState().revertAnimation(), Timber::e);
      addToUnsubscription(subscription);
    }
  }

  public void setSelectedTime(int position) {
    if (mDataServiceEntity.get(dayPosition).getScheduleEntities().get(position).getStatus()) {
      getViewState().setSelectedTime(position);
      timePosition = position;
    } else {
      getViewState().timeIsNotAvailable();
    }
  }

  public void setDateToTv() {
    getViewState().setTextToDayTv();
  }

  public void setSelectedDay(int position) {
    dayPosition = position;
    getViewState().setSelectedDay(position);
  }

  @Override public void onDestroy() {
    mRxBus.post(new RxBusHelper.ShowFloatingButton());
  }
}
