package com.apps.twelve.floor.salon;

import android.content.Context;
import android.content.res.Configuration;
import android.support.multidex.MultiDexApplication;
import com.apps.twelve.floor.authorization.AuthorizationManager;
import com.apps.twelve.floor.salon.data.local.LocaleHelper;
import com.apps.twelve.floor.salon.di.components.AppComponent;
import com.apps.twelve.floor.salon.di.components.BookingComponent;
import com.apps.twelve.floor.salon.di.components.DaggerAppComponent;
import com.apps.twelve.floor.salon.di.modules.AppModule;
import com.apps.twelve.floor.salon.di.modules.BookingModule;
import com.apps.twelve.floor.salon.utils.Constants;
import com.apps.twelve.floor.salon.utils.jobs.JobsCreator;
import com.arellomobile.mvp.RegisterMoxyReflectorPackages;
import com.crashlytics.android.Crashlytics;
import com.evernote.android.job.JobManager;
import io.fabric.sdk.android.Fabric;
import io.realm.Realm;
import shortbread.Shortbread;
import timber.log.Timber;

import static com.apps.twelve.floor.salon.utils.Constants.Language.RU;

/**
 * Created by Vrungel on 25.01.2017.
 */

@RegisterMoxyReflectorPackages({ "com.apps.twelve.floor.authorization" }) public class App
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
    Shortbread.create(this);
    AuthorizationManager.init(this, Constants.Remote.BASE_URL);
    Realm.init(this);

    if (BuildConfig.DEBUG) {
      Timber.plant(new Timber.DebugTree());
    } else {
      Fabric.with(this, new Crashlytics());
    }

    sAppComponent = DaggerAppComponent.builder().appModule(new AppModule(this)).build();

    //after initialize modules dagger
    JobManager.create(this).addJobCreator(new JobsCreator());
  }

  @Override protected void attachBaseContext(Context base) {
    super.attachBaseContext(LocaleHelper.onAttach(base, RU));
  }

  @Override public void onConfigurationChanged(Configuration newConfig) {
    super.onConfigurationChanged(newConfig);
    LocaleHelper.onAttach(getBaseContext());
    //update locale and resources, configuration on each config change
  }

  public static void initBookingComponent() {
    sBookingComponent = sAppComponent.plusBookingComponent(new BookingModule());
  }

  public static void destroyBookingComponent() {
    sBookingComponent = null;
  }
}
