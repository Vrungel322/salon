package com.apps.twelve.floor.salon;

import android.support.multidex.MultiDexApplication;
import com.apps.twelve.floor.salon.di.components.AppComponent;
import com.apps.twelve.floor.salon.di.components.BookingComponent;
import com.apps.twelve.floor.salon.di.components.DaggerAppComponent;
import com.apps.twelve.floor.salon.di.modules.AppModule;
import com.apps.twelve.floor.salon.di.modules.BookingModule;
import com.apps.twelve.floor.salon.utils.Constants;
import com.apps.twelve.floor.salon.utils.jobs.JobsCreator;
import com.arellomobile.mvp.RegisterMoxyReflectorPackages;
import com.authorization.floor12.authorization.AuthorizationManager;
import com.crashlytics.android.Crashlytics;
import com.evernote.android.job.JobManager;
import io.fabric.sdk.android.Fabric;
import shortbread.Shortbread;
import timber.log.Timber;

/**
 * Created by Vrungel on 25.01.2017.
 */

@RegisterMoxyReflectorPackages({ "com.authorization.floor12.authorization" }) public class App
    extends MultiDexApplication {

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
    Fabric.with(this, new Crashlytics());
    Shortbread.create(this);
    AuthorizationManager.init(this, Constants.Remote.BASE_URL);

    if (BuildConfig.DEBUG) {
      Timber.plant(new Timber.DebugTree());
    }

    sAppComponent = DaggerAppComponent.builder().appModule(new AppModule(this)).build();

    //after initialize modules dagger
    JobManager.create(this).addJobCreator(new JobsCreator());
  }

  public static void initBookingComponent() {
    sBookingComponent = sAppComponent.plusBookingComponent(new BookingModule());
  }

  public static void destroyBookingComponent() {
    sBookingComponent = null;
  }
}
