package com.apps.twelve.floor.salon.di.modules;

import android.app.Application;
import android.content.Context;
import com.apps.twelve.floor.salon.base.Navigator;
import com.apps.twelve.floor.salon.di.scopes.AppScope;
import com.apps.twelve.floor.salon.utils.JobsCreator;
import com.apps.twelve.floor.salon.utils.RxBus;
import dagger.Module;
import dagger.Provides;

/**
 * Created by Vrungel on 25.01.2017.
 */

@Module(includes = { DataModule.class }) public class AppModule {

  private final Application mApplication;

  public AppModule(Application application) {
    mApplication = application;
  }

  @Provides @AppScope Context provideAppContext() {
    return mApplication;
  }

  @Provides @AppScope public RxBus provideRxBus() {
    return new RxBus();
  }

  @Provides @AppScope public JobsCreator provideJobsCreator() {
    return new JobsCreator();
  }

  @Provides @AppScope public Navigator provideNavigator() {
    return new Navigator();
  }
}
