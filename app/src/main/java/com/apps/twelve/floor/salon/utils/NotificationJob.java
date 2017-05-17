package com.apps.twelve.floor.salon.utils;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import com.apps.twelve.floor.salon.R;
import com.apps.twelve.floor.salon.feature.start_point.activities.MainActivity;
import com.evernote.android.job.Job;
import java.util.Random;

import static com.apps.twelve.floor.salon.utils.Constants.Notifications.DAILY;
import static com.apps.twelve.floor.salon.utils.Constants.Notifications.HOURLY;
import static com.apps.twelve.floor.salon.utils.Constants.Notifications.NOTIFICATION_TYPE;

/**
 * Created by Alexandra on 17.05.2017.
 */

public class NotificationJob extends Job {

  @Override @NonNull protected Result onRunJob(Params params) {
    PendingIntent pendingIntent =
        PendingIntent.getActivity(getContext(), 0, new Intent(getContext(), MainActivity.class), 0);

    String message = "Приходите в салон через ";
    switch (params.getExtras().getString(NOTIFICATION_TYPE, "hz")) {
      case HOURLY:
        message += "час";
        break;
      case DAILY:
        message += "день";
      default:
        break;
    }

    Notification notification =
        new NotificationCompat.Builder(getContext()).setContentTitle("Notification")
            .setContentText(message)
            .setAutoCancel(true)
            .setContentIntent(pendingIntent)
            .setSmallIcon(R.drawable.alerter_ic_face)
            .setShowWhen(true)
            .setColor(Color.GREEN)
            .setLocalOnly(true)
            .build();

    NotificationManagerCompat.from(getContext()).notify(new Random().nextInt(), notification);

    return Result.SUCCESS;
  }
}