package com.apps.twelve.floor.salon.feature.my_booking.presenters;

import com.apps.twelve.floor.salon.App;
import com.apps.twelve.floor.salon.R;
import com.apps.twelve.floor.salon.base.BasePresenter;
import com.apps.twelve.floor.salon.data.DataManager;
import com.apps.twelve.floor.salon.data.model.DataServiceEntity;
import com.apps.twelve.floor.salon.feature.my_booking.views.IPostponeFragmentView;
import com.apps.twelve.floor.salon.utils.RxBus;
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

/**
 * Created by Alexandra on 25.04.2017.
 */

@InjectViewState public class PostponeFragmentPresenter
    extends BasePresenter<IPostponeFragmentView> {

  @Inject DataManager mDataManager;
  @Inject JobsCreator mJobsCreator;
  @Inject RxBus mRxBus;
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

  private void getAvailableTime(String masterId) {
    Subscription subscription = mDataManager.fetchDaysDataWithMasterId(masterId)
        .compose(ThreadSchedulers.applySchedulers())
        .subscribe(dataServiceEntities -> {
          mDataServiceEntity = dataServiceEntities;
          getViewState().hideProgressBarBookingTime();
          if (!dataServiceEntities.isEmpty()) {
            getViewState().setUpUi(dataServiceEntities);
            setDateToTv();
            getViewState().showTimeBooking();
          } else {
            getViewState().showNotTime();
          }
        }, Timber::e);
    addToUnsubscription(subscription);
  }

  public void saveNewTime(String entryId) {
    if (timePosition != -1) {
      Subscription subscription = mDataManager.postponeService(entryId, Integer.parseInt(
          mDataServiceEntity.get(dayPosition).getScheduleEntities().get(timePosition).getId()))
          .compose(ThreadSchedulers.applySchedulers())
          .doOnNext(voidResponse -> {
            switch (voidResponse.code()) {
              case 200: // updated
                mRxBus.post(new RxBusHelper.UpdateLastBookingListEvent());
                mJobsCreator.cancelJob(entryId);
                mJobsCreator.createNotification(entryId, Integer.parseInt(
                    mDataServiceEntity.get(dayPosition)
                        .getScheduleEntities()
                        .get(timePosition)
                        .getTimeInSec()) * 1000L - System.currentTimeMillis());
                getViewState().stopAnimation();
                break;
              case 400: // this time has already been picked
                getViewState().showErrorMessage(R.string.error_time_not_available);
                break;
              case 404: // this booking entity does not exist
                getViewState().showErrorMessage(R.string.error_booking_entity_not_exist);
                break;
              default:
                break;
            }
          }).delay(1, TimeUnit.SECONDS, AndroidSchedulers.mainThread())
          .subscribe(response -> {
            if (response.code() == 200) {
              getViewState().closeTheFragment();
            } else {
              getViewState().revertAnimation();
            }
          }, Timber::e);
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
