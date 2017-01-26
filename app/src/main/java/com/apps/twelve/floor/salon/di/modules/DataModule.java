package com.apps.twelve.floor.salon.di.modules;

import com.apps.twelve.floor.salon.mvp.data.DataManager;
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

  @Provides @Singleton public SalonApi provideSalonApi(Retrofit retrofit) {
    return retrofit.create(SalonApi.class);
  }

  @Provides @Singleton public RestApi provideRestApi(SalonApi api) {
    return new RestApi(api);
  }

  @Provides @Singleton public DataManager provideDataManager(RestApi restApi) {
    return new DataManager(restApi);
  }
}
