package com.apps.twelve.floor.salon.di.modules;

import android.content.Context;
import com.apps.twelve.floor.authorization.AuthorizationManager;
import com.apps.twelve.floor.salon.data.DataManager;
import com.apps.twelve.floor.salon.data.local.DbHelper;
import com.apps.twelve.floor.salon.data.local.PreferencesHelper;
import com.apps.twelve.floor.salon.data.model.SalonApi;
import com.apps.twelve.floor.salon.data.remote.RestApi;
import com.apps.twelve.floor.salon.di.scopes.AppScope;
import com.google.gson.Gson;
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

  @Provides @AppScope DbHelper provideDbHelper() {
    return new DbHelper();
  }

  @Provides @AppScope DataManager provideDataManager(RestApi restApi,
      PreferencesHelper preferencesHelper, AuthorizationManager authorizationManager,
      DbHelper dbHelper) {
    return new DataManager(restApi, preferencesHelper, authorizationManager, dbHelper);
  }

  @Provides @AppScope PreferencesHelper providePreferencesHelper(Context context, Gson gson) {
    return new PreferencesHelper(context, gson);
  }
}
