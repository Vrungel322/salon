package com.apps.twelve.floor.salon.feature.booking.activities;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import berlin.volders.badger.BadgeShape;
import berlin.volders.badger.Badger;
import berlin.volders.badger.CountBadge;
import com.apps.twelve.floor.salon.R;
import com.apps.twelve.floor.salon.base.BaseActivity;
import com.apps.twelve.floor.salon.feature.booking.fragments.BookingFragment;
import com.apps.twelve.floor.salon.feature.booking.presenters.BookingActivityPresenter;
import com.apps.twelve.floor.salon.feature.booking.views.IBookingActivityView;
import com.apps.twelve.floor.salon.feature.my_bonus.fragments.MyBonusFragment;
import com.apps.twelve.floor.salon.utils.Constants;
import com.apps.twelve.floor.salon.utils.ThemeUtils;
import com.arellomobile.mvp.presenter.InjectPresenter;
import shortbread.Shortcut;

/**
 * Created by John on 23.03.2017.
 */

@Shortcut(id = "booking", icon = R.drawable.ic_shortcut_border_color, shortLabelRes = R.string.book_create)
public class BookingActivity extends BaseActivity implements IBookingActivityView {

  @InjectPresenter BookingActivityPresenter mBookingActivityPresenter;

  private CountBadge.Factory mCircleFactory;
  private CountBadge mBadge;
  private int mCountBonus;

  @Override protected void onCreate(Bundle savedInstanceState) {
    setTheme(ThemeUtils.getThemeOtherActivity(getBaseContext()));
    setContentView(R.layout.activity_booking);
    super.onCreate(savedInstanceState);

    setTitleAppBar(R.string.book_create);
    setIconAppBar(R.drawable.ic_home_white_24dp);

    mCircleFactory = new CountBadge.Factory(BadgeShape.circle(.6f, Gravity.END | Gravity.TOP),
        ContextCompat.getColor(this, R.color.colorRed),
        ContextCompat.getColor(this, R.color.colorWhite));

  }

  @Override protected void onResume() {
    super.onResume();
    mBookingActivityPresenter.fetchBonusCount();
  }

  @Override protected void onPause() {
    super.onPause();
  }

  @Override public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.menu_booking, menu);
    mBadge = Badger.sett(menu.findItem(R.id.action_my_bonus), mCircleFactory);
    mBadge.setCount(mCountBonus);
    return true;
  }

  @Override public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case R.id.action_my_bonus:
        mNavigator.addFragmentTagBackStackNotCopy(BookingActivity.this, R.id.container_booking,
            MyBonusFragment.newInstance(), Constants.FragmentTag.MY_BONUS_FRAGMENT);
        return true;
      default:
        return super.onOptionsItemSelected(item);
    }
  }

  @Override public void addFragmentBooking() {
    mNavigator.addFragment(BookingActivity.this, R.id.container_booking,
        BookingFragment.newInstance());
  }

  @Override public void closeBookingService() {
    mNavigator.clearBackStack(this);
    mNavigator.replaceFragment(this, R.id.container_booking, BookingFragment.newInstance());
  }

  @Override public void setBonusCount(int count) {
    mCountBonus = count;
    if (mBadge != null) {
      mBadge.setCount(count);
    }
  }

  @Override public void showConnectErrorMessage() {
    showAlertMessage(getString(R.string.error_connection),
        getString(R.string.сheck_internet_connection));
  }

  @Override public void onBackPressed() {
    if (mNavigator.isFragmentTag(this, Constants.FragmentTag.BOOKING_DETAIL_SERVICE_FRAGMENT)) {
      if (mNavigator.getCountBackStack(this) == 2) {
        mBookingActivityPresenter.backCategories();
      } else {
        mBookingActivityPresenter.stateBackBookingService();
        super.onBackPressed();
      }
      return;
    }

    if (mNavigator.isFragmentTag(this, Constants.FragmentTag.BOOKING_DETAIL_MASTER_FRAGMENT)) {
      if (mNavigator.getCountBackStack(this) > 2) {
        mBookingActivityPresenter.stateBackBookingMaster();
        super.onBackPressed();
      } else {
        mNavigator.clearBackStack(this);
        mNavigator.replaceFragment(this, R.id.container_booking, BookingFragment.newInstance());
      }

      return;
    }

    super.onBackPressed();
  }
}
