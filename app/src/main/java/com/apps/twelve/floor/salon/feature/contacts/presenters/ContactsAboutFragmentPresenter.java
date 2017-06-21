package com.apps.twelve.floor.salon.feature.contacts.presenters;

import com.apps.twelve.floor.salon.App;
import com.apps.twelve.floor.salon.base.BasePresenter;
import com.apps.twelve.floor.salon.feature.contacts.views.IContactsAboutFragmentView;
import com.arellomobile.mvp.InjectViewState;

/**
 * Created by Alexandra on 21.06.2017.
 */

@InjectViewState public class ContactsAboutFragmentPresenter
    extends BasePresenter<IContactsAboutFragmentView> {

  @Override protected void inject() {
    App.getAppComponent().inject(this);
  }
}
