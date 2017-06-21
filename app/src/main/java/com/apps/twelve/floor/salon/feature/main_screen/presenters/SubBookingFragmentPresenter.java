package com.apps.twelve.floor.salon.feature.main_screen.presenters;

import com.apps.twelve.floor.salon.App;
import com.apps.twelve.floor.salon.base.BasePresenter;
import com.apps.twelve.floor.salon.feature.main_screen.views.ISubFragmentBookingView;
import com.apps.twelve.floor.salon.utils.RxBusHelper;
import com.apps.twelve.floor.salon.utils.ThreadSchedulers;
import com.arellomobile.mvp.InjectViewState;
import rx.Observable;
import rx.Subscription;
import timber.log.Timber;

/**
 * Created by Vrungel on 28.02.2017.
 */

@InjectViewState public class SubBookingFragmentPresenter
    extends BasePresenter<ISubFragmentBookingView> {

  @Override protected void inject() {
    App.getAppComponent().inject(this);
  }

  @Override protected void onFirstViewAttach() {
    super.onFirstViewAttach();
    fetchBookingEntities();
    //RxBus
    subscribeUpdateSubBooking();
  }

  public void showAlertDialog() {
    mRxBus.post(new RxBusHelper.ShowAuthDialog());
  }

  private void fetchBookingEntities() {
    if (mAuthorizationManager.isAuthorized()) {
      Subscription subscription = mDataManager.fetchLastBooking()
          .concatMap(Observable::from)
          .take(2)
          .toList()
          .compose(ThreadSchedulers.applySchedulers())
          .subscribe(lastBookingEntities -> getViewState().showLastBookings(lastBookingEntities),
              throwable -> {
                Timber.e(throwable);
                showMessageException(throwable);
              });
      addToUnsubscription(subscription);
    }
  }

  private void subscribeUpdateSubBooking() {
    Subscription subscription =
        mRxBus.filteredObservable(RxBusHelper.UpdateLastBookingListEvent.class)
            .concatMap(updateLastBookingListEvent -> mDataManager.fetchLastBooking()
                .concatMap(Observable::from)
                .take(2)
                .toList())
            .compose(ThreadSchedulers.applySchedulers())
            .subscribe(lastBookingEntities -> getViewState().showLastBookings(lastBookingEntities),
                throwable -> {
                  subscribeUpdateSubBooking();
                  Timber.e(throwable);
                  showMessageException(throwable);
                });
    addToUnsubscription(subscription);
  }
}
