package com.apps.twelve.floor.authorization.logic.userdetail.fragments;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import com.apps.twelve.floor.authorization.R;
import com.apps.twelve.floor.authorization.R2;
import com.apps.twelve.floor.authorization.base.BaseFragment;
import com.apps.twelve.floor.authorization.logic.userdetail.presenters.UserProfileFragmentPresenter;
import com.apps.twelve.floor.authorization.logic.userdetail.views.IUserProfileFragmentView;
import com.apps.twelve.floor.authorization.utils.DateUtils;
import com.apps.twelve.floor.authorization.utils.DialogFactory;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.bumptech.glide.Glide;
import com.bumptech.glide.signature.StringSignature;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;
import de.hdodenhof.circleimageview.CircleImageView;
import java.util.Calendar;
import java.util.Locale;
import timber.log.Timber;

import static android.app.Activity.RESULT_OK;
import static com.apps.twelve.floor.authorization.utils.Constants.ChangingUserInfoField.EMAIL;
import static com.apps.twelve.floor.authorization.utils.Constants.ChangingUserInfoField.NAME;
import static com.apps.twelve.floor.authorization.utils.Constants.ChangingUserInfoField.PHONE;
import static com.apps.twelve.floor.authorization.utils.Constants.UserProfile.ARG_CONTAINER_RES_ID;

public class UserProfileFragment extends BaseFragment implements IUserProfileFragmentView {

  @InjectPresenter UserProfileFragmentPresenter mUserProfileFragmentPresenter;

  @BindView(R2.id.ivProfilePhoto) CircleImageView mProfileImage;
  @BindView(R2.id.tvName) TextView mTextViewName;
  @BindView(R2.id.tvEmail) TextView mTextViewEmail;
  @BindView(R2.id.tvPhone) TextView mTextViewPhone;
  Unbinder unbinder;
  @BindView(R2.id.tvGender) TextView mTvGender;
  @BindView(R2.id.tvBirthDate) TextView mTvBirthDate;

  private Dialog mChangeGenderDialog;
  private Dialog mExitDialog;
  private ProgressDialog mExitProgressDialog;
  private int mSelectedGenderPosition;

  public UserProfileFragment() {
    super(R.layout.fragment_user_profile);
  }

  public static UserProfileFragment newInstance(@IdRes int resId) {
    Bundle args = new Bundle();
    args.putInt(ARG_CONTAINER_RES_ID, resId);
    UserProfileFragment fragment = new UserProfileFragment();
    fragment.setArguments(args);
    return fragment;
  }

  @Override public void setUserName(String name) {
    mTextViewName.setText(name);
  }

  @Override public void setUserEmail(String email) {
    mTextViewEmail.setText(email);
  }

  @Override public void setUserPhone(String phone) {
    if (TextUtils.isEmpty(phone)) {
      mTextViewPhone.setText(getString(R.string.default_phone));
    } else {
      mTextViewPhone.setText(phone);
    }
  }

  @Override public void setUserGender(@StringRes int resId) {
    mTvGender.setText(getString(resId));
  }

  @Override public void showChangeGenderDialog() {
    String[] genders = getResources().getStringArray(R.array.gender);
    mChangeGenderDialog = DialogFactory.createAlertDialogBuilder(getContext(),
        getString(R.string.dialog_title_select_gender))
        .setSingleChoiceItems(genders, mUserProfileFragmentPresenter.getGenderPosition(),
            (dialog, which) -> mSelectedGenderPosition = which)
        .setPositiveButton(R.string.btn_ok,
            (dialog, which) -> mUserProfileFragmentPresenter.saveGender(mSelectedGenderPosition))
        .setCancelable(false)
        .create();
    mChangeGenderDialog.setOnCancelListener(
        dialog -> mUserProfileFragmentPresenter.closeChangeGenderDialog());
    mChangeGenderDialog.show();
  }

  @Override public void closeChangeGenderDialog() {
    if (mChangeGenderDialog != null) {
      mChangeGenderDialog.dismiss();
    }
  }

  /** edit user info stuff */

  @OnClick(R2.id.rlName) void changeName() {
    mNavigator.addFragmentBackStack((AppCompatActivity) getActivity(),
        getArguments().getInt(ARG_CONTAINER_RES_ID),
        ChangeUserInfoFragment.newInstance(NAME, mTextViewName.getText()));
  }

  @OnClick(R2.id.rlPassword) void changePassword() {
    mNavigator.addFragmentBackStack((AppCompatActivity) getActivity(),
        getArguments().getInt(ARG_CONTAINER_RES_ID), ChangePasswordFragment.newInstance());
  }

  @OnClick(R2.id.rlEmail) void changeEmail() {
    mNavigator.addFragmentBackStack((AppCompatActivity) getActivity(),
        getArguments().getInt(ARG_CONTAINER_RES_ID),
        ChangeUserInfoFragment.newInstance(EMAIL, mTextViewEmail.getText()));
  }

  @OnClick(R2.id.rlPhone) void changePhone() {
    mNavigator.addFragmentBackStack((AppCompatActivity) getActivity(),
        getArguments().getInt(ARG_CONTAINER_RES_ID),
        ChangeUserInfoFragment.newInstance(PHONE, mTextViewPhone.getText()));
  }

