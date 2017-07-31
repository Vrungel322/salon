package com.apps.twelve.floor.salon.feature.my_booking.presenters;

import com.apps.twelve.floor.salon.App;
import com.apps.twelve.floor.salon.base.BasePresenter;
import com.apps.twelve.floor.salon.feature.my_booking.views.IBookingListActivityView;
import com.apps.twelve.floor.salon.utils.ThreadSchedulers;
import com.arellomobile.mvp.InjectViewState;
import rx.Subscription;

/**
 * Created by Vrungel on 20.07.2017.
 */
@InjectViewState public class BookingListActivityPresenter
    extends BasePresenter<IBookingListActivityView> {

  @Override protected void inject() {
    App.getAppComponent().inject(this);
  }

  @Override protected void onFirstViewAttach() {
    super.onFirstViewAttach();
    Subscription subscription = mDataManager.fetchLastBooking()
        .compose(ThreadSchedulers.applySchedulers())
        .subscribe(listResponse -> getViewState().showMyBookFragment(
            listResponse.body().get(listResponse.body().size() - 1)));
    addToUnsubscription(subscription);
  }
}
