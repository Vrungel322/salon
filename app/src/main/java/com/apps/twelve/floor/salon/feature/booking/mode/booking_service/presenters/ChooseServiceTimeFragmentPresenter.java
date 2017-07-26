package com.apps.twelve.floor.salon.feature.booking.mode.booking_service.presenters;

import com.apps.twelve.floor.salon.App;
import com.apps.twelve.floor.salon.base.BasePresenter;
import com.apps.twelve.floor.salon.data.model.BookingEntity;
import com.apps.twelve.floor.salon.data.model.DataServiceEntity;
import com.apps.twelve.floor.salon.feature.booking.mode.booking_service.views.IChooseServiceTimeFragmentView;
import com.apps.twelve.floor.salon.utils.Constants;
import com.apps.twelve.floor.salon.utils.RxBusHelper;
import com.apps.twelve.floor.salon.utils.ThreadSchedulers;
import com.arellomobile.mvp.InjectViewState;
import java.util.List;
import javax.inject.Inject;
import rx.Subscription;
import timber.log.Timber;

import static com.apps.twelve.floor.salon.utils.Constants.StatusCode.RESPONSE_200;

/**
 * Created by Vrungel on 27.03.2017.
 */

@InjectViewState public class ChooseServiceTimeFragmentPresenter
    extends BasePresenter<IChooseServiceTimeFragmentView> {

  @Inject BookingEntity mBookingEntity;
  private List<DataServiceEntity> mDataServiceEntity;
  private int dayPosition;

  @Override protected void inject() {
    App.getBookingComponent().inject(this);
  }

  @Override protected void onFirstViewAttach() {
    super.onFirstViewAttach();
    fetchDaysData();
  }

  @SuppressWarnings("ConstantConditions") private void fetchDaysData() {
    Subscription subscription = mDataManager.fetchDaysData(mBookingEntity.getServiceId())
        .compose(ThreadSchedulers.applySchedulers())
        .subscribe(response -> {
          if (response.code() == RESPONSE_200) {
            mDataServiceEntity = response.body();
            getViewState().setServiceName(mBookingEntity.getServiceName());
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

  public void setSelectedTime(int position) {
    if (mDataServiceEntity.get(dayPosition).getScheduleEntities().get(position).getStatus()) {
      mBookingEntity.setDateId(String.valueOf(
          mDataServiceEntity.get(dayPosition).getScheduleEntities().get(position).getId()));
      mBookingEntity.setServiceTime(String.valueOf(
          mDataServiceEntity.get(dayPosition).getScheduleEntities().get(position).getTime()));
      getViewState().setSelectedTime(position);
      mBookingEntity.setRemainTimeInSec(
          mDataServiceEntity.get(dayPosition).getScheduleEntities().get(position).getTimeInSec());
      mRxBus.post(
          new RxBusHelper.EventForNextStep(Constants.FragmentTag.CHOOSE_SERVICE_MASTER_FRAGMENT));
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

  public void clearLastBookingEntity() {
    mBookingEntity.setMasterId("");
  }
}
