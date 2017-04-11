package com.apps.twelve.floor.salon.mvp.presenters.pr_fragments;

import com.apps.twelve.floor.salon.App;
import com.apps.twelve.floor.salon.mvp.data.DataManager;
import com.apps.twelve.floor.salon.mvp.data.model.BookingEntity;
import com.apps.twelve.floor.salon.mvp.data.model.ServiceEntity;
import com.apps.twelve.floor.salon.mvp.presenters.BasePresenter;
import com.apps.twelve.floor.salon.mvp.views.IChooseMasterServiceView;
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
    extends BasePresenter<IChooseMasterServiceView> {
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
    setMasterName();
  }

  private void setMasterName() {
    Subscription subscription = mRxBus.filteredObservable(RxBusHelper.MasterID.class)
        .compose(ThreadSchedulers.applySchedulers())
        .subscribe(masterID -> getViewState().setMasterName(mBookingEntity.getMasterName()));
    addToUnsubscription(subscription);
  }

  public void fetchAllServices() {
    getViewState().showProgressBarAllServices();
    Subscription subscription = mDataManager.fetchAllServices()
        .compose(ThreadSchedulers.applySchedulers())
        .subscribe(serviceEntities -> {
          getViewState().hideProgressBar();
          getViewState().updateRvServices(serviceEntities);
          mServiceEntities.clear();
          mServiceEntities.addAll(serviceEntities);
          getViewState().hideProgressBarAllServices();
        }, throwable -> {
          Timber.e(throwable);
          getViewState().hideProgressBarAllServices();
          getViewState().showErrorMsg(throwable.getMessage());
        });
    addToUnsubscription(subscription);
  }

  public void filterServices(String s) {
    Subscription subscription;
    if (!s.isEmpty()) {
      subscription = Observable.from(mServiceEntities)
          .filter(serviceEntity -> serviceEntity.getTitle().toLowerCase().contains(s.trim()))
          .toList()
          .subscribe(serviceEntities -> getViewState().updateRvServices(serviceEntities));
    } else {
      subscription = Observable.just(mServiceEntities)
          .subscribe(serviceEntities -> getViewState().updateRvServices(serviceEntities));
    }
    addToUnsubscription(subscription);
  }

  public void setItemSelected(int position) {
    mBookingEntity.setServiceId(String.valueOf(mServiceEntities.get(position).getServiceId()));
    mBookingEntity.setServiceName(mServiceEntities.get(position).getTitle());
    getViewState().setItemSelected(position);
  }
}
