package com.apps.twelve.floor.salon.feature.booking.mode.booking_service.presenters;

import com.apps.twelve.floor.salon.App;
import com.apps.twelve.floor.salon.base.BasePresenter;
import com.apps.twelve.floor.salon.data.DataManager;
import com.apps.twelve.floor.salon.data.model.BookingEntity;
import com.apps.twelve.floor.salon.data.model.DataServiceEntity;
import com.apps.twelve.floor.salon.feature.booking.mode.booking_service.views.IChooseTimeFragmentView;
import com.apps.twelve.floor.salon.utils.RxBus;
import com.apps.twelve.floor.salon.utils.RxBusHelper;
import com.apps.twelve.floor.salon.utils.ThreadSchedulers;
import com.arellomobile.mvp.InjectViewState;
import java.util.List;
import javax.inject.Inject;
import rx.Subscription;
import timber.log.Timber;

/**
 * Created by Vrungel on 27.03.2017.
 */

@InjectViewState public class ChooseTimeFragmentPresenter
    extends BasePresenter<IChooseTimeFragmentView> {
  @Inject DataManager mDataManager;
  @Inject RxBus mRxBus;
  @Inject BookingEntity mBookingEntity;
  private List<DataServiceEntity> mDataServiceEntity;
  private int dayPosition;

  @Override protected void inject() {
    App.getBookingComponent().inject(this);
  }

  @Override protected void onFirstViewAttach() {
    super.onFirstViewAttach();
    getInfFromRxBus();
  }

  private void getInfFromRxBus() {
    Subscription subscription = mRxBus.filteredObservable(RxBusHelper.ServiceID.class)
        .concatMap(serviceID -> mDataManager.fetchDaysData(serviceID.serviceId))
        .compose(ThreadSchedulers.applySchedulers())
        .subscribe(dataServiceEntities -> {
          mDataServiceEntity = dataServiceEntities;
          getViewState().setServiceName(mBookingEntity.getServiceName());
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
    if (mDataServiceEntity.get(dayPosition).getScheduleEntities().get(position).getStatus()) {
      mBookingEntity.setDateId(String.valueOf(
          mDataServiceEntity.get(dayPosition).getScheduleEntities().get(position).getId()));
      mBookingEntity.setServiceTime(String.valueOf(
          mDataServiceEntity.get(dayPosition).getScheduleEntities().get(position).getTime()));
      getViewState().setSelectedTime(position);
      mRxBus.post(new RxBusHelper.EventForNextStep(1));
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

  public void clearSelectedTime() {
    mBookingEntity.setDateId("");
    getViewState().clearSelectedTime();
  }
}
