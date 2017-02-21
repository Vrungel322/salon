package com.apps.twelve.floor.salon.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;
import butterknife.BindView;
import com.apps.twelve.floor.salon.R;
import com.apps.twelve.floor.salon.mvp.presenters.FragmentContactsPresenter;
import com.apps.twelve.floor.salon.mvp.views.IFragmentContactsView;
import com.apps.twelve.floor.salon.ui.base.BaseFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;

/**
 * Created by Vrungel on 21.02.2017.
 */

public class FragmentContacts extends BaseFragment implements IFragmentContactsView {
  @InjectPresenter FragmentContactsPresenter mFragmentContactsPresenter;

  @BindView(R.id.tvTest) TextView mTvTest;

  public FragmentContacts() {
    super(R.layout.fragment_contacts);
  }

  @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

    mTvTest.setOnClickListener(v -> showToastMessage("tvTest"));
  }
}