package com.apps.twelve.floor.salon.feature.start_point.activities;

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
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import berlin.volders.badger.BadgeShape;
import berlin.volders.badger.Badger;
import berlin.volders.badger.CountBadge;
import butterknife.BindView;
import com.apps.twelve.floor.salon.R;
import com.apps.twelve.floor.salon.base.BaseActivity;
import com.apps.twelve.floor.salon.feature.booking.activities.BookingActivity;
import com.apps.twelve.floor.salon.feature.catalog.fragments.CatalogFragment;
import com.apps.twelve.floor.salon.feature.contacts.fragments.ContactsFragment;
import com.apps.twelve.floor.salon.feature.main_screen.fragments.MainFragment;
import com.apps.twelve.floor.salon.feature.my_bonus.fragments.MyBonusFragment;
import com.apps.twelve.floor.salon.feature.my_booking.fragments.MyBookFragment;
import com.apps.twelve.floor.salon.feature.news.fragments.AllNewsViewFragment;
import com.apps.twelve.floor.salon.feature.our_works.fragments.OurWorkFragment;
import com.apps.twelve.floor.salon.feature.settings.activities.SettingsActivity;
import com.apps.twelve.floor.salon.feature.start_point.presenters.StartActivityPresenter;
import com.apps.twelve.floor.salon.feature.start_point.views.IStartActivityView;
import com.apps.twelve.floor.salon.utils.Constants;
import com.apps.twelve.floor.salon.utils.DialogFactory;
import com.apps.twelve.floor.salon.utils.ThemeUtils;
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
  private int mCountBonus;
  private AlertDialog mAuthorizationDialog;

  @Override protected void onCreate(Bundle savedInstanceState) {
    setTheme(ThemeUtils.getThemeNoActionBar(getBaseContext()));
    setContentView(R.layout.activity_start);
    super.onCreate(savedInstanceState);
    setUpUI();

    mFabBooking.setOnClickListener(v -> mNavigator.startActivity(StartActivity.this,
        new Intent(StartActivity.this, BookingActivity.class)));

    getSupportFragmentManager().addOnBackStackChangedListener(
        () -> mStartActivityPresenter.setDrawerIndicator());

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

    mCircleFactory = new CountBadge.Factory(BadgeShape.circle(.6f, Gravity.END | Gravity.TOP),
        ContextCompat.getColor(this, R.color.colorRed),
        ContextCompat.getColor(this, R.color.colorWhite));
  }

  @Override protected void onResume() {
    super.onResume();
    mStartActivityPresenter.fetchBonusCount();

    if (mAuthorizationManager.isAuthorized()) {
      mNavViewTopPart.getMenu().getItem(2).setCheckable(true);
      mNavViewTopPart.getMenu().getItem(3).setCheckable(true);
    } else {
      mNavViewTopPart.getMenu().getItem(2).setCheckable(false);
      mNavViewTopPart.getMenu().getItem(3).setCheckable(false);
    }
  }

  @Override protected void onPause() {
    super.onPause();
  }

  @Override public void setMyBooksItemInMenu() {
    mNavViewTopPart.getMenu().getItem(2).setChecked(true);
  }

  @Override public void setNewsItemInMenu() {
    mNavViewTopPart.getMenu().getItem(6).setChecked(true);
  }

  @Override public void setBonusItemInMenu() {
    mNavViewTopPart.getMenu().getItem(3).setChecked(true);
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

  @Override public void hideFloatingButton() {
    mFabBooking.setVisibility(View.INVISIBLE);
  }

  @Override public void showFloatingButton() {
    mFabBooking.setVisibility(View.VISIBLE);
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
      if (mNavigator.isOneFragmentBackStack(this)) {
        setTitleAppBar(R.string.title_activity_start);
        mNavViewTopPart.getMenu().getItem(0).setChecked(true);
      }
      super.onBackPressed();
    }
  }

  @Override public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.menu_start, menu);
    mBadge = Badger.sett(menu.findItem(R.id.action_my_bonus), mCircleFactory);
    mBadge.setCount(mCountBonus);
    return true;
  }

  @Override public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case R.id.action_favourite:
        mStartActivityPresenter.share();
        return true;
      case R.id.action_my_bonus:
        if (mAuthorizationManager.isAuthorized()) {
          mNavigator.addFragmentTagClearBackStackNotCopy(StartActivity.this, R.id.container_main,
              MyBonusFragment.newInstance(), Constants.FragmentTag.MY_BONUS_FRAGMENT);
        } else {
          mStartActivityPresenter.showAlertDialog();
        }
        return true;
      default:
        return super.onOptionsItemSelected(item);
    }
  }

  @Override public boolean onNavigationItemSelected(@NonNull MenuItem item) {
    switch (item.getItemId()) {
      case R.id.nav_main:
        setTitleAppBar(R.string.title_activity_start);
        mNavigator.clearBackStack(this);
        break;
      case R.id.nav_booking:
        mNavigator.startActivity(StartActivity.this,
            new Intent(StartActivity.this, BookingActivity.class));
        break;
      case R.id.nav_my_book:
        if (mAuthorizationManager.isAuthorized()) {
          mNavigator.addFragmentTagClearBackStackNotCopy(StartActivity.this, R.id.container_main,
              MyBookFragment.newInstance(), Constants.FragmentTag.MY_BOOK_FRAGMENT);
        } else {
          mStartActivityPresenter.showAlertDialog();
        }
        break;
      case R.id.nav_my_bonus:
        if (mAuthorizationManager.isAuthorized()) {
          mNavigator.addFragmentTagClearBackStackNotCopy(StartActivity.this, R.id.container_main,
              MyBonusFragment.newInstance(), Constants.FragmentTag.MY_BONUS_FRAGMENT);
        } else {
          mStartActivityPresenter.showAlertDialog();
        }
        break;
      case R.id.nav_our_work:
        mNavigator.addFragmentTagClearBackStackNotCopy(StartActivity.this, R.id.container_main,
            OurWorkFragment.newInstance(), Constants.FragmentTag.OUR_WORK_FRAGMENT);
        break;
      case R.id.nav_catalog:
        mNavigator.addFragmentTagClearBackStackNotCopy(StartActivity.this, R.id.container_main,
            CatalogFragment.newInstance(), Constants.FragmentTag.CATALOG_FRAGMENT);
        break;
      case R.id.nav_news:
        mNavigator.addFragmentTagClearBackStackNotCopy(StartActivity.this, R.id.container_main,
            AllNewsViewFragment.newInstance(), Constants.FragmentTag.ALL_NEWS_FRAGMENT);
        break;
      case R.id.nav_contacts:
        mNavigator.addFragmentTagClearBackStackNotCopy(StartActivity.this, R.id.container_main,
            ContactsFragment.newInstance(), Constants.FragmentTag.CONTACTS_FRAGMENT);
        break;
      case R.id.nav_settings:
        mNavigator.startActivity(StartActivity.this,
            new Intent(StartActivity.this, SettingsActivity.class));
        break;
    }
    mDrawerLayout.closeDrawer(GravityCompat.START);
    return true;
  }

  @Override public void share() {
    String appUrl = "http://www.google.com";
    Intent intent = new Intent(Intent.ACTION_SEND);
    intent.setType("text/plain");
    intent.putExtra(Intent.EXTRA_SUBJECT, "Watch this cool app!");
    intent.putExtra(Intent.EXTRA_TEXT, appUrl);
    startActivity(Intent.createChooser(intent, "Share app URL"));
  }

  @Override public void setDrawerIndicator() {
    if (getSupportActionBar() != null && !mNavigator.isEmptyBackStack(StartActivity.this)) {
      mToggle.setDrawerIndicatorEnabled(false);
      getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    } else {
      getSupportActionBar().setDisplayHomeAsUpEnabled(false);
      mToggle.setDrawerIndicatorEnabled(true);
    }
  }

  @Override public void addFragmentMain() {
    mNavigator.addFragmentTag(StartActivity.this, R.id.container_main, new MainFragment(),
        Constants.FragmentTag.MAIN_FRAGMENT);
  }

  @Override public void showAlertDialog() {
    mAuthorizationDialog = DialogFactory.createAuthorizationDialogBuilder(this)
        .setNegativeButton(R.string.dialog_auth_cancel,
            (dialog, which) -> mStartActivityPresenter.cancelAlertDialog())
        .setPositiveButton(R.string.dialog_auth_yes, (dialog, which) -> {
          mAuthorizationManager.startSignInActivity(this, ThemeUtils.getThemeActionBar(mContext));
          mStartActivityPresenter.cancelAlertDialog();
        })
        .create();
    mAuthorizationDialog.show();
  }

  @Override public void cancelAlertDialog() {
    if (mAuthorizationDialog != null) {
      mAuthorizationDialog.dismiss();
    }
  }

  @Override public void onDestroy() {
    super.onDestroy();
    cancelAlertDialog();
  }
}
