package com.apps.twelve.floor.salon;

import dagger.Component;

/**
 * Created by Vrungel on 25.01.2017.
 */

@Component(modules = { AppModule.class }) public interface AppComponent {

  void inject(MainActivityPresenter presenter);
}
