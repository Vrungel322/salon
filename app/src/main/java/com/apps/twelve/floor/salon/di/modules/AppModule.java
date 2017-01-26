package com.apps.twelve.floor.salon.di.modules;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;

/**
 * Created by Vrungel on 25.01.2017.
 */

@Module(includes = { DataModule.class }) public class AppModule {

  private final Application mApplication;

  public AppModule(Application application) {
    mApplication = application;
  }

  @Provides @Singleton Context provideAppContext() {
    return mApplication;
  }

  @Provides @Singleton SharedPreferences provideSharedPreferences(Context mContext) {
    return mContext.getSharedPreferences("com.salon.Salon", Context.MODE_PRIVATE);
  }
}
