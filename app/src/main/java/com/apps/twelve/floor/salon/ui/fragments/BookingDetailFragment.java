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
import com.apps.twelve.floor.salon.mvp.presenters.fragments.BookingDetailFragmentPresenter;
import com.apps.twelve.floor.salon.mvp.views.IBookingDetailFragmentView;
import com.apps.twelve.floor.salon.ui.adapters.BookingStepsAdapter;
import com.apps.twelve.floor.salon.ui.base.BaseFragment;
import com.apps.twelve.floor.salon.utils.ViewUtil;
import com.arellomobile.mvp.presenter.InjectPresenter;

import static com.apps.twelve.floor.salon.R.id.tabLayout;

/**
 * Created by Vrungel on 23.03.2017.
 */

public class BookingDetailFragment extends BaseFragment implements IBookingDetailFragmentView {
  @InjectPresenter BookingDetailFragmentPresenter mBookingDetailFragmentPresenter;
  @BindView(tabLayout) TabLayout mTabLayout;
  @BindView(R.id.vpBookingSteps) ViewPager mViewPager;
  @BindView(R.id.bNextStep) Button mButtonNextStep;
  @BindView(R.id.bPrevStep) Button mButtonPrevStep;

  public static BookingDetailFragment newInstance() {
    Bundle args = new Bundle();
    BookingDetailFragment fragment = new BookingDetailFragment();
    fragment.setArguments(args);
    return fragment;
  }

  public BookingDetailFragment() {
    super(R.layout.fragment_booking_detail);
  }

  @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
  }

  @Override public void setUpViewPager() {
    BookingStepsAdapter adapter =
        new BookingStepsAdapter(getActivity().getSupportFragmentManager(), mContext);
    mViewPager.setAdapter(adapter);
    mTabLayout.setupWithViewPager(mViewPager);
    ViewUtil.TabLayoutUtils.enableTabs(mTabLayout, false);
  }

  @OnClick(R.id.bNextStep) public void bNextStepClicked() {
    mViewPager.setCurrentItem(mViewPager.getCurrentItem() + 1, true);
  }

  @OnClick(R.id.bPrevStep) public void bPrevStepClicked() {
    if (mViewPager.getCurrentItem() > 0){
      mViewPager.setCurrentItem(mViewPager.getCurrentItem() - 1, true);
    }
  }
}
