package com.apps.twelve.floor.salon.feature.booking.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import com.apps.twelve.floor.salon.R;
import com.apps.twelve.floor.salon.base.BaseFragment;
import com.apps.twelve.floor.salon.feature.booking.mode.booking_master.fragments.BookingDetailMasterFragment;
import com.apps.twelve.floor.salon.feature.booking.mode.booking_service.fragments.BookingDetailServiceFragment;
import com.apps.twelve.floor.salon.feature.booking.presenters.BookingFragmentPresenter;
import com.apps.twelve.floor.salon.feature.booking.views.IBookingFragmentView;
import com.apps.twelve.floor.salon.utils.Constants;
import com.apps.twelve.floor.salon.utils.NetworkUtil;
import com.arellomobile.mvp.presenter.InjectPresenter;

/**
 * Created by Vrungel on 20.02.2017.
 */

public class BookingFragment extends BaseFragment implements IBookingFragmentView {

  @InjectPresenter BookingFragmentPresenter mBookingFragmentPresenter;
  @BindView(R.id.cvChooseMaster) CardView cvChooseMaser;
  @BindView(R.id.cvChooseService) CardView cvChooseService;
  @BindView(R.id.rlNetworkDisconnection) RelativeLayout rlNetworkDisconnection;
  @BindView(R.id.buttonBookingPhone) Button mButtonBookingPhone;
  Unbinder unbinder;

  public BookingFragment() {
    super(R.layout.fragment_booking);
  }

  public static BookingFragment newInstance() {
    Bundle args = new Bundle();
    BookingFragment fragment = new BookingFragment();
    fragment.setArguments(args);
    return fragment;
  }

  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    View rootView = super.onCreateView(inflater, container, savedInstanceState);
    unbinder = ButterKnife.bind(this, rootView);
    return rootView;
  }

  @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    if (!NetworkUtil.isNetworkConnected(getContext())) {
      cvChooseMaser.setVisibility(View.GONE);
      cvChooseService.setVisibility(View.GONE);
      rlNetworkDisconnection.setVisibility(View.VISIBLE);
    }
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

  @OnClick(R.id.buttonBookingPhone) public void onViewClicked() {
    Intent intent = new Intent(Intent.ACTION_DIAL);
    intent.setData(Uri.parse("tel:" + mButtonBookingPhone.getText()));
    startActivity(intent);
  }

  @Override public void onDestroyView() {
    super.onDestroyView();
    unbinder.unbind();
  }
}

