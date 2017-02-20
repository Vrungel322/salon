package com.apps.twelve.floor.salon.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;
import butterknife.BindView;
import com.apps.twelve.floor.salon.R;
import com.apps.twelve.floor.salon.mvp.presenters.FragmentBookingPresenter;
import com.apps.twelve.floor.salon.mvp.views.IFragmentBookingView;
import com.apps.twelve.floor.salon.ui.base.BaseFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;

/**
 * Created by Vrungel on 20.02.2017.
 */

public class FragmentBooking extends BaseFragment implements IFragmentBookingView {
  @InjectPresenter FragmentBookingPresenter mFragmentBookingPresenter;

  @BindView(R.id.tvTest) TextView mTvTest;

  public FragmentBooking() {
    super(R.layout.fragment_booking);
  }

  @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

    mTvTest.setOnClickListener(v -> showToastMessage("tvTest"));
  }
}

