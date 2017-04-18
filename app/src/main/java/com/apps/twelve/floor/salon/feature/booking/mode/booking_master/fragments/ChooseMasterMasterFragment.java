package com.apps.twelve.floor.salon.feature.booking.mode.booking_master.fragments;

import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ProgressBar;
import butterknife.BindView;
import com.apps.twelve.floor.salon.R;
import com.apps.twelve.floor.salon.data.model.MasterEntity;
import com.apps.twelve.floor.salon.feature.booking.mode.booking_master.presenters.ChooseMasterMasterFragmentPresenter;
import com.apps.twelve.floor.salon.feature.booking.mode.booking_master.views.IChooseMasterMasterView;
import com.apps.twelve.floor.salon.feature.booking.mode.adapters.MastersVerticalAdapter;
import com.apps.twelve.floor.salon.base.BaseFragment;
import com.apps.twelve.floor.salon.utils.ItemClickSupport;
import com.arellomobile.mvp.presenter.InjectPresenter;
import java.util.List;

public class ChooseMasterMasterFragment extends BaseFragment implements IChooseMasterMasterView {

  @InjectPresenter ChooseMasterMasterFragmentPresenter mChooseMasterMasterFragmentPresenter;
  private static final int SELECTED_ITEM_DEFAULT = -1;

  @BindView(R.id.rvMasters) RecyclerView mRecyclerViewMasters;
  @BindView(R.id.cbAnyMaster) CheckBox mCheckBoxAnyMaster;
  @BindView(R.id.progressBarChooseMaster) ProgressBar mProgressBar;
  @BindView(R.id.nestedScrollChooseMaster) NestedScrollView mNestedScroll;
  @BindView(R.id.viewBlockedClickRv) View mViewBlockedClickRv;

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
    mRecyclerViewMasters.setLayoutManager(new GridLayoutManager(getContext(), 2));
    mMastersVerticalAdapter = new MastersVerticalAdapter();
    mRecyclerViewMasters.setAdapter(mMastersVerticalAdapter);
    mRecyclerViewMasters.setNestedScrollingEnabled(false);
    mRecyclerViewMasters.setFocusable(false);
    ItemClickSupport.addTo(mRecyclerViewMasters)
        .setOnItemClickListener(
            (recyclerView, position, v) -> mChooseMasterMasterFragmentPresenter.setSelectedItem(
                position));

    mCheckBoxAnyMaster.setOnCheckedChangeListener((buttonView, isChecked) -> {
      if (isChecked) {
        mChooseMasterMasterFragmentPresenter.blockedClickRv(true);
        mChooseMasterMasterFragmentPresenter.setAnyMasterSelected();
      } else {
        mChooseMasterMasterFragmentPresenter.blockedClickRv(false);
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

  @Override public void blockedClickRv(boolean status) {
    mViewBlockedClickRv.setClickable(status);
    if (status) {
      mMastersVerticalAdapter.setSelectedItem(SELECTED_ITEM_DEFAULT);
    }
  }
}
