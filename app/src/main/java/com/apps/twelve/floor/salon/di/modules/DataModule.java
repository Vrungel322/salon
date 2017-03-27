package com.apps.twelve.floor.salon.di.modules;

import android.content.Context;
import com.apps.twelve.floor.salon.di.scopes.AppScope;
import com.apps.twelve.floor.salon.mvp.data.DataManager;
import com.apps.twelve.floor.salon.mvp.data.local.PreferencesHelper;
import com.apps.twelve.floor.salon.mvp.data.model.SalonApi;
import com.apps.twelve.floor.salon.mvp.data.remote.RestApi;
import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * Created by Vrungel on 26.01.2017.
 */

@Module(includes = { RetrofitModule.class }) public class DataModule {

  @Provides @AppScope SalonApi provideSalonApi(Retrofit retrofit) {
    return retrofit.create(SalonApi.class);
  }

  @Provides @AppScope RestApi provideRestApi(SalonApi api) {
    return new RestApi(api);
  }

  @Provides @AppScope DataManager provideDataManager(RestApi restApi) {
    return new DataManager(restApi);
  }

  @Provides @AppScope PreferencesHelper providePreferencesHelper(Context context) {
    return new PreferencesHelper(context);
  }
}
