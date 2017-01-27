package com.apps.twelve.floor.salon;

import android.app.Application;
import com.apps.twelve.floor.salon.di.components.AppComponent;
import com.apps.twelve.floor.salon.di.components.DaggerAppComponent;
import com.apps.twelve.floor.salon.di.modules.AppModule;
import timber.log.Timber;

/**
 * Created by Vrungel on 25.01.2017.
 */

public class App extends Application {

  private static AppComponent sAppComponent;

  public static AppComponent getsAppComponent() {
    return sAppComponent;
  }

  @Override public void onCreate() {
    super.onCreate();

    if (BuildConfig.DEBUG) {
      Timber.plant(new Timber.DebugTree());
    }

    sAppComponent = DaggerAppComponent.builder().appModule(new AppModule(this)).build();
  }
}
