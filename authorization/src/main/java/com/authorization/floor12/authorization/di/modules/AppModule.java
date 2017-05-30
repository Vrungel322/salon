package com.authorization.floor12.authorization.di.modules;

import android.app.Application;
import android.content.Context;
import com.authorization.floor12.authorization.base.Navigator;
import com.authorization.floor12.authorization.di.scopes.AppScope;
import com.authorization.floor12.authorization.utils.RxBus;
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

  @Provides @AppScope public RxBus provideRxBus() {
    return new RxBus();
  }
}
