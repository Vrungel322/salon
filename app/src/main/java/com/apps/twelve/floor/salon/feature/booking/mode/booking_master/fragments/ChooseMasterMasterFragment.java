package com.apps.twelve.floor.salon.feature.booking.mode.booking_master.fragments;

import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import butterknife.BindView;
import com.apps.twelve.floor.salon.R;
import com.apps.twelve.floor.salon.base.BaseFragment;
import com.apps.twelve.floor.salon.data.model.MasterEntity;
import com.apps.twelve.floor.salon.feature.booking.mode.adapters.MastersVerticalAdapter;
import com.apps.twelve.floor.salon.feature.booking.mode.booking_master.presenters.ChooseMasterMasterFragmentPresenter;
import com.apps.twelve.floor.salon.feature.booking.mode.booking_master.views.IChooseMasterMasterFragmentView;
import com.apps.twelve.floor.salon.utils.ItemClickSupport;
import com.apps.twelve.floor.salon.utils.ViewUtil;
import com.arellomobile.mvp.presenter.InjectPresenter;
import java.util.List;

public class ChooseMasterMasterFragment extends BaseFragment
    implements IChooseMasterMasterFragmentView {

  @InjectPresenter ChooseMasterMasterFragmentPresenter mChooseMasterMasterFragmentPresenter;

  @BindView(R.id.rvMasters) RecyclerView mRecyclerViewMasters;
  @BindView(R.id.progressBarChooseMaster) ProgressBar mProgressBar;
  @BindView(R.id.nestedScrollChooseMaster) NestedScrollView mNestedScroll;

  private MastersVerticalAdapter mMastersVerticalAdapter;

  public ChooseMasterMasterFragment() {
    super(R.layout.fragment_choose_master_master);
  }

  public static ChooseMasterMasterFragment newInstance() {
    Bundle args = new Bundle();
    ChooseMasterMasterFragment fragment = new ChooseMasterMasterFragment();
    fragment.setArguments(args);
    return fragment;
  }

  @Override public void setUpUi() {
    mRecyclerViewMasters.setLayoutManager(
        new GridLayoutManager(getContext(), ViewUtil.getRotation(getContext())));
    mMastersVerticalAdapter = new MastersVerticalAdapter(getContext());
    mRecyclerViewMasters.setAdapter(mMastersVerticalAdapter);
    mRecyclerViewMasters.setNestedScrollingEnabled(false);
    mRecyclerViewMasters.setFocusable(false);
    ItemClickSupport.addTo(mRecyclerViewMasters)
        .setOnItemClickListener((recyclerView, position, v) -> {
          if (position == 0) {
            mChooseMasterMasterFragmentPresenter.setAnyMasterSelected();
          } else {
            mChooseMasterMasterFragmentPresenter.setSelectedItem(position - 1);
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
}
