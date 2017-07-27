package com.apps.twelve.floor.salon.utils.jobs;

import android.content.Context;
import com.apps.twelve.floor.salon.App;
import com.apps.twelve.floor.salon.data.DataManager;
import com.apps.twelve.floor.salon.data.model.LastBookingEntity;
import com.apps.twelve.floor.salon.utils.Converters;
import com.evernote.android.job.Job;
import com.evernote.android.job.JobCreator;
import com.evernote.android.job.JobManager;
import com.evernote.android.job.JobRequest;
import com.evernote.android.job.util.support.PersistableBundleCompat;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.inject.Inject;

import static com.apps.twelve.floor.salon.utils.Constants.Notifications.DAILY;
import static com.apps.twelve.floor.salon.utils.Constants.Notifications.DATE;
import static com.apps.twelve.floor.salon.utils.Constants.Notifications.HOURLY;
import static com.apps.twelve.floor.salon.utils.Constants.Notifications.NOTIFICATION_TYPE;
import static com.apps.twelve.floor.salon.utils.Constants.Notifications.SERVICE;
import static com.apps.twelve.floor.salon.utils.Constants.Notifications.TIME;

/**
 * Created by Alexandra on 17.05.2017.
 */

public class JobsCreator implements JobCreator {

  @Inject DataManager mDataManager;
  @Inject Context mContext;

  public JobsCreator() {
    App.getAppComponent().inject(this);
  }

  @Override public Job create(String tag) {
    return new NotificationJob();
  }

  public void createNotification(String tag, Long millis, String service, String date,
      String time) {
    if (millis - mDataManager.getNotificationHours() > 0) {
      createHourly(tag, millis - mDataManager.getNotificationHours(), service, time);
    }
    if (millis - TimeUnit.DAYS.toMillis(mDataManager.getNotificationDays()) > 0) {
      createDaily(tag, millis - TimeUnit.DAYS.toMillis(mDataManager.getNotificationDays()), service,
          date, time);
    }
  }

  private void createHourly(String tag, Long millis, String service, String time) {
    PersistableBundleCompat bundle = new PersistableBundleCompat();
    bundle.putString(NOTIFICATION_TYPE, HOURLY);
    bundle.putString(SERVICE, service);
    bundle.putString(TIME, time);
    new JobRequest.Builder(tag).setExact(millis)
        .setRequiresCharging(false)
        .setExtras(bundle)
        .setRequiredNetworkType(JobRequest.NetworkType.ANY)
        .build()
        .schedule();
  }

  private void createDaily(String tag, Long millis, String service, String date, String time) {
    PersistableBundleCompat bundle = new PersistableBundleCompat();
    bundle.putString(NOTIFICATION_TYPE, DAILY);
    bundle.putString(SERVICE, service);
    bundle.putString(DATE, date);
    bundle.putString(TIME, time);
    new JobRequest.Builder(tag).setExact(millis)
        .setRequiresCharging(false)
        .setExtras(bundle)
        .setRequiredNetworkType(JobRequest.NetworkType.ANY)
        .build()
        .schedule();
  }

  public void updateNotifications() {
    List<LastBookingEntity> bookingEntities = mDataManager.getBooking();
    for (LastBookingEntity bookingEntity : bookingEntities) {
      cancelJob(String.valueOf(bookingEntity.getId()));
      createNotification(String.valueOf(bookingEntity.getId()),
          bookingEntity.getServiceTime() * 1000L - System.currentTimeMillis(),
          bookingEntity.getServiceName(),
          Converters.detailDayFromSeconds(mContext, String.valueOf(bookingEntity.getServiceTime())),
          Converters.timeFromSeconds(mContext, String.valueOf(bookingEntity.getServiceTime())));
    }
  }

  public void cancelJob(String tag) {
    JobManager.instance().cancelAllForTag(tag);
  }
}