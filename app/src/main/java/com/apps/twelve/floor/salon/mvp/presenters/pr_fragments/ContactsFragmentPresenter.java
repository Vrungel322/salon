package com.apps.twelve.floor.salon.mvp.presenters.pr_fragments;

import com.apps.twelve.floor.salon.App;
import com.apps.twelve.floor.salon.mvp.presenters.BasePresenter;
import com.apps.twelve.floor.salon.mvp.presenters.pr_interfaces.IContactsFragmentPresenter;
import com.apps.twelve.floor.salon.mvp.views.IContactsFragmentView;
import com.arellomobile.mvp.InjectViewState;

/**
 * Created by Vrungel on 21.02.2017.
 */

@InjectViewState public class ContactsFragmentPresenter extends BasePresenter<IContactsFragmentView>
    implements IContactsFragmentPresenter {
  @Override protected void inject() {
    App.getAppComponent().inject(this);
  }
}