package com.apps.twelve.floor.authorization.di.modules;

import android.app.Application;
import android.content.Context;
import com.apps.twelve.floor.authorization.base.Navigator;
import com.apps.twelve.floor.authorization.di.scopes.AppScope;
import com.apps.twelve.floor.authorization.utils.AuthRxBus;
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

  @Provides @AppScope public Navigator provideNavigator() {
    return new Navigator();
  }

  @Provides @AppScope public AuthRxBus provideRxBus() {
    return new AuthRxBus();
  }
}
