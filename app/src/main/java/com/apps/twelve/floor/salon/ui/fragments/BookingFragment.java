package com.apps.twelve.floor.salon.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;
import butterknife.BindView;
import com.apps.twelve.floor.salon.R;
import com.apps.twelve.floor.salon.mvp.presenters.fragments.BookingPresenter;
import com.apps.twelve.floor.salon.mvp.views.IBookingFragmentView;
import com.apps.twelve.floor.salon.ui.base.BaseFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;

/**
 * Created by Vrungel on 20.02.2017.
 */

public class BookingFragment extends BaseFragment implements IBookingFragmentView {
  @InjectPresenter BookingPresenter mBookingFragmentPresenter;

  @BindView(R.id.tvTest) TextView mTvTest;

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

    mTvTest.setOnClickListener(v -> showToastMessage("tvTest"));
  }
}

