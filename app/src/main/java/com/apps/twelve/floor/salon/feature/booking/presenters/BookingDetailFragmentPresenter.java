package com.apps.twelve.floor.salon.feature.booking.presenters;

import com.apps.twelve.floor.salon.App;
import com.apps.twelve.floor.salon.R;
import com.apps.twelve.floor.salon.base.BasePresenter;
import com.apps.twelve.floor.salon.data.DataManager;
import com.apps.twelve.floor.salon.data.model.BookingEntity;
import com.apps.twelve.floor.salon.feature.booking.views.IBookingDetailFragmentView;
import com.apps.twelve.floor.salon.utils.Constants;
import com.apps.twelve.floor.salon.utils.RxBus;
import com.apps.twelve.floor.salon.utils.RxBusHelper;
import com.apps.twelve.floor.salon.utils.ThreadSchedulers;
import com.arellomobile.mvp.InjectViewState;
import javax.inject.Inject;
import rx.Subscription;
import timber.log.Timber;

/**
 * Created by Vrungel on 23.03.2017.
 */

@InjectViewState public class BookingDetailFragmentPresenter
    extends BasePresenter<IBookingDetailFragmentView> {
  @Inject BookingEntity mBookingEntity;
  @Inject DataManager mDataManager;
  @Inject RxBus mRxBus;

  private int mStartWith;

  @Override protected void inject() {
    App.initBookingComponent();
    App.getBookingComponent().inject(this);
  }

  @Override protected void onFirstViewAttach() {
    super.onFirstViewAttach();
    getViewState().setUpViewPager();
    stateBooking();
    nextStep();
  }

  private void nextStep() {
    Subscription subscription = mRxBus.filteredObservable(RxBusHelper.EventForNextStep.class)
        .compose(ThreadSchedulers.applySchedulers())
        .subscribe(eventForNextStep -> {
          if (mStartWith == Constants.BookingMode.START_WITH_SERVICE) {
            switch (eventForNextStep.currentItem) {
              case 0:
                if (!mBookingEntity.getServiceId().isEmpty()) {
                  mRxBus.post(
                      new RxBusHelper.ServiceID(String.valueOf(mBookingEntity.getServiceId()),
                          mBookingEntity.getMasterName()));
                  getViewState().goNext(eventForNextStep.currentItem + 1);
                  getViewState().hideKeyboard();
                } else {
                  getViewState().showMessageWarning(R.string.error_empty_service);
                  getViewState().goNext(eventForNextStep.currentItem + 1);
                }
                break;
              case 1:
                if (!mBookingEntity.getDateId().isEmpty()) {
                  mRxBus.post(new RxBusHelper.DataID(String.valueOf(mBookingEntity.getDateId()),
                      mBookingEntity.getServiceTime()));
                  getViewState().goNext(eventForNextStep.currentItem + 1);
                } else {
                  getViewState().showMessageWarning(R.string.error_empty_date);
                  getViewState().goNext(eventForNextStep.currentItem + 1);
                }
                break;
              case 2:
                if (!mBookingEntity.getMasterId().isEmpty()) {
                  mRxBus.post(new RxBusHelper.MasterID(String.valueOf(mBookingEntity.getMasterId()),
                      mBookingEntity.getMasterName()));
                  getViewState().goNext(eventForNextStep.currentItem + 1);
                } else {
                  getViewState().showMessageWarning(R.string.error_empty_master);
                  getViewState().goNext(eventForNextStep.currentItem + 1);
                }
                break;
            }
          } else {
            switch (eventForNextStep.currentItem) {
              case 0:
                if (!mBookingEntity.getMasterId().isEmpty()) {
                  mRxBus.post(new RxBusHelper.MasterID(String.valueOf(mBookingEntity.getMasterId()),
                      mBookingEntity.getMasterName()));
                  getViewState().goNext(eventForNextStep.currentItem + 1);
                } else {
                  getViewState().showMessageWarning(R.string.error_empty_master);
                  getViewState().goNext(eventForNextStep.currentItem + 1);
                }
                break;
              case 1:
                if (!mBookingEntity.getServiceId().isEmpty()) {
                  mRxBus.post(
                      new RxBusHelper.ServiceID(String.valueOf(mBookingEntity.getServiceId()),
                          mBookingEntity.getMasterName()));
                  getViewState().goNext(eventForNextStep.currentItem + 1);
                  getViewState().hideKeyboard();
                } else {
                  getViewState().showMessageWarning(R.string.error_empty_service);
                  getViewState().goNext(eventForNextStep.currentItem + 1);
                }
                break;
              case 2:
                if (!mBookingEntity.getDateId().isEmpty()) {
                  mRxBus.post(new RxBusHelper.DataID(String.valueOf(mBookingEntity.getDateId()),
                      mBookingEntity.getServiceTime()));
                  getViewState().goNext(eventForNextStep.currentItem + 1);
                } else {
                  getViewState().showMessageWarning(R.string.error_empty_date);
                  getViewState().goNext(eventForNextStep.currentItem + 1);
                }
                break;
            }
          }
        });
    addToUnsubscription(subscription);
  }

  public void prevStep(int currentItem) {
    if (currentItem > 0) {
      getViewState().goPrev(currentItem - 1);
    }
  }

  public void isVisibleFragment(boolean visible) {
    mRxBus.post(new RxBusHelper.VisibleFragmentChooseService(visible));
  }

  public void setMode(int mode) {
    this.mStartWith = mode;
  }

  @Override public void onDestroy() {
    super.onDestroy();
    App.destroyBookingComponent();
  }

  private void stateBooking() {
    Subscription subscription = mRxBus.filteredObservable(RxBusHelper.StateBooking.class)
        .compose(ThreadSchedulers.applySchedulers())
        .subscribe(stateBooking -> getViewState().stateBooking(), Timber::e);
    addToUnsubscription(subscription);
  }

  public void eventForNextStep(int position) {
    mRxBus.post(new RxBusHelper.EventForNextStep(position));
  }
}