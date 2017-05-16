package com.apps.twelve.floor.salon.feature.booking.mode.booking_master.presenters;

import com.apps.twelve.floor.salon.App;
import com.apps.twelve.floor.salon.base.BasePresenter;
import com.apps.twelve.floor.salon.data.DataManager;
import com.apps.twelve.floor.salon.data.local.mappers.BookingToBookingServerEntityMapper;
import com.apps.twelve.floor.salon.data.model.BookingEntity;
import com.apps.twelve.floor.salon.feature.booking.mode.booking_master.views.IChooseMasterContactFragmentView;
import com.apps.twelve.floor.salon.utils.RxBus;
import com.apps.twelve.floor.salon.utils.RxBusHelper;
import com.apps.twelve.floor.salon.utils.ThreadSchedulers;
import com.arellomobile.mvp.InjectViewState;
import java.util.concurrent.TimeUnit;
import javax.inject.Inject;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import timber.log.Timber;

@InjectViewState public class ChooseMasterContactFragmentPresenter
    extends BasePresenter<IChooseMasterContactFragmentView> {

  @Inject BookingEntity mBookingEntity;
  @Inject DataManager mDataManager;
  @Inject RxBus mRxBus;
  @Inject BookingToBookingServerEntityMapper mapper;

  @Override protected void inject() {
    App.getBookingComponent().inject(this);
  }

  @Override protected void onFirstViewAttach() {
    super.onFirstViewAttach();
    getViewState().setUpBookingInformation(mBookingEntity.getServiceName(),
        mBookingEntity.getServiceTime(), mBookingEntity.getDurationServices(),
        mBookingEntity.getMasterName());
  }

  public void setPersonName(String s) {
    mBookingEntity.setUserName(s);
  }

  public void setPersonPhone(String s) {
    mBookingEntity.setUserPhone(s);
  }

  public void sendBookingEntity() {
    Subscription subscription =
        mDataManager.checkInService(mapper.transform(mBookingEntity))
            .compose(ThreadSchedulers.applySchedulers())
            .doOnNext(voidResponse -> {
              if (voidResponse.code() == 200) {
                mRxBus.post(new RxBusHelper.UpdateLastBookingListEvent());
                getViewState().stopAnimation();
              } else {
                getViewState().showAlert();
              }
            }).delay(1, TimeUnit.SECONDS, AndroidSchedulers.mainThread())
            .subscribe(response -> {
              if (response.code() == 200) {
                getViewState().closeBooking();
              } else {
                getViewState().revertAnimation();
              }
            }, Timber::e);
    addToUnsubscription(subscription);
  }
}
