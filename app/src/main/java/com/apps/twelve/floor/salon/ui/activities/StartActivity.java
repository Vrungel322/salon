package com.apps.twelve.floor.salon.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import berlin.volders.badger.BadgeShape;
import berlin.volders.badger.Badger;
import berlin.volders.badger.CountBadge;
import butterknife.BindView;
import com.apps.twelve.floor.salon.R;
import com.apps.twelve.floor.salon.mvp.presenters.activities.StartActivityPresenter;
import com.apps.twelve.floor.salon.mvp.views.IStartActivityView;
import com.apps.twelve.floor.salon.ui.base.BaseActivity;
import com.apps.twelve.floor.salon.ui.fragments.ContactsFragment;
import com.apps.twelve.floor.salon.ui.fragments.MainFragment;
import com.apps.twelve.floor.salon.ui.fragments.MyBonusFragment;
import com.apps.twelve.floor.salon.ui.fragments.MyBookFragment;
import com.apps.twelve.floor.salon.ui.fragments.OurWorkFragment;
import com.apps.twelve.floor.salon.utils.Constants;
import com.arellomobile.mvp.presenter.InjectPresenter;

public class StartActivity extends BaseActivity
    implements NavigationView.OnNavigationItemSelectedListener, IStartActivityView {

  @InjectPresenter StartActivityPresenter mStartActivityPresenter;

  @BindView(R.id.toolbar) Toolbar mToolbar;
  @BindView(R.id.fab_booking) FloatingActionButton mFabBooking;
  @BindView(R.id.navigation_drawer_topPart) NavigationView mNavViewTopPart;
  @BindView(R.id.navigation_drawer_bottomPart) NavigationView mNavViewBottomPart;
  @BindView(R.id.drawer_layout) DrawerLayout mDrawerLayout;

  private ActionBarDrawerToggle mToggle;
  private CountBadge.Factory mCircleFactory;
  private CountBadge mBadge;

  @Override protected void onCreate(Bundle savedInstanceState) {
    setContentView(R.layout.activity_start);
    super.onCreate(savedInstanceState);
    setUpUI();

    mFabBooking.setOnClickListener(v -> mNavigator.startActivity(StartActivity.this,
        new Intent(StartActivity.this, BookingActivity.class)));

    getSupportFragmentManager().addOnBackStackChangedListener(() -> {
      if (getSupportActionBar() != null && !mNavigator.isEmptyBackStack(StartActivity.this)) {
        mToggle.setDrawerIndicatorEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
      } else {
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        mToggle.setDrawerIndicatorEnabled(true);
      }
    });

    mToggle.setToolbarNavigationClickListener(v -> onBackPressed());
  }

  private void setUpUI() {
    setSupportActionBar(mToolbar);

    mToggle =
        new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.navigation_drawer_open,
            R.string.navigation_drawer_close);
    mDrawerLayout.addDrawerListener(mToggle);
    mToggle.syncState();

    mNavViewTopPart.setNavigationItemSelectedListener(this);
    mNavViewBottomPart.setNavigationItemSelectedListener(this);

    mCircleFactory = new CountBadge.Factory(BadgeShape.circle(.5f, Gravity.END | Gravity.TOP),
        ContextCompat.getColor(this, R.color.colorDarkPink),
        ContextCompat.getColor(this, R.color.colorWhite));
  }

  @Override public void onBackPressed() {
    DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
    if (drawer.isDrawerOpen(GravityCompat.START)) {
      drawer.closeDrawer(GravityCompat.START);
    } else {
      if (!mNavigator.isEmptyBackStack(StartActivity.this)) {
        AppBarLayout appBarLayout = (AppBarLayout) this.findViewById(R.id.appBar);
        appBarLayout.setExpanded(true, true);
      }
      super.onBackPressed();
    }
  }

  @Override public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.menu, menu);
    mBadge = Badger.sett(menu.findItem(R.id.action_my_bonus), mCircleFactory);
    mBadge.setCount(5);
    return true;
  }

  @Override public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case R.id.action_booking:
        return true;
      case R.id.action_my_bonus:
        mBadge.setCount(55);
        return true;
      default:
        return super.onOptionsItemSelected(item);
    }
  }

  @Override public boolean onNavigationItemSelected(@NonNull MenuItem item) {
    switch (item.getItemId()) {
      case R.id.nav_main:
        mNavigator.clearBackStack(this);
        mNavigator.replaceFragmentTagNotCopy(StartActivity.this, R.id.container_main,
            MainFragment.newInstance(), Constants.FragmentTag.MAIN_FRAGMENT);
        break;
      case R.id.nav_booking:
        mNavigator.clearBackStack(this);
        mNavigator.startActivity(StartActivity.this,
            new Intent(StartActivity.this, BookingActivity.class));
        break;
      case R.id.nav_my_book:
        mNavigator.clearBackStack(this);
        mNavigator.replaceFragmentTagNotCopy(StartActivity.this, R.id.container_main,
            MyBookFragment.newInstance(), Constants.FragmentTag.MY_BOOK_FRAGMENT);
        break;
      case R.id.nav_my_bonus:
        mNavigator.clearBackStack(this);
        mNavigator.replaceFragmentTagNotCopy(StartActivity.this, R.id.container_main,
            MyBonusFragment.newInstance(), Constants.FragmentTag.MY_BONUS_FRAGMENT);
        break;
      case R.id.nav_share:
        showAlertMessage("Talk about how to share ???");
        break;
      case R.id.nav_our_work:
        mNavigator.clearBackStack(this);
        mNavigator.replaceFragmentTagNotCopy(StartActivity.this, R.id.container_main,
            OurWorkFragment.newInstance(), Constants.FragmentTag.OUR_WORK_FRAGMENT);
        break;
      case R.id.nav_contacts:
        mNavigator.clearBackStack(this);
        mNavigator.replaceFragmentTagNotCopy(StartActivity.this, R.id.container_main,
            ContactsFragment.newInstance(), Constants.FragmentTag.CONTACTS_FRAGMENT);
        break;
      case R.id.nav_settings:
        mNavigator.clearBackStack(this);
        mNavigator.startActivity(StartActivity.this,
            new Intent(StartActivity.this, SettingsActivity.class));
        break;
    }
    mDrawerLayout.closeDrawer(GravityCompat.START);
    return true;
  }

  @Override public void addFragmentMain() {
    mNavigator.addFragmentTag(StartActivity.this, R.id.container_main, new MainFragment(),
        Constants.FragmentTag.MAIN_FRAGMENT);
  }
}
