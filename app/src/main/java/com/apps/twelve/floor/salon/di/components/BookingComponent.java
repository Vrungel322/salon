package com.apps.twelve.floor.salon.di.components;

import com.apps.twelve.floor.salon.di.modules.BookingModule;
import com.apps.twelve.floor.salon.di.scopes.BookingScope;
import com.apps.twelve.floor.salon.mvp.presenters.fragments.BookingDetailFragmentPresenter;
import dagger.Component;

/**
 * Created by Vrungel on 24.03.2017.
 */

@BookingScope @Component(dependencies = AppComponent.class, modules = BookingModule.class)
public interface BookingComponent {

  void inject(BookingDetailFragmentPresenter presenter);
}
