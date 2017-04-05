package com.apps.twelve.floor.salon.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import butterknife.BindView;
import butterknife.OnClick;
import com.apps.twelve.floor.salon.R;
import com.apps.twelve.floor.salon.mvp.presenters.pr_fragments.BookingDetailFragmentPresenter;
import com.apps.twelve.floor.salon.mvp.views.IBookingDetailFragmentView;
import com.apps.twelve.floor.salon.ui.adapters.ViewPagerBookingStepsAdapter;
import com.apps.twelve.floor.salon.ui.base.BaseFragment;
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
  @BindView(R.id.bNextStep) Button mButtonNextStep;
  @BindView(R.id.bPrevStep) Button mButtonPrevStep;

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
  }

  @Override public void setUpViewPager() {
    String serializedData = (String) getArguments().getSerializable(BOOKING_SCREEN_TO_START);
    String screenToStart = serializedData != null ? serializedData : "";
    switch (screenToStart) {
      case CHOOSE_MASTER:
        showChooseMasterFragment();
        break;
      case CHOOSE_SERVICE:
        showChooseServiceFragment();
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

  @Override public void showMessageWarning() {
    showAlertMessage(getString(R.string.title_write_error),
        getString(R.string.description_write_error));
  }

  @OnClick(R.id.bNextStep) public void bNextStepClicked() {
    mBookingDetailFragmentPresenter.nextStep(mViewPager.getCurrentItem());
  }

  @OnClick(R.id.bPrevStep) public void bPrevStepClicked() {
    mBookingDetailFragmentPresenter.prevStep(mViewPager.getCurrentItem());
  }

  private void showChooseMasterFragment() {
    ViewPagerBookingStepsAdapter adapter =
        new ViewPagerBookingStepsAdapter(getActivity().getSupportFragmentManager());
    adapter.addFragment(ChooseMasterMasterFragment.newInstance(), getString(R.string.tab_master));
    adapter.addFragment(ChooseMasterServiceFragment.newInstance(),
        getString(R.string.tab_services));
    adapter.addFragment(ChooseMasterTimeFragment.newInstance(), getString(R.string.tab_time));
    adapter.addFragment(BookingContactFragment.newInstance(), getString(R.string.tab_data));
    mViewPager.setAdapter(adapter);
    mTabLayout.setupWithViewPager(mViewPager);
    ViewUtil.TabLayoutUtils.enableTabs(mTabLayout, false);
  }

  private void showChooseServiceFragment() {
    ViewPagerBookingStepsAdapter adapter =
        new ViewPagerBookingStepsAdapter(getActivity().getSupportFragmentManager());
    adapter.addFragment(ChooseServiceFragment.newInstance(), getString(R.string.tab_services));
    adapter.addFragment(ChooseTimeFragment.newInstance(), getString(R.string.tab_time));
    adapter.addFragment(ChooseMasterFragment.newInstance(), getString(R.string.tab_master));
    adapter.addFragment(BookingContactFragment.newInstance(), getString(R.string.tab_data));
    mViewPager.setAdapter(adapter);
    mTabLayout.setupWithViewPager(mViewPager);
    ViewUtil.TabLayoutUtils.enableTabs(mTabLayout, false);
  }
}
