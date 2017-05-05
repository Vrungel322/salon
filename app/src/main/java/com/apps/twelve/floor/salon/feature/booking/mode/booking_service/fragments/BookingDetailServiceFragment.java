package com.apps.twelve.floor.salon.feature.booking.mode.booking_service.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindView;
import com.apps.twelve.floor.salon.R;
import com.apps.twelve.floor.salon.base.BaseFragment;
import com.apps.twelve.floor.salon.feature.booking.mode.booking_service.presenters.BookingDetailServiceFragmentPresenter;
import com.apps.twelve.floor.salon.feature.booking.mode.booking_service.views.IBookingDetailServiceView;
import com.arellomobile.mvp.presenter.InjectPresenter;

/**
 * Created by John on 05.05.2017.
 */

public class BookingDetailServiceFragment extends BaseFragment
    implements IBookingDetailServiceView {

  @InjectPresenter BookingDetailServiceFragmentPresenter mBookingDetailServiceFragmentPresenter;
  @BindView(R.id.textAccentService) TextView mTextAccentService;
  @BindView(R.id.viewAccentService) View mViewAccentService;
  @BindView(R.id.tabService) LinearLayout mTabService;
  @BindView(R.id.textAccentTime) TextView mTextAccentTime;
  @BindView(R.id.viewAccentTime) View mViewAccentTime;
  @BindView(R.id.tabTime) LinearLayout mTabTime;
  @BindView(R.id.textAccentMaster) TextView mTextAccentMaster;
  @BindView(R.id.viewAccentMaster) View mViewAccentMaster;
  @BindView(R.id.tabMaster) LinearLayout mTabMaster;
  @BindView(R.id.textAccentData) TextView mTextAccentData;
  @BindView(R.id.viewAccentData) View mViewAccentData;
  @BindView(R.id.tabData) LinearLayout mTabData;

  public BookingDetailServiceFragment() {
    super(R.layout.fragment_booking_detail_service);
  }

  public static BookingDetailServiceFragment newInstance() {
    Bundle args = new Bundle();
    BookingDetailServiceFragment fragment = new BookingDetailServiceFragment();
    fragment.setArguments(args);
    return fragment;
  }

  @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
  }
}
