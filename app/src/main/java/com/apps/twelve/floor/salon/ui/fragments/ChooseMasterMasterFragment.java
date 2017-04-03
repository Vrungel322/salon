package com.apps.twelve.floor.salon.ui.fragments;

import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import butterknife.BindView;
import com.apps.twelve.floor.salon.R;
import com.apps.twelve.floor.salon.mvp.data.model.MasterEntity;
import com.apps.twelve.floor.salon.mvp.presenters.pr_fragments.ChooseMasterMasterFragmentPresenter;
import com.apps.twelve.floor.salon.mvp.views.IChooseMasterMasterView;
import com.apps.twelve.floor.salon.ui.adapters.MastersVerticalAdapter;
import com.apps.twelve.floor.salon.ui.base.BaseFragment;
import com.apps.twelve.floor.salon.utils.ItemClickSupport;
import com.arellomobile.mvp.presenter.InjectPresenter;
import java.util.List;

public class ChooseMasterMasterFragment extends BaseFragment implements IChooseMasterMasterView {

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
    mRecyclerViewMasters.setLayoutManager(new GridLayoutManager(getContext(), 2));
    mMastersVerticalAdapter = new MastersVerticalAdapter();
    mRecyclerViewMasters.setAdapter(mMastersVerticalAdapter);
    mRecyclerViewMasters.setNestedScrollingEnabled(false);
    mRecyclerViewMasters.setFocusable(false);
    ItemClickSupport.addTo(mRecyclerViewMasters)
        .setOnItemClickListener(
            (recyclerView, position, v) -> mMastersVerticalAdapter.setSelectedItem(position));
  }

  @Override public void showMasters(List<MasterEntity> masterEntities) {
    mMastersVerticalAdapter.addListMasterEntity(masterEntities);
  }

  @Override public void hideProgressBar() {
    mProgressBar.setVisibility(View.GONE);
    mNestedScroll.setVisibility(View.VISIBLE);
  }
}
