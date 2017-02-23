package com.apps.twelve.floor.salon.di.components;

import com.apps.twelve.floor.salon.di.modules.AppModule;
import com.apps.twelve.floor.salon.mvp.presenters.FragmentBookingPresenter;
import com.apps.twelve.floor.salon.mvp.presenters.FragmentContactsPresenter;
import com.apps.twelve.floor.salon.mvp.presenters.FragmentDetailNewsPresenter;
import com.apps.twelve.floor.salon.mvp.presenters.FragmentMainPresenter;
import com.apps.twelve.floor.salon.mvp.presenters.FragmentMyBonusPresenter;
import com.apps.twelve.floor.salon.mvp.presenters.FragmentMyBookPresenter;
import com.apps.twelve.floor.salon.mvp.presenters.FragmentOurWorkPresenter;
import com.apps.twelve.floor.salon.mvp.presenters.FragmentWorkDetailsPresenter;
import com.apps.twelve.floor.salon.mvp.presenters.MainActivityPresenter;
import com.apps.twelve.floor.salon.mvp.presenters.StartActivityPresenter;
import com.apps.twelve.floor.salon.mvp.presenters.SubFragmentNewsPresenter;
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

  void inject(FragmentMainPresenter presenter);

  void inject(FragmentBookingPresenter presenter);

  void inject(FragmentMyBookPresenter presenter);

  void inject(FragmentMyBonusPresenter presenter);

  void inject(FragmentOurWorkPresenter presenter);

  void inject(FragmentWorkDetailsPresenter presenter);

  void inject(FragmentContactsPresenter presenter);

  void inject(SubFragmentNewsPresenter presenter);

  void inject(FragmentDetailNewsPresenter presenter);

  //activities
  void inject(BaseActivity activity);

  //fragments
  void inject(BaseFragment fragment);
}
