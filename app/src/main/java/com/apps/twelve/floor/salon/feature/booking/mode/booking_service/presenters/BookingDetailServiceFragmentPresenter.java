package com.apps.twelve.floor.salon.feature.booking.mode.booking_service.presenters;

import com.apps.twelve.floor.salon.App;
import com.apps.twelve.floor.salon.R;
import com.apps.twelve.floor.salon.base.BasePresenter;
import com.apps.twelve.floor.salon.data.model.BookingEntity;
import com.apps.twelve.floor.salon.feature.booking.mode.booking_service.views.IBookingDetailServiceView;
import com.apps.twelve.floor.salon.utils.Constants;
import com.apps.twelve.floor.salon.utils.RxBus;
import com.apps.twelve.floor.salon.utils.RxBusHelper;
import com.apps.twelve.floor.salon.utils.ThreadSchedulers;
import com.arellomobile.mvp.InjectViewState;
import javax.inject.Inject;
import rx.Subscription;

/**
 * Created by John on 05.05.2017.
 */

@InjectViewState public class BookingDetailServiceFragmentPresenter
    extends BasePresenter<IBookingDetailServiceView> {

  @Inject BookingEntity mBookingEntity;
  @Inject RxBus mRxBus;

  @Override protected void inject() {
    App.initBookingComponent();
    App.getBookingComponent().inject(this);
  }

  @Override protected void onFirstViewAttach() {
    super.onFirstViewAttach();
    getViewState().addFirstFragment();
    nextStep();
  }

  @Override public void onDestroy() {
    super.onDestroy();
    App.destroyBookingComponent();
  }

  public void clickTab(String fragmentTag) {
    mRxBus.post(new RxBusHelper.EventForNextStep(fragmentTag));
  }

  public void setSelectedTab(String fragmentTag) {
    getViewState().setSelectedTab(fragmentTag);
  }

  private void nextStep() {
    Subscription subscription = mRxBus.filteredObservable(RxBusHelper.EventForNextStep.class)
        .compose(ThreadSchedulers.applySchedulers())
        .subscribe(eventForNextStep -> {
          switch (eventForNextStep.fragmentTag) {
            case Constants.FragmentTag.CHOOSE_SERVICE_SERVICE_FRAGMENT:
              getViewState().goNextFragment(Constants.FragmentTag.CHOOSE_SERVICE_SERVICE_FRAGMENT);
              break;
            case Constants.FragmentTag.CHOOSE_SERVICE_TIME_FRAGMENT:
              if (!mBookingEntity.getServiceId().isEmpty()) {
                getViewState().goNextFragment(Constants.FragmentTag.CHOOSE_SERVICE_TIME_FRAGMENT);
                getViewState().hideKeyboard();
              } else {
                getViewState().showMessageWarning(R.string.error_empty_service);
              }
              break;
            case Constants.FragmentTag.CHOOSE_SERVICE_MASTER_FRAGMENT:
              if (!mBookingEntity.getDateId().isEmpty()) {
                getViewState().goNextFragment(Constants.FragmentTag.CHOOSE_SERVICE_MASTER_FRAGMENT);
              } else {
                getViewState().showMessageWarning(R.string.error_empty_date);
              }
              break;
            case Constants.FragmentTag.CHOOSE_SERVICE_CONTACT_FRAGMENT:
              if (!mBookingEntity.getMasterId().isEmpty()) {
                getViewState().goNextFragment(
                    Constants.FragmentTag.CHOOSE_SERVICE_CONTACT_FRAGMENT);
              } else {
                getViewState().showMessageWarning(R.string.error_empty_master);
              }
              break;
          }
        });
    addToUnsubscription(subscription);
  }
}
