package com.apps.twelve.floor.salon.ui.adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import com.apps.twelve.floor.salon.R;
import com.apps.twelve.floor.salon.ui.fragments.OurWorkFragment;
import com.apps.twelve.floor.salon.ui.fragments.SubNewsFragment;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vrungel on 23.03.2017.
 */

public class BookingStepsAdapter extends FragmentStatePagerAdapter {

  public static final int FRAGMENT_COUNT = 4;
  private final String[] tabNames;
  //integer to count number of tabs
  private final List<Fragment> fragments = new ArrayList<>();

  public BookingStepsAdapter(FragmentManager fm, Context context) {
    super(fm);
    this.tabNames = context.getResources().getStringArray(R.array.tab_names);
    //Initializing tab count

    // TODO: 23.03.2017 insert viewPager fragments
    fragments.add(OurWorkFragment.newInstance());
    fragments.add(OurWorkFragment.newInstance());
    fragments.add(OurWorkFragment.newInstance());
    fragments.add(OurWorkFragment.newInstance());
  }

  @Override public CharSequence getPageTitle(int position) {
    return tabNames[position];
  }

  @Override public Fragment getItem(int position) {
    //Returning the current tabs
    return fragments.get(position);
  }

  @Override public int getCount() {
    return FRAGMENT_COUNT;
  }
}
