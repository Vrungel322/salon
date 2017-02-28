package com.apps.twelve.floor.salon.ui.fragments;

import android.os.Bundle;
import com.apps.twelve.floor.salon.R;
import com.apps.twelve.floor.salon.mvp.presenters.fragments.SubFragmentBookingPresenter;
import com.apps.twelve.floor.salon.mvp.views.ISubFragmentBookingView;
import com.apps.twelve.floor.salon.ui.base.BaseFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;

/**
 * Created by Vrungel on 28.02.2017.
 */

public class SubFragmentBooking extends BaseFragment implements ISubFragmentBookingView {
  @InjectPresenter SubFragmentBookingPresenter mSubFragmentBookingPresenter;

  public static SubFragmentBooking newInstance() {
    Bundle args = new Bundle();
    SubFragmentBooking fragment = new SubFragmentBooking();
    fragment.setArguments(args);
    return fragment;
  }

  public SubFragmentBooking() {
    super(R.layout.fragment_sub_booking);
  }
}
