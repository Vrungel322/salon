package com.apps.twelve.floor.salon.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import butterknife.BindView;
import com.apps.twelve.floor.salon.R;
import com.apps.twelve.floor.salon.mvp.presenters.fragments.SubFragmentBookingPresenter;
import com.apps.twelve.floor.salon.mvp.views.ISubFragmentBookingView;
import com.apps.twelve.floor.salon.ui.adapters.MyLastBookingAdapter;
import com.apps.twelve.floor.salon.ui.base.BaseFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;

/**
 * Created by Vrungel on 28.02.2017.
 */

public class SubFragmentBooking extends BaseFragment implements ISubFragmentBookingView {
  @InjectPresenter SubFragmentBookingPresenter mSubFragmentBookingPresenter;
  @BindView(R.id.rvMyLastBooking) RecyclerView mRecyclerViewMyLastBooking;

  public static SubFragmentBooking newInstance() {
    Bundle args = new Bundle();
    SubFragmentBooking fragment = new SubFragmentBooking();
    fragment.setArguments(args);
    return fragment;
  }

  public SubFragmentBooking() {
    super(R.layout.fragment_sub_booking);
  }

  @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    MyLastBookingAdapter myLastBookingAdapter = new MyLastBookingAdapter();
    mRecyclerViewMyLastBooking.setLayoutManager(new LinearLayoutManager(getContext()));
  }
}
