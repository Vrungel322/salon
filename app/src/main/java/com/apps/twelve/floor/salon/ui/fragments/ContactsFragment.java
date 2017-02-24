package com.apps.twelve.floor.salon.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;
import butterknife.BindView;
import com.apps.twelve.floor.salon.R;
import com.apps.twelve.floor.salon.mvp.presenters.fragments.ContactsFragmentPresenter;
import com.apps.twelve.floor.salon.mvp.views.IContactsFragmentView;
import com.apps.twelve.floor.salon.ui.base.BaseFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;

/**
 * Created by Vrungel on 21.02.2017.
 */

public class ContactsFragment extends BaseFragment implements IContactsFragmentView {
  @InjectPresenter ContactsFragmentPresenter mContactsFragmentPresenter;

  @BindView(R.id.tvTest) TextView mTvTest;

  public static ContactsFragment newInstance() {
    Bundle args = new Bundle();
    ContactsFragment fragment = new ContactsFragment();
    fragment.setArguments(args);
    return fragment;
  }

  public ContactsFragment() {
    super(R.layout.fragment_contacts);
  }

  @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

    mTvTest.setOnClickListener(v -> showToastMessage("tvTest"));
  }
}