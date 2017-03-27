package com.apps.twelve.floor.salon.di.components;

import android.content.Context;
import com.apps.twelve.floor.salon.di.modules.AppModule;
import com.apps.twelve.floor.salon.di.modules.BookingModule;
import com.apps.twelve.floor.salon.di.scopes.AppScope;
import com.apps.twelve.floor.salon.mvp.data.DataManager;
import com.apps.twelve.floor.salon.mvp.presenters.activities.BookingActivityPresenter;
import com.apps.twelve.floor.salon.mvp.presenters.activities.MainActivityPresenter;
import com.apps.twelve.floor.salon.mvp.presenters.activities.RegistrationActivityPresenter;
import com.apps.twelve.floor.salon.mvp.presenters.activities.StartActivityPresenter;
import com.apps.twelve.floor.salon.mvp.presenters.adapters.MyLastBookingAdapterPresenter;
import com.apps.twelve.floor.salon.mvp.presenters.fragments.AllNewsFragmentPresenter;
import com.apps.twelve.floor.salon.mvp.presenters.fragments.BookingDetailFragmentPresenter;
import com.apps.twelve.floor.salon.mvp.presenters.fragments.BookingFragmentPresenter;
import com.apps.twelve.floor.salon.mvp.presenters.fragments.ContactsFragmentPresenter;
import com.apps.twelve.floor.salon.mvp.presenters.fragments.DetailNewsFragmentPresenter;
import com.apps.twelve.floor.salon.mvp.presenters.fragments.MainFragmentPresenter;
import com.apps.twelve.floor.salon.mvp.presenters.fragments.MyBonusFragmentPresenter;
import com.apps.twelve.floor.salon.mvp.presenters.fragments.MyBookFragmentPresenter;
import com.apps.twelve.floor.salon.mvp.presenters.fragments.OurWorkFragmentPresenter;
import com.apps.twelve.floor.salon.mvp.presenters.fragments.SubBonusRegistrationFragmentPresenter;
import com.apps.twelve.floor.salon.mvp.presenters.fragments.SubFragmentBookingPresenter;
import com.apps.twelve.floor.salon.mvp.presenters.fragments.SubNewsFragmentPresenter;
import com.apps.twelve.floor.salon.mvp.presenters.fragments.WorkDetailsFragmentPresenter;
import com.apps.twelve.floor.salon.ui.base.BaseActivity;
import com.apps.twelve.floor.salon.ui.base.BaseFragment;
import com.apps.twelve.floor.salon.ui.base.Navigator;
import dagger.Component;

/**
 * Created by Vrungel on 25.01.2017.
 */
@AppScope @Component(modules = AppModule.class) public interface AppComponent {

  BookingComponent plusBookingComponent(BookingModule bookingModule);

  //presenters
  void inject(MainActivityPresenter presenter);

  void inject(StartActivityPresenter presenter);

  void inject(RegistrationActivityPresenter presenter);

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

  //activities
  void inject(BaseActivity activity);

  //fragments
  void inject(BaseFragment fragment);

  //adapters
  void inject(MyLastBookingAdapterPresenter presenter);
}
