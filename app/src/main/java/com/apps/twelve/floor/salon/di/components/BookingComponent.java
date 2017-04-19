package com.apps.twelve.floor.salon.di.components;

import com.apps.twelve.floor.salon.di.modules.BookingModule;
import com.apps.twelve.floor.salon.di.scopes.BookingScope;
import com.apps.twelve.floor.salon.feature.booking.mode.booking_master.presenters.BookingMasterContactFragmentPresenter;
import com.apps.twelve.floor.salon.feature.booking.mode.booking_master.presenters.ChooseMasterMasterFragmentPresenter;
import com.apps.twelve.floor.salon.feature.booking.mode.booking_master.presenters.ChooseMasterServiceFragmentPresenter;
import com.apps.twelve.floor.salon.feature.booking.mode.booking_master.presenters.ChooseMasterTimeFragmentPresenter;
import com.apps.twelve.floor.salon.feature.booking.mode.booking_service.presenters.BookingContactFragmentPresenter;
import com.apps.twelve.floor.salon.feature.booking.mode.booking_service.presenters.ChooseMasterFragmentPresenter;
import com.apps.twelve.floor.salon.feature.booking.mode.booking_service.presenters.ChooseServiceFragmentPresenter;
import com.apps.twelve.floor.salon.feature.booking.mode.booking_service.presenters.ChooseTimeFragmentPresenter;
import com.apps.twelve.floor.salon.feature.booking.presenters.BookingDetailFragmentPresenter;
import dagger.Subcomponent;

/**
 * Created by Vrungel on 24.03.2017.
 */

@BookingScope @Subcomponent(modules = BookingModule.class) public interface BookingComponent {

  void inject(BookingDetailFragmentPresenter presenter);

  void inject(ChooseServiceFragmentPresenter presenter);

  void inject(ChooseTimeFragmentPresenter presenter);

  void inject(ChooseMasterFragmentPresenter presenter);

  void inject(BookingContactFragmentPresenter presenter);

  void inject(ChooseMasterMasterFragmentPresenter presenter);

  void inject(ChooseMasterServiceFragmentPresenter presenter);

  void inject(ChooseMasterTimeFragmentPresenter presenter);

  void inject(BookingMasterContactFragmentPresenter presenter);
}
