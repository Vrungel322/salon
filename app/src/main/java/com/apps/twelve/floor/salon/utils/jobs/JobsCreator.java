package com.apps.twelve.floor.salon.utils.jobs;

import com.evernote.android.job.Job;
import com.evernote.android.job.JobCreator;
import com.evernote.android.job.JobManager;
import com.evernote.android.job.JobRequest;
import com.evernote.android.job.util.support.PersistableBundleCompat;
import java.util.concurrent.TimeUnit;

import static com.apps.twelve.floor.salon.utils.Constants.Notifications.DAILY;
import static com.apps.twelve.floor.salon.utils.Constants.Notifications.HOURLY;
import static com.apps.twelve.floor.salon.utils.Constants.Notifications.NOTIFICATION_TYPE;

/**
 * Created by Alexandra on 17.05.2017.
 */

public class JobsCreator implements JobCreator {

  @Override public Job create(String tag) {
    return new NotificationJob();
  }

  public void createNotification(String tag, Long millis) {
    if (millis - TimeUnit.HOURS.toMillis(1) > 0) {
      createHourly(tag, millis - TimeUnit.HOURS.toMillis(1));
    }
    if (millis - TimeUnit.HOURS.toMillis(24) > 0) {
      createDaily(tag, millis - TimeUnit.HOURS.toMillis(24));
    }
  }

  private void createHourly(String tag, Long millis) {
    PersistableBundleCompat bundle = new PersistableBundleCompat();
    bundle.putString(NOTIFICATION_TYPE, HOURLY);
    new JobRequest.Builder(tag).setExact(millis).setRequiresCharging(false).setExtras(bundle)
        .setRequiredNetworkType(JobRequest.NetworkType.ANY)
        .build()
        .schedule();
  }

  private void createDaily(String tag, Long millis) {
    PersistableBundleCompat bundle = new PersistableBundleCompat();
    bundle.putString(NOTIFICATION_TYPE, DAILY);
    new JobRequest.Builder(tag).setExact(millis).setRequiresCharging(false).setExtras(bundle)
        .setRequiredNetworkType(JobRequest.NetworkType.ANY)
        .build()
        .schedule();
  }

  public void cancelJob(String tag) {
    JobManager.instance().cancelAllForTag(tag);
  }
}