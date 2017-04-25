package com.apps.twelve.floor.salon.feature.my_booking.presenters;

import com.apps.twelve.floor.salon.App;
import com.apps.twelve.floor.salon.base.BasePresenter;
import com.apps.twelve.floor.salon.data.DataManager;
import com.apps.twelve.floor.salon.data.model.DataServiceEntity;
import com.apps.twelve.floor.salon.feature.my_booking.views.IPostponeFragmentView;
import com.apps.twelve.floor.salon.utils.RxBus;
import com.apps.twelve.floor.salon.utils.RxBusHelper;
import com.apps.twelve.floor.salon.utils.ThreadSchedulers;
import com.arellomobile.mvp.InjectViewState;
import java.util.List;
import javax.inject.Inject;
import rx.Subscription;
import timber.log.Timber;

/**
 * Created by Alexandra on 25.04.2017.
 */

@InjectViewState public class PostponeFragmentPresenter
    extends BasePresenter<IPostponeFragmentView> {
  @Inject DataManager mDataManager;
  @Inject RxBus mRxBus;
  private List<DataServiceEntity> mDataServiceEntity;
  private int dayPosition;

  @Override protected void inject() {
    App.getAppComponent().inject(this);
  }

  @Override protected void onFirstViewAttach() {
    super.onFirstViewAttach();
    mRxBus.post(new RxBusHelper.HideFloatingButton());
  }

  @Override public void onDestroy() {
    mRxBus.post(new RxBusHelper.ShowFloatingButton());
  }

  public void getInfFromRxBus(String masterName) {
    Subscription subscription = mRxBus.filteredObservable(RxBusHelper.ServiceID.class)
        .concatMap(serviceID -> mDataManager.fetchDaysDataInMasterMode(null))
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

  public void setSelectedTime(int position) {
    /*
    if (mDataServiceEntity.get(dayPosition).getScheduleEntities().get(position).getStatus()) {
      mBookingEntity.setDateId(String.valueOf(
          mDataServiceEntity.get(dayPosition).getScheduleEntities().get(position).getId()));
      mBookingEntity.setServiceTime(String.valueOf(
          mDataServiceEntity.get(dayPosition).getScheduleEntities().get(position).getTime()));
      getViewState().setSelectedTime(position);
    } else {
      getViewState().timeIsNotAvailable();
    }
    */
  }

  public void setDateToTv() {
    getViewState().setTextToDayTv();
  }

  public void setSelectedDay(int position) {
    dayPosition = position;
    getViewState().setSelectedDay(position);
  }

  public void clearSelectedTime() {
    /*
    mBookingEntity.setDateId("");
    getViewState().clearSelectedTime();
    */
  }
}
