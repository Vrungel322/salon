package com.apps.twelve.floor.salon.feature.booking.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import butterknife.BindView;
import butterknife.OnClick;
import com.apps.twelve.floor.salon.R;
import com.apps.twelve.floor.salon.base.BaseFragment;
import com.apps.twelve.floor.salon.feature.booking.mode.booking_master.fragments.BookingDetailMasterFragment;
import com.apps.twelve.floor.salon.feature.booking.mode.booking_service.fragments.BookingDetailServiceFragment;
import com.apps.twelve.floor.salon.feature.booking.presenters.BookingFragmentPresenter;
import com.apps.twelve.floor.salon.feature.booking.views.IBookingFragmentView;
import com.apps.twelve.floor.salon.utils.Constants;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.bumptech.glide.Glide;

/**
 * Created by Vrungel on 20.02.2017.
 */

public class BookingFragment extends BaseFragment implements IBookingFragmentView {

  @InjectPresenter BookingFragmentPresenter mBookingFragmentPresenter;

  @BindView(R.id.ivMaser) ImageView mIvMaser;
  @BindView(R.id.ivService) ImageView mIvService;

  public BookingFragment() {
    super(R.layout.fragment_booking);
  }

  public static BookingFragment newInstance() {
    Bundle args = new Bundle();
    BookingFragment fragment = new BookingFragment();
    fragment.setArguments(args);
    return fragment;
  }

  @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

    Glide.with(this).load(R.drawable.choose_master).into(mIvMaser);
    Glide.with(this).load(R.drawable.choose_service).into(mIvService);
  }

  @OnClick(R.id.clChooseService) void chooseService() {
    mNavigator.addFragmentTagBackStack((AppCompatActivity) getActivity(), R.id.container_booking,
        BookingDetailServiceFragment.newInstance(),
        Constants.FragmentTag.BOOKING_DETAIL_SERVICE_FRAGMENT);
  }

  @OnClick(R.id.clChooseMaser) void chooseMasters() {
    mNavigator.addFragmentTagBackStack((AppCompatActivity) getActivity(), R.id.container_booking,
        BookingDetailMasterFragment.newInstance(),
        Constants.FragmentTag.BOOKING_DETAIL_MASTER_FRAGMENT);
  }
}

