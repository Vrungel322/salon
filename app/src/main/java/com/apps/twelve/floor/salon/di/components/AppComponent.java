package com.apps.twelve.floor.salon.di.components;

import com.apps.twelve.floor.salon.base.BaseActivity;
import com.apps.twelve.floor.salon.base.BaseFragment;
import com.apps.twelve.floor.salon.di.modules.AppModule;
import com.apps.twelve.floor.salon.di.modules.BookingModule;
import com.apps.twelve.floor.salon.di.scopes.AppScope;
import com.apps.twelve.floor.salon.feature.booking.presenters.BookingActivityPresenter;
import com.apps.twelve.floor.salon.feature.booking.presenters.BookingFragmentPresenter;
import com.apps.twelve.floor.salon.feature.contacts.presenters.ContactsFragmentPresenter;
import com.apps.twelve.floor.salon.feature.main_screen.presenters.MainFragmentPresenter;
import com.apps.twelve.floor.salon.feature.main_screen.presenters.MyLastBookingAdapterPresenter;
import com.apps.twelve.floor.salon.feature.main_screen.presenters.SubBonusRegistrationFragmentPresenter;
import com.apps.twelve.floor.salon.feature.main_screen.presenters.SubFragmentBookingPresenter;
import com.apps.twelve.floor.salon.feature.main_screen.presenters.SubNewsFragmentPresenter;
import com.apps.twelve.floor.salon.feature.my_bonus.presenters.MyBonusFragmentPresenter;
import com.apps.twelve.floor.salon.feature.my_booking.presenters.MyBookFragmentPresenter;
import com.apps.twelve.floor.salon.feature.my_booking.presenters.PostponeFragmentPresenter;
import com.apps.twelve.floor.salon.feature.news.presenters.AllNewsFragmentPresenter;
import com.apps.twelve.floor.salon.feature.news.presenters.DetailNewsFragmentPresenter;
import com.apps.twelve.floor.salon.feature.our_works.presenters.OurWorkFragmentPresenter;
import com.apps.twelve.floor.salon.feature.our_works.presenters.WorkDetailsFragmentPresenter;
import com.apps.twelve.floor.salon.feature.settings.presenters.SettingsActivityPresenter;
import com.apps.twelve.floor.salon.feature.start_point.presenters.MainActivityPresenter;
import com.apps.twelve.floor.salon.feature.start_point.presenters.StartActivityPresenter;
import dagger.Component;

/**
 * Created by Vrungel on 25.01.2017.
 */
@AppScope @Component(modules = AppModule.class) public interface AppComponent {

  BookingComponent plusBookingComponent(BookingModule bookingModule);

  //presenters
  void inject(MainActivityPresenter presenter);

  void inject(StartActivityPresenter presenter);

  void inject(BookingActivityPresenter presenter);

  void inject(MainFragmentPresenter presenter);

  void inject(BookingFragmentPresenter presenter);

  void inject(MyBookFragmentPresenter presenter);

  void inject(MyBonusFragmentPresenter presenter);

  void inject(OurWorkFragmentPresenter presenter);

  void inject(WorkDetailsFragmentPresenter presenter);

  void inject(ContactsFragmentPresenter presenter);

  void inject(SubNewsFragmentPresenter presenter);

  void inject(DetailNewsFragmentPresenter presenter);

  void inject(AllNewsFragmentPresenter presenter);

  void inject(SubBonusRegistrationFragmentPresenter presenter);

  void inject(SubFragmentBookingPresenter presenter);

  void inject(SettingsActivityPresenter presenter);

  void inject(PostponeFragmentPresenter presenter);

  //activities
  void inject(BaseActivity activity);

  //fragments
  void inject(BaseFragment fragment);

  //adapters
  void inject(MyLastBookingAdapterPresenter presenter);
}
