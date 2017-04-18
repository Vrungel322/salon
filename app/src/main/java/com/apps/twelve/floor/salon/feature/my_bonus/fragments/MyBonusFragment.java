package com.apps.twelve.floor.salon.feature.my_bonus.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.TextView;
import butterknife.BindView;
import com.apps.twelve.floor.salon.R;
import com.apps.twelve.floor.salon.feature.my_bonus.presenters.MyBonusFragmentPresenter;
import com.apps.twelve.floor.salon.feature.my_bonus.views.IMyBonusFragmentView;
import com.apps.twelve.floor.salon.base.BaseFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;

/**
 * Created by Vrungel on 21.02.2017.
 */

public class MyBonusFragment extends BaseFragment implements IMyBonusFragmentView {
  @InjectPresenter MyBonusFragmentPresenter mMyBonusFragmentPresenter;

  @BindView(R.id.tvTest) TextView mTvTest;
  @BindView(R.id.srlRefreshLayout) SwipeRefreshLayout mSwipeRefreshLayout;

  public static MyBonusFragment newInstance() {
    Bundle args = new Bundle();
    MyBonusFragment fragment = new MyBonusFragment();
    fragment.setArguments(args);
    return fragment;
  }

  public MyBonusFragment() {
    super(R.layout.fragment_my_bonus);
  }

  @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

    mTvTest.setOnClickListener(v -> showToastMessage("tvTest"));
  }

  @Override public void startRefreshingView() {
    if (!mSwipeRefreshLayout.isRefreshing()) mSwipeRefreshLayout.setRefreshing(true);
  }

  @Override public void stopRefreshingView() {
    if (mSwipeRefreshLayout.isRefreshing()) mSwipeRefreshLayout.setRefreshing(false);
  }
}