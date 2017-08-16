package com.apps.twelve.floor.salon.feature.my_booking.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import com.apps.twelve.floor.salon.feature.my_booking.fragments.ActiveBookingFragment;
import com.apps.twelve.floor.salon.feature.my_booking.fragments.HistoryBookingFragment;

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
      return (HistoryBookingFragment.newInstance());
    }
  }

  @Override public CharSequence getPageTitle(int position) {
    if (position == 0) {
      return ("Активные записи");
    } else {
      return ("История");
    }
  }

  @Override public int getCount() {
    return 2;
  }
}
