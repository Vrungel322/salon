package com.apps.twelve.floor.salon.feature.booking.mode.booking_master.presenters;

import com.apps.twelve.floor.salon.App;
import com.apps.twelve.floor.salon.base.BasePresenter;
import com.apps.twelve.floor.salon.data.DataManager;
import com.apps.twelve.floor.salon.data.model.BookingEntity;
import com.apps.twelve.floor.salon.data.model.DataServiceEntity;
import com.apps.twelve.floor.salon.feature.booking.mode.booking_master.views.IChooseMasterTimeFragmentView;
import com.apps.twelve.floor.salon.utils.Constants;
import com.apps.twelve.floor.salon.utils.RxBus;
import com.apps.twelve.floor.salon.utils.RxBusHelper;
import com.apps.twelve.floor.salon.utils.ThreadSchedulers;
import com.arellomobile.mvp.InjectViewState;
import java.util.List;
import javax.inject.Inject;
import rx.Subscription;
import timber.log.Timber;

@InjectViewState public class ChooseMasterTimeFragmentPresenter
    extends BasePresenter<IChooseMasterTimeFragmentView> {
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
    getTimeMaster();
  }

  @Override public void onDestroy() {
    super.onDestroy();
    mBookingEntity.setDateId("");
  }

  private void getTimeMaster() {
    Subscription subscription = mDataManager.fetchDaysDataWithMasterId(mBookingEntity.getMasterId())
        .compose(ThreadSchedulers.applySchedulers())
        .subscribe(dataServiceEntities -> {
          mDataServiceEntity = dataServiceEntities;
          getViewState().hideProgressBarBookingTime();
          getViewState().setUpRedSquare(mBookingEntity.getServiceName(),
              mBookingEntity.getMasterName());
          if (!dataServiceEntities.isEmpty()) {
            getViewState().setUpUi(dataServiceEntities);
            setDateToTv();
            getViewState().showTimeBooking();
          } else {
            getViewState().showNotTime();
          }
        }, throwable -> {
          Timber.e(throwable);
          showMessageConnectException(throwable);
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
          new RxBusHelper.EventForNextStep(Constants.FragmentTag.CHOOSE_MASTER_CONTACT_FRAGMENT));
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
