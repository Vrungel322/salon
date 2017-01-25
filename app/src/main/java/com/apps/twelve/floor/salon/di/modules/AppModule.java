package com.apps.twelve.floor.salon.di.modules;

import android.app.Application;
import android.content.Context;
import com.apps.twelve.floor.salon.App;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;

/**
 * Created by Vrungel on 25.01.2017.
 */

@Module public class AppModule {

  private final App mApplication;

  public AppModule(Context application) {
    mApplication = (App) application;
  }

  @Provides @Singleton Context provideAppContext() {
    return mApplication;
  }
}
