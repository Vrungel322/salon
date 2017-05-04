package com.apps.twelve.floor.salon.feature.booking.mode.booking_service.presenters;

import com.apps.twelve.floor.salon.App;
import com.apps.twelve.floor.salon.base.BasePresenter;
import com.apps.twelve.floor.salon.data.DataManager;
import com.apps.twelve.floor.salon.data.model.BookingEntity;
import com.apps.twelve.floor.salon.data.model.MasterEntity;
import com.apps.twelve.floor.salon.feature.booking.mode.booking_service.views.IChooseMasterFragmentView;
import com.apps.twelve.floor.salon.utils.Randomizer;
import com.apps.twelve.floor.salon.utils.RxBus;
import com.apps.twelve.floor.salon.utils.RxBusHelper;
import com.apps.twelve.floor.salon.utils.ThreadSchedulers;
import com.arellomobile.mvp.InjectViewState;
import java.util.List;
import javax.inject.Inject;
import rx.Subscription;
import timber.log.Timber;

/**
 * Created by Vrungel on 28.03.2017.
 */

@InjectViewState public class ChooseMasterFragmentPresenter
    extends BasePresenter<IChooseMasterFragmentView> {
  @Inject DataManager mDataManager;
  @Inject RxBus mRxBus;
  @Inject BookingEntity mBookingEntity;
  private List<MasterEntity> mMasterEntities;

  @Override protected void inject() {
    App.getBookingComponent().inject(this);
  }

  @Override protected void onFirstViewAttach() {
    super.onFirstViewAttach();
    getViewState().setUpUi();
    getInfFromRxBus();
  }

  private void getInfFromRxBus() {
    Subscription subscription = mRxBus.filteredObservable(RxBusHelper.DataID.class)
        .concatMap(
            dataID -> mDataManager.fetchMasters(mBookingEntity.getServiceId(), dataID.dataId))
        .compose(ThreadSchedulers.applySchedulers())
        .subscribe(masterEntities -> {
          mMasterEntities = masterEntities;
          getViewState().setUpRedSquare(mBookingEntity.getServiceName(),
              mBookingEntity.getServiceTime(), mBookingEntity.getDurationServices());
          getViewState().showMasters(masterEntities);
          getViewState().hideProgressBar();
        }, Timber::e);
    addToUnsubscription(subscription);
  }

  public void setSelectedItem(int position) {
    mBookingEntity.setMasterId(mMasterEntities.get(position).getMasterId());
    mBookingEntity.setMasterName(mMasterEntities.get(position).getMasterName());
    getViewState().setSelectedItem(position + 1);
    mRxBus.post(new RxBusHelper.EventForNextStep(2));
  }

  public void setAnyMasterSelected() {
    mBookingEntity.setMasterId(
        mMasterEntities.get(Randomizer.getRandomNumberInRange(1, mMasterEntities.size()))
            .getMasterId());
    mBookingEntity.setMasterName("any");
    getViewState().setSelectedItem(0);
    mRxBus.post(new RxBusHelper.EventForNextStep(2));
  }
}
