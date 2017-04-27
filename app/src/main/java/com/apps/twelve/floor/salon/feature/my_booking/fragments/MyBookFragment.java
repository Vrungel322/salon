package com.apps.twelve.floor.salon.feature.my_booking.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import butterknife.BindView;
import com.apps.twelve.floor.salon.R;
import com.apps.twelve.floor.salon.base.BaseFragment;
import com.apps.twelve.floor.salon.data.model.LastBookingEntity;
import com.apps.twelve.floor.salon.feature.my_booking.adapters.MyBookingAdapter;
import com.apps.twelve.floor.salon.feature.my_booking.presenters.MyBookFragmentPresenter;
import com.apps.twelve.floor.salon.feature.my_booking.views.IMyBookFragmentView;
import com.apps.twelve.floor.salon.feature.start_point.activities.StartActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import java.util.List;

/**
 * Created by Vrungel on 21.02.2017.
 */

public class MyBookFragment extends BaseFragment implements IMyBookFragmentView {
  @InjectPresenter MyBookFragmentPresenter mMyBookFragmentPresenter;

  @BindView(R.id.srlRefreshLayout) SwipeRefreshLayout mSwipeRefreshLayout;
  @BindView(R.id.rvMyBook) RecyclerView mRecyclerViewMyBooks;
  @BindView(R.id.tvBookEmptyList) TextView mTextViewBookEmptyList;

  private MyBookingAdapter mMyBookingAdapter;

  public static MyBookFragment newInstance() {
    Bundle args = new Bundle();
    MyBookFragment fragment = new MyBookFragment();
    fragment.setArguments(args);
    return fragment;
  }

  public MyBookFragment() {
    super(R.layout.fragment_my_book);
  }

  @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

    ((StartActivity) getActivity()).setTitleAppBar(R.string.menu_my_booking);

    mMyBookingAdapter =
        new MyBookingAdapter(getMvpDelegate(), getContext(), getActivity(), mNavigator);
    mRecyclerViewMyBooks.setLayoutManager(new LinearLayoutManager(getContext()));
    mRecyclerViewMyBooks.setNestedScrollingEnabled(false);
    mRecyclerViewMyBooks.setFocusable(false);

    mRecyclerViewMyBooks.setAdapter(mMyBookingAdapter);

    mSwipeRefreshLayout.setColorSchemeResources(R.color.colorAccent);
    mSwipeRefreshLayout.setOnRefreshListener(() -> mMyBookFragmentPresenter.startRefreshing());
  }

  @Override public void showAllBooking(List<LastBookingEntity> bookingEntities) {
    if (!bookingEntities.isEmpty()) {
      mTextViewBookEmptyList.setVisibility(View.GONE);
      mRecyclerViewMyBooks.setAdapter(mMyBookingAdapter);
      mMyBookingAdapter.addListLastBookingEntity(bookingEntities);
    } else {
      mTextViewBookEmptyList.setVisibility(View.VISIBLE);
    }
  }

  @Override public void startRefreshingView() {
    if (!mSwipeRefreshLayout.isRefreshing()) mSwipeRefreshLayout.setRefreshing(true);
  }

  @Override public void stopRefreshingView() {
    if (mSwipeRefreshLayout.isRefreshing()) mSwipeRefreshLayout.setRefreshing(false);
  }
}