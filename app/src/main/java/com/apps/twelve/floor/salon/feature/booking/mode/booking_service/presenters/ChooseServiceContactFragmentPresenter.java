package com.apps.twelve.floor.salon.feature.booking.mode.booking_service.presenters;

import com.apps.twelve.floor.authorization.utils.AuthRxBusHelper;
import com.apps.twelve.floor.salon.App;
import com.apps.twelve.floor.salon.R;
import com.apps.twelve.floor.salon.base.BasePresenter;
import com.apps.twelve.floor.salon.data.local.mappers.BookingToBookingServerEntityMapper;
import com.apps.twelve.floor.salon.data.model.BookingEntity;
import com.apps.twelve.floor.salon.feature.booking.mode.booking_service.views.IChooseServiceContactFragmentView;
import com.apps.twelve.floor.salon.utils.RxBusHelper;
import com.apps.twelve.floor.salon.utils.ThreadSchedulers;
import com.apps.twelve.floor.salon.utils.ViewUtil;
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
import static com.apps.twelve.floor.salon.utils.Constants.StatusCode.RESPONSE_400;
import static com.apps.twelve.floor.salon.utils.Constants.StatusCode.RESPONSE_404;

/**
 * Created by Alexandra on 28.03.2017.
 */

@InjectViewState public class ChooseServiceContactFragmentPresenter
    extends BasePresenter<IChooseServiceContactFragmentView> {

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
    getViewState().setLastPhone(s);
  }

  public void setPersonPhone() {
    mBookingEntity.setUserPhone(mDataManager.getLastPhoneForBooking());
    getViewState().setLastPhone(mDataManager.getLastPhoneForBooking());
  }

  @SuppressWarnings("ConstantConditions") public void sendBookingEntity() {
    if (mAuthorizationManager.isAuthorized()) {
      getViewState().startAnimation();
      if (ViewUtil.checkPhone(mBookingEntity.getUserPhone())) {
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
                  mDataManager.setLastPhoneForBooking(mBookingEntity.getUserPhone());
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
                case RESPONSE_400:
                  getViewState().showErrorMessage(R.string.error_phone);
                  getViewState().revertAnimation();
                  break;
                case RESPONSE_404:
                  getViewState().showErrorMessage(R.string.error_booking_entity_not_exist);
                  getViewState().revertAnimation();
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
