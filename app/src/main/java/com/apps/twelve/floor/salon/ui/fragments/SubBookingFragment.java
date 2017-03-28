package com.apps.twelve.floor.salon.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import butterknife.BindView;
import com.apps.twelve.floor.salon.R;
import com.apps.twelve.floor.salon.mvp.data.model.LastBookingEntity;
import com.apps.twelve.floor.salon.mvp.presenters.pr_fragments.SubFragmentBookingPresenter;
import com.apps.twelve.floor.salon.mvp.views.ISubFragmentBookingView;
import com.apps.twelve.floor.salon.ui.adapters.MyLastBookingAdapter;
import com.apps.twelve.floor.salon.ui.base.BaseFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import java.util.List;

/**
 * Created by Vrungel on 28.02.2017.
 */

public class SubBookingFragment extends BaseFragment implements ISubFragmentBookingView {

  @InjectPresenter SubFragmentBookingPresenter mSubFragmentBookingPresenter;

  @BindView(R.id.rvMyLastBooking) RecyclerView mRecyclerViewMyLastBooking;
  private MyLastBookingAdapter mMyLastBookingAdapter;

  public static SubBookingFragment newInstance() {
    Bundle args = new Bundle();
    SubBookingFragment fragment = new SubBookingFragment();
    fragment.setArguments(args);
    return fragment;
  }

  public SubBookingFragment() {
    super(R.layout.fragment_sub_booking);
  }

  @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    mMyLastBookingAdapter = new MyLastBookingAdapter(getMvpDelegate());
    mRecyclerViewMyLastBooking.setAdapter(mMyLastBookingAdapter);
    mRecyclerViewMyLastBooking.setLayoutManager(new LinearLayoutManager(getContext()));
    mRecyclerViewMyLastBooking.setNestedScrollingEnabled(false);
    mRecyclerViewMyLastBooking.setFocusable(false);
  }

  @Override public void showAllBooking(List<LastBookingEntity> lastBookingEntities) {
    mMyLastBookingAdapter.addListLastBookingEntity(lastBookingEntities);
  }
}
