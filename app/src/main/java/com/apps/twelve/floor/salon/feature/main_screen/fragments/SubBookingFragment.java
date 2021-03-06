package com.apps.twelve.floor.salon.feature.main_screen.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import butterknife.BindView;
import com.apps.twelve.floor.salon.R;
import com.apps.twelve.floor.salon.base.BaseFragment;
import com.apps.twelve.floor.salon.data.model.LastBookingEntity;
import com.apps.twelve.floor.salon.feature.main_screen.adapters.MyLastBookingAdapter;
import com.apps.twelve.floor.salon.feature.main_screen.presenters.SubBookingFragmentPresenter;
import com.apps.twelve.floor.salon.feature.main_screen.views.ISubFragmentBookingView;
import com.apps.twelve.floor.salon.feature.my_booking.fragments.MyBookFragment;
import com.apps.twelve.floor.salon.feature.start_point.activities.StartActivity;
import com.apps.twelve.floor.salon.utils.Constants;
import com.arellomobile.mvp.presenter.InjectPresenter;
import java.util.List;

/**
 * Created by Vrungel on 28.02.2017.
 */

public class SubBookingFragment extends BaseFragment implements ISubFragmentBookingView {

  @InjectPresenter SubBookingFragmentPresenter mSubBookingFragmentPresenter;

  @BindView(R.id.rvMyLastBooking) RecyclerView mRecyclerViewMyLastBooking;
  @BindView(R.id.tvAllBooks) TextView mTextViewAllBooks;

  private MyLastBookingAdapter mMyLastBookingAdapter;

  public SubBookingFragment() {
    super(R.layout.fragment_sub_booking);
  }

  public static SubBookingFragment newInstance() {
    Bundle args = new Bundle();
    SubBookingFragment fragment = new SubBookingFragment();
    fragment.setArguments(args);
    return fragment;
  }

  @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

    mMyLastBookingAdapter =
        new MyLastBookingAdapter(getMvpDelegate(), getContext(), getActivity(), mNavigator);
    mRecyclerViewMyLastBooking.setLayoutManager(new LinearLayoutManager(getContext()));
    mRecyclerViewMyLastBooking.setAdapter(mMyLastBookingAdapter);
    mRecyclerViewMyLastBooking.setNestedScrollingEnabled(false);
    mRecyclerViewMyLastBooking.setFocusable(false);

    mTextViewAllBooks.setOnClickListener(c -> {
      if (mAuthorizationManager.isAuthorized()) {
        mNavigator.addFragmentTagClearBackStackNotCopy((StartActivity) getActivity(),
            R.id.container_main, MyBookFragment.newInstance(),
            Constants.FragmentTag.MY_BOOK_FRAGMENT);
      } else {
        mSubBookingFragmentPresenter.showAlertDialog();
      }
    });
  }

  @Override public void showLastBookings(List<LastBookingEntity> lastBookingEntities) {
    mMyLastBookingAdapter.addListLastBookingEntity(lastBookingEntities);
  }

  @Override public void onDestroyView() {
    mMyLastBookingAdapter.cancelAlertDialog();
    super.onDestroyView();
  }
}
