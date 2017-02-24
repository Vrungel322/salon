package com.apps.twelve.floor.salon.mvp.presenters.fragments;

import com.apps.twelve.floor.salon.App;
import com.apps.twelve.floor.salon.mvp.presenters.base.FragmentBasePresenter;
import com.apps.twelve.floor.salon.mvp.presenters.interfaces.IContactsFragmentPresenter;
import com.apps.twelve.floor.salon.mvp.views.IContactsFragmentView;
import com.arellomobile.mvp.InjectViewState;

/**
 * Created by Vrungel on 21.02.2017.
 */

@InjectViewState public class ContactsFragmentPresenter extends FragmentBasePresenter<IContactsFragmentView>
    implements IContactsFragmentPresenter {
  @Override protected void inject() {
    App.getAppComponent().inject(this);
  }
}
