package com.apps.twelve.floor.salon.feature.booking.mode.booking_service.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindView;
import com.apps.twelve.floor.salon.R;
import com.apps.twelve.floor.salon.base.BaseFragment;
import com.apps.twelve.floor.salon.feature.booking.mode.booking_service.presenters.BookingDetailServiceFragmentPresenter;
import com.apps.twelve.floor.salon.feature.booking.mode.booking_service.views.IBookingDetailServiceFragmentView;
import com.apps.twelve.floor.salon.utils.Constants;
import com.apps.twelve.floor.salon.utils.ViewUtil;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

/**
 * Created by John on 05.05.2017.
 */

public class BookingDetailServiceFragment extends BaseFragment
    implements IBookingDetailServiceFragmentView {

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

    mTabService.setOnClickListener(v -> {
      mBookingDetailServiceFragmentPresenter.clickTab(
          Constants.FragmentTag.CHOOSE_SERVICE_SERVICE_FRAGMENT);
      mNavigator.clearBackStackWithCountFragment((AppCompatActivity) getActivity(), 2);
    });

    mTabTime.setOnClickListener(v -> {
      mBookingDetailServiceFragmentPresenter.clickTab(
          Constants.FragmentTag.CHOOSE_SERVICE_TIME_FRAGMENT);
      mNavigator.clearBackStackWithCountFragment((AppCompatActivity) getActivity(), 3);
    });

    mTabMaster.setOnClickListener(v -> {
      mBookingDetailServiceFragmentPresenter.clickTab(
          Constants.FragmentTag.CHOOSE_SERVICE_MASTER_FRAGMENT);
      mNavigator.clearBackStackWithCountFragment((AppCompatActivity) getActivity(), 4);
    });

    mTabData.setOnClickListener(v -> mBookingDetailServiceFragmentPresenter.clickTab(
        Constants.FragmentTag.CHOOSE_SERVICE_CONTACT_FRAGMENT));
  }

  @Override public void addFirstFragment() {
    mNavigator.addFragmentTagBackStack((AppCompatActivity) getActivity(),
        R.id.container_booking_detail_service, ChooseServiceServiceFragment.newInstance(),
        Constants.FragmentTag.CHOOSE_SERVICE_SERVICE_FRAGMENT);
  }

  @Override public void showMessageWarning(int warning) {
    showAlertMessage(getString(R.string.title_write_error), getString(warning));
  }

  @Override public void hideKeyboard() {
    ViewUtil.hideKeyboard(getActivity());
  }

  @Override public void goNextFragment(String fragmentTag) {
    switch (fragmentTag) {
      case Constants.FragmentTag.CHOOSE_SERVICE_SERVICE_FRAGMENT:
        mBookingDetailServiceFragmentPresenter.setSelectedTab(
            Constants.FragmentTag.CHOOSE_SERVICE_SERVICE_FRAGMENT);
        break;
      case Constants.FragmentTag.CHOOSE_SERVICE_TIME_FRAGMENT:
        mNavigator.addFragmentTagBackStackNotCopy((AppCompatActivity) getActivity(),
            R.id.container_booking_detail_service, ChooseServiceTimeFragment.newInstance(),
            Constants.FragmentTag.CHOOSE_SERVICE_TIME_FRAGMENT);
        mBookingDetailServiceFragmentPresenter.setSelectedTab(
            Constants.FragmentTag.CHOOSE_SERVICE_TIME_FRAGMENT);
        break;
      case Constants.FragmentTag.CHOOSE_SERVICE_MASTER_FRAGMENT:
        mNavigator.addFragmentTagBackStackNotCopy((AppCompatActivity) getActivity(),
            R.id.container_booking_detail_service, ChooseServiceMasterFragment.newInstance(),
            Constants.FragmentTag.CHOOSE_SERVICE_MASTER_FRAGMENT);
        mBookingDetailServiceFragmentPresenter.setSelectedTab(
            Constants.FragmentTag.CHOOSE_SERVICE_MASTER_FRAGMENT);
        break;
      case Constants.FragmentTag.CHOOSE_SERVICE_CONTACT_FRAGMENT:
        mNavigator.addFragmentTagBackStackNotCopy((AppCompatActivity) getActivity(),
            R.id.container_booking_detail_service, ChooseServiceContactFragment.newInstance(),
            Constants.FragmentTag.CHOOSE_SERVICE_CONTACT_FRAGMENT);
        mBookingDetailServiceFragmentPresenter.setSelectedTab(
            Constants.FragmentTag.CHOOSE_SERVICE_CONTACT_FRAGMENT);
        break;
    }
  }

  @Override public void setSelectedTab(String fragmentTag) {
    mViewAccentService.setVisibility(View.INVISIBLE);
    mViewAccentTime.setVisibility(View.INVISIBLE);
    mViewAccentMaster.setVisibility(View.INVISIBLE);
    mViewAccentData.setVisibility(View.INVISIBLE);
    switch (fragmentTag) {
      case Constants.FragmentTag.CHOOSE_SERVICE_SERVICE_FRAGMENT:
        mViewAccentService.setVisibility(View.VISIBLE);
        YoYo.with(Techniques.Wave).duration(700).playOn(mViewAccentService);
        break;
      case Constants.FragmentTag.CHOOSE_SERVICE_TIME_FRAGMENT:
        mViewAccentTime.setVisibility(View.VISIBLE);
        YoYo.with(Techniques.Wave).duration(700).playOn(mViewAccentTime);
        break;
      case Constants.FragmentTag.CHOOSE_SERVICE_MASTER_FRAGMENT:
        mViewAccentMaster.setVisibility(View.VISIBLE);
        YoYo.with(Techniques.Wave).duration(700).playOn(mViewAccentMaster);
        break;
      case Constants.FragmentTag.CHOOSE_SERVICE_CONTACT_FRAGMENT:
        mViewAccentData.setVisibility(View.VISIBLE);
        YoYo.with(Techniques.Wave).duration(700).playOn(mViewAccentData);
        break;
    }
  }

  @Override public void stateBackBookingService() {
    if (mNavigator.isFragmentTag((AppCompatActivity) getActivity(),
        Constants.FragmentTag.CHOOSE_SERVICE_MASTER_FRAGMENT)) {
      mBookingDetailServiceFragmentPresenter.clickTab(
          Constants.FragmentTag.CHOOSE_SERVICE_MASTER_FRAGMENT);
      return;
    }
    if (mNavigator.isFragmentTag((AppCompatActivity) getActivity(),
        Constants.FragmentTag.CHOOSE_SERVICE_TIME_FRAGMENT)) {
      mBookingDetailServiceFragmentPresenter.clickTab(
          Constants.FragmentTag.CHOOSE_SERVICE_TIME_FRAGMENT);
      return;
    }
    if (mNavigator.isFragmentTag((AppCompatActivity) getActivity(),
        Constants.FragmentTag.CHOOSE_SERVICE_SERVICE_FRAGMENT)) {
      mBookingDetailServiceFragmentPresenter.clickTab(
          Constants.FragmentTag.CHOOSE_SERVICE_SERVICE_FRAGMENT);
    }
  }
}
