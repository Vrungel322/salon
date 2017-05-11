package com.apps.twelve.floor.salon.feature.booking.mode.booking_master.presenters;

import android.content.Context;
import com.apps.twelve.floor.salon.App;
import com.apps.twelve.floor.salon.R;
import com.apps.twelve.floor.salon.base.BasePresenter;
import com.apps.twelve.floor.salon.data.DataManager;
import com.apps.twelve.floor.salon.data.model.BookingEntity;
import com.apps.twelve.floor.salon.data.model.MasterEntity;
import com.apps.twelve.floor.salon.feature.booking.mode.booking_master.views.IChooseMasterMasterFragmentView;
import com.apps.twelve.floor.salon.utils.Constants;
import com.apps.twelve.floor.salon.utils.Randomizer;
import com.apps.twelve.floor.salon.utils.RxBus;
import com.apps.twelve.floor.salon.utils.RxBusHelper;
import com.apps.twelve.floor.salon.utils.ThreadSchedulers;
import com.arellomobile.mvp.InjectViewState;
import java.util.List;
import javax.inject.Inject;
import rx.Subscription;
import timber.log.Timber;

@InjectViewState public class ChooseMasterMasterFragmentPresenter
    extends BasePresenter<IChooseMasterMasterFragmentView> {
  @Inject DataManager mDataManager;
  @Inject RxBus mRxBus;
  @Inject BookingEntity mBookingEntity;
  @Inject Context mContext;
  private List<MasterEntity> mMasterEntities;

  @Override protected void inject() {
    App.getBookingComponent().inject(this);
  }

  @Override protected void onFirstViewAttach() {
    super.onFirstViewAttach();
    getViewState().setUpUi();
    fetchAllMasters();
  }

  private void fetchAllMasters() {
    Subscription subscription = mDataManager.fetchAllMasters()
        .compose(ThreadSchedulers.applySchedulers())
        .subscribe(masterEntities -> {
          mMasterEntities = masterEntities;
          getViewState().showMasters(masterEntities);
          getViewState().hideProgressBar();
        }, Timber::e);
    addToUnsubscription(subscription);
  }

  public void setSelectedItem(int position) {
    mBookingEntity.setMasterId(mMasterEntities.get(position).getMasterId());
    mBookingEntity.setMasterName(mMasterEntities.get(position).getMasterName());
    getViewState().setSelectedItem(position + 1);
    mRxBus.post(
        new RxBusHelper.EventForNextStep(Constants.FragmentTag.CHOOSE_MASTER_SERVICE_FRAGMENT));
  }

  public void setAnyMasterSelected() {
    mBookingEntity.setMasterId(
        mMasterEntities.get(Randomizer.getRandomNumberInRange(1, mMasterEntities.size()))
            .getMasterId());
    mBookingEntity.setMasterName(mContext.getString(R.string.any_master));
    getViewState().setSelectedItem(0);
    mRxBus.post(
        new RxBusHelper.EventForNextStep(Constants.FragmentTag.CHOOSE_MASTER_SERVICE_FRAGMENT));
  }
}
