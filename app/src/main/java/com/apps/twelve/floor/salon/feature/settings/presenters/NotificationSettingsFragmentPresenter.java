package com.apps.twelve.floor.salon.feature.settings.presenters;

import com.apps.twelve.floor.authorization.utils.AuthRxBusHelper;
import com.apps.twelve.floor.authorization.utils.ThreadSchedulers;
import com.apps.twelve.floor.salon.App;
import com.apps.twelve.floor.salon.base.BasePresenter;
import com.apps.twelve.floor.salon.feature.settings.views.INotificationSettingsFragmentView;
import com.arellomobile.mvp.InjectViewState;
import rx.Observable;
import rx.Subscription;

import static com.apps.twelve.floor.authorization.utils.Constants.Notification.PREF_NOTIF_DAILY_ENABLED;
import static com.apps.twelve.floor.authorization.utils.Constants.Notification.PREF_NOTIF_DAYS;
import static com.apps.twelve.floor.authorization.utils.Constants.Notification.PREF_NOTIF_HOURLY_ENABLED;
import static com.apps.twelve.floor.authorization.utils.Constants.Notification.PREF_NOTIF_HOURS;
import static com.apps.twelve.floor.authorization.utils.Constants.Notification.PREF_NOTIF_HOURS_NIGHT_END;
import static com.apps.twelve.floor.authorization.utils.Constants.Notification.PREF_NOTIF_HOURS_NIGHT_START;
import static com.apps.twelve.floor.authorization.utils.Constants.Notification.PREF_NOTIF_NIGHT_MODE;
import static com.apps.twelve.floor.authorization.utils.Constants.Remote.RESPONSE_TOKEN_EXPIRED;
import static com.apps.twelve.floor.authorization.utils.Constants.Remote.RESPONSE_UNAUTHORIZED;
import static com.apps.twelve.floor.salon.utils.Constants.StatusCode.RESPONSE_200;

/**
 * Created by Alexandra on 05.05.2017.
 */

