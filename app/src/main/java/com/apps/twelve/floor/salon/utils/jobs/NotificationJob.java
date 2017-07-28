package com.apps.twelve.floor.salon.utils.jobs;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.app.TaskStackBuilder;
import com.apps.twelve.floor.authorization.AuthorizationManager;
import com.apps.twelve.floor.salon.App;
import com.apps.twelve.floor.salon.R;
import com.apps.twelve.floor.salon.data.DataManager;
import com.apps.twelve.floor.salon.data.model.LastBookingEntity;
import com.apps.twelve.floor.salon.feature.my_booking.activities.BookingDetailActivity;
import com.evernote.android.job.Job;
import java.util.List;
import java.util.Random;
import javax.inject.Inject;

import static com.apps.twelve.floor.salon.utils.Constants.Notifications.DAILY;
import static com.apps.twelve.floor.salon.utils.Constants.Notifications.DATE;
import static com.apps.twelve.floor.salon.utils.Constants.Notifications.HOURLY;
import static com.apps.twelve.floor.salon.utils.Constants.Notifications.LAST_BOOKING_ENTITY;
import static com.apps.twelve.floor.salon.utils.Constants.Notifications.LAST_BOOKING_ENTITY_ID;
import static com.apps.twelve.floor.salon.utils.Constants.Notifications.NOTIFICATION_TYPE;
import static com.apps.twelve.floor.salon.utils.Constants.Notifications.SERVICE;
import static com.apps.twelve.floor.salon.utils.Constants.Notifications.TIME;

/**
 * Created by Alexandra on 17.05.2017.
 */

public class NotificationJob extends Job {

  @Inject DataManager mDataManager;
  @Inject AuthorizationManager mAuthManager;

  public NotificationJob() {
    App.getAppComponent().inject(this);
  }

  @Override @NonNull protected Result onRunJob(Params params) {

    boolean showNotification = mAuthManager.isAuthorized();
    if (showNotification) {
      switch (params.getExtras().getString(NOTIFICATION_TYPE, "")) {
        case HOURLY:
          showNotification = mDataManager.isHourlyNotificationsEnabled();
          break;
        case DAILY:
          showNotification = mDataManager.isDailyNotificationsEnabled();
          break;
      }
    }

    if (showNotification) {

      String message = "";
      switch (params.getExtras().getString(NOTIFICATION_TYPE, "")) {
        case HOURLY:
          message = getContext().getString(R.string.notification_text,
              params.getExtras().getString(SERVICE, ""),
              getContext().getString(R.string.notification_text_today),
              params.getExtras().getString(TIME, ""));
          break;
        case DAILY:
          message = getContext().getString(R.string.notification_text,
              params.getExtras().getString(SERVICE, ""), params.getExtras().getString(DATE, ""),
              params.getExtras().getString(TIME, ""));
          break;
      }

      int lastBookingEntityId = params.getExtras().getInt(LAST_BOOKING_ENTITY_ID, -1);
      List<LastBookingEntity> bookingEntities = mDataManager.getBooking();

      LastBookingEntity lastBookingEntity = null;
      for (LastBookingEntity bookingEntity : bookingEntities) {
        if (lastBookingEntityId == bookingEntity.getId()) {
          lastBookingEntity = bookingEntity;
        }
      }

      TaskStackBuilder stackBuilder = TaskStackBuilder.create(getContext());
      Intent intent = new Intent(getContext(), BookingDetailActivity.class);
      intent.putExtra(LAST_BOOKING_ENTITY, lastBookingEntity);
      stackBuilder.addNextIntentWithParentStack(intent);

      PendingIntent pendingIntent =
          stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);

      NotificationCompat.Builder builder =
          new NotificationCompat.Builder(getContext()).setContentTitle(
              getContext().getResources().getString(R.string.notification_title))
              .setContentText(message)
              .setAutoCancel(true)
              .setContentIntent(pendingIntent)
              .setSmallIcon(R.drawable.ic_create_booking)
              .setShowWhen(true)
              .setLocalOnly(true);

      long currentHour = System.currentTimeMillis();

      if ((currentHour < mDataManager.getNotificationHoursNightStart() && currentHour > mDataManager
          .getNotificationHoursNightEnd()) || !mDataManager.isNightMode()) {
        Uri uriSound = Uri.parse(
            "android.resource://" + getContext().getPackageName() + "/" + R.raw.sound_notification);
        builder.setSound(uriSound);
      }

      Notification notification = builder.build();

      NotificationManagerCompat.from(getContext()).notify(new Random().nextInt(), notification);
    }
    return Result.SUCCESS;
  }
}