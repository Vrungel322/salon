package com.apps.twelve.floor.salon.mvp.presenters.pr_fragments;

import com.apps.twelve.floor.salon.App;
import com.apps.twelve.floor.salon.mvp.data.DataManager;
import com.apps.twelve.floor.salon.mvp.data.model.BookingEntity;
import com.apps.twelve.floor.salon.mvp.presenters.BasePresenter;
import com.apps.twelve.floor.salon.mvp.views.IBookingMasterContactFragmentView;
import com.apps.twelve.floor.salon.utils.RxBus;
import com.apps.twelve.floor.salon.utils.RxBusHelper;
import com.apps.twelve.floor.salon.utils.ThreadSchedulers;
import com.arellomobile.mvp.InjectViewState;
import javax.inject.Inject;
import rx.Subscription;

@InjectViewState public class BookingMasterContactFragmentPresenter
    extends BasePresenter<IBookingMasterContactFragmentView> {

  @Inject BookingEntity mBookingEntity;
  @Inject DataManager mDataManager;
  @Inject RxBus mRxBus;

  @Override protected void inject() {
    App.getBookingComponent().inject(this);
  }

  @Override protected void onFirstViewAttach() {
    super.onFirstViewAttach();
    getInfFromRxBus();
  }

  private void getInfFromRxBus() {
    Subscription subscription = mRxBus.filteredObservable(RxBusHelper.DataID.class)
        .compose(ThreadSchedulers.applySchedulers())
        .subscribe(dataID -> getViewState().setUpBookingInformation(mBookingEntity.getServiceName(),
            mBookingEntity.getServiceTime(), mBookingEntity.getDurationServices(),
            mBookingEntity.getMasterName()));
    addToUnsubscription(subscription);
  }

  public void setPersonName(String s) {
    mBookingEntity.setUserName(s);
  }

  public void setPersonPhone(String s) {
    mBookingEntity.setUserPhone(s);
  }

  public void sendBookingEntity() {
    // TODO: 04.04.2017 проверка всех полей и откравка
  }
}
