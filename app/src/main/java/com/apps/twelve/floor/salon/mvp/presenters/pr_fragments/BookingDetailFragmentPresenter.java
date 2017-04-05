package com.apps.twelve.floor.salon.mvp.presenters.pr_fragments;

import com.apps.twelve.floor.salon.App;
import com.apps.twelve.floor.salon.mvp.data.DataManager;
import com.apps.twelve.floor.salon.mvp.data.model.BookingEntity;
import com.apps.twelve.floor.salon.mvp.presenters.BasePresenter;
import com.apps.twelve.floor.salon.mvp.views.IBookingDetailFragmentView;
import com.apps.twelve.floor.salon.utils.RxBus;
import com.apps.twelve.floor.salon.utils.RxBusHelper;
import com.arellomobile.mvp.InjectViewState;
import javax.inject.Inject;
import timber.log.Timber;

/**
 * Created by Vrungel on 23.03.2017.
 */

@InjectViewState public class BookingDetailFragmentPresenter
    extends BasePresenter<IBookingDetailFragmentView> {
  @Inject BookingEntity mBookingEntity;
  @Inject DataManager mDataManager;
  @Inject RxBus mRxBus;

  @Override protected void inject() {
    App.getBookingComponent().inject(this);
  }

  @Override protected void onFirstViewAttach() {
    super.onFirstViewAttach();
    getViewState().setUpViewPager();
  }

  public void nextStep(int currentItem) {
    switch (currentItem) {
      case 0:
        if (!mBookingEntity.getServiceId().isEmpty()) {
          getViewState().goNext(currentItem + 1);
          getViewState().hideKeyboard();
          mRxBus.post(new RxBusHelper.ServiceID(String.valueOf(mBookingEntity.getServiceId()),
              mBookingEntity.getMasterName()));
        } else {
          getViewState().showMessageWarning();
        }
        break;
      case 1:
        if (!mBookingEntity.getDateId().isEmpty()) {
          getViewState().goNext(currentItem + 1);
          mRxBus.post(new RxBusHelper.DataID(String.valueOf(mBookingEntity.getDateId()),
              mBookingEntity.getServiceTime()));
        } else {
          getViewState().showMessageWarning();
        }
        break;
      case 2:
        if (!mBookingEntity.getMasterId().isEmpty()) {
          getViewState().goNext(currentItem + 1);
          mRxBus.post(new RxBusHelper.MasterID(String.valueOf(mBookingEntity.getMasterId()),
              mBookingEntity.getMasterName()));
          getViewState().replaceTitleNextButton(true);
        } else {
          getViewState().showMessageWarning();
        }
        break;
    }
  }

  public void prevStep(int currentItem) {
    if (currentItem > 0) {
      getViewState().goPrev(currentItem - 1);
    }
    Timber.e("" + currentItem);
    if (currentItem == 3) {
      getViewState().replaceTitleNextButton(false);
    }
  }
}
