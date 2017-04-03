package com.apps.twelve.floor.salon.di.components;

import com.apps.twelve.floor.salon.di.modules.AppModule;
import com.apps.twelve.floor.salon.di.modules.BookingModule;
import com.apps.twelve.floor.salon.di.scopes.AppScope;
import com.apps.twelve.floor.salon.mvp.presenters.pr_activities.BookingActivityPresenter;
import com.apps.twelve.floor.salon.mvp.presenters.pr_activities.MainActivityPresenter;
import com.apps.twelve.floor.salon.mvp.presenters.pr_activities.RegistrationActivityPresenter;
import com.apps.twelve.floor.salon.mvp.presenters.pr_activities.StartActivityPresenter;
import com.apps.twelve.floor.salon.mvp.presenters.pr_adapters.MyLastBookingAdapterPresenter;
import com.apps.twelve.floor.salon.mvp.presenters.pr_fragments.AllNewsFragmentPresenter;
import com.apps.twelve.floor.salon.mvp.presenters.pr_fragments.BookingContactFragmentPresenter;
import com.apps.twelve.floor.salon.mvp.presenters.pr_fragments.BookingFragmentPresenter;
import com.apps.twelve.floor.salon.mvp.presenters.pr_fragments.ChooseMasterFragmentPresenter;
import com.apps.twelve.floor.salon.mvp.presenters.pr_fragments.ChooseMasterMasterFragmentPresenter;
import com.apps.twelve.floor.salon.mvp.presenters.pr_fragments.ChooseMasterServiceFragmentPresenter;
import com.apps.twelve.floor.salon.mvp.presenters.pr_fragments.ChooseMasterTimeFragmentPresenter;
import com.apps.twelve.floor.salon.mvp.presenters.pr_fragments.ChooseServiceFragmentPresenter;
import com.apps.twelve.floor.salon.mvp.presenters.pr_fragments.ChooseTimeFragmentPresenter;
import com.apps.twelve.floor.salon.mvp.presenters.pr_fragments.ContactsFragmentPresenter;
import com.apps.twelve.floor.salon.mvp.presenters.pr_fragments.DetailNewsFragmentPresenter;
import com.apps.twelve.floor.salon.mvp.presenters.pr_fragments.MainFragmentPresenter;
import com.apps.twelve.floor.salon.mvp.presenters.pr_fragments.MyBonusFragmentPresenter;
import com.apps.twelve.floor.salon.mvp.presenters.pr_fragments.MyBookFragmentPresenter;
import com.apps.twelve.floor.salon.mvp.presenters.pr_fragments.OurWorkFragmentPresenter;
import com.apps.twelve.floor.salon.mvp.presenters.pr_fragments.SubBonusRegistrationFragmentPresenter;
import com.apps.twelve.floor.salon.mvp.presenters.pr_fragments.SubFragmentBookingPresenter;
import com.apps.twelve.floor.salon.mvp.presenters.pr_fragments.SubNewsFragmentPresenter;
import com.apps.twelve.floor.salon.mvp.presenters.pr_fragments.WorkDetailsFragmentPresenter;
import com.apps.twelve.floor.salon.ui.base.BaseActivity;
import com.apps.twelve.floor.salon.ui.base.BaseFragment;
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

  void inject(ChooseTimeFragmentPresenter presenter);

  void inject(ChooseMasterFragmentPresenter presenter);

  void inject(BookingContactFragmentPresenter presenter);

  void inject(ChooseServiceFragmentPresenter presenter);

  void inject(ChooseMasterMasterFragmentPresenter presenter);

  void inject(ChooseMasterTimeFragmentPresenter presenter);

  void inject(ChooseMasterServiceFragmentPresenter presenter);

  //activities
  void inject(BaseActivity activity);

  //fragments
  void inject(BaseFragment fragment);

  //adapters
  void inject(MyLastBookingAdapterPresenter presenter);
}
