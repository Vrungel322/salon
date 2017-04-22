package com.apps.twelve.floor.salon.feature.main_screen.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import butterknife.BindView;
import com.apps.twelve.floor.salon.R;
import com.apps.twelve.floor.salon.base.BaseFragment;
import com.apps.twelve.floor.salon.feature.main_screen.presenters.MainFragmentPresenter;
import com.apps.twelve.floor.salon.feature.main_screen.views.IMainFragmentView;
import com.apps.twelve.floor.salon.utils.Constants;
import com.arellomobile.mvp.presenter.InjectPresenter;

/**
 * Created by Vrungel on 20.02.2017.
 */

public class MainFragment extends BaseFragment implements IMainFragmentView {

  @InjectPresenter MainFragmentPresenter mMainFragmentPresenter;

  @BindView(R.id.swipeRefreshMainFragment) SwipeRefreshLayout mSwipeRefreshMainFragment;

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

    mSwipeRefreshMainFragment.setColorSchemeResources(R.color.colorAccent);
    mSwipeRefreshMainFragment.setOnRefreshListener(
        () -> mMainFragmentPresenter.updateBookingAndNews());
  }

  @Override public void addSubFragments() {
    mNavigator.addChildFragment(this, R.id.subFragmentContainerNews, SubNewsFragment.newInstance());
    mNavigator.addChildFragment(this, R.id.subFragmentContainerBonusRegistration,
        SubBonusRegistrationFragment.newInstance(Constants.FragmentToShow.REGISTRATION));
    mNavigator.addChildFragment(this, R.id.subFragmentContainerBooking,
        SubBookingFragment.newInstance());
  }

  @Override public void stopRefreshingView() {
    if (mSwipeRefreshMainFragment.isRefreshing()) mSwipeRefreshMainFragment.setRefreshing(false);
  }

  @Override public void startRefreshingView() {
    if (!mSwipeRefreshMainFragment.isRefreshing()) mSwipeRefreshMainFragment.setRefreshing(true);
  }
}
