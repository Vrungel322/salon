package com.apps.twelve.floor.salon.di.components;

import com.apps.twelve.floor.salon.di.modules.BookingModule;
import com.apps.twelve.floor.salon.di.scopes.BookingScope;
import com.apps.twelve.floor.salon.feature.booking.mode.booking_master.presenters.BookingDetailMasterFragmentPresenter;
import com.apps.twelve.floor.salon.feature.booking.mode.booking_master.presenters.ChooseMasterContactFragmentPresenter;
import com.apps.twelve.floor.salon.feature.booking.mode.booking_master.presenters.ChooseMasterMasterFragmentPresenter;
import com.apps.twelve.floor.salon.feature.booking.mode.booking_master.presenters.ChooseMasterServiceFragmentPresenter;
import com.apps.twelve.floor.salon.feature.booking.mode.booking_master.presenters.ChooseMasterTimeFragmentPresenter;
import com.apps.twelve.floor.salon.feature.booking.mode.booking_service.presenters.BookingDetailServiceFragmentPresenter;
import com.apps.twelve.floor.salon.feature.booking.mode.booking_service.presenters.ChooseServiceContactFragmentPresenter;
import com.apps.twelve.floor.salon.feature.booking.mode.booking_service.presenters.ChooseServiceMasterFragmentPresenter;
import com.apps.twelve.floor.salon.feature.booking.mode.booking_service.presenters.ChooseServiceServiceFragmentPresenter;
import com.apps.twelve.floor.salon.feature.booking.mode.booking_service.presenters.ChooseServiceTimeFragmentPresenter;
import com.apps.twelve.floor.salon.feature.booking.presenters.BookingDetailFragmentPresenter;
import dagger.Subcomponent;

/**
 * Created by Vrungel on 24.03.2017.
 */

@BookingScope @Subcomponent(modules = BookingModule.class) public interface BookingComponent {

  void inject(BookingDetailFragmentPresenter presenter);

  void inject(ChooseServiceServiceFragmentPresenter presenter);

  void inject(ChooseServiceTimeFragmentPresenter presenter);

  void inject(ChooseServiceMasterFragmentPresenter presenter);

  void inject(ChooseServiceContactFragmentPresenter presenter);

  void inject(ChooseMasterMasterFragmentPresenter presenter);

  void inject(ChooseMasterServiceFragmentPresenter presenter);

  void inject(ChooseMasterTimeFragmentPresenter presenter);

  void inject(ChooseMasterContactFragmentPresenter presenter);

  void inject(BookingDetailMasterFragmentPresenter presenter);

  void inject(BookingDetailServiceFragmentPresenter presenter);

}
