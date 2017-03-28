package com.apps.twelve.floor.salon.mvp.presenters.pr_fragments;

import com.apps.twelve.floor.salon.App;
import com.apps.twelve.floor.salon.mvp.data.DataManager;
import com.apps.twelve.floor.salon.mvp.presenters.BasePresenter;
import com.apps.twelve.floor.salon.mvp.presenters.pr_interfaces.ISubFragmentBookingPresenter;
import com.apps.twelve.floor.salon.mvp.views.ISubFragmentBookingView;
import com.apps.twelve.floor.salon.utils.ThreadSchedulers;
import com.arellomobile.mvp.InjectViewState;
import javax.inject.Inject;
import rx.Subscription;

/**
 * Created by Vrungel on 28.02.2017.
 */

@InjectViewState public class SubFragmentBookingPresenter
    extends BasePresenter<ISubFragmentBookingView> implements ISubFragmentBookingPresenter {
  @Inject DataManager mDataManager;

  @Override protected void inject() {
    App.getAppComponent().inject(this);
  }

  @Override protected void onFirstViewAttach() {
    super.onFirstViewAttach();
    fetchBookingEntities();
  }

  @Override public void fetchBookingEntities() {
    Subscription subscription = mDataManager.fetchLastBooking()
        .compose(ThreadSchedulers.applySchedulers())
        .subscribe(lastBookingEntities -> getViewState().showAllBooking(lastBookingEntities),
            Throwable::printStackTrace);
    addToUnsubscription(subscription);
  }
}
