package com.apps.twelve.floor.salon.feature.booking.mode.booking_service.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
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

  private String mName, mPhone;

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

    mEditTextName.addTextChangedListener(new TextWatcher() {

      @Override public void afterTextChanged(Editable s) {
        mName = s.toString();
      }

      @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {
      }

      @Override public void onTextChanged(CharSequence s, int start, int before, int count) {
      }
    });

    mEditTextPhone.addTextChangedListener(new TextWatcher() {

      @Override public void afterTextChanged(Editable s) {
        mPhone = s.toString();
      }

      @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {
      }

      @Override public void onTextChanged(CharSequence s, int start, int before, int count) {
      }
    });
  }

  @OnClick(R.id.btn_booking_contact) void createBooking() {
    mBookingContactsFragmentPresenter.setPersonName(mEditTextName.getText().toString());
    mBookingContactsFragmentPresenter.setPersonPhone(mEditTextPhone.getText().toString());
    mBookingContactsFragmentPresenter.sendBookingEntity();
    showToastMessage("Записаться\n" + mName + "\n" + mPhone);
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
}
