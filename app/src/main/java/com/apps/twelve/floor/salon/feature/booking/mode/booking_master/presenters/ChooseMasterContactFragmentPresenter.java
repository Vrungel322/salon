package com.apps.twelve.floor.salon.feature.booking.mode.booking_master.presenters;

import com.apps.twelve.floor.authorization.utils.AuthRxBusHelper;
import com.apps.twelve.floor.salon.App;
import com.apps.twelve.floor.salon.base.BasePresenter;
import com.apps.twelve.floor.salon.data.local.mappers.BookingToBookingServerEntityMapper;
import com.apps.twelve.floor.salon.data.model.BookingEntity;
import com.apps.twelve.floor.salon.feature.booking.mode.booking_master.views.IChooseMasterContactFragmentView;
import com.apps.twelve.floor.salon.utils.RxBusHelper;
import com.apps.twelve.floor.salon.utils.ThreadSchedulers;
import com.apps.twelve.floor.salon.utils.jobs.JobsCreator;
import com.arellomobile.mvp.InjectViewState;
import java.util.concurrent.TimeUnit;
import javax.inject.Inject;
import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import timber.log.Timber;

import static com.apps.twelve.floor.authorization.utils.Constants.Remote.RESPONSE_TOKEN_EXPIRED;
import static com.apps.twelve.floor.authorization.utils.Constants.Remote.RESPONSE_UNAUTHORIZED;
import static com.apps.twelve.floor.salon.utils.Constants.StatusCode.RESPONSE_200;

@InjectViewState public class ChooseMasterContactFragmentPresenter
    extends BasePresenter<IChooseMasterContactFragmentView> {

  @Inject BookingEntity mBookingEntity;
  @Inject JobsCreator mJobsCreator;
  @Inject BookingToBookingServerEntityMapper mapper;

  @Override protected void inject() {
    App.getBookingComponent().inject(this);
  }

  @Override protected void onFirstViewAttach() {
    super.onFirstViewAttach();
    getViewState().setUpBookingInformation(mBookingEntity.getServiceName(),
        mBookingEntity.getServiceTime(), mBookingEntity.getDurationServices(),
        mBookingEntity.getMasterName());
  }

  public void setPersonName(String s) {
    mBookingEntity.setUserName(s);
  }

  public void setPersonPhone(String s) {
    mBookingEntity.setUserPhone(s);
  }

  @SuppressWarnings("ConstantConditions") public void sendBookingEntity() {
    if (mAuthorizationManager.isAuthorized()) {
      getViewState().startAnimation();
      if (!mBookingEntity.getUserPhone().equals("")) {
        Subscription subscription = mAuthorizationManager.checkToken(
            mDataManager.checkInService(mapper.transform(mBookingEntity)))
            .concatMap(lastBookingEntityResponse -> {
              if (lastBookingEntityResponse.code() == RESPONSE_TOKEN_EXPIRED) {
                return mAuthorizationManager.checkToken(
                    mDataManager.checkInService(mapper.transform(mBookingEntity)));
              }
              return Observable.just(lastBookingEntityResponse);
            })
            .compose(ThreadSchedulers.applySchedulers())
            .doOnNext(response -> {
              if (response.code() == RESPONSE_200) {
                getViewState().stopAnimation();
              }
            })
            .delay(1, TimeUnit.SECONDS, AndroidSchedulers.mainThread())
            .subscribe(response -> {
              switch (response.code()) {
                case RESPONSE_200:
                  mRxBus.post(new RxBusHelper.UpdateLastBookingListEvent());
                  mJobsCreator.createNotification(String.valueOf(response.body().getId()),
                      Integer.parseInt(mBookingEntity.getRemainTimeInSec()) * 1000L
                          - System.currentTimeMillis(), mBookingEntity.getServiceName());
                  getViewState().closeBooking();
                  break;
                case RESPONSE_UNAUTHORIZED:
                  getViewState().revertAnimation();
                  mAuthorizationManager.getAuthRxBus()
                      .post(new AuthRxBusHelper.UnauthorizedEvent());
                  break;
                default:
                  getViewState().revertAnimation();
                  showMessageException();
                  break;
              }
            }, throwable -> {
              Timber.e(throwable);
              getViewState().revertAnimation();
              showMessageException(throwable);
            });
        addToUnsubscription(subscription);
      } else {
        getViewState().revertAnimation();
        getViewState().showEmptyPhoneError();
      }
    } else {
      mRxBus.post(new RxBusHelper.ShowAuthDialogBooking());
    }
  }
}
