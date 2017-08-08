package com.apps.twelve.floor.salon.feature.my_booking.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import com.apps.twelve.floor.salon.feature.my_booking.fragments.ActiveBookingFragment;

/**
 * Created by Vrungel on 08.08.2017.
 */

public class ListsPagerAdapter extends FragmentStatePagerAdapter {

  public ListsPagerAdapter(FragmentManager fm) {
    super(fm);
  }

  @Override public Fragment getItem(int position) {
    if (position == 0) {
      return (ActiveBookingFragment.newInstance());
    } else {
      return (ActiveBookingFragment.newInstance());
    }
  }

  @Override public CharSequence getPageTitle(int position) {
    if (position == 0) {
      return ("ActiveBookingFragment");
    } else {
      return ("ActiveBookingFragment");
    }
  }

  @Override public int getCount() {
    return 2;
  }
}
