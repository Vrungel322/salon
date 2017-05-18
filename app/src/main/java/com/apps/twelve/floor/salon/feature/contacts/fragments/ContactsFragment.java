package com.apps.twelve.floor.salon.feature.contacts.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import com.apps.twelve.floor.salon.R;
import com.apps.twelve.floor.salon.base.BaseFragment;
import com.apps.twelve.floor.salon.feature.contacts.presenters.ContactsFragmentPresenter;
import com.apps.twelve.floor.salon.feature.contacts.views.IContactsFragmentView;
import com.apps.twelve.floor.salon.feature.start_point.activities.StartActivity;
import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by Vrungel on 21.02.2017.
 */

public class ContactsFragment extends BaseFragment implements IContactsFragmentView {
  @InjectPresenter ContactsFragmentPresenter mContactsFragmentPresenter;

  @BindView(R.id.tv_map) TextView mTvMap;

  public ContactsFragment() {
    super(R.layout.fragment_contacts);
  }

  public static ContactsFragment newInstance() {
    Bundle args = new Bundle();
    ContactsFragment fragment = new ContactsFragment();
    fragment.setArguments(args);
    return fragment;
  }

  @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

    ActionBar actionBar = ((MvpAppCompatActivity) getActivity()).getSupportActionBar();
    if (actionBar != null) {
      ((StartActivity) getActivity()).setTitleAppBar(R.string.menu_contacts);

      /* turn off scrolling */
      Toolbar mToolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);

      AppBarLayout.LayoutParams toolbarLayoutParams =
          (AppBarLayout.LayoutParams) mToolbar.getLayoutParams();
      toolbarLayoutParams.setScrollFlags(0);
      mToolbar.setLayoutParams(toolbarLayoutParams);
    }
  }

  @OnClick(R.id.tv_about) void showAbout() {
    showToastMessage("Show about");
  }

  @OnClick(R.id.tv_website) void openWebsite() {
    String url = "http://shanti-med.com.ua/";
    startActivity(new Intent(Intent.ACTION_VIEW).setData(Uri.parse(url)));
  }

  @OnClick(R.id.tv_map) void openMap() {
    try {
      Intent mapIntent = new Intent(Intent.ACTION_VIEW,
          Uri.parse("geo:0,0?q=" + getString(R.string.contacts_address)));
      startActivity(mapIntent);
    } catch (android.content.ActivityNotFoundException e) {
      String query = null;
      try {
        query = URLEncoder.encode(getString(R.string.contacts_address), "utf-8");
      } catch (UnsupportedEncodingException e1) {
        e1.printStackTrace();
        query = "";
      }
      String url = "http://maps.google.com/maps?daddr=" + query;
      startActivity(new Intent(Intent.ACTION_VIEW).setData(Uri.parse(url)));
    }
  }

  @Override public void onDestroy() {
    /* turn on scrolling */
    Toolbar mToolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
    AppBarLayout.LayoutParams toolbarLayoutParams =
        (AppBarLayout.LayoutParams) mToolbar.getLayoutParams();

    toolbarLayoutParams.setScrollFlags(AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL
        | AppBarLayout.LayoutParams.SCROLL_FLAG_ENTER_ALWAYS);
    mToolbar.setLayoutParams(toolbarLayoutParams);

    super.onDestroy();
  }
}