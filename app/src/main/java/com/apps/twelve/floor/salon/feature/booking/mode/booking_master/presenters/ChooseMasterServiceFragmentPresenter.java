package com.apps.twelve.floor.salon.feature.booking.mode.booking_master.presenters;

import com.apps.twelve.floor.salon.App;
import com.apps.twelve.floor.salon.base.BasePresenter;
import com.apps.twelve.floor.salon.data.DataManager;
import com.apps.twelve.floor.salon.data.model.BookingEntity;
import com.apps.twelve.floor.salon.data.model.ServiceEntity;
import com.apps.twelve.floor.salon.feature.booking.mode.booking_master.views.IChooseMasterServiceFragmentView;
import com.apps.twelve.floor.salon.utils.Constants;
import com.apps.twelve.floor.salon.utils.RxBus;
import com.apps.twelve.floor.salon.utils.RxBusHelper;
import com.apps.twelve.floor.salon.utils.ThreadSchedulers;
import com.arellomobile.mvp.InjectViewState;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import rx.Observable;
import rx.Subscription;
import timber.log.Timber;

@InjectViewState public class ChooseMasterServiceFragmentPresenter
    extends BasePresenter<IChooseMasterServiceFragmentView> {
  @Inject DataManager mDataManager;
  @Inject BookingEntity mBookingEntity;
  @Inject RxBus mRxBus;

  private List<ServiceEntity> mServiceEntities = new ArrayList<>();

  @Override protected void inject() {
    App.getBookingComponent().inject(this);
  }

  @Override protected void onFirstViewAttach() {
    super.onFirstViewAttach();
    getViewState().setUpRvServices();
    fetchAllServicesByMasterId();
  }

  @Override public void onDestroy() {
    super.onDestroy();
    mBookingEntity.setServiceId("");
  }

  private void fetchAllServicesByMasterId() {
    getViewState().setMasterName(mBookingEntity.getMasterName());
    Subscription subscription =
        mDataManager.fetchAllServicesByMasterId(mBookingEntity.getMasterId())
            .compose(ThreadSchedulers.applySchedulers())
            .subscribe(serviceEntities -> {
              getViewState().hideProgressBar();
              getViewState().updateRvServices(serviceEntities);
              mServiceEntities.clear();
              mServiceEntities.addAll(serviceEntities);
            }, throwable -> {
              Timber.e(throwable);
              showMessageConnectException(throwable);
            });
    addToUnsubscription(subscription);
  }

  public void filterServices(String s) {
    Subscription subscription;
    if (!s.isEmpty()) {
      subscription = Observable.from(mServiceEntities)
          .filter(serviceEntity -> serviceEntity.getTitle().toLowerCase().contains(s.trim()))
          .toList()
          .subscribe(serviceEntities -> getViewState().updateRvServices(serviceEntities),
              Timber::e);
    } else {
      subscription = Observable.just(mServiceEntities)
          .subscribe(serviceEntities -> getViewState().updateRvServices(serviceEntities),
              Timber::e);
    }
    addToUnsubscription(subscription);
  }

  public void setItemSelected(int position) {
    mBookingEntity.setServiceId(String.valueOf(mServiceEntities.get(position).getServiceId()));
    mBookingEntity.setServiceName(mServiceEntities.get(position).getTitle());
    mBookingEntity.setDurationServices(String.valueOf(mServiceEntities.get(position).getTime()));
    getViewState().setItemSelected(position);
    mRxBus.post(
        new RxBusHelper.EventForNextStep(Constants.FragmentTag.CHOOSE_MASTER_TIME_FRAGMENT));
  }
}
