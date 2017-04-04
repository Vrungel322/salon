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
        if (!mBookingEntity.getMasterServiceId().isEmpty()) {
          getViewState().goNext(currentItem + 1);
          mRxBus.post(new RxBusHelper.ServiceID(String.valueOf(mBookingEntity.getMasterServiceId())));
        }
        break;
      case 1:
        if (!mBookingEntity.getDateId().isEmpty()) {
          getViewState().goNext(currentItem + 1);
          mRxBus.post(new RxBusHelper.DataID(String.valueOf(mBookingEntity.getDateId())));
        }
        break;
      case 2:
        break;
      case 3:
        break;
    }
  }

  public void prevStep(int currentItem) {
    if (currentItem > 0) {
      getViewState().goPrev(currentItem - 1);
    }
  }
}