@InjectViewState public class NotificationSettingsFragmentPresenter
    extends BasePresenter<INotificationSettingsFragmentView> {

  private int mLastPickedDays;

  @Override protected void inject() {
    App.getAppComponent().inject(this);
  }

  @Override protected void onFirstViewAttach() {
    super.onFirstViewAttach();
    mLastPickedDays = getDays();
  }

  public void setUpSwitches() {
    getViewState().setUpSwitchers(isHourlyNotificationsEnabled(), isDailyNotificationsEnabled(),
        isNightMode());
  }

  private boolean isHourlyNotificationsEnabled() {
    return mAuthorizationManager.getAdditionalField(PREF_NOTIF_HOURLY_ENABLED, true);
  }

  private boolean isDailyNotificationsEnabled() {
    return mAuthorizationManager.getAdditionalField(PREF_NOTIF_DAILY_ENABLED, true);
  }

  private boolean isNightMode() {
    return mAuthorizationManager.getAdditionalField(PREF_NOTIF_NIGHT_MODE, true);
  }

  public void setUpStrings() {
    getViewState().setUpDaysString(getDays());
    getViewState().setUpHoursString(getHours());
    getViewState().setUpNightHours(getHoursNightStart(), getHoursNightEnd());
  }

  public long getHours() {
    return mAuthorizationManager.getAdditionalField(PREF_NOTIF_HOURS, 3600000);
  }

  public int getDays() {
    return (int) mAuthorizationManager.getAdditionalField(PREF_NOTIF_DAYS, 1);
  }

  public long getHoursNightStart() {
    return mAuthorizationManager.getAdditionalField(PREF_NOTIF_HOURS_NIGHT_START, 82800000);
  }

  public long getHoursNightEnd() {
    return mAuthorizationManager.getAdditionalField(PREF_NOTIF_HOURS_NIGHT_END, 25200000);
  }

  public void setHourlyNotificationsEnabled(boolean checked) {
    Subscription subscription = mAuthorizationManager.checkToken(
        mAuthorizationManager.populateAdditionalField(PREF_NOTIF_HOURLY_ENABLED, checked))
        .concatMap(response -> {
          if (response.code() == RESPONSE_TOKEN_EXPIRED) {
            return mAuthorizationManager.checkToken(
                mAuthorizationManager.populateAdditionalField(PREF_NOTIF_HOURLY_ENABLED, checked));
          }
          return Observable.just(response);
        })
        .compose(ThreadSchedulers.applySchedulers())
        .subscribe(response -> {
          switch (response.code()) {
            case RESPONSE_UNAUTHORIZED:
              mAuthorizationManager.getAuthRxBus().post(new AuthRxBusHelper.UnauthorizedEvent());
              break;
          }
        }, throwable -> {
          mAuthorizationManager.setAdditionalField(PREF_NOTIF_HOURLY_ENABLED, checked);
        });
    addToUnsubscription(subscription);
  }

  public void setDailyNotificationsEnabled(boolean checked) {
    Subscription subscription = mAuthorizationManager.checkToken(
        mAuthorizationManager.populateAdditionalField(PREF_NOTIF_DAILY_ENABLED, checked))
        .concatMap(response -> {
          if (response.code() == RESPONSE_TOKEN_EXPIRED) {
            return mAuthorizationManager.checkToken(
                mAuthorizationManager.populateAdditionalField(PREF_NOTIF_DAILY_ENABLED, checked));
          }
          return Observable.just(response);
        })
        .compose(ThreadSchedulers.applySchedulers())
        .subscribe(response -> {
          switch (response.code()) {
            case RESPONSE_UNAUTHORIZED:
              mAuthorizationManager.getAuthRxBus().post(new AuthRxBusHelper.UnauthorizedEvent());
              break;
          }
        }, throwable -> {
          mAuthorizationManager.setAdditionalField(PREF_NOTIF_DAILY_ENABLED, checked);
        });
    addToUnsubscription(subscription);
  }

  public void setNightModeNotificationsEnabled(boolean checked) {
    Subscription subscription = mAuthorizationManager.checkToken(
        mAuthorizationManager.populateAdditionalField(PREF_NOTIF_NIGHT_MODE, checked))
        .concatMap(response -> {
          if (response.code() == RESPONSE_TOKEN_EXPIRED) {
            return mAuthorizationManager.checkToken(
                mAuthorizationManager.populateAdditionalField(PREF_NOTIF_NIGHT_MODE, checked));
          }
          return Observable.just(response);
        })
        .compose(ThreadSchedulers.applySchedulers())
        .subscribe(response -> {
          switch (response.code()) {
            case RESPONSE_UNAUTHORIZED:
              mAuthorizationManager.getAuthRxBus().post(new AuthRxBusHelper.UnauthorizedEvent());
              break;
          }
        }, throwable -> {
          mAuthorizationManager.setAdditionalField(PREF_NOTIF_NIGHT_MODE, checked);
        });
    addToUnsubscription(subscription);
  }

  public void setHours(long millis) {
    Subscription subscription = mAuthorizationManager.checkToken(
        mAuthorizationManager.populateAdditionalField(PREF_NOTIF_HOURS, millis))
        .concatMap(response -> {
          if (response.code() == RESPONSE_TOKEN_EXPIRED) {
            return mAuthorizationManager.checkToken(
                mAuthorizationManager.populateAdditionalField(PREF_NOTIF_HOURS, millis));
          }
          return Observable.just(response);
        })
        .compose(ThreadSchedulers.applySchedulers())
        .subscribe(response -> {
          switch (response.code()) {
            case RESPONSE_200:
              getViewState().setUpHoursString(millis);
              break;
            case RESPONSE_UNAUTHORIZED:
              mAuthorizationManager.getAuthRxBus().post(new AuthRxBusHelper.UnauthorizedEvent());
              break;
          }
        }, throwable -> {
          mAuthorizationManager.setAdditionalField(PREF_NOTIF_HOURS, millis);
          getViewState().setUpHoursString(millis);
        });
    addToUnsubscription(subscription);
  }

  public void setHoursNightStart(long millis) {
    Subscription subscription = mAuthorizationManager.checkToken(
        mAuthorizationManager.populateAdditionalField(PREF_NOTIF_HOURS_NIGHT_START, millis))
        .concatMap(response -> {
          if (response.code() == RESPONSE_TOKEN_EXPIRED) {
            return mAuthorizationManager.checkToken(
                mAuthorizationManager.populateAdditionalField(PREF_NOTIF_HOURS_NIGHT_START,
                    millis));
          }
          return Observable.just(response);
        })
        .compose(ThreadSchedulers.applySchedulers())
        .subscribe(response -> {
          switch (response.code()) {
            case RESPONSE_200:
              getViewState().setUpNightHours(millis, getHoursNightEnd());
              break;
            case RESPONSE_UNAUTHORIZED:
              mAuthorizationManager.getAuthRxBus().post(new AuthRxBusHelper.UnauthorizedEvent());
              break;
          }
        }, throwable -> {
          mAuthorizationManager.setAdditionalField(PREF_NOTIF_HOURS_NIGHT_START, millis);
          getViewState().setUpNightHours(millis, getHoursNightEnd());
        });
    addToUnsubscription(subscription);
  }

  public void setHoursNightEnd(long millis) {
    Subscription subscription = mAuthorizationManager.checkToken(
        mAuthorizationManager.populateAdditionalField(PREF_NOTIF_HOURS_NIGHT_END, millis))
        .concatMap(response -> {
          if (response.code() == RESPONSE_TOKEN_EXPIRED) {
            return mAuthorizationManager.checkToken(
                mAuthorizationManager.populateAdditionalField(PREF_NOTIF_HOURS_NIGHT_END, millis));
          }
          return Observable.just(response);
        })
        .compose(ThreadSchedulers.applySchedulers())
        .subscribe(response -> {
          switch (response.code()) {
            case RESPONSE_200:
              getViewState().setUpNightHours(getHoursNightStart(), millis);
              break;
            case RESPONSE_UNAUTHORIZED:
              mAuthorizationManager.getAuthRxBus().post(new AuthRxBusHelper.UnauthorizedEvent());
              break;
          }
        }, throwable -> {
          mAuthorizationManager.setAdditionalField(PREF_NOTIF_HOURS_NIGHT_END, millis);
          getViewState().setUpNightHours(getHoursNightStart(), millis);
        });
    addToUnsubscription(subscription);
  }

  public int getLastPickedDays() {
    return mLastPickedDays;
  }

  public void setLastPickedDays(int days) {
    mLastPickedDays = days;
  }

  public void saveDays() {
    getViewState().setUpDaysString(mLastPickedDays);
    Subscription subscription = mAuthorizationManager.checkToken(
        mAuthorizationManager.populateAdditionalField(PREF_NOTIF_DAYS, mLastPickedDays))
        .concatMap(response -> {
          if (response.code() == RESPONSE_TOKEN_EXPIRED) {
            return mAuthorizationManager.checkToken(
                mAuthorizationManager.populateAdditionalField(PREF_NOTIF_DAYS, mLastPickedDays));
          }
          return Observable.just(response);
        })
        .compose(ThreadSchedulers.applySchedulers())
        .subscribe(response -> {
          switch (response.code()) {
            case RESPONSE_UNAUTHORIZED:
              mAuthorizationManager.getAuthRxBus().post(new AuthRxBusHelper.UnauthorizedEvent());
              break;
          }
        }, throwable -> {
          mAuthorizationManager.setAdditionalField(PREF_NOTIF_DAYS, mLastPickedDays);
        });
    addToUnsubscription(subscription);
  }

  public void showPickDayDialog() {
    getViewState().showPickDayDialog();
  }

  public void cancelPickDayDialog() {
    getViewState().cancelPickDayDialog();
    mLastPickedDays = getDays();
  }
}
