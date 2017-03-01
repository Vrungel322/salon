package com.apps.twelve.floor.salon.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import com.apps.twelve.floor.salon.R;
import com.apps.twelve.floor.salon.mvp.presenters.fragments.MainPresenter;
import com.apps.twelve.floor.salon.mvp.views.IMainFragmentView;
import com.apps.twelve.floor.salon.ui.base.BaseFragment;
import com.apps.twelve.floor.salon.utils.Constants;
import com.arellomobile.mvp.presenter.InjectPresenter;

/**
 * Created by Vrungel on 20.02.2017.
 */

public class MainFragment extends BaseFragment implements IMainFragmentView {
  @InjectPresenter MainPresenter mMainFragmentPresenter;

  public static MainFragment newInstance() {
    Bundle args = new Bundle();
    MainFragment fragment = new MainFragment();
    fragment.setArguments(args);
    return fragment;
  }

  public MainFragment() {
    super(R.layout.fragment_main);
  }

  @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
  }

  @Override public void addSubFragments() {
    mNavigator.addChildFragment(this, R.id.subFragmentContainerNews, SubNewsFragment.newInstance());
    mNavigator.addChildFragment(this, R.id.subFragmentContainerBonusRegistration,
        SubBonusRegistrationFragment.newInstance(Constants.FragmentToShow.REGISTRATION));
    mNavigator.addChildFragment(this, R.id.subFragmentContainerBooking,
        SubBookingFragment.newInstance());
  }
}
