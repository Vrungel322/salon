package com.apps.twelve.floor.salon.feature.booking.mode.booking_master.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import com.apps.twelve.floor.salon.R;
import com.apps.twelve.floor.salon.base.BaseFragment;
import com.apps.twelve.floor.salon.feature.booking.mode.booking_master.presenters.BookingMasterContactFragmentPresenter;
import com.apps.twelve.floor.salon.feature.booking.mode.booking_master.views.IBookingMasterContactFragmentView;
import com.apps.twelve.floor.salon.utils.Constants;
import com.arellomobile.mvp.presenter.InjectPresenter;

public class BookingMasterContactFragment extends BaseFragment
    implements IBookingMasterContactFragmentView {

  @InjectPresenter BookingMasterContactFragmentPresenter mBookingMasterContactFragmentPresenter;

  @BindView(R.id.tv_service_description) TextView mTextViewService;
  @BindView(R.id.tv_time_description) TextView mTextViewTime;
  @BindView(R.id.tv_time_duration) TextView mTextViewDuration;
  @BindView(R.id.tv_master_description) TextView mTextViewMaster;
  @BindView(R.id.tv_master_details) TextView mTextViewMasterDetails;
  @BindView(R.id.edit_name) EditText mEditTextName;
  @BindView(R.id.edit_phone) EditText mEditTextPhone;

  public static BookingMasterContactFragment newInstance() {
    Bundle args = new Bundle();
    BookingMasterContactFragment fragment = new BookingMasterContactFragment();
    fragment.setArguments(args);
    return fragment;
  }

  public BookingMasterContactFragment() {
    super(R.layout.fragment_booking_master_contact);
  }

  @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

    mTextViewService.setText(
        getArguments().getString(Constants.FragmentsArgumentKeys.SERVICE_NAME));
  }

  @OnClick(R.id.btn_booking_contact) void createBooking() {
    mBookingMasterContactFragmentPresenter.setPersonName(mEditTextName.getText().toString());
    mBookingMasterContactFragmentPresenter.setPersonPhone(mEditTextPhone.getText().toString());
    mBookingMasterContactFragmentPresenter.sendBookingEntity();
  }

  @Override public void setUpBookingInformation(String serviceName, String serviceTime,
      String serviceDuration, String masterName) {
    mTextViewService.setText(serviceName);
    mTextViewTime.setText(serviceTime);
    mTextViewDuration.setText(serviceDuration);
    mTextViewMaster.setText(masterName);
  }

  @Override public void closeActivity() {
    getActivity().finish();
  }

  @Override public void showAlert() {
    showAlertMessage("Error", "Warning");
  }
}
