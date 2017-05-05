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
import timber.log.Timber;

/**
 * Created by John on 23.03.2017.
 */

@Shortcut(id = "booking", icon = R.drawable.ic_shortcut_border_color, shortLabelRes = R.string.book_create)
public class BookingActivity extends BaseActivity implements IBookingActivityView {

  @InjectPresenter BookingActivityPresenter mBookingActivityPresenter;

  @Override protected void onCreate(Bundle savedInstanceState) {
    setContentView(R.layout.activity_booking);
    super.onCreate(savedInstanceState);

    setTitleAppBar(R.string.book_create);
    setIconAppBar(R.drawable.ic_home_white_24dp);
  }

  @Override public void addFragmentBooking() {
    mNavigator.addFragment(BookingActivity.this, R.id.container_booking,
        BookingFragment.newInstance());
  }

  @Override public void onBackPressed() {
    if (mNavigator.isFragmentTag(this, Constants.FragmentTag.CHOOSE_SERVICE_SERVICE_FRAGMENT)) {
      mBookingActivityPresenter.backCategories();
      Timber.e("yes");
    } else {
      Timber.e("no");
      mBookingActivityPresenter.stateBackBookingService();
    }

    if (mNavigator.isFragmentTag(this, Constants.FragmentTag.BOOKING_DETAIL_MASTER_FRAGMENT)) {
      mBookingActivityPresenter.stateBackBookingMaster();
      super.onBackPressed();
    }

    /*if (!mNavigator.isFragmentTag(this, Constants.FragmentTag.BOOKING_DETAIL_MASTER_FRAGMENT)
        && !mNavigator.isFragmentTag(this, Constants.FragmentTag.BOOKING_DETAIL_SERVICE_FRAGMENT)) {
      super.onBackPressed();
    }*/
  }
}
