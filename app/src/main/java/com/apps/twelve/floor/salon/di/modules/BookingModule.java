package com.apps.twelve.floor.salon.di.modules;

import com.apps.twelve.floor.salon.data.local.mappers.BookingToBookingServerEntityMapper;
import com.apps.twelve.floor.salon.data.model.BookingEntity;
import com.apps.twelve.floor.salon.di.scopes.BookingScope;
import dagger.Module;
import dagger.Provides;

/**
 * Created by Vrungel on 24.03.2017.
 */

@Module public class BookingModule {
  @Provides @BookingScope BookingEntity provideBookingEntity() {
    return new BookingEntity("", "", "", "", "", "", "", "", "");
  }

  @Provides @BookingScope BookingToBookingServerEntityMapper provideMApper() {
    return new BookingToBookingServerEntityMapper();
  }
}
