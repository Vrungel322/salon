package com.apps.twelve.floor.salon.feature.booking.mode.booking_master.fragments;

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
import com.apps.twelve.floor.salon.feature.booking.mode.booking_master.presenters.ChooseMasterContactFragmentPresenter;
import com.apps.twelve.floor.salon.feature.booking.mode.booking_master.views.IChooseMasterContactFragmentView;
import com.arellomobile.mvp.presenter.InjectPresenter;

public class ChooseMasterContactFragment extends BaseFragment
    implements IChooseMasterContactFragmentView {

  @InjectPresenter ChooseMasterContactFragmentPresenter mChooseMasterContactFragmentPresenter;

  @BindView(R.id.tv_service_description) TextView mTextViewService;
  @BindView(R.id.tv_time_description) TextView mTextViewTime;
  @BindView(R.id.tv_time_duration) TextView mTextViewDuration;
  @BindView(R.id.tv_master_description) TextView mTextViewMaster;
  @BindView(R.id.edit_name) EditText mEditTextName;
  @BindView(R.id.edit_phone) EditText mEditTextPhone;
  @BindView(R.id.tvEmptyPhone) TextView mTextViewEmptyPhone;
  @BindView(R.id.btn_booking_contact) CircularProgressButton mBtnCreateBooking;

  public ChooseMasterContactFragment() {
    super(R.layout.fragment_choose_master_contact);
  }

  public static ChooseMasterContactFragment newInstance() {
    Bundle args = new Bundle();
    ChooseMasterContactFragment fragment = new ChooseMasterContactFragment();
    fragment.setArguments(args);
    return fragment;
  }

  @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
  }

  @OnClick(R.id.btn_booking_contact) void animate() {
    mChooseMasterContactFragmentPresenter.setPersonName(mEditTextName.getText().toString());
    mChooseMasterContactFragmentPresenter.setPersonPhone(mEditTextPhone.getText().toString());
    mChooseMasterContactFragmentPresenter.sendBookingEntity();
  }

  @Override public void onResume() {
    super.onResume();
    if (mAuthorizationManager.isAuthorized()) {
      mEditTextName.setText(mAuthorizationManager.getUserName());
      if (mAuthorizationManager.getUserPhone().equals("")) {
        mChooseMasterContactFragmentPresenter.setPersonPhone();
      } else {
        mChooseMasterContactFragmentPresenter.setPersonPhone(mAuthorizationManager.getUserPhone());
      }
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

  @Override public void setLastPhone(String lastPhone) {
    mEditTextPhone.setText(lastPhone);
  }

  @Override public void setUpBookingInformation(String serviceName, String serviceTime,
      String serviceDuration, String masterName) {
    mTextViewService.setText(serviceName);
    mTextViewTime.setText(serviceTime);
    mTextViewDuration.setText(serviceDuration);
    mTextViewMaster.setText(masterName);
  }
}
