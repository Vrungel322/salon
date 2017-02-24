package com.apps.twelve.floor.salon.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import butterknife.BindView;
import com.apps.twelve.floor.salon.R;
import com.apps.twelve.floor.salon.mvp.presenters.activities.StartActivityPresenter;
import com.apps.twelve.floor.salon.mvp.views.IStartActivityView;
import com.apps.twelve.floor.salon.ui.base.BaseActivity;
import com.apps.twelve.floor.salon.ui.fragments.BookingFragment;
import com.apps.twelve.floor.salon.ui.fragments.ContactsFragment;
import com.apps.twelve.floor.salon.ui.fragments.MainFragment;
import com.apps.twelve.floor.salon.ui.fragments.MyBonusFragment;
import com.apps.twelve.floor.salon.ui.fragments.MyBookFragment;
import com.apps.twelve.floor.salon.ui.fragments.OurWorkFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;

public class StartActivity extends BaseActivity
    implements NavigationView.OnNavigationItemSelectedListener, IStartActivityView {

  @InjectPresenter StartActivityPresenter mStartActivityPresenter;

  @BindView(R.id.toolbar) Toolbar mToolbar;
  @BindView(R.id.fab) FloatingActionButton mFab;
  @BindView(R.id.navigation_drawer_topPart) NavigationView mNavViewTopPart;
  @BindView(R.id.navigation_drawer_bottomPart) NavigationView mNavViewBottomPart;
  @BindView(R.id.drawer_layout) DrawerLayout mDrawerLayout;

  @Override protected void onCreate(Bundle savedInstanceState) {
    setContentView(R.layout.activity_start);
    super.onCreate(savedInstanceState);
    setUpUI();

    mFab.setOnClickListener(
        v -> Snackbar.make(v, "Replace with your own action", Snackbar.LENGTH_LONG)
            .setAction("Action", null)
            .show());
  }

  private void setUpUI() {
    setSupportActionBar(mToolbar);

    ActionBarDrawerToggle toggle =
        new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.navigation_drawer_open,
            R.string.navigation_drawer_close);
    mDrawerLayout.addDrawerListener(toggle);
    toggle.syncState();

    mNavViewTopPart.setNavigationItemSelectedListener(this);
    mNavViewBottomPart.setNavigationItemSelectedListener(this);
  }

  @Override public void onBackPressed() {
    DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
    if (drawer.isDrawerOpen(GravityCompat.START)) {
      drawer.closeDrawer(GravityCompat.START);
    } else {
      super.onBackPressed();
    }
  }

  @Override public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.menu, menu);
    return true;
  }

  @Override public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case R.id.action_settings:
        return true;
      default:
        return super.onOptionsItemSelected(item);
    }
  }

  @Override public boolean onNavigationItemSelected(@NonNull MenuItem item) {
    switch (item.getItemId()) {
      case R.id.nav_main:
        mNavigator.clearBackStack(this);
        mNavigator.replaceFragment(StartActivity.this, R.id.container_main,
            MainFragment.newInstance());
        break;
      case R.id.nav_booking:
        mNavigator.clearBackStack(this);
        mNavigator.replaceFragment(StartActivity.this, R.id.container_main,
            BookingFragment.newInstance());
        break;
      case R.id.nav_my_book:
        mNavigator.clearBackStack(this);
        mNavigator.replaceFragment(StartActivity.this, R.id.container_main,
            MyBookFragment.newInstance());
        break;
      case R.id.nav_my_bonus:
        mNavigator.clearBackStack(this);
        mNavigator.replaceFragment(StartActivity.this, R.id.container_main,
            MyBonusFragment.newInstance());
        break;
      case R.id.nav_share:
        showAlertMessage("Talk about how to share ???");
        break;
      case R.id.nav_our_work:
        mNavigator.clearBackStack(this);
        mNavigator.replaceFragment(StartActivity.this, R.id.container_main,
            OurWorkFragment.newInstance());
        break;
      case R.id.nav_contacts:
        mNavigator.clearBackStack(this);
        mNavigator.replaceFragment(StartActivity.this, R.id.container_main,
            ContactsFragment.newInstance());
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
    mNavigator.addFragment(StartActivity.this, R.id.container_main, new MainFragment());
  }

  @Override public void showAppBarLayout() {
    AppBarLayout appBarLayout = (AppBarLayout) this.findViewById(R.id.appBar);
    appBarLayout.setExpanded(true, false);
  }
}
