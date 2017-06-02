package com.apps.twelve.floor.salon.feature.settings.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import butterknife.OnItemSelected;
import com.apps.twelve.floor.salon.R;
import com.apps.twelve.floor.salon.base.BaseFragment;
import com.apps.twelve.floor.salon.feature.settings.presenters.SettingsFragmentPresenter;
import com.apps.twelve.floor.salon.feature.settings.views.ISettingsFragmentView;
import com.apps.twelve.floor.salon.feature.start_point.activities.StartActivity;
import com.apps.twelve.floor.salon.utils.DialogFactory;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.squareup.picasso.Picasso;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;
import de.hdodenhof.circleimageview.CircleImageView;
import timber.log.Timber;

import static android.app.Activity.RESULT_OK;
import static com.apps.twelve.floor.salon.utils.Constants.ChangingUserInfoField.EMAIL;
import static com.apps.twelve.floor.salon.utils.Constants.ChangingUserInfoField.NAME;
import static com.apps.twelve.floor.salon.utils.Constants.ChangingUserInfoField.PASSWORD;
import static com.apps.twelve.floor.salon.utils.Constants.ChangingUserInfoField.PHONE;

public class SettingsFragment extends BaseFragment implements ISettingsFragmentView {

  @InjectPresenter SettingsFragmentPresenter mSettingsFragmentPresenter;

  @BindView(R.id.ivProfilePhoto) CircleImageView mProfileImage;
  @BindView(R.id.tvName) TextView mTextViewName;
  @BindView(R.id.tvEmail) TextView mTextViewEmail;
  @BindView(R.id.tvPhone) TextView mTextViewPhone;
  @BindView(R.id.spinnerGender) Spinner mSpinnerGender;
  @BindView(R.id.hidingBlockTop) RelativeLayout mRelativeLayoutHidingBlockTop;
  @BindView(R.id.hidingBlockBottom) RelativeLayout mRelativeLayoutHidingBlockBottom;

  private AlertDialog mChooseThemeDialog;

  public SettingsFragment() {
    super(R.layout.fragment_settings);
  }

  public static SettingsFragment newInstance() {
    Bundle args = new Bundle();
    SettingsFragment fragment = new SettingsFragment();
    fragment.setArguments(args);
    return fragment;
  }

  @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

    if (!mDataManager.isAuthorized()) mSettingsFragmentPresenter.hideUserSettings();

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

  @Override public void setUserGender(String gender) {
    if (gender.equals("")) {
      mSpinnerGender.setSelection(2);
    } else {
      mSpinnerGender.setSelection(Integer.parseInt(gender));
    }
  }

  @Override public void showSetThemeDialog(int position) {
    String[] themes = getResources().getStringArray(R.array.dialog_theme);
    mChooseThemeDialog = DialogFactory.createAlertDialogBuilder(getActivity(),
        getString(R.string.dialog_title_select_theme))
        .setSingleChoiceItems(themes, position, (dialog, which) -> {
          mSettingsFragmentPresenter.setThemeApp(which);
          mNavigator.startActivityClearStack((AppCompatActivity) getActivity(),
              new Intent(getActivity(), StartActivity.class));
        })
        .create();
    mChooseThemeDialog.show();
  }

  @Override public void closeSetThemeDialog() {
    if (mChooseThemeDialog != null) {
      mChooseThemeDialog.dismiss();
    }
  }

  /** edit user info stuff */

  @OnClick(R.id.rlName) void changeName() {
    mNavigator.addFragmentBackStack((AppCompatActivity) getActivity(), R.id.container_settings,
        ChangeUserInfoFragment.newInstance(NAME, mTextViewName.getText()));
  }

  @OnClick(R.id.rlPassword) void changePassword() {
    mNavigator.addFragmentBackStack((AppCompatActivity) getActivity(), R.id.container_settings,
        ChangeUserInfoFragment.newInstance(PASSWORD, getString(R.string.info_unknown)));
  }

  @OnClick(R.id.rlEmail) void changeEmail() {
    mNavigator.addFragmentBackStack((AppCompatActivity) getActivity(), R.id.container_settings,
        ChangeUserInfoFragment.newInstance(EMAIL, mTextViewEmail.getText()));
  }

  @OnClick(R.id.rlPhone) void changePhone() {
    mNavigator.addFragmentBackStack((AppCompatActivity) getActivity(), R.id.container_settings,
        ChangeUserInfoFragment.newInstance(PHONE, mTextViewPhone.getText()));
  }

  @OnItemSelected(R.id.spinnerGender) void changeGender(int position) {
    mSettingsFragmentPresenter.saveGender(position);
  }

  /** bottom buttons */

  @OnClick(R.id.rlNotifications) void editNotifications() {
    mNavigator.addFragmentBackStack((AppCompatActivity) getActivity(), R.id.container_settings,
        NotificationSettingsFragment.newInstance());
  }

  @OnClick(R.id.rlProblem) void problems() {
    mNavigator.addFragmentBackStack((AppCompatActivity) getActivity(), R.id.container_settings,
        ReportProblemFragment.newInstance());
  }

  @OnClick(R.id.rlTheme) void changeTheme() {
    mSettingsFragmentPresenter.showSetThemeDialog();
  }

  @OnClick(R.id.rlClearHistory) void clearHistory() {
    showToastMessage("clear history");
  }

  @OnClick(R.id.rlExit) void exit() {
    showToastMessage("Exit");
  }

  /** change user image stuff */

  public void setUserPhoto(Uri uri) {
    Picasso.with(getActivity())
        .load(uri)
        .error(R.drawable.ic_user_female_settings)
        .into(mProfileImage);
  }

  @OnClick(R.id.btnChangePhoto) void getNewImage() {
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
        mSettingsFragmentPresenter.savePhoto(uri.toString());
      } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
        Timber.e(result.getError());
      }
    }
  }

  @Override public void hideUserSettings() {
    mRelativeLayoutHidingBlockTop.setVisibility(View.GONE);
    mRelativeLayoutHidingBlockBottom.setVisibility(View.GONE);
  }

  @Override public void onDestroy() {
    super.onDestroy();
    if (mChooseThemeDialog != null) {
      mChooseThemeDialog.dismiss();
    }
  }
}
