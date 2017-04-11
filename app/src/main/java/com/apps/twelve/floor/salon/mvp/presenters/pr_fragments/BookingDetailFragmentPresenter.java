package com.apps.twelve.floor.salon.mvp.presenters.pr_fragments;

import com.apps.twelve.floor.salon.App;
import com.apps.twelve.floor.salon.mvp.data.DataManager;
import com.apps.twelve.floor.salon.mvp.data.model.BookingEntity;
import com.apps.twelve.floor.salon.mvp.presenters.BasePresenter;
import com.apps.twelve.floor.salon.mvp.views.IBookingDetailFragmentView;
import com.apps.twelve.floor.salon.utils.Constants;
import com.apps.twelve.floor.salon.utils.RxBus;
import com.apps.twelve.floor.salon.utils.RxBusHelper;
import com.apps.twelve.floor.salon.utils.ThreadSchedulers;
import com.arellomobile.mvp.InjectViewState;
import javax.inject.Inject;
import rx.Subscription;

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
  }

  public void nextStep(int currentItem) {
    if (mStartWith == Constants.BookingMode.START_WITH_SERVICE) {
      switch (currentItem) {
        case 0:
          if (!mBookingEntity.getServiceId().isEmpty()) {
            mRxBus.post(new RxBusHelper.ServiceID(String.valueOf(mBookingEntity.getServiceId()),
                mBookingEntity.getMasterName()));
            getViewState().goNext(currentItem + 1);
            getViewState().hideKeyboard();
          } else {
            getViewState().showMessageWarning();
          }
          break;
        case 1:
          if (!mBookingEntity.getDateId().isEmpty()) {
            mRxBus.post(new RxBusHelper.DataID(String.valueOf(mBookingEntity.getDateId()),
                mBookingEntity.getServiceTime()));
            getViewState().goNext(currentItem + 1);
          } else {
            getViewState().showMessageWarning();
          }
          break;
        case 2:
          if (!mBookingEntity.getMasterId().isEmpty()) {
            mRxBus.post(new RxBusHelper.MasterID(String.valueOf(mBookingEntity.getMasterId()),
                mBookingEntity.getMasterName()));
            getViewState().goNext(currentItem + 1);
            getViewState().replaceTitleNextButton(true);
          } else {
            getViewState().showMessageWarning();
          }
          break;
      }
    } else {
      switch (currentItem) {
        case 0:
          if (!mBookingEntity.getMasterId().isEmpty()) {
            mRxBus.post(new RxBusHelper.MasterID(String.valueOf(mBookingEntity.getMasterId()),
                mBookingEntity.getMasterName()));
            getViewState().goNext(currentItem + 1);
          } else {
            getViewState().showMessageWarning();
          }
          break;
        case 1:
          if (!mBookingEntity.getServiceId().isEmpty()) {
            mRxBus.post(new RxBusHelper.ServiceID(String.valueOf(mBookingEntity.getServiceId()),
                mBookingEntity.getMasterName()));
            getViewState().goNext(currentItem + 1);
            getViewState().hideKeyboard();
          } else {
            getViewState().showMessageWarning();
          }
          break;
        case 2:
          if (!mBookingEntity.getDateId().isEmpty()) {
            mRxBus.post(new RxBusHelper.DataID(String.valueOf(mBookingEntity.getDateId()),
                mBookingEntity.getServiceTime()));
            getViewState().goNext(currentItem + 1);
            getViewState().replaceTitleNextButton(true);
          } else {
            getViewState().showMessageWarning();
          }
          break;
      }
    }
  }

  public void prevStep(int currentItem) {
    if (currentItem > 0) {
      getViewState().goPrev(currentItem - 1);
    }
    if (currentItem == 3) {
      getViewState().replaceTitleNextButton(false);
    }
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
        .subscribe(stateBooking -> getViewState().stateBooking());
    addToUnsubscription(subscription);
  }
}
