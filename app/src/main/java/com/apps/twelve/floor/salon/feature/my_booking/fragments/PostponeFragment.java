package com.apps.twelve.floor.salon.feature.my_booking.fragments;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import br.com.simplepass.loading_button_lib.customViews.CircularProgressButton;
import butterknife.BindView;
import butterknife.OnClick;
import com.apps.twelve.floor.salon.R;
import com.apps.twelve.floor.salon.base.BaseFragment;
import com.apps.twelve.floor.salon.data.model.DataServiceEntity;
import com.apps.twelve.floor.salon.feature.booking.mode.adapters.DatesHorizontalAdapter;
import com.apps.twelve.floor.salon.feature.booking.mode.adapters.DatesInMonthViewPagerAdapter;
import com.apps.twelve.floor.salon.feature.booking.mode.adapters.ScheduleAdapter;
import com.apps.twelve.floor.salon.feature.my_booking.presenters.PostponeFragmentPresenter;
import com.apps.twelve.floor.salon.feature.my_booking.views.IPostponeFragmentView;
import com.apps.twelve.floor.salon.utils.ItemClickSupport;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import java.util.ArrayList;
import java.util.List;

import static com.apps.twelve.floor.salon.utils.Constants.FragmentsArgumentKeys.ENTITY_ID;
import static com.apps.twelve.floor.salon.utils.Constants.FragmentsArgumentKeys.MASTER_ID;
import static com.apps.twelve.floor.salon.utils.Constants.FragmentsArgumentKeys.MASTER_NAME;
import static com.apps.twelve.floor.salon.utils.Constants.FragmentsArgumentKeys.SERVICE_NAME;

/**
 * Created by Alexandra on 25.04.2017.
 */

public class PostponeFragment extends BaseFragment implements IPostponeFragmentView {
  private static final int SCHEDULE_SPAN_COUNT = 6;
  private static final int SELECTED_ITEM_DEFAULT = -1;

  @InjectPresenter PostponeFragmentPresenter mPostponeFragmentPresenter;
  @BindView(R.id.tv_service_description) TextView mTextViewServiceName;
  @BindView(R.id.tv_master_description) TextView mTextViewMasterDescription;
  @BindView(R.id.bPrevDay) ImageView mImageViewPrevDay;
  @BindView(R.id.vpDatesOfMonth) ViewPager mViewPagerDatesOfMonth;
  @BindView(R.id.bNextDay) ImageView mImageViewNextDay;
  @BindView(R.id.rvDates) RecyclerView mRecyclerViewHorizontalDates;
  @BindView(R.id.tvDateInText) TextView mTextViewDateInText;
  @BindView(R.id.rvScheduleInDay) RecyclerView mRecyclerViewScheduleInDay;
  @BindView(R.id.relativeLayoutNotTime) RelativeLayout mRelativeLayoutNotTime;
  @BindView(R.id.linearLayoutTime) LinearLayout mLinearLayoutTime;
  @BindView(R.id.buttonBookingPhone) Button mButtonBookingPhone;
  @BindView(R.id.textViewCurrentDate) TextView mTextViewCurrentDate;
  @BindView(R.id.nestedScrollBookingTime) NestedScrollView mNestedScrollBookingTime;
  @BindView(R.id.progressBarBookingTime) ProgressBar mProgressBarBookingTime;
  @BindView(R.id.btnConfirmPostpone) CircularProgressButton mBtnConfirmPostpone;
  private List<DataServiceEntity> mDays = new ArrayList<>();
  private DatesHorizontalAdapter mDatesHorizontalAdapter;
  private ScheduleAdapter mScheduleAdapter;
  private DatesInMonthViewPagerAdapter mDatesInMonthViewPagerAdapter;
  private Integer mEntityId;

  public PostponeFragment() {
    super(R.layout.fragment_postpone);
  }

  public static PostponeFragment newInstance(String serviceName, String masterName,
      Integer masterId, Integer entityId) {
    Bundle args = new Bundle();
    args.putString(SERVICE_NAME, serviceName);
    args.putString(MASTER_NAME, masterName);
    args.putInt(MASTER_ID, masterId);
    args.putInt(ENTITY_ID, entityId);
    PostponeFragment fragment = new PostponeFragment();
    fragment.setArguments(args);
    return fragment;
  }

  @ProvidePresenter PostponeFragmentPresenter providePostponeFragmentPresenter() {
    return new PostponeFragmentPresenter(String.valueOf(getArguments().getInt(MASTER_ID)));
  }

  @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

