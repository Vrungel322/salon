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
  private int timePosition = -1;

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

  public void getAvailableTime(String serviceId) {
    Subscription subscription = mDataManager.fetchDaysData(serviceId)
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
      getViewState().setConfirmButtonUnClickable();
      Subscription subscription = mDataManager.postponeService(entryId, Integer.parseInt(
          mDataServiceEntity.get(dayPosition).getScheduleEntities().get(timePosition).getId()))
          .compose(ThreadSchedulers.applySchedulers())
          .subscribe(voidResponse -> {
            switch (voidResponse.code()) {
              case 200: // updated
                getViewState().showSuccessMessageAndCloseTheFragment();
                mRxBus.post(new RxBusHelper.UpdateLastBookingListEvent());
                break;
              case 400: // this time has already been picked
                getViewState().showErrorMessage("This time has already been picked");
                getViewState().setConfirmButtonClickable();
                break;
              case 404: // this booking entity does not exist
                getViewState().showErrorMessage("This booking entity does not exist");
                break;
              default:
                break;
            }
          }, Timber::e);
      addToUnsubscription(subscription);
    } else {
      getViewState().showErrorMessage("Pick the time!");
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

}
