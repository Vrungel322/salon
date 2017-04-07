package com.apps.twelve.floor.salon.mvp.presenters.pr_fragments;

import com.apps.twelve.floor.salon.App;
import com.apps.twelve.floor.salon.mvp.data.DataManager;
import com.apps.twelve.floor.salon.mvp.presenters.BasePresenter;
import com.apps.twelve.floor.salon.mvp.views.IChooseMasterTimeView;
import com.arellomobile.mvp.InjectViewState;
import javax.inject.Inject;

@InjectViewState public class ChooseMasterTimeFragmentPresenter
    extends BasePresenter<IChooseMasterTimeView> {
  @Inject DataManager mDataManager;

  @Override protected void inject() {
    App.getAppComponent().inject(this);
  }

  @Override protected void onFirstViewAttach() {
    super.onFirstViewAttach();
    loadData();
  }

  private void loadData() {
    //Subscription subscription = mDataManager.fetchDaysData("")
    //    .compose(ThreadSchedulers.applySchedulers())
    //    .subscribe(strings -> {
    //      getViewState().hideProgressBarBookingTime();
    //      if (!strings.isEmpty()) {
    //        getViewState().setUpUi(strings);
    //        setDateToTv();
    //        loadWorkSchedule();
    //        getViewState().showTimeBooking();
    //      } else {
    //        getViewState().showNotTime();
    //      }
    //    }, Timber::e);
    //addToUnsubscription(subscription);
  }

  private void loadWorkSchedule() {
    //Subscription subscription = mDataManager.fetchWorkSchedule()
    //    .compose(ThreadSchedulers.applySchedulers())
    //    .subscribe(workStartEndEntities -> getViewState().updateWorkSchedule(workStartEndEntities));
    //addToUnsubscription(subscription);
  }

  public void setSelectedTime(int position) {
    getViewState().setSelectedTime(position);
  }

  public void setDateToTv() {
    getViewState().setTextToDayTv();
  }
}