  @OnClick(R2.id.tvGender) public void changeGender() {
    mUserProfileFragmentPresenter.showChangeGenderDialog();
  }

  @Override public void setUserBirthDay(String date) {
    if (TextUtils.isEmpty(date)) {
      mTvBirthDate.setText(R.string.default_birth_day);
    } else {
      mTvBirthDate.setText(date);
    }
  }

  @OnClick(R2.id.rlBirthDate) public void showDatePickerDialog() {
    //setup DatePickerDialog locale
    Locale locale = getResources().getConfiguration().locale;
    Locale.setDefault(locale);

    Calendar now = DateUtils.parseClientDate(mTvBirthDate.getText().toString());
    DatePickerDialog dpd = DatePickerDialog.newInstance((view, year, monthOfYear, dayOfMonth) -> {
      String date =
          String.format(Locale.getDefault(), "%d-%02d-%02d", year, (monthOfYear + 1), dayOfMonth);
      mUserProfileFragmentPresenter.saveUserBirthDay(date);
    }, now.get(Calendar.YEAR), now.get(Calendar.MONTH), now.get(Calendar.DAY_OF_MONTH));
    dpd.setCancelText(R.string.btn_cancel);
    dpd.setOkText(R.string.btn_ok);
    dpd.show(getActivity().getFragmentManager(), "Datepickerdialog");
  }

  @OnClick(R2.id.rlDeleteAccount) public void deleteAccount() {
    mNavigator.addFragmentBackStack((AppCompatActivity) getActivity(),
        getArguments().getInt(ARG_CONTAINER_RES_ID), DeleteAccountFragment.newInstance());
  }

  @OnClick(R2.id.rlHistoryActivity) public void onHistoryActivity() {
    mNavigator.addFragmentBackStack((AppCompatActivity) getActivity(),
        getArguments().getInt(ARG_CONTAINER_RES_ID), ActivityHistoryFragment.newInstance());
  }

  @OnClick(R2.id.btnChangePhoto) void getNewImage() {
    CropImage.activity(null)
        .setFixAspectRatio(true)
        .setCropShape(CropImageView.CropShape.OVAL)
        .setShowCropOverlay(true)
        .start(getContext(), this);
  }

  @OnClick(R2.id.rlExit) void exit() {
    mUserProfileFragmentPresenter.showExitDialog();
  }

  @Override public void showExitDialog() {
    mExitDialog =
        DialogFactory.createAlertDialogBuilder(getContext(), getString(R.string.dialog_title_exit),
            getString(R.string.dialog_message_exit))
            .setPositiveButton(R.string.btn_ok,
                (dialog, which) -> mUserProfileFragmentPresenter.logout())
            .setNegativeButton(R.string.btn_cancel,
                (dialog, which) -> mUserProfileFragmentPresenter.closeExitDialog())
            .setCancelable(false)
            .create();
    mExitDialog.setOnCancelListener(dialog -> mUserProfileFragmentPresenter.closeExitDialog());
    mExitDialog.show();
  }

  @Override public void closeExitDialog() {
    if (mExitDialog != null) {
      mExitDialog.dismiss();
    }
  }

  @Override public void showExitProgressDialog() {
    mExitProgressDialog = DialogFactory.createProgressDialog(getContext(),
        getString(R.string.dialog_progress_exit_message));
    mExitProgressDialog.setCancelable(false);
    mExitProgressDialog.show();
  }

  @Override public void closeExitProgressDialog() {
    if (mExitProgressDialog != null) {
      mExitProgressDialog.dismiss();
    }
  }

  @Override public void closeFragment() {
    mPreferencesHelper.clear();
    getActivity().onBackPressed();
  }

  /** change user image stuff */

  public void setUserPhoto(Uri uri) {
    Glide.with(getActivity())
        .load(uri)
        .placeholder(R.drawable.cap)
        .error(R.drawable.cap)
        .dontAnimate()
        .signature(new StringSignature(String.valueOf(System.currentTimeMillis())))
        .into(mProfileImage);
  }

  @Override public void onActivityResult(int requestCode, int resultCode, Intent data) {
    if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
      CropImage.ActivityResult result = CropImage.getActivityResult(data);
      if (resultCode == RESULT_OK) {
        Uri uri = result.getUri();
        mUserProfileFragmentPresenter.savePhoto(uri.toString());
      } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
        Timber.e(result.getError());
      }
    }
  }

  @Override public void showConnectErrorMessage() {
    showAlert(getString(R.string.error_title_no_internet_connection),
        getString(R.string.no_internet_connection_save_changes));
  }

  @Override public void showToastMessage(@StringRes int resId) {
    showToastMessage(resId);
  }

  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    View rootView = super.onCreateView(inflater, container, savedInstanceState);
    // TODO: 20/07/17 temp solution
    Locale.setDefault(new Locale("ru"));
    unbinder = ButterKnife.bind(this, rootView);
    return rootView;
  }

  @Override public void onDestroyView() {
    super.onDestroyView();
    if (mChangeGenderDialog != null) {
      mChangeGenderDialog.dismiss();
    }
    if (mExitDialog != null) {
      mExitDialog.dismiss();
    }
    if (mExitProgressDialog != null) {
      mExitProgressDialog.dismiss();
    }
    unbinder.unbind();
  }
}
