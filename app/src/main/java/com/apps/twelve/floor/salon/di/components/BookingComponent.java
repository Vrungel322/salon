package com.apps.twelve.floor.salon.di.components;

import com.apps.twelve.floor.salon.di.modules.BookingModule;
import com.apps.twelve.floor.salon.di.scopes.BookingScope;
import com.apps.twelve.floor.salon.mvp.presenters.pr_fragments.BookingContactFragmentPresenter;
import com.apps.twelve.floor.salon.mvp.presenters.pr_fragments.BookingDetailFragmentPresenter;
import com.apps.twelve.floor.salon.mvp.presenters.pr_fragments.BookingMasterContactFragmentPresenter;
import com.apps.twelve.floor.salon.mvp.presenters.pr_fragments.ChooseMasterFragmentPresenter;
import com.apps.twelve.floor.salon.mvp.presenters.pr_fragments.ChooseMasterMasterFragmentPresenter;
import com.apps.twelve.floor.salon.mvp.presenters.pr_fragments.ChooseMasterServiceFragmentPresenter;
import com.apps.twelve.floor.salon.mvp.presenters.pr_fragments.ChooseMasterTimeFragmentPresenter;
import com.apps.twelve.floor.salon.mvp.presenters.pr_fragments.ChooseServiceFragmentPresenter;
import com.apps.twelve.floor.salon.mvp.presenters.pr_fragments.ChooseTimeFragmentPresenter;
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
