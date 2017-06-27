package com.apps.twelve.floor.salon.feature.booking.mode.booking_service.fragments;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import br.com.simplepass.loading_button_lib.customViews.CircularProgressButton;
import butterknife.BindView;
import butterknife.OnClick;
import com.apps.twelve.floor.salon.R;
import com.apps.twelve.floor.salon.base.BaseFragment;
import com.apps.twelve.floor.salon.feature.booking.mode.booking_service.presenters.ChooseServiceContactFragmentPresenter;
import com.apps.twelve.floor.salon.feature.booking.mode.booking_service.views.IChooseServiceContactFragmentView;
import com.arellomobile.mvp.presenter.InjectPresenter;

/**
 * Created by Alexandra on 28.03.2017.
 */

public class ChooseServiceContactFragment extends BaseFragment
    implements IChooseServiceContactFragmentView {

  @InjectPresenter ChooseServiceContactFragmentPresenter mChooseServiceContactFragmentPresenter;

  @BindView(R.id.tv_service_description) TextView mTextViewService;
  @BindView(R.id.tv_time_description) TextView mTextViewTime;
  @BindView(R.id.tv_time_duration) TextView mTextViewDuration;
  @BindView(R.id.tv_master_description) TextView mTextViewMaster;
  @BindView(R.id.edit_name) EditText mEditTextName;
  @BindView(R.id.edit_phone) EditText mEditTextPhone;
  @BindView(R.id.tvEmptyPhone) TextView mTextViewEmptyPhone;
  @BindView(R.id.btn_booking_contact) CircularProgressButton mBtnCreateBooking;

  public ChooseServiceContactFragment() {
    super(R.layout.fragment_choose_service_contact);
  }

  public static ChooseServiceContactFragment newInstance() {
    Bundle args = new Bundle();
    ChooseServiceContactFragment fragment = new ChooseServiceContactFragment();
    fragment.setArguments(args);
    return fragment;
  }

  @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
  }

  @OnClick(R.id.btn_booking_contact) void animate() {
    mChooseServiceContactFragmentPresenter.setPersonName(mEditTextName.getText().toString());
    mChooseServiceContactFragmentPresenter.setPersonPhone(mEditTextPhone.getText().toString());
    mChooseServiceContactFragmentPresenter.sendBookingEntity();
  }

  @Override public void onResume() {
    super.onResume();
    if (mAuthorizationManager.isAuthorized()) {
      mEditTextName.setText(mAuthorizationManager.getUserName());
      mEditTextPhone.setText(mAuthorizationManager.getUserPhone());
    }
  }

  @Override public void startAnimation() {
    mBtnCreateBooking.startAnimation();
  }

  @Override public void stopAnimation() {
    TypedValue value = new TypedValue();
    getActivity().getTheme().resolveAttribute(R.attr.colorAccent, value, true);
    mBtnCreateBooking.doneLoadingAnimation(ContextCompat.getColor(getContext(), value.resourceId),
        BitmapFactory.decodeResource(getResources(), R.drawable.ic_done_white_48dp));
  }

  @Override public void revertAnimation() {
    mBtnCreateBooking.revertAnimation();
  }

  @Override public void showEmptyPhoneError() {
    mTextViewEmptyPhone.setVisibility(View.VISIBLE);
  }

  @Override public void showErrorMessage(int message) {
    showAlertMessage(getString(R.string.title_write_error), getString(message));
  }

  @Override public void closeBooking() {
    mNavigator.finishActivity((AppCompatActivity) getActivity());
  }

  @Override public void setUpBookingInformation(String serviceName, String serviceTime,
      String serviceDuration, String masterName) {
    mTextViewService.setText(serviceName);
    mTextViewTime.setText(serviceTime);
    mTextViewDuration.setText(serviceDuration);
    mTextViewMaster.setText(masterName);
  }
}
