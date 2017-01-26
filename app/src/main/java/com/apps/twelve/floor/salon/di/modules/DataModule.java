package com.apps.twelve.floor.salon.di.modules;

import android.content.Context;
import com.apps.twelve.floor.salon.mvp.data.DataManager;
import com.apps.twelve.floor.salon.mvp.data.local.PreferencesHelper;
import com.apps.twelve.floor.salon.mvp.data.model.SalonApi;
import com.apps.twelve.floor.salon.mvp.data.remote.RestApi;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;
import retrofit2.Retrofit;

/**
 * Created by Vrungel on 26.01.2017.
 */

@Module(includes = { RetrofitModule.class }) public class DataModule {

  @Provides @Singleton SalonApi provideSalonApi(Retrofit retrofit) {
    return retrofit.create(SalonApi.class);
  }

  @Provides @Singleton RestApi provideRestApi(SalonApi api) {
    return new RestApi(api);
  }

  @Provides @Singleton DataManager provideDataManager(RestApi restApi) {
    return new DataManager(restApi);
  }

  @Provides @Singleton PreferencesHelper providePreferencesHelper(Context context) {
    return new PreferencesHelper(context);
  }
}
