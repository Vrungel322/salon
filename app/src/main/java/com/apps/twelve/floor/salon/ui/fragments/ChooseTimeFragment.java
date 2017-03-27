package com.apps.twelve.floor.salon.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import com.apps.twelve.floor.salon.R;
import com.apps.twelve.floor.salon.mvp.data.model.WorkStartEndEntity;
import com.apps.twelve.floor.salon.mvp.presenters.fragments.ChooseTimeFragmentPresenter;
import com.apps.twelve.floor.salon.mvp.views.IChooseTimeFragmentView;
import com.apps.twelve.floor.salon.ui.adapters.DatesAdapter;
import com.apps.twelve.floor.salon.ui.adapters.DatesInMonthPagerAdapter;
import com.apps.twelve.floor.salon.ui.adapters.ScheduleAdapter;
import com.apps.twelve.floor.salon.ui.base.BaseFragment;
import com.apps.twelve.floor.salon.utils.Constants;
import com.apps.twelve.floor.salon.utils.ItemClickSupport;
import com.arellomobile.mvp.presenter.InjectPresenter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vrungel on 27.03.2017.
 */

public class ChooseTimeFragment extends BaseFragment implements IChooseTimeFragmentView {
  private static final int SCHEDULE_SPAN_COUNT = 6;
  @InjectPresenter ChooseTimeFragmentPresenter mChooseTimeFragmentPresenter;
  @BindView(R.id.tvServiceName) TextView mTextViewServiceName;
  @BindView(R.id.bPrevDay) ImageView mImageViewPrevDay;
  @BindView(R.id.vpDatesOfMonth) ViewPager mViewPagerDatesOfMonth;
  @BindView(R.id.bNextDay) ImageView mImageViewNextDay;
  @BindView(R.id.rvDates) RecyclerView mRecyclerViewHorizontalDates;
  @BindView(R.id.tvDateInText) TextView mTextViewDateInText;
  @BindView(R.id.rvScheduleInDay) RecyclerView mRecyclerViewScheduleInDay;
  private String serviceName;
  private List<String> mDays = new ArrayList<>();
  private List<WorkStartEndEntity> mWorkStartEndEntities;
  private DatesAdapter mAdapter;
  private ScheduleAdapter mScheduleAdapter;

  public static ChooseTimeFragment newInstance() {
    Bundle args = new Bundle();
    args.putString(Constants.FragmentsArgumentKeys.SERVICE_NAME, "ТЕСТОВАЯ УСЛУГА");
    ChooseTimeFragment fragment = new ChooseTimeFragment();
    fragment.setArguments(args);
    return fragment;
  }

  public ChooseTimeFragment() {
    super(R.layout.fragment_choose_time);
  }

  @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    serviceName = getArguments().getString(Constants.FragmentsArgumentKeys.SERVICE_NAME);
    mTextViewServiceName.setText(serviceName);
  }

  @Override public void setUpUi(List<String> days) {
    //viewPager
    mDays = days;
    DatesInMonthPagerAdapter datesInMonthPagerAdapter =
        new DatesInMonthPagerAdapter(getContext(), days);
    mViewPagerDatesOfMonth.setAdapter(datesInMonthPagerAdapter);
    mViewPagerDatesOfMonth.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
      @Override
      public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

      }

      @Override public void onPageSelected(int position) {
        chainViewPagerRecyclerView(position);
        mScheduleAdapter.setTimeSchedule(
            mWorkStartEndEntities.get(mViewPagerDatesOfMonth.getCurrentItem()).getFreeTime());
      }

      @Override public void onPageScrollStateChanged(int state) {

      }
    });

    //Horizontal RV
    mRecyclerViewHorizontalDates.setLayoutManager(
        new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
    mAdapter = new DatesAdapter();
    mRecyclerViewHorizontalDates.setAdapter(mAdapter);
    chainViewPagerRecyclerView(mViewPagerDatesOfMonth.getCurrentItem());
    ItemClickSupport.addTo(mRecyclerViewHorizontalDates)
        .setOnItemClickListener((recyclerView, position, v) -> {
          mAdapter.setSelectedItem(position);
          mViewPagerDatesOfMonth.setCurrentItem(position);
        });

    //Schedule RV (hours)
    mRecyclerViewScheduleInDay.setLayoutManager(
        new GridLayoutManager(getContext(), SCHEDULE_SPAN_COUNT));
    mScheduleAdapter = new ScheduleAdapter();
    mRecyclerViewScheduleInDay.setAdapter(mScheduleAdapter);
    ItemClickSupport.addTo(mRecyclerViewScheduleInDay)
        .setOnItemClickListener(
            (recyclerView, position, v) -> mChooseTimeFragmentPresenter.setSelectedTime(position));
  }

  private void chainViewPagerRecyclerView(int currentItem) {
    mAdapter.setSelectedItem(currentItem);
    mRecyclerViewHorizontalDates.smoothScrollToPosition(currentItem);
  }

  @Override public void updateWorkSchedule(List<WorkStartEndEntity> workStartEndEntities) {
    mWorkStartEndEntities = workStartEndEntities;
    mAdapter.addListWorkStartEndEntity(workStartEndEntities);
    mScheduleAdapter.setTimeSchedule(
        mWorkStartEndEntities.get(mViewPagerDatesOfMonth.getCurrentItem()).getFreeTime());
  }

  @Override public void setSelectedTime(int position) {
    mScheduleAdapter.setSelectedItem(position);
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
}
