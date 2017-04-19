package com.apps.twelve.floor.salon.feature.booking.activities;

import android.os.Bundle;
import com.apps.twelve.floor.salon.R;
import com.apps.twelve.floor.salon.base.BaseActivity;
import com.apps.twelve.floor.salon.feature.booking.fragments.BookingFragment;
import com.apps.twelve.floor.salon.feature.booking.presenters.BookingActivityPresenter;
import com.apps.twelve.floor.salon.feature.booking.views.IBookingActivityView;
import com.apps.twelve.floor.salon.utils.Constants;
import com.arellomobile.mvp.presenter.InjectPresenter;
import shortbread.Shortcut;

/**
 * Created by John on 23.03.2017.
 */

@Shortcut(id = "booking", icon = R.drawable.ic_shortcut_border_color, shortLabelRes = R.string.book_create)
public class BookingActivity extends BaseActivity implements IBookingActivityView {

  @InjectPresenter BookingActivityPresenter mBookingActivityPresenter;

  private boolean mVisible = true;

  @Override protected void onCreate(Bundle savedInstanceState) {
    setContentView(R.layout.activity_booking);
    super.onCreate(savedInstanceState);

    setTitleAppBar(R.string.book_create);
  }

  @Override public void addFragmentBooking() {
    mNavigator.addFragment(BookingActivity.this, R.id.container_booking,
        BookingFragment.newInstance());
  }

  @Override public void isVisibleChooseService(boolean visible) {
    mVisible = visible;
  }

  @Override public void onBackPressed() {
    if (mNavigator.isFragmentTag(this, Constants.FragmentTag.BOOKING_SERVICES_FRAGMENT)) {
      if (mVisible) {
        mBookingActivityPresenter.backCategories();
      } else {
        mBookingActivityPresenter.stateBooking();
      }
    }

    if (mNavigator.isFragmentTag(this, Constants.FragmentTag.BOOKING_MASTERS_FRAGMENT)) {
      mBookingActivityPresenter.stateBooking();
    }

    if (!mNavigator.isFragmentTag(this, Constants.FragmentTag.BOOKING_MASTERS_FRAGMENT)
        && !mNavigator.isFragmentTag(this, Constants.FragmentTag.BOOKING_SERVICES_FRAGMENT)) {
      super.onBackPressed();
    }
  }
}
