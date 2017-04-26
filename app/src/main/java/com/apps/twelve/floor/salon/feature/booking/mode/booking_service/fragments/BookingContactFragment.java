package com.apps.twelve.floor.salon.feature.booking.mode.booking_service.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import com.apps.twelve.floor.salon.R;
import com.apps.twelve.floor.salon.base.BaseFragment;
import com.apps.twelve.floor.salon.feature.booking.mode.booking_service.presenters.BookingContactFragmentPresenter;
import com.apps.twelve.floor.salon.feature.booking.mode.booking_service.views.IBookingContactFragmentView;
import com.apps.twelve.floor.salon.utils.Constants;
import com.arellomobile.mvp.presenter.InjectPresenter;

/**
 * Created by Alexandra on 28.03.2017.
 */

public class BookingContactFragment extends BaseFragment implements IBookingContactFragmentView {

  @InjectPresenter BookingContactFragmentPresenter mBookingContactsFragmentPresenter;

  @BindView(R.id.tv_service_description) TextView mTextViewService;
  @BindView(R.id.tv_time_description) TextView mTextViewTime;
  @BindView(R.id.tv_time_duration) TextView mTextViewDuration;
  @BindView(R.id.tv_master_description) TextView mTextViewMaster;
  @BindView(R.id.tv_master_details) TextView mTextViewMasterDetails;
  @BindView(R.id.edit_name) EditText mEditTextName;
  @BindView(R.id.edit_phone) EditText mEditTextPhone;
  @BindView(R.id.btn_booking_contact) Button mBtnCreateBooking;

  public static BookingContactFragment newInstance() {
    Bundle args = new Bundle();
    args.putString(Constants.FragmentsArgumentKeys.SERVICE_NAME, "ТЕСТОВАЯ УСЛУГА");
    BookingContactFragment fragment = new BookingContactFragment();
    fragment.setArguments(args);
    return fragment;
  }

  public BookingContactFragment() {
    super(R.layout.fragment_booking_contact);
  }

  @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

    mTextViewService.setText(
        getArguments().getString(Constants.FragmentsArgumentKeys.SERVICE_NAME));
  }

  @OnClick(R.id.btn_booking_contact) void createBooking() {
    mBtnCreateBooking.setClickable(false);
    mBookingContactsFragmentPresenter.setPersonName(mEditTextName.getText().toString());
    mBookingContactsFragmentPresenter.setPersonPhone(mEditTextPhone.getText().toString());
    mBookingContactsFragmentPresenter.sendBookingEntity();
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
    showAlertMessage("Error", "smth wrong");
  }

  @Override public void setButtonClickable() {
    mBtnCreateBooking.setClickable(true);
  }
}
