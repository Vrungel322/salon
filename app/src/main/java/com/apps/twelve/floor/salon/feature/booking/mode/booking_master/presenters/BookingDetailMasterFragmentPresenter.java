package com.apps.twelve.floor.salon.feature.booking.mode.booking_master.presenters;

import com.apps.twelve.floor.salon.App;
import com.apps.twelve.floor.salon.R;
import com.apps.twelve.floor.salon.base.BasePresenter;
import com.apps.twelve.floor.salon.data.model.BookingEntity;
import com.apps.twelve.floor.salon.feature.booking.mode.booking_master.views.IBookingDetailMasterView;
import com.apps.twelve.floor.salon.utils.Constants;
import com.apps.twelve.floor.salon.utils.RxBus;
import com.apps.twelve.floor.salon.utils.RxBusHelper;
import com.apps.twelve.floor.salon.utils.ThreadSchedulers;
import com.arellomobile.mvp.InjectViewState;
import javax.inject.Inject;
import rx.Subscription;
import timber.log.Timber;

/**
 * Created by John on 04.05.2017.
 */

@InjectViewState public class BookingDetailMasterFragmentPresenter
    extends BasePresenter<IBookingDetailMasterView> {

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
    stateBackBookingMaster();
  }

  @Override public void onDestroy() {
    super.onDestroy();
    App.destroyBookingComponent();
  }

  private void nextStep() {
    Subscription subscription = mRxBus.filteredObservable(RxBusHelper.EventForNextStep.class)
        .compose(ThreadSchedulers.applySchedulers())
        .subscribe(eventForNextStep -> {
          switch (eventForNextStep.fragmentTag) {
            case Constants.FragmentTag.CHOOSE_MASTER_MASTER_FRAGMENT:
              getViewState().goNextFragment(Constants.FragmentTag.CHOOSE_MASTER_MASTER_FRAGMENT);
              break;
            case Constants.FragmentTag.CHOOSE_MASTER_SERVICE_FRAGMENT:
              if (!mBookingEntity.getMasterId().isEmpty()) {
                getViewState().goNextFragment(Constants.FragmentTag.CHOOSE_MASTER_SERVICE_FRAGMENT);
              } else {
                getViewState().showMessageWarning(R.string.error_empty_master);
              }
              break;
            case Constants.FragmentTag.CHOOSE_MASTER_TIME_FRAGMENT:
              if (!mBookingEntity.getServiceId().isEmpty()) {
                getViewState().goNextFragment(Constants.FragmentTag.CHOOSE_MASTER_TIME_FRAGMENT);
                getViewState().hideKeyboard();
              } else {
                getViewState().showMessageWarning(R.string.error_empty_service);
              }
              break;
            case Constants.FragmentTag.CHOOSE_MASTER_CONTACT_FRAGMENT:
              if (!mBookingEntity.getDateId().isEmpty()) {
                getViewState().goNextFragment(Constants.FragmentTag.CHOOSE_MASTER_CONTACT_FRAGMENT);
              } else {
                getViewState().showMessageWarning(R.string.error_empty_date);
              }
              break;
          }
        });
    addToUnsubscription(subscription);
  }

  public void setSelectedTab(String fragmentTag) {
    getViewState().setSelectedTab(fragmentTag);
  }

  public void clickTab(String fragmentTag) {
    mRxBus.post(new RxBusHelper.EventForNextStep(fragmentTag));
  }

  private void stateBackBookingMaster() {
    Subscription subscription = mRxBus.filteredObservable(RxBusHelper.StateBackBookingMaster.class)
        .compose(ThreadSchedulers.applySchedulers())
        .subscribe(stateBackBookingMaster -> getViewState().stateBackBookingMaster(), Timber::e);
    addToUnsubscription(subscription);
  }
}
