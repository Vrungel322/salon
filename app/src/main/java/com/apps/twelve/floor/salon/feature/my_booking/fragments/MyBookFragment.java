package com.apps.twelve.floor.salon.feature.my_booking.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import butterknife.BindView;
import com.apps.twelve.floor.salon.R;
import com.apps.twelve.floor.salon.base.BaseFragment;
import com.apps.twelve.floor.salon.feature.my_booking.adapters.ListsPagerAdapter;
import com.apps.twelve.floor.salon.feature.my_booking.views.IMyBookFragmentView;

/**
 * Created by Vrungel on 21.02.2017.
 */

public class MyBookFragment extends BaseFragment implements IMyBookFragmentView {

  @BindView(R.id.ViewPagerLists) ViewPager mViewPagerLists;
  @BindView(R.id.tabLayout) TabLayout mTabLayout;

  public MyBookFragment() {
    super(R.layout.fragment_my_book);
  }

  public static MyBookFragment newInstance() {
    Bundle args = new Bundle();
    MyBookFragment fragment = new MyBookFragment();
    fragment.setArguments(args);
    return fragment;
  }

  @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

    mViewPagerLists.setAdapter(new ListsPagerAdapter(this.getChildFragmentManager()));
    mTabLayout.setupWithViewPager(mViewPagerLists);
    mViewPagerLists.setCurrentItem(0);
  }
}