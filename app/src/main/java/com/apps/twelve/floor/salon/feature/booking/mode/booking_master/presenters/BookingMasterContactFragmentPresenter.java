package com.apps.twelve.floor.salon.feature.booking.mode.booking_master.presenters;

import com.apps.twelve.floor.salon.App;
import com.apps.twelve.floor.salon.data.DataManager;
import com.apps.twelve.floor.salon.data.model.BookingEntity;
import com.apps.twelve.floor.salon.base.BasePresenter;
import com.apps.twelve.floor.salon.data.model.BookingServerEntity;
import com.apps.twelve.floor.salon.feature.booking.mode.booking_master.views.IBookingMasterContactFragmentView;
import com.apps.twelve.floor.salon.utils.RxBus;
import com.apps.twelve.floor.salon.utils.RxBusHelper;
import com.apps.twelve.floor.salon.utils.ThreadSchedulers;
import com.arellomobile.mvp.InjectViewState;
import javax.inject.Inject;
import rx.Subscription;
import timber.log.Timber;

@InjectViewState public class BookingMasterContactFragmentPresenter
    extends BasePresenter<IBookingMasterContactFragmentView> {

  @Inject BookingEntity mBookingEntity;
  @Inject DataManager mDataManager;
  @Inject RxBus mRxBus;

  @Override protected void inject() {
    App.getBookingComponent().inject(this);
  }

  @Override protected void onFirstViewAttach() {
    super.onFirstViewAttach();
    getInfFromRxBus();
  }

  private void getInfFromRxBus() {
    Subscription subscription = mRxBus.filteredObservable(RxBusHelper.DataID.class)
        .compose(ThreadSchedulers.applySchedulers())
        .subscribe(dataID -> getViewState().setUpBookingInformation(mBookingEntity.getServiceName(),
            mBookingEntity.getServiceTime(), mBookingEntity.getDurationServices(),
            mBookingEntity.getMasterName()), Timber::e);
    addToUnsubscription(subscription);
  }

  public void setPersonName(String s) {
    mBookingEntity.setUserName(s);
  }

  public void setPersonPhone(String s) {
    mBookingEntity.setUserPhone(s);
  }

  public void sendBookingEntity() {
    BookingServerEntity bookingServerEntity =
        new BookingServerEntity(Integer.parseInt(mBookingEntity.getMasterId()),
            Integer.parseInt(mBookingEntity.getServiceId()),
            Integer.parseInt(mBookingEntity.getDateId()), mBookingEntity.getUserName(),
            mBookingEntity.getUserPhone());

    Subscription subscription = mDataManager.checkInService(1, bookingServerEntity)
        .compose(ThreadSchedulers.applySchedulers())
        .subscribe(response -> {
          if (response.code() == 200){
            mRxBus.post(new RxBusHelper.UpdateLastBookingListEvent());
            getViewState().closeActivity();
          }
          else {
            getViewState().showAlert();
          }
        }, Timber::e);
    addToUnsubscription(subscription);
  }
}
