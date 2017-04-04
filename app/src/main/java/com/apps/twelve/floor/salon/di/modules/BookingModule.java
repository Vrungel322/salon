package com.apps.twelve.floor.salon.di.modules;

import com.apps.twelve.floor.salon.di.scopes.BookingScope;
import com.apps.twelve.floor.salon.mvp.data.model.BookingEntity;
import dagger.Module;
import dagger.Provides;

/**
 * Created by Vrungel on 24.03.2017.
 */

@Module public class BookingModule {
  @Provides @BookingScope BookingEntity provideBookingEntity() {
    return new BookingEntity("", "", "", "", "");
  }
}
