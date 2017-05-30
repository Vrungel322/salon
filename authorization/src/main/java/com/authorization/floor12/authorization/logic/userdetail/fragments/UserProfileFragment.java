package com.authorization.floor12.authorization.logic.userdetail.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemSelected;
import butterknife.Unbinder;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.authorization.floor12.authorization.R;
import com.authorization.floor12.authorization.R2;
import com.authorization.floor12.authorization.base.BaseFragment;
import com.authorization.floor12.authorization.logic.authorization.activities.ModuleSignInActivity;
import com.authorization.floor12.authorization.logic.userdetail.presenters.UserProfileFragmentPresenter;
import com.authorization.floor12.authorization.logic.userdetail.views.IUserProfileFragmentView;
import com.squareup.picasso.Picasso;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;
import de.hdodenhof.circleimageview.CircleImageView;
import timber.log.Timber;

import static android.app.Activity.RESULT_OK;
import static com.authorization.floor12.authorization.utils.Constants.ChangingUserInfoField.EMAIL;
import static com.authorization.floor12.authorization.utils.Constants.ChangingUserInfoField.NAME;
import static com.authorization.floor12.authorization.utils.Constants.ChangingUserInfoField.PHONE;
import static com.authorization.floor12.authorization.utils.Constants.UserProfile.FEMALE;
import static com.authorization.floor12.authorization.utils.Constants.UserProfile.MALE;

public class UserProfileFragment extends BaseFragment implements IUserProfileFragmentView {

  @InjectPresenter UserProfileFragmentPresenter mUserProfileFragmentPresenter;

  @BindView(R2.id.ivProfilePhoto) CircleImageView mProfileImage;
  @BindView(R2.id.tvName) TextView mTextViewName;
  @BindView(R2.id.tvPassword) TextView mTextViewPassword;
  @BindView(R2.id.tvEmail) TextView mTextViewEmail;
  @BindView(R2.id.tvPhone) TextView mTextViewPhone;
  @BindView(R2.id.spinnerGender) Spinner mSpinnerGender;
  @BindView(R2.id.cbLogoutAll) CheckBox mLogoutAll;
  Unbinder unbinder;

  public UserProfileFragment() {
    super(R.layout.fragment_user_profile);
  }

  public static UserProfileFragment newInstance() {
    Bundle args = new Bundle();
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
    mTextViewPhone.setText(phone);
  }

  @Override public void setUserPassword(String password) {
    mTextViewPassword.setText(password);
  }

  @Override public void setUserGender(String gender) {
    mSpinnerGender.setSelection(getItemPosition(gender));
  }

  /** edit user info stuff */

  @OnClick(R2.id.rlName) void changeName() {
    mNavigator.addFragmentBackStack((AppCompatActivity) getActivity(), R.id.container_settings,
        UserInfoFragment.newInstance(NAME, mTextViewName.getText()));
  }

  @OnClick(R2.id.rlPassword) void changePassword() {
    mNavigator.addFragmentBackStack((AppCompatActivity) getActivity(), R.id.container_settings,
        ChangePasswordFragment.newInstance());
  }

  @OnClick(R2.id.rlEmail) void changeEmail() {
    mNavigator.addFragmentBackStack((AppCompatActivity) getActivity(), R.id.container_settings,
        UserInfoFragment.newInstance(EMAIL, mTextViewEmail.getText()));
  }

  @OnClick(R2.id.rlPhone) void changePhone() {
    mNavigator.addFragmentBackStack((AppCompatActivity) getActivity(), R.id.container_settings,
        UserInfoFragment.newInstance(PHONE, mTextViewPhone.getText()));
  }

  @OnItemSelected(R2.id.spinnerGender) void changeGender(int position) {
    mUserProfileFragmentPresenter.saveGender(mSpinnerGender.getSelectedItem().toString());
  }

  @OnClick(R2.id.rlDeleteAccount) public void deleteAccount() {
    mNavigator.addFragmentBackStack((AppCompatActivity) getActivity(), R.id.container_settings,
        DeleteAccountFragment.newInstance());
  }

  @OnClick(R2.id.rlHistoryActivity) public void onHistoryActivity() {
    showToastMessage("Заглушка");
  }

  @OnClick(R2.id.rlExit) void exit() {
    if (mLogoutAll.isChecked()) {
      mUserProfileFragmentPresenter.logoutAll();
    } else {
      mUserProfileFragmentPresenter.logout();
    }
  }

  @Override public void showSignIn() {
    mPreferencesHelper.clear();
    mNavigator.startActivityClearStack((AppCompatActivity) getContext(),
        new Intent(getContext(), ModuleSignInActivity.class));
  }

  /** change user image stuff */

  public void setUserPhoto(Uri uri) {
    Picasso.with(getActivity()).load(uri).into(mProfileImage);
  }

  @OnClick(R2.id.btnChangePhoto) void getNewImage() {
    CropImage.activity(null)
        .setFixAspectRatio(true)
        .setCropShape(CropImageView.CropShape.OVAL)
        .setShowCropOverlay(true)
        .start(getContext(), this);
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

  private int getItemPosition(String value) {
    switch (value) {
      case MALE:
        return 1;
      case FEMALE:
        return 2;
      default:
        return 0;
    }
  }

  @Override public void showConnectErrorMessage() {
    showAlert(getString(R.string.error_title_no_internet_connection),
        getString(R.string.no_internet_connection));
  }

  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    // TODO: inflate a fragment view
    View rootView = super.onCreateView(inflater, container, savedInstanceState);
    unbinder = ButterKnife.bind(this, rootView);
    return rootView;
  }

  @Override public void onDestroyView() {
    super.onDestroyView();
    unbinder.unbind();
  }
}
