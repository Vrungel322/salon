package com.apps.twelve.floor.salon.feature.booking.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import butterknife.BindView;
import butterknife.OnClick;
import com.apps.twelve.floor.salon.R;
import com.apps.twelve.floor.salon.base.BaseFragment;
import com.apps.twelve.floor.salon.feature.booking.adapters.ViewPagerBookingStepsAdapter;
import com.apps.twelve.floor.salon.feature.booking.mode.booking_master.fragments.BookingMasterContactFragment;
import com.apps.twelve.floor.salon.feature.booking.mode.booking_master.fragments.ChooseMasterMasterFragment;
import com.apps.twelve.floor.salon.feature.booking.mode.booking_master.fragments.ChooseMasterServiceFragment;
import com.apps.twelve.floor.salon.feature.booking.mode.booking_master.fragments.ChooseMasterTimeFragment;
import com.apps.twelve.floor.salon.feature.booking.mode.booking_service.fragments.BookingContactFragment;
import com.apps.twelve.floor.salon.feature.booking.mode.booking_service.fragments.ChooseMasterFragment;
import com.apps.twelve.floor.salon.feature.booking.mode.booking_service.fragments.ChooseServiceFragment;
import com.apps.twelve.floor.salon.feature.booking.mode.booking_service.fragments.ChooseTimeFragment;
import com.apps.twelve.floor.salon.feature.booking.presenters.BookingDetailFragmentPresenter;
import com.apps.twelve.floor.salon.feature.booking.views.IBookingDetailFragmentView;
import com.apps.twelve.floor.salon.utils.Constants;
import com.apps.twelve.floor.salon.utils.ViewUtil;
import com.arellomobile.mvp.presenter.InjectPresenter;

import static com.apps.twelve.floor.salon.R.id.tabLayout;
import static com.apps.twelve.floor.salon.utils.Constants.FragmentToShow.CHOOSE_MASTER;
import static com.apps.twelve.floor.salon.utils.Constants.FragmentToShow.CHOOSE_SERVICE;
import static com.apps.twelve.floor.salon.utils.Constants.FragmentsArgumentKeys.BOOKING_SCREEN_TO_START;

/**
 * Created by Vrungel on 23.03.2017.
 */

public class BookingDetailFragment extends BaseFragment implements IBookingDetailFragmentView {

  @InjectPresenter BookingDetailFragmentPresenter mBookingDetailFragmentPresenter;

  @BindView(tabLayout) TabLayout mTabLayout;
  @BindView(R.id.vpBookingSteps) ViewPager mViewPager;
  @BindView(R.id.linearLayoutState) LinearLayout mLinearLayoutState;

  public static BookingDetailFragment newInstance(String screenToStart) {
    Bundle args = new Bundle();
    args.putSerializable(BOOKING_SCREEN_TO_START, screenToStart);
    BookingDetailFragment fragment = new BookingDetailFragment();
    fragment.setArguments(args);
    return fragment;
  }

  public BookingDetailFragment() {
    super(R.layout.fragment_booking_detail);
  }

  @Override public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
      @Override
      public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

      }

      @Override public void onPageSelected(int position) {
        if (position == 0) {
          mBookingDetailFragmentPresenter.isVisibleFragment(true);
        } else {
          mBookingDetailFragmentPresenter.isVisibleFragment(false);
        }
      }

      @Override public void onPageScrollStateChanged(int state) {

      }
    });
  }

  @Override public void setUpViewPager() {
    String serializedData = (String) getArguments().getSerializable(BOOKING_SCREEN_TO_START);
    String screenToStart = serializedData != null ? serializedData : "";
    switch (screenToStart) {
      case CHOOSE_MASTER:
        showChooseMasterFragment();
        mBookingDetailFragmentPresenter.setMode(Constants.BookingMode.START_WITH_MASTER);
        break;
      case CHOOSE_SERVICE:
        showChooseServiceFragment();
        mBookingDetailFragmentPresenter.setMode(Constants.BookingMode.START_WITH_SERVICE);
        break;
      default:
        break;
    }
  }

  @Override public void goNext(int position) {
    mViewPager.setCurrentItem(position, true);
  }

  @Override public void goPrev(int position) {
    mViewPager.setCurrentItem(position, true);
  }

  @Override public void hideKeyboard() {
    ViewUtil.hideKeyboard(getActivity());
  }

  @Override public void replaceTitleNextButton(boolean state) {
    if (state) {
      mLinearLayoutState.setVisibility(View.GONE);
    } else {
      mLinearLayoutState.setVisibility(View.VISIBLE);
    }
  }

  @Override public void showMessageWarning(int warning) {
    showAlertMessage(getString(R.string.title_write_error), getString(warning));
  }

  @Override public void stateBooking() {
    if (mViewPager.getCurrentItem() == 0) {
      mNavigator.replaceFragment((AppCompatActivity) getActivity(), R.id.container_booking,
          BookingFragment.newInstance());
    } else {
      mBookingDetailFragmentPresenter.prevStep(mViewPager.getCurrentItem());
    }
  }

  @OnClick(R.id.bNextStep) public void bNextStepClicked() {
    mBookingDetailFragmentPresenter.nextStep(mViewPager.getCurrentItem());
  }

  private void showChooseMasterFragment() {
    ViewPagerBookingStepsAdapter adapter =
        new ViewPagerBookingStepsAdapter(this.getChildFragmentManager());
    adapter.addFragment(ChooseMasterMasterFragment.newInstance(), getString(R.string.tab_master));
    adapter.addFragment(ChooseMasterServiceFragment.newInstance(),
        getString(R.string.tab_services));
    adapter.addFragment(ChooseMasterTimeFragment.newInstance(), getString(R.string.tab_time));
    adapter.addFragment(BookingMasterContactFragment.newInstance(), getString(R.string.tab_data));
    mViewPager.setOffscreenPageLimit(3);
    mViewPager.setAdapter(adapter);
    mTabLayout.setupWithViewPager(mViewPager);
    ViewUtil.TabLayoutUtils.enableTabs(mTabLayout, false);
  }

  private void showChooseServiceFragment() {
    ViewPagerBookingStepsAdapter adapter =
        new ViewPagerBookingStepsAdapter(this.getChildFragmentManager());
    adapter.addFragment(ChooseServiceFragment.newInstance(), getString(R.string.tab_services));
    adapter.addFragment(ChooseTimeFragment.newInstance(), getString(R.string.tab_time));
    adapter.addFragment(ChooseMasterFragment.newInstance(), getString(R.string.tab_master));
    adapter.addFragment(BookingContactFragment.newInstance(), getString(R.string.tab_data));
    mViewPager.setOffscreenPageLimit(3);
    mViewPager.setAdapter(adapter);
    mTabLayout.setupWithViewPager(mViewPager);
    ViewUtil.TabLayoutUtils.enableTabs(mTabLayout, false);
  }
}
