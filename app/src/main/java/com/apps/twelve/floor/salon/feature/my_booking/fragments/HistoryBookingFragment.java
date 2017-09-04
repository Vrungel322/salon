package com.apps.twelve.floor.salon.feature.my_booking.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.View;
import android.widget.TextView;
import butterknife.BindView;
import com.apps.twelve.floor.salon.R;
import com.apps.twelve.floor.salon.base.BaseFragment;
import com.apps.twelve.floor.salon.data.model.booking.LastBookingEntity;
import com.apps.twelve.floor.salon.feature.my_booking.activities.BookingListActivity;
import com.apps.twelve.floor.salon.feature.my_booking.adapters.MyBookingAdapter;
import com.apps.twelve.floor.salon.feature.my_booking.presenters.HistoryBookingFragmentPresenter;
import com.apps.twelve.floor.salon.feature.my_booking.views.IHistoryBookingFragmentView;
import com.apps.twelve.floor.salon.feature.start_point.activities.StartActivity;
import com.apps.twelve.floor.salon.utils.ItemClickSupport;
import com.arellomobile.mvp.presenter.InjectPresenter;
import java.util.List;

/**
 * Created by Vrungel on 15.08.2017.
 */

public class HistoryBookingFragment extends BaseFragment implements IHistoryBookingFragmentView {

  @InjectPresenter HistoryBookingFragmentPresenter mHistoryBookingFragmentPresenter;
  @BindView(R.id.srlRefreshLayout) SwipeRefreshLayout mSwipeRefreshLayout;
  @BindView(R.id.rvMyBook) RecyclerView mRecyclerViewMyBooks;
  @BindView(R.id.tvBookEmptyList) TextView mTextViewBookEmptyList;

  private MyBookingAdapter mMyBookingAdapter;
  private boolean fromStartActivity;

  public HistoryBookingFragment() {
    super(R.layout.fragment_book_history);
  }

  public static HistoryBookingFragment newInstance() {
    Bundle args = new Bundle();
    HistoryBookingFragment fragment = new HistoryBookingFragment();
    fragment.setArguments(args);
    return fragment;
  }

  @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

    //if (getActivity() instanceof StartActivity) {
    //  ((StartActivity) getActivity()).setTitleAppBar(R.string.menu_my_booking);
    //} else {
    //  ((BookingListActivity) getActivity()).setTitleAppBar(R.string.menu_my_booking);
    //}

    if (getActivity() instanceof StartActivity) {
      fromStartActivity = true;
    } else {
      fromStartActivity = false;
    }
    mMyBookingAdapter =
        new MyBookingAdapter(getMvpDelegate(), getContext(), mNavigator, fromStartActivity);
    mRecyclerViewMyBooks.setLayoutManager(new LinearLayoutManager(getContext()));
    mRecyclerViewMyBooks.setAdapter(mMyBookingAdapter);
    mRecyclerViewMyBooks.setNestedScrollingEnabled(false);
    mRecyclerViewMyBooks.setFocusable(false);

    mRecyclerViewMyBooks.setAdapter(mMyBookingAdapter);

    TypedValue value = new TypedValue();
    getActivity().getTheme().resolveAttribute(R.attr.colorAccent, value, true);
    mSwipeRefreshLayout.setColorSchemeResources(value.resourceId);
    mSwipeRefreshLayout.setOnRefreshListener(
        () -> mHistoryBookingFragmentPresenter.startRefreshing());

    ItemClickSupport.addTo(mRecyclerViewMyBooks)
        .setOnItemClickListener((recyclerView, position, v) -> {
          if (getActivity() instanceof StartActivity) {
            mNavigator.addFragmentBackStack((StartActivity) getActivity(), R.id.container_main,
                BookDetailsFragment.newInstance(mMyBookingAdapter.getEntity(position)));
          } else {
            mNavigator.addFragmentBackStack((BookingListActivity) getActivity(),
                R.id.container_for_list_of_booked_services,
                BookDetailsFragment.newInstance(mMyBookingAdapter.getEntity(position)));
          }
        });
  }

  @Override public void showAllBooking(List<LastBookingEntity> bookingEntities) {
    mMyBookingAdapter.addListBookingEntity(bookingEntities);
    if (!bookingEntities.isEmpty()) {
      mTextViewBookEmptyList.setVisibility(View.GONE);
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

  @Override public void showServerErrorMsg() {
    showLongAlertMessage(getString(R.string.dialog_error_title), getString(R.string.server_error));
  }
}
