package com.apps.twelve.floor.salon.utils.jobs;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.content.ContextCompat;
import com.apps.twelve.floor.salon.App;
import com.apps.twelve.floor.salon.R;
import com.apps.twelve.floor.salon.data.DataManager;
import com.apps.twelve.floor.salon.feature.start_point.activities.StartActivity;
import com.evernote.android.job.Job;
import java.util.Random;
import javax.inject.Inject;

import static com.apps.twelve.floor.salon.utils.Constants.Notifications.DAILY;
import static com.apps.twelve.floor.salon.utils.Constants.Notifications.HOURLY;
import static com.apps.twelve.floor.salon.utils.Constants.Notifications.NOTIFICATION_TYPE;

/**
 * Created by Alexandra on 17.05.2017.
 */

public class NotificationJob extends Job {

  @Inject DataManager mDataManager;

  public NotificationJob() {
    App.getAppComponent().inject(this);
  }

  @Override @NonNull protected Result onRunJob(Params params) {

    boolean showNotification = true;
    switch (params.getExtras().getString(NOTIFICATION_TYPE, "")) {
      case HOURLY:
        showNotification = mDataManager.isHourlyNotificationsEnabled();
        break;
      case DAILY:
        showNotification = mDataManager.isDailyNotificationsEnabled();
        break;
    }

    if (showNotification) {

      StringBuilder message =
          new StringBuilder(getContext().getResources().getString(R.string.notification_text));
      message.append(" ");
      switch (params.getExtras().getString(NOTIFICATION_TYPE, "")) {
        case HOURLY:
          message.append(getContext().getResources().getString(R.string.notification_hourly));
          break;
        case DAILY:
          message.append(getContext().getResources().getString(R.string.notification_daily));
          break;
      }

      PendingIntent pendingIntent = PendingIntent.getActivity(getContext(), 0,
          new Intent(getContext(), StartActivity.class).setAction(
              Long.toString(System.currentTimeMillis())), PendingIntent.FLAG_UPDATE_CURRENT);

      Uri uriSound = Uri.parse(
          "android.resource://" + getContext().getPackageName() + "/" + R.raw.sound_notification);

      Notification notification = new NotificationCompat.Builder(getContext()).setContentTitle(
          getContext().getResources().getString(R.string.notification_title))
          .setContentText(message.toString())
          .setAutoCancel(true)
          .setContentIntent(pendingIntent)
          .setSmallIcon(R.drawable.ic_create_booking)
          .setShowWhen(true)
          .setSound(uriSound)
          .setColor(ContextCompat.getColor(getContext(), R.color.colorNotifications))
          .setLocalOnly(true)
          .build();

      NotificationManagerCompat.from(getContext()).notify(new Random().nextInt(), notification);
    }
    return Result.SUCCESS;
  }
}