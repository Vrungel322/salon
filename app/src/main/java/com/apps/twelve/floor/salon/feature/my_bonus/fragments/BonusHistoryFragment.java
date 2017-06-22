package com.apps.twelve.floor.salon.feature.my_bonus.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import butterknife.BindView;
import com.apps.twelve.floor.salon.R;
import com.apps.twelve.floor.salon.base.BaseFragment;
import com.apps.twelve.floor.salon.data.model.BonusHistoryEntity;
import com.apps.twelve.floor.salon.feature.my_bonus.adapters.BonusHistoryAdapter;
import com.apps.twelve.floor.salon.feature.my_bonus.presenters.BonusHistoryFragmentPresenter;
import com.apps.twelve.floor.salon.feature.my_bonus.views.IBonusHistoryFragmentView;
import com.arellomobile.mvp.presenter.InjectPresenter;
import java.util.List;

/**
 * Created by Alexandra on 30.05.2017.
 */

public class BonusHistoryFragment extends BaseFragment implements IBonusHistoryFragmentView {

  @InjectPresenter BonusHistoryFragmentPresenter mBonusHistoryFragmentPresenter;

  @BindView(R.id.tvBonusCount) TextView mTextViewBonusCount;
  @BindView(R.id.tvNoTransactions) TextView mTextViewNoTransactions;
  @BindView(R.id.rvHistory) RecyclerView mRecyclerViewHistory;
  @BindView(R.id.viewNoTransSeparator) View mViewNoTransSeparator;

  private BonusHistoryAdapter mBonusHistoryAdapter;

  public BonusHistoryFragment() {
    super(R.layout.fragment_bonus_history);
  }

  public static BonusHistoryFragment newInstance() {
    Bundle args = new Bundle();
    BonusHistoryFragment fragment = new BonusHistoryFragment();
    fragment.setArguments(args);
    return fragment;
  }

  @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

    mBonusHistoryAdapter = new BonusHistoryAdapter(getActivity());
    mRecyclerViewHistory.setLayoutManager(new LinearLayoutManager(getContext()));
    mRecyclerViewHistory.setAdapter(mBonusHistoryAdapter);
  }

  @Override public void addBonusHistoryList(List<BonusHistoryEntity> historyEntities) {
    mBonusHistoryAdapter.addBonusHistoryList(historyEntities);
    if (!historyEntities.isEmpty()) {
      mTextViewNoTransactions.setVisibility(View.GONE);
      mViewNoTransSeparator.setVisibility(View.GONE);
    } else {
      mTextViewNoTransactions.setVisibility(View.VISIBLE);
      mViewNoTransSeparator.setVisibility(View.VISIBLE);
    }
  }

  @Override public void setBonusCount(Integer count) {
    mTextViewBonusCount.setText(getString(R.string.bonus_measure, String.valueOf(count)));
  }
}
