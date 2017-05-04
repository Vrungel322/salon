package com.apps.twelve.floor.salon.feature.booking.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import butterknife.OnClick;
import com.apps.twelve.floor.salon.R;
import com.apps.twelve.floor.salon.base.BaseFragment;
import com.apps.twelve.floor.salon.feature.booking.mode.booking_master.fragments.BookingDetailMasterFragment;
import com.apps.twelve.floor.salon.feature.booking.presenters.BookingFragmentPresenter;
import com.apps.twelve.floor.salon.feature.booking.views.IBookingFragmentView;
import com.apps.twelve.floor.salon.utils.Constants;
import com.arellomobile.mvp.presenter.InjectPresenter;

/**
 * Created by Vrungel on 20.02.2017.
 */

public class BookingFragment extends BaseFragment implements IBookingFragmentView {
  @InjectPresenter BookingFragmentPresenter mBookingFragmentPresenter;

  public static BookingFragment newInstance() {
    Bundle args = new Bundle();
    BookingFragment fragment = new BookingFragment();
    fragment.setArguments(args);
    return fragment;
  }

  public BookingFragment() {
    super(R.layout.fragment_booking);
  }

  @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
  }

  @OnClick(R.id.rl_choose_service) void chooseService() {
    mNavigator.replaceFragmentTag((AppCompatActivity) getActivity(), R.id.container_booking,
        BookingDetailFragment.newInstance(Constants.FragmentToShow.CHOOSE_SERVICE),
        Constants.FragmentTag.BOOKING_SERVICES_FRAGMENT);
  }

  @OnClick(R.id.rl_choose_specialist) void chooseMasters() {
    mNavigator.replaceFragmentTag((AppCompatActivity) getActivity(), R.id.container_booking,
        BookingDetailMasterFragment.newInstance(),
        Constants.FragmentTag.BOOKING_MASTERS_FRAGMENT);
  }
}

