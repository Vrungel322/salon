package com.apps.twelve.floor.salon.di.components;

import com.apps.twelve.floor.salon.di.modules.AppModule;
import com.apps.twelve.floor.salon.mvp.presenters.activities.MainActivityPresenter;
import com.apps.twelve.floor.salon.mvp.presenters.activities.RegistrationActivityPresenter;
import com.apps.twelve.floor.salon.mvp.presenters.activities.StartActivityPresenter;
import com.apps.twelve.floor.salon.mvp.presenters.fragments.AllNewsPresenter;
import com.apps.twelve.floor.salon.mvp.presenters.fragments.BookingPresenter;
import com.apps.twelve.floor.salon.mvp.presenters.fragments.ContactsPresenter;
import com.apps.twelve.floor.salon.mvp.presenters.fragments.DetailNewsPresenter;
import com.apps.twelve.floor.salon.mvp.presenters.fragments.MainPresenter;
import com.apps.twelve.floor.salon.mvp.presenters.fragments.MyBonusPresenter;
import com.apps.twelve.floor.salon.mvp.presenters.fragments.MyBookPresenter;
import com.apps.twelve.floor.salon.mvp.presenters.fragments.OurWorkPresenter;
import com.apps.twelve.floor.salon.mvp.presenters.fragments.SubBonusRegistrationPresenter;
import com.apps.twelve.floor.salon.mvp.presenters.fragments.SubNewsPresenter;
import com.apps.twelve.floor.salon.mvp.presenters.fragments.WorkDetailsPresenter;
import com.apps.twelve.floor.salon.ui.base.BaseActivity;
import com.apps.twelve.floor.salon.ui.base.BaseFragment;
import dagger.Component;
import javax.inject.Singleton;

/**
 * Created by Vrungel on 25.01.2017.
 */
@Singleton @Component(modules = AppModule.class) public interface AppComponent {

  //presenters
  void inject(MainActivityPresenter presenter);

  void inject(StartActivityPresenter presenter);

  void inject(RegistrationActivityPresenter presenter);

  void inject(MainPresenter presenter);

  void inject(BookingPresenter presenter);

  void inject(MyBookPresenter presenter);

  void inject(MyBonusPresenter presenter);

  void inject(OurWorkPresenter presenter);

  void inject(WorkDetailsPresenter presenter);

  void inject(ContactsPresenter presenter);

  void inject(SubNewsPresenter presenter);

  void inject(DetailNewsPresenter presenter);

  void inject(AllNewsPresenter presenter);

  void inject(SubBonusRegistrationPresenter presenter);

  //activities
  void inject(BaseActivity activity);

  //fragments
  void inject(BaseFragment fragment);
}
