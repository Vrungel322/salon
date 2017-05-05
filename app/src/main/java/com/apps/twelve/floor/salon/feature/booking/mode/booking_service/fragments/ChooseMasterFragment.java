package com.apps.twelve.floor.salon.feature.booking.mode.booking_service.fragments;

import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import butterknife.BindView;
import com.apps.twelve.floor.salon.R;
import com.apps.twelve.floor.salon.base.BaseFragment;
import com.apps.twelve.floor.salon.data.model.MasterEntity;
import com.apps.twelve.floor.salon.feature.booking.mode.adapters.MastersVerticalAdapter;
import com.apps.twelve.floor.salon.feature.booking.mode.booking_service.presenters.ChooseMasterFragmentPresenter;
import com.apps.twelve.floor.salon.feature.booking.mode.booking_service.views.IChooseMasterFragmentView;
import com.apps.twelve.floor.salon.utils.ItemClickSupport;
import com.arellomobile.mvp.presenter.InjectPresenter;
import java.util.List;

/**
 * Created by Vrungel on 28.03.2017.
 */

public class ChooseMasterFragment extends BaseFragment implements IChooseMasterFragmentView {

  @InjectPresenter ChooseMasterFragmentPresenter mChooseMasterFragmentPresenter;

  private static final int SELECTED_ITEM_DEFAULT = -1;

  @BindView(R.id.tvServiceName) TextView mTextViewServiceName;
  @BindView(R.id.tvServiceTime) TextView mTextViewServiceTime;
  @BindView(R.id.tvServiceDuration) TextView mTextViewServiceDuration;
  @BindView(R.id.rvMasters) RecyclerView mRecyclerViewMasters;
  @BindView(R.id.progressBarChooseMaster) ProgressBar mProgressBar;
  @BindView(R.id.nestedScrollChooseMaster) NestedScrollView mNestedScroll;
  @BindView(R.id.viewBlockedClickRv) View mViewBlockedClickRv;

  private MastersVerticalAdapter mMastersVerticalAdapter;

  public static ChooseMasterFragment newInstance() {
    Bundle args = new Bundle();
    ChooseMasterFragment fragment = new ChooseMasterFragment();
    fragment.setArguments(args);
    return fragment;
  }

  public ChooseMasterFragment() {
    super(R.layout.fragment_choose_master);
  }

  @Override public void setUpUi() {
    mRecyclerViewMasters.setLayoutManager(new GridLayoutManager(getContext(), 2));
    mMastersVerticalAdapter = new MastersVerticalAdapter();
    mRecyclerViewMasters.setAdapter(mMastersVerticalAdapter);
    mRecyclerViewMasters.setNestedScrollingEnabled(false);
    mRecyclerViewMasters.setFocusable(false);
    ItemClickSupport.addTo(mRecyclerViewMasters)
        .setOnItemClickListener((recyclerView, position, v) -> {
          if (position == 0) {
            mChooseMasterFragmentPresenter.setAnyMasterSelected();
          } else {
            mChooseMasterFragmentPresenter.setSelectedItem(position - 1);
          }
        });
  }

  @Override public void showMasters(List<MasterEntity> masterEntities) {
    mMastersVerticalAdapter.addListMasterEntity(masterEntities);
  }

  @Override public void hideProgressBar() {
    mProgressBar.setVisibility(View.GONE);
    mNestedScroll.setVisibility(View.VISIBLE);
  }

  @Override public void setSelectedItem(int position) {
    mMastersVerticalAdapter.setSelectedItem(position);
  }

  @Override
  public void setUpRedSquare(String serviceName, String serviceTime, String serviceDuration) {
    mTextViewServiceName.setText(serviceName);
    mTextViewServiceTime.setText(serviceTime);
    mTextViewServiceDuration.setText(serviceDuration);
  }
}