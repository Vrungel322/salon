package com.apps.twelve.floor.salon.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import com.apps.twelve.floor.salon.R;
import com.apps.twelve.floor.salon.mvp.presenters.fragments.BookingContactFragmentPresenter;
import com.apps.twelve.floor.salon.mvp.views.IBookingContactFragmentView;
import com.apps.twelve.floor.salon.ui.base.BaseFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;

/**
 * Created by Alexandra on 28.03.2017.
 */

public class BookingContactFragment extends BaseFragment implements IBookingContactFragmentView {
  @InjectPresenter BookingContactFragmentPresenter mBookingContactsFragmentPresenter;

  public static BookingContactFragment newInstance() {
    Bundle args = new Bundle();
    BookingContactFragment fragment = new BookingContactFragment();
    fragment.setArguments(args);
    return fragment;
  }

  public BookingContactFragment() {
    super(R.layout.fragment_booking_contact);
  }

  @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
  }
}
