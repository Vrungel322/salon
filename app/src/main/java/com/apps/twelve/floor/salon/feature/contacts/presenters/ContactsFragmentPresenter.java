package com.apps.twelve.floor.salon.feature.contacts.presenters;

import com.apps.twelve.floor.salon.App;
import com.apps.twelve.floor.salon.base.BasePresenter;
import com.apps.twelve.floor.salon.feature.contacts.views.IContactsFragmentView;
import com.arellomobile.mvp.InjectViewState;

/**
 * Created by Vrungel on 21.02.2017.
 */

@InjectViewState public class ContactsFragmentPresenter
    extends BasePresenter<IContactsFragmentView> {

  @Override protected void inject() {
    App.getAppComponent().inject(this);
  }
}
