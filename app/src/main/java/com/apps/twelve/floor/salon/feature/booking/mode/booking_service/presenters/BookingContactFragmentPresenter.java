package com.apps.twelve.floor.salon.feature.booking.mode.booking_service.presenters;

import com.apps.twelve.floor.salon.App;
import com.apps.twelve.floor.salon.data.DataManager;
import com.apps.twelve.floor.salon.data.model.BookingEntity;
import com.apps.twelve.floor.salon.base.BasePresenter;
import com.apps.twelve.floor.salon.feature.booking.mode.booking_service.views.IBookingContactFragmentView;
import com.apps.twelve.floor.salon.utils.RxBus;
import com.apps.twelve.floor.salon.utils.RxBusHelper;
import com.apps.twelve.floor.salon.utils.ThreadSchedulers;
import com.arellomobile.mvp.InjectViewState;
import javax.inject.Inject;
import rx.Subscription;
import timber.log.Timber;

/**
 * Created by Alexandra on 28.03.2017.
 */

@InjectViewState public class BookingContactFragmentPresenter
    extends BasePresenter<IBookingContactFragmentView> {

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
    Subscription subscription = mRxBus.filteredObservable(RxBusHelper.MasterID.class)
        .compose(ThreadSchedulers.applySchedulers())
        .subscribe(
            masterID -> getViewState().setUpBookingInformation(mBookingEntity.getServiceName(),
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
    // TODO: 04.04.2017 проверка всех полей и откравка
  }
}
