package com.apps.twelve.floor.salon.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.view.View;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import com.apps.twelve.floor.salon.R;
import com.apps.twelve.floor.salon.mvp.presenters.fragments.ContactsPresenter;
import com.apps.twelve.floor.salon.mvp.views.IContactsFragmentView;
import com.apps.twelve.floor.salon.ui.base.BaseFragment;
import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;

/**
 * Created by Vrungel on 21.02.2017.
 */

public class ContactsFragment extends BaseFragment implements IContactsFragmentView {
  @InjectPresenter ContactsPresenter mContactsFragmentPresenter;

  @BindView(R.id.tv_map) TextView mTvMap;

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

    ActionBar actionBar = ((MvpAppCompatActivity) getActivity()).getSupportActionBar();
    if (actionBar != null) {
      actionBar.setTitle(R.string.menu_contacts);
    }

    mTvMap.setOnClickListener(v -> showToastMessage("Show map"));
  }

  @OnClick(R.id.tv_about) void about() {
    showToastMessage("Show about");
  }

  @OnClick(R.id.tv_website) void website() {
    showToastMessage("Show website");
  }

}