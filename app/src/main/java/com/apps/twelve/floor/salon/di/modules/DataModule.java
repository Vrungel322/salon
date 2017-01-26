package com.apps.twelve.floor.salon.di.modules;

import com.apps.twelve.floor.salon.Constants;
import com.apps.twelve.floor.salon.mvp.data.DataManager;
import com.apps.twelve.floor.salon.mvp.data.model.SalonApi;
import com.apps.twelve.floor.salon.mvp.data.remote.RestApi;
import dagger.Module;
import dagger.Provides;
import java.util.concurrent.TimeUnit;
import javax.inject.Singleton;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Vrungel on 26.01.2017.
 */

@Module public class DataModule {

  @Provides @Singleton public OkHttpClient provideOkClient() {
    HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
    interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

    return new OkHttpClient.Builder().readTimeout(10, TimeUnit.SECONDS)
        .addInterceptor(interceptor)
        .build();
  }

  @Provides @Singleton public Retrofit provideRetrofit(OkHttpClient okClient) {
    return new Retrofit.Builder().baseUrl(Constants.Remote.BASE_URL)
        .client(okClient)
        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .build();
  }

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
