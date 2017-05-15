package com.apps.twelve.floor.salon.feature.our_works.presenters;

import com.apps.twelve.floor.salon.App;
import com.apps.twelve.floor.salon.base.BasePresenter;
import com.apps.twelve.floor.salon.data.DataManager;
import com.apps.twelve.floor.salon.feature.our_works.views.IOurWorkFragmentView;
import com.apps.twelve.floor.salon.utils.RxBus;
import com.apps.twelve.floor.salon.utils.RxBusHelper;
import com.apps.twelve.floor.salon.utils.ThreadSchedulers;
import com.arellomobile.mvp.InjectViewState;
import javax.inject.Inject;
import rx.Subscription;
import timber.log.Timber;

/**
 * Created by Vrungel on 21.02.2017.
 */

@InjectViewState public class OurWorkFragmentPresenter extends BasePresenter<IOurWorkFragmentView> {

  @Inject DataManager mDataManager;
  @Inject RxBus mRxBus;

  @Override protected void inject() {
    App.getAppComponent().inject(this);
  }

  @Override protected void onFirstViewAttach() {
    super.onFirstViewAttach();
    fetchListOfWorks();
    //RxBus
    getInfFromRxBus();
  }

  private void getInfFromRxBus() {
    Subscription subscription = mRxBus.filteredObservable(RxBusHelper.UpdateOurWorkList.class)
        .compose(ThreadSchedulers.applySchedulers()).subscribe(updateOurWorkList -> {
          fetchListOfWorks();
          getViewState().stopRefreshingView();
        }, throwable -> {
          getViewState().stopRefreshingView();
          Timber.e(throwable);
        });
    addToUnsubscription(subscription);
  }

  public void fetchListOfWorks() {
    Subscription subscription = mDataManager.fetchListOfWorks()
        .compose(ThreadSchedulers.applySchedulers()).subscribe(ourWorkEntities -> {
          getViewState().addListOfWorks(ourWorkEntities);
          getViewState().stopRefreshingView();
        }, throwable -> {
          getViewState().stopRefreshingView();
          Timber.e(throwable);
        });
    addToUnsubscription(subscription);
  }
}
