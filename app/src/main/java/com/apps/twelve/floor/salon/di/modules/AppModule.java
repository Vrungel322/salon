package com.apps.twelve.floor.salon.di.modules;

import android.app.Application;
import android.content.Context;
import com.apps.twelve.floor.salon.base.Navigator;
import com.apps.twelve.floor.salon.di.scopes.AppScope;
import com.apps.twelve.floor.salon.utils.RxBus;
import com.apps.twelve.floor.salon.utils.jobs.JobsCreator;
import com.authorization.floor12.authorization.AuthorizationManager;
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

  @Provides @AppScope RxBus provideRxBus() {
    return new RxBus();
  }

  @Provides @AppScope JobsCreator provideJobsCreator() {
    return new JobsCreator();
  }

  @Provides @AppScope Navigator provideNavigator() {
    return new Navigator();
  }

  @Provides @AppScope AuthorizationManager provideAuthorizationManager() {
    return AuthorizationManager.getInstance();
  }
}
