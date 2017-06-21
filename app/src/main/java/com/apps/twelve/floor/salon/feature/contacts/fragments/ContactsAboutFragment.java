package com.apps.twelve.floor.salon.feature.contacts.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import com.apps.twelve.floor.salon.R;
import com.apps.twelve.floor.salon.base.BaseFragment;
import com.apps.twelve.floor.salon.feature.contacts.presenters.ContactsAboutFragmentPresenter;
import com.apps.twelve.floor.salon.feature.contacts.views.IContactsAboutFragmentView;
import com.arellomobile.mvp.presenter.InjectPresenter;

/**
 * Created by Alexandra on 21.06.2017.
 */

public class ContactsAboutFragment extends BaseFragment implements IContactsAboutFragmentView {
  @InjectPresenter ContactsAboutFragmentPresenter mContactsAboutFragmentPresenter;

  public ContactsAboutFragment() {
    super(R.layout.fragment_contacts_about);
  }

  public static ContactsAboutFragment newInstance() {
    Bundle args = new Bundle();
    ContactsAboutFragment fragment = new ContactsAboutFragment();
    fragment.setArguments(args);
    return fragment;
  }

  @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
  }

  @Override public void onDestroy() {
    super.onDestroy();
  }
}