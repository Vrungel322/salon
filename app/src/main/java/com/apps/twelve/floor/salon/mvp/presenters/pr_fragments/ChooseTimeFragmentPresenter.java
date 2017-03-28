package com.apps.twelve.floor.salon.mvp.presenters.pr_fragments;

import com.apps.twelve.floor.salon.App;
import com.apps.twelve.floor.salon.mvp.data.DataManager;
import com.apps.twelve.floor.salon.mvp.presenters.BasePresenter;
import com.apps.twelve.floor.salon.mvp.presenters.pr_interfaces.IChooseTimeFragmentPresenter;
import com.apps.twelve.floor.salon.mvp.views.IChooseTimeFragmentView;
import com.apps.twelve.floor.salon.utils.ThreadSchedulers;
import com.arellomobile.mvp.InjectViewState;
import javax.inject.Inject;
import rx.Subscription;
import timber.log.Timber;

/**
 * Created by Vrungel on 27.03.2017.
 */

@InjectViewState public class ChooseTimeFragmentPresenter
    extends BasePresenter<IChooseTimeFragmentView> implements IChooseTimeFragmentPresenter {
  @Inject DataManager mDataManager;

  @Override protected void inject() {
    App.getAppComponent().inject(this);
  }

  @Override protected void onFirstViewAttach() {
    super.onFirstViewAttach();
    loadData();
  }

  private void loadData() {
    Subscription subscription = mDataManager.fetchDaysData()
        .compose(ThreadSchedulers.applySchedulers())
        .subscribe(strings -> {
          getViewState().hideProgressBarBookingTime();
          if (!strings.isEmpty()) {
            getViewState().setUpUi(strings);
            setDateToTv();
            loadWorkSchedule();
            getViewState().showTimeBooking();
          } else {
            getViewState().showNotTime();
          }
        }, Timber::e);
    addToUnsubscription(subscription);
  }

  private void loadWorkSchedule() {
    Subscription subscription = mDataManager.fetchWorkSchedule()
        .compose(ThreadSchedulers.applySchedulers())
        .subscribe(workStartEndEntities -> getViewState().updateWorkSchedule(workStartEndEntities));
    addToUnsubscription(subscription);
  }

  public void setSelectedTime(int position) {
    getViewState().setSelectedTime(position);
  }

  public void setDateToTv() {
    getViewState().setTextToDayTv();
  }
}
