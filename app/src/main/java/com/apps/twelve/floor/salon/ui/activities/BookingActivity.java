package com.apps.twelve.floor.salon.ui.activities;

import android.os.Bundle;
import com.apps.twelve.floor.salon.R;
import com.apps.twelve.floor.salon.mvp.presenters.pr_activities.BookingActivityPresenter;
import com.apps.twelve.floor.salon.mvp.views.IBookingActivityView;
import com.apps.twelve.floor.salon.ui.base.BaseActivity;
import com.apps.twelve.floor.salon.ui.fragments.BookingFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import shortbread.Shortcut;

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
  }

  @Override public void addFragmentBooking() {
    mNavigator.addFragment(BookingActivity.this, R.id.container_booking,
        BookingFragment.newInstance());
  }
}
