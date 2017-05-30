package com.authorization.floor12.authorization.di.modules;

import android.content.Context;
import com.authorization.floor12.authorization.data.DataManager;
import com.authorization.floor12.authorization.data.local.PreferencesHelper;
import com.authorization.floor12.authorization.data.model.AuthorizationApi;
import com.authorization.floor12.authorization.data.remote.RestApi;
import com.authorization.floor12.authorization.di.scopes.AppScope;
import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * Created by Vrungel on 26.01.2017.
 */

@Module(includes = { RetrofitModule.class }) public class DataModule {

  @Provides @AppScope AuthorizationApi provideAuthorizationApi(Retrofit retrofit) {
    return retrofit.create(AuthorizationApi.class);
  }

  @Provides @AppScope RestApi provideRestApi(AuthorizationApi api) {
    return new RestApi(api);
  }

  @Provides @AppScope DataManager provideDataManager(RestApi restApi,
      PreferencesHelper preferencesHelper) {
    return new DataManager(restApi, preferencesHelper);
  }

  @Provides @AppScope PreferencesHelper providePreferencesHelper(Context context) {
    return new PreferencesHelper(context);
  }
}
