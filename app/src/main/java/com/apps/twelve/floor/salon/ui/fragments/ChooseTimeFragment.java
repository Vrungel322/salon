package com.apps.twelve.floor.salon.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
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
  @InjectPresenter ChooseTimeFragmentPresenter mChooseTimeFragmentPresenter;
  @BindView(R.id.tvServiceName) TextView mTextViewServiceName;
  @BindView(R.id.bPrevDay) ImageView mImageViewPrevDay;
  @BindView(R.id.vpDatesOfMonth) ViewPager mViewPagerDatesOfMonth;
  @BindView(R.id.bNextDay) ImageView mImageViewNextDay;
  @BindView(R.id.rvDates) RecyclerView mRecyclerViewDates;
  @BindView(R.id.tvDateInText) TextView mTextViewDateInText;
  @BindView(R.id.rvScheduleInDay) RecyclerView mRecyclerViewScheduleInDay;
  private String serviceName;
  private List<String> mDays = new ArrayList<>();
  private DatesAdapter mAdapter;

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

    //Horizontal RV
    mRecyclerViewDates.setLayoutManager(
        new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
    mAdapter = new DatesAdapter();
    mRecyclerViewDates.setAdapter(mAdapter);
    ItemClickSupport.addTo(mRecyclerViewDates)
        .setOnItemClickListener((recyclerView, position, v) -> {

        });
  }

  @Override public void updateWorkSchedule(List<WorkStartEndEntity> workStartEndEntities) {
    mAdapter.addListWorkStartEndEntity(workStartEndEntities);
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
