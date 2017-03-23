package com.apps.twelve.floor.salon.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import com.apps.twelve.floor.salon.R;
import com.apps.twelve.floor.salon.mvp.presenters.fragments.BookingFragmentPresenter;
import com.apps.twelve.floor.salon.mvp.views.IBookingFragmentView;
import com.apps.twelve.floor.salon.ui.base.BaseFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;

/**
 * Created by Vrungel on 20.02.2017.
 */

public class BookingFragment extends BaseFragment implements IBookingFragmentView {
  @InjectPresenter BookingFragmentPresenter mBookingFragmentPresenter;

  @BindView(R.id.tv_book) TextView tvBook;

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
    showToastMessage(R.string.book_choose_service);
  }

  @OnClick(R.id.rl_choose_specialist) void chooseSpecialist() {
    showToastMessage(R.string.book_choose_specialist);
  }
}

