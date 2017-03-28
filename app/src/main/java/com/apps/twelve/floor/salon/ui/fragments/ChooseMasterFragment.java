package com.apps.twelve.floor.salon.ui.fragments;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.CheckBox;
import android.widget.TextView;
import butterknife.BindView;
import com.apps.twelve.floor.salon.R;
import com.apps.twelve.floor.salon.mvp.data.model.MasterEntity;
import com.apps.twelve.floor.salon.mvp.presenters.pr_fragments.ChooseMasterFragmentPresenter;
import com.apps.twelve.floor.salon.mvp.views.IChooseMasterFragmentView;
import com.apps.twelve.floor.salon.ui.adapters.MastersHorizontalAdapter;
import com.apps.twelve.floor.salon.ui.base.BaseFragment;
import com.apps.twelve.floor.salon.utils.ItemClickSupport;
import com.arellomobile.mvp.presenter.InjectPresenter;
import java.util.List;

/**
 * Created by Vrungel on 28.03.2017.
 */

public class ChooseMasterFragment extends BaseFragment implements IChooseMasterFragmentView {
  @InjectPresenter ChooseMasterFragmentPresenter mChooseMasterFragmentPresenter;

  @BindView(R.id.tvServiceName) TextView mTextViewServiceName;
  @BindView(R.id.tvServiceTime) TextView mTextViewServiceTime;
  @BindView(R.id.tvServiceDuration) TextView mTextViewServiceDuration;
  @BindView(R.id.cbAnyMaster) CheckBox mCheckBoxAnyMaster;
  @BindView(R.id.rvMasters) RecyclerView mRecyclerViewMasters;

  private MastersHorizontalAdapter mMastersHorizontalAdapter;

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
    mRecyclerViewMasters.setLayoutManager(
        new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
    mMastersHorizontalAdapter = new MastersHorizontalAdapter();
    mRecyclerViewMasters.setAdapter(mMastersHorizontalAdapter);
    ItemClickSupport.addTo(mRecyclerViewMasters)
        .setOnItemClickListener(
            (recyclerView, position, v) -> mMastersHorizontalAdapter.setSelectedItem(position));
  }

  @Override public void showMasters(List<MasterEntity> masterEntities) {
    mMastersHorizontalAdapter.addListMasterEntity(masterEntities);
  }
}
