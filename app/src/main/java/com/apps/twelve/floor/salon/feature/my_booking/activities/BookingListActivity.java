package com.apps.twelve.floor.salon.feature.my_booking.activities;

import android.os.Bundle;
import com.apps.twelve.floor.salon.R;
import com.apps.twelve.floor.salon.base.BaseActivity;
import com.apps.twelve.floor.salon.feature.my_booking.fragments.MyBookFragment;
import com.apps.twelve.floor.salon.feature.my_booking.presenters.BookingListActivityPresenter;
import com.apps.twelve.floor.salon.feature.my_booking.views.IBookingListActivityView;
import com.apps.twelve.floor.salon.utils.ThemeUtils;
import com.arellomobile.mvp.presenter.InjectPresenter;

public class BookingListActivity extends BaseActivity implements IBookingListActivityView {
  @InjectPresenter BookingListActivityPresenter mBookingListActivityPresenter;

  @Override protected void onCreate(Bundle savedInstanceState) {
    setTheme(ThemeUtils.getThemeActionBar(getBaseContext()));
    setContentView(R.layout.activity_booking_list);
    super.onCreate(savedInstanceState);
  }

  @Override public void showMyBookFragment() {
    mNavigator.addFragment(BookingListActivity.this, R.id.container_for_list_of_booked_services,
        MyBookFragment.newInstance());
  }
}
