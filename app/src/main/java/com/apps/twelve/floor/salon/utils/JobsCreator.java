package com.apps.twelve.floor.salon.utils;

import com.evernote.android.job.Job;
import com.evernote.android.job.JobManager;
import com.evernote.android.job.JobRequest;
import java.util.concurrent.TimeUnit;

/**
 * Created by Alexandra on 17.05.2017.
 */

public class JobsCreator implements com.evernote.android.job.JobCreator {

  @Override public Job create(String tag) {
    return new NotificationJob();
  }

  public void createNotification(String tag) {
    new JobRequest.Builder(tag).setExact(TimeUnit.SECONDS.toMillis(24))
        .setRequiresCharging(false)
        .setRequiredNetworkType(JobRequest.NetworkType.ANY)
        .build()
        .schedule();
    new JobRequest.Builder(tag).setExact(TimeUnit.SECONDS.toMillis(5))
        .setRequiresCharging(false)
        .setRequiredNetworkType(JobRequest.NetworkType.ANY)
        .build()
        .schedule();
  }

  public void cancelJob(String tag) {
    JobManager.instance().cancelAllForTag(tag);
  }
}