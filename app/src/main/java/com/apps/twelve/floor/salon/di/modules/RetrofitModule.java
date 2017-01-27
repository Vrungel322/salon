package com.apps.twelve.floor.salon.di.modules;

import com.apps.twelve.floor.salon.utils.Constants;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dagger.Module;
import dagger.Provides;
import java.util.concurrent.TimeUnit;
import javax.inject.Singleton;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import timber.log.Timber;

/**
 * Created by John on 26.01.2017.
 */
@Module public class RetrofitModule {

  @Provides @Singleton Retrofit provideRetrofit(Converter.Factory converterFactory,
      OkHttpClient okClient) {
    return new Retrofit.Builder().baseUrl(Constants.Remote.BASE_URL)
        .client(okClient)
        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
        .addConverterFactory(converterFactory)
        .build();
  }

  @Provides @Singleton Converter.Factory provideConverterFactory(Gson gson) {
    return GsonConverterFactory.create(gson);
  }

  @Provides @Singleton Gson provideGson() {
    return new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
        .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
        .serializeNulls()
        .create();
  }

  @Provides @Singleton OkHttpClient provideOkClient() {
    HttpLoggingInterceptor interceptor =
        new HttpLoggingInterceptor(message -> Timber.tag("response").d(message));
    interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

    return new OkHttpClient.Builder().readTimeout(10, TimeUnit.SECONDS)
        .addInterceptor(interceptor)
        .build();
  }
}
