package com.apps.twelve.floor.salon.utils;

import android.app.NotificationManager;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.NotificationCompat;
import com.apps.twelve.floor.salon.R;
import com.evernote.android.job.Job;
import timber.log.Timber;

/**
 * Created by Alexandra on 17.05.2017.
 */

public class NotificationJob extends Job {

  public static final String TAG = "job_salon_tag";

  @Override @NonNull protected Result onRunJob(Params params) {
    Timber.e("JOB SUCCESS");

    NotificationCompat.Builder mBuilder =
        new NotificationCompat.Builder(getContext()).setSmallIcon(R.drawable.alerter_ic_face)
            .setContentTitle("My notification")
            .setContentText("Hello World!");
    NotificationManager mNotificationManager =
        (NotificationManager) getContext().getSystemService(Context.NOTIFICATION_SERVICE);
    // mId allows you to update the notification later on.
    mNotificationManager.notify(1, mBuilder.build());

    return Result.SUCCESS;
  }
}