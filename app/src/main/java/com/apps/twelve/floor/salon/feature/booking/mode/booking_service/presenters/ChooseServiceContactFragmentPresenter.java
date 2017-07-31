package com.apps.twelve.floor.salon.feature.booking.mode.booking_service.presenters;

import com.apps.twelve.floor.authorization.utils.AuthRxBusHelper;
import com.apps.twelve.floor.salon.App;
import com.apps.twelve.floor.salon.R;
import com.apps.twelve.floor.salon.base.BasePresenter;
import com.apps.twelve.floor.salon.data.local.mappers.BookingToBookingServerEntityMapper;
import com.apps.twelve.floor.salon.data.model.BookingEntity;
import com.apps.twelve.floor.salon.feature.booking.mode.booking_service.views.IChooseServiceContactFragmentView;
import com.apps.twelve.floor.salon.utils.Converters;
import com.apps.twelve.floor.salon.utils.RxBusHelper;
import com.apps.twelve.floor.salon.utils.ThreadSchedulers;
import com.apps.twelve.floor.salon.utils.ViewUtil;
import com.apps.twelve.floor.salon.utils.jobs.JobsCreator;
import com.arellomobile.mvp.InjectViewState;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.concurrent.TimeUnit;
import javax.inject.Inject;
import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import timber.log.Timber;

import static com.apps.twelve.floor.authorization.utils.Constants.Remote.RESPONSE_TOKEN_EXPIRED;
import static com.apps.twelve.floor.authorization.utils.Constants.Remote.RESPONSE_UNAUTHORIZED;
import static com.apps.twelve.floor.salon.data.local.PreferencesHelper.PREF_LAST_PHONE_FOR_BOOKING;
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
        mContext.getString(R.string.booking_date_and_time,
            Converters.detailDayFromSeconds(mContext, mBookingEntity.getRemainTimeInSec()),
            mBookingEntity.getServiceTime()), mBookingEntity.getDurationServices(),
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
      getViewState().showEmptyPhoneError(false);
      getViewState().showEmptyNameError(false);
      if (ViewUtil.checkPhone(mBookingEntity.getUserPhone()) && !mBookingEntity.getUserName()
          .equals("")) {
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
                          - System.currentTimeMillis(), mBookingEntity.getServiceName(),
                      Converters.detailDayFromSeconds(mContext,
                          mBookingEntity.getRemainTimeInSec()), mBookingEntity.getServiceTime());
                  getViewState().moveToBookingListActivity();
                  break;
                case RESPONSE_UNAUTHORIZED:
                  getViewState().revertAnimation();
                  mAuthorizationManager.getAuthRxBus()
                      .post(new AuthRxBusHelper.UnauthorizedEvent());
                  break;
                case RESPONSE_400:
                  getViewState().showErrorMessage(R.string.error_data_not_correct);
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
        subscription = mAuthorizationManager.checkToken(
            mAuthorizationManager.populateAdditionalField(PREF_LAST_PHONE_FOR_BOOKING,
                mBookingEntity.getUserPhone())).concatMap(response -> {
          if (response.code() == RESPONSE_TOKEN_EXPIRED) {
            return mAuthorizationManager.checkToken(
                mAuthorizationManager.populateAdditionalField(PREF_LAST_PHONE_FOR_BOOKING,
                    mBookingEntity.getUserPhone()));
          }
          return Observable.just(response);
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(response -> {
          if (response.code() == RESPONSE_UNAUTHORIZED) {
            mAuthorizationManager.getAuthRxBus().post(new AuthRxBusHelper.UnauthorizedEvent());
          }
        }, throwable -> {
          if (throwable instanceof SocketTimeoutException
              || throwable instanceof UnknownHostException) {
            mDataManager.setLastPhoneForBooking(mBookingEntity.getUserPhone());
          }
          Timber.e(throwable);
        });
        addToUnsubscription(subscription);
      } else {
        if (!ViewUtil.checkPhone(mBookingEntity.getUserPhone())) {
          getViewState().showEmptyPhoneError(true);
          getViewState().revertAnimation();
        }
        if (mBookingEntity.getUserName().equals("")) {
          getViewState().showEmptyNameError(true);
          getViewState().revertAnimation();
        }
      }
    } else {
      mRxBus.post(new RxBusHelper.ShowAuthDialogBooking());
    }
  }

  public void checkIfBookOnSameDate() {
    Subscription subscription = mDataManager.fetchLastBooking()
        .compose(ThreadSchedulers.applySchedulers())
        .flatMap(listResponse -> Observable.from(listResponse.body()))
        .filter(lastBookingEntity -> lastBookingEntity.getScheduleId()
            .equals(Integer.parseInt(mBookingEntity.getDateId())))
        .toList()
        .subscribe(lastBookingEntities -> {
          if (lastBookingEntities.size() != 0) {
            getViewState().showDoubleCheckinTimeDialog(lastBookingEntities);
          } else {
            getViewState().checkin();
          }
        });
    addToUnsubscription(subscription);
  }
}
