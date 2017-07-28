package com.apps.twelve.floor.salon.feature.my_booking.activities;

import android.os.Bundle;
import com.apps.twelve.floor.salon.R;
import com.apps.twelve.floor.salon.base.BaseActivity;
import com.apps.twelve.floor.salon.data.model.LastBookingEntity;
import com.apps.twelve.floor.salon.feature.my_booking.fragments.BookDetailsFragment;
import com.apps.twelve.floor.salon.feature.my_booking.presenters.BookingDetailActivityPresenter;
import com.apps.twelve.floor.salon.feature.my_booking.views.IBookingDetailActivity;
import com.apps.twelve.floor.salon.utils.ThemeUtils;
import com.arellomobile.mvp.presenter.InjectPresenter;

import static com.apps.twelve.floor.salon.utils.Constants.Notifications.LAST_BOOKING_ENTITY;

/**
 * Created by alexandersvyatetsky on 28/07/17.
 */

public class BookingDetailActivity extends BaseActivity implements IBookingDetailActivity {

  @InjectPresenter BookingDetailActivityPresenter mBookingDetailActivityPresenter;

  @Override protected void onCreate(Bundle savedInstanceState) {
    setTheme(ThemeUtils.getThemeActionBar(getBaseContext()));
    setContentView(R.layout.activity_booking_detail);
    super.onCreate(savedInstanceState);
  }

  @Override public void addBookDetailFragment() {
    LastBookingEntity bookingEntity = getIntent().getExtras().getParcelable(LAST_BOOKING_ENTITY);
    if (bookingEntity != null) {
      mNavigator.addFragment(BookingDetailActivity.this, R.id.container,
          BookDetailsFragment.newInstance(bookingEntity));
    }
  }
}
