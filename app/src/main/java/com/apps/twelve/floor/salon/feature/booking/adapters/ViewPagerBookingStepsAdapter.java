package com.apps.twelve.floor.salon.feature.booking.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import java.util.ArrayList;

/**
 * Created by Vrungel on 23.03.2017.
 */

public class ViewPagerBookingStepsAdapter extends FragmentStatePagerAdapter {

  private ArrayList<Fragment> mFragmentList = new ArrayList<>();
  private ArrayList<String> mFragmentTitleList = new ArrayList<>();

  public ViewPagerBookingStepsAdapter(FragmentManager fm) {
    super(fm);
  }

  @Override public CharSequence getPageTitle(int position) {
    return mFragmentTitleList.get(position);
  }

  @Override public Fragment getItem(int position) {
    return mFragmentList.get(position);
  }

  @Override public int getCount() {
    return mFragmentList.size();
  }

  public void addFragment(Fragment fragment, String title) {
    mFragmentList.add(fragment);
    mFragmentTitleList.add(title);
  }
}
