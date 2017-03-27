package com.apps.twelve.floor.salon;

import android.app.Application;
import com.apps.twelve.floor.salon.di.components.AppComponent;
import com.apps.twelve.floor.salon.di.components.BookingComponent;
import com.apps.twelve.floor.salon.di.components.DaggerAppComponent;
import com.apps.twelve.floor.salon.di.components.DaggerBookingComponent;
import com.apps.twelve.floor.salon.di.modules.AppModule;
import com.apps.twelve.floor.salon.di.modules.BookingModule;
import shortbread.Shortbread;
import timber.log.Timber;

/**
 * Created by Vrungel on 25.01.2017.
 */

public class App extends Application {

  private static AppComponent sAppComponent;
  private static BookingComponent sBookingComponent;

  public static AppComponent getAppComponent() {
    return sAppComponent;
  }

  public static BookingComponent getBookingComponent() {
    return sBookingComponent;
  }

  @Override public void onCreate() {
    super.onCreate();

    Shortbread.create(this);

    if (BuildConfig.DEBUG) {
      Timber.plant(new Timber.DebugTree());
    }

    sAppComponent = DaggerAppComponent.builder().appModule(new AppModule(this)).build();
  }

  public static void initBookingComponent() {
    sBookingComponent = DaggerBookingComponent.builder()
        .bookingModule(new BookingModule())
        .appComponent(sAppComponent)
        .build();
  }

  public static void destroyBookingComponent() {
    sBookingComponent = null;
  }
}