    /* turn off scrolling */
    Toolbar mToolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);

    AppBarLayout.LayoutParams toolbarLayoutParams =
        (AppBarLayout.LayoutParams) mToolbar.getLayoutParams();
    toolbarLayoutParams.setScrollFlags(0);
    mToolbar.setLayoutParams(toolbarLayoutParams);

    /* get booking information */
    mEntityId = getArguments().getInt(ENTITY_ID);

    setUpRedSquare(getArguments().getString(SERVICE_NAME), getArguments().getString(MASTER_NAME));
  }

  @OnClick(R.id.btnConfirmPostpone) void confirmPostpone() {
    mBtnConfirmPostpone.startAnimation();
    mPostponeFragmentPresenter.saveNewTime(String.valueOf(mEntityId));
  }

  @Override public void stopAnimation() {
    mBtnConfirmPostpone.doneLoadingAnimation(
        ContextCompat.getColor(getContext(), R.color.colorAccent),
        BitmapFactory.decodeResource(getResources(), R.drawable.ic_done_white_48dp));
  }

  @Override public void revertAnimation() {
    mBtnConfirmPostpone.revertAnimation();
  }

  @Override public void closeTheFragment() {
    getActivity().onBackPressed();
  }

  @Override public void showErrorMessage(int message) {
    showAlertMessage(getString(R.string.title_write_error), getString(message));
  }

  @Override public void setUpUi(List<DataServiceEntity> days) {
    //viewPager
    mDays = days;
    mDatesInMonthViewPagerAdapter = new DatesInMonthViewPagerAdapter(getContext(), days);
    mViewPagerDatesOfMonth.setAdapter(mDatesInMonthViewPagerAdapter);
    mViewPagerDatesOfMonth.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
      @Override
      public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

      }

      @Override public void onPageSelected(int position) {
        chainViewPagerRecyclerView(position);
        mScheduleAdapter.setTimeSchedule(
            mDays.get(mViewPagerDatesOfMonth.getCurrentItem()).getScheduleEntities());
        mPostponeFragmentPresenter.setDateToTv();
      }

      @Override public void onPageScrollStateChanged(int state) {

      }
    });

    //Horizontal RV
    mRecyclerViewHorizontalDates.setLayoutManager(
        new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
    mDatesHorizontalAdapter = new DatesHorizontalAdapter();
    mRecyclerViewHorizontalDates.setAdapter(mDatesHorizontalAdapter);
    chainViewPagerRecyclerView(mViewPagerDatesOfMonth.getCurrentItem());
    ItemClickSupport.addTo(mRecyclerViewHorizontalDates)
        .setOnItemClickListener((recyclerView, position, v) -> {
          mPostponeFragmentPresenter.setSelectedDay(position);
          mViewPagerDatesOfMonth.setCurrentItem(position);
        });

    //Schedule RV (hours)
    mRecyclerViewScheduleInDay.setLayoutManager(
        new GridLayoutManager(getContext(), SCHEDULE_SPAN_COUNT));
    mScheduleAdapter = new ScheduleAdapter();
    mRecyclerViewScheduleInDay.setAdapter(mScheduleAdapter);
    ItemClickSupport.addTo(mRecyclerViewScheduleInDay)
        .setOnItemClickListener(
            (recyclerView, position, v) -> mPostponeFragmentPresenter.setSelectedTime(position));

    mDatesHorizontalAdapter.addListWorkStartEndEntity(mDays);
    mScheduleAdapter.setTimeSchedule(
        mDays.get(mViewPagerDatesOfMonth.getCurrentItem()).getScheduleEntities());
  }

  private void chainViewPagerRecyclerView(int currentItem) {
    mDatesHorizontalAdapter.setSelectedItem(currentItem);
    mRecyclerViewHorizontalDates.smoothScrollToPosition(currentItem);
  }

  @Override public void setSelectedTime(int position) {
    mScheduleAdapter.setSelectedItem(position);
  }

  @Override public void setSelectedDay(int position) {
    mDatesHorizontalAdapter.setSelectedItem(position);
  }

  @Override public void setTextToDayTv() {
    mTextViewDateInText.setText(
        mDatesInMonthViewPagerAdapter.getEntity(mViewPagerDatesOfMonth.getCurrentItem()));
  }

  @Override public void showTimeBooking() {
    mLinearLayoutTime.setVisibility(View.VISIBLE);
  }

  @Override public void showNotTime() {
    mRelativeLayoutNotTime.setVisibility(View.VISIBLE);
    mTextViewCurrentDate.setVisibility(View.VISIBLE);
  }

  @Override public void hideProgressBarBookingTime() {
    mProgressBarBookingTime.setVisibility(View.GONE);
    mNestedScrollBookingTime.setVisibility(View.VISIBLE);
  }

  @Override public void clearSelectedTime() {
    mScheduleAdapter.setSelectedItem(SELECTED_ITEM_DEFAULT);
  }

  @Override public void setUpRedSquare(String serviceName, String masterName) {
    mTextViewMasterDescription.setText(masterName);
    mTextViewServiceName.setText(serviceName);
  }

  @Override public void timeIsNotAvailable() {
    showToastMessage("Time is not Available");
  }

  @OnClick(R.id.bPrevDay) public void bPrevDayClicked() {
    if (mViewPagerDatesOfMonth.getCurrentItem() > 0) {
      mViewPagerDatesOfMonth.setCurrentItem(mViewPagerDatesOfMonth.getCurrentItem() - 1);
    }
  }

  @OnClick(R.id.bNextDay) public void bNextDayClicked() {
    if (mViewPagerDatesOfMonth.getCurrentItem() < mDays.size()) {
      mViewPagerDatesOfMonth.setCurrentItem(mViewPagerDatesOfMonth.getCurrentItem() + 1);
    }
  }

  @OnClick(R.id.buttonBookingPhone) public void onViewClicked() {
    Intent intent = new Intent(Intent.ACTION_DIAL);
    intent.setData(Uri.parse("tel:" + mButtonBookingPhone.getText()));
    startActivity(intent);
  }
}
