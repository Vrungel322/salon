package com.apps.twelve.floor.authorization.logic.userdetail.fragments;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import br.com.simplepass.loading_button_lib.customViews.CircularProgressButton;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import com.apps.twelve.floor.authorization.R;
import com.apps.twelve.floor.authorization.R2;
import com.apps.twelve.floor.authorization.base.BaseFragment;
import com.apps.twelve.floor.authorization.base.NoChangeBackgroundTextInputLayout;
import com.apps.twelve.floor.authorization.data.model.UserEntity;
import com.apps.twelve.floor.authorization.logic.authorization.activities.ModuleSignInActivity;
import com.apps.twelve.floor.authorization.logic.userdetail.presenters.ChangeUserInfoFragmentPresenter;
import com.apps.twelve.floor.authorization.logic.userdetail.views.IChangeUserInfoFragmentView;
import com.apps.twelve.floor.authorization.utils.ActionUtils;
import com.apps.twelve.floor.authorization.utils.DialogFactory;
import com.apps.twelve.floor.authorization.utils.ValidatorUtils;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.rey.material.app.BottomSheetDialog;

import static com.apps.twelve.floor.authorization.utils.Constants.ChangingUserInfoField.EMAIL;
import static com.apps.twelve.floor.authorization.utils.Constants.ChangingUserInfoField.NAME;
import static com.apps.twelve.floor.authorization.utils.Constants.ChangingUserInfoField.PASSWORD;
import static com.apps.twelve.floor.authorization.utils.Constants.ChangingUserInfoField.PHONE;
import static com.apps.twelve.floor.authorization.utils.Constants.FragmentsArgumentKeys.CHANGING_FIELD;
import static com.apps.twelve.floor.authorization.utils.Constants.FragmentsArgumentKeys.CHANGING_FIELD_VALUE;
import static com.apps.twelve.floor.authorization.utils.Constants.Regex.REGEX_EMAIL;
import static com.apps.twelve.floor.authorization.utils.Constants.Regex.REGEX_NOT_EMPTY;
import static com.apps.twelve.floor.authorization.utils.Constants.Regex.REGEX_PASSWORD_LENGTH;
import static com.apps.twelve.floor.authorization.utils.Constants.Regex.REGEX_PHONE_NOT_EMPTY;
import static com.basgeekball.awesomevalidation.ValidationStyle.TEXT_INPUT_LAYOUT;

/**
 * Created by Alexandra on 04.05.2017.
 */

public class ChangeUserInfoFragment extends BaseFragment implements IChangeUserInfoFragmentView {

  @InjectPresenter ChangeUserInfoFragmentPresenter mChangeUserInfoFragmentPresenter;

  @BindView(R2.id.tvCurrentFieldText) TextView mCurrentFieldText;
  @BindView(R2.id.tvCurrentField) TextView mCurrentField;
  @BindView(R2.id.tvNewFieldText) TextView mNewFieldText;
  @BindView(R2.id.etNewField) EditText mEditTextNewField;
  @BindView(R2.id.btnSave) CircularProgressButton mButtonSave;
  @BindView(R2.id.tvPasswordFieldText) TextView mTvPasswordFieldText;
  @BindView(R2.id.etPasswordField) EditText mEtPasswordField;
  @BindView(R2.id.til_new_field) NoChangeBackgroundTextInputLayout mTilNewField;
  @BindView(R2.id.til_password_field) NoChangeBackgroundTextInputLayout mTilPasswordField;

  private Unbinder unbinder;
  private Dialog mDialog;
  private AwesomeValidation mAwesomeValidation;
  private BottomSheetDialog mVerifyDialog;

  //verify dialog
  private EditText mEtVerifyCode;
  private NoChangeBackgroundTextInputLayout mTilVerifyCode;
  private CircularProgressButton mBtnVerifyCode;

  public ChangeUserInfoFragment() {
    super(R.layout.fragment_change_user_info);
  }

  public static ChangeUserInfoFragment newInstance(int changingField, CharSequence currentValue) {
    Bundle args = new Bundle();
    args.putInt(CHANGING_FIELD, changingField);
    args.putCharSequence(CHANGING_FIELD_VALUE, currentValue);
    ChangeUserInfoFragment fragment = new ChangeUserInfoFragment();
    fragment.setArguments(args);
    return fragment;
  }

  @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

    String field = getResources().getStringArray(R.array.changing_field)[getArguments().getInt(
        CHANGING_FIELD)];

    ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
    if (actionBar != null) {
      actionBar.setTitle(getString(R.string.change_user_info, field));
    }

    setTextViews(getArguments().getInt(CHANGING_FIELD));

    mCurrentField.setText(getArguments().getCharSequence(CHANGING_FIELD_VALUE));
  }

  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    View rootView = super.onCreateView(inflater, container, savedInstanceState);
    unbinder = ButterKnife.bind(this, rootView);

    mAwesomeValidation = new AwesomeValidation(TEXT_INPUT_LAYOUT);

    if (getArguments().getInt(CHANGING_FIELD) == NAME) {
      mAwesomeValidation.addValidation(mTilNewField, REGEX_NOT_EMPTY,
          getString(R.string.error_empty_name));
    } else {
      if (getArguments().getInt(CHANGING_FIELD) == EMAIL) {
        mAwesomeValidation.addValidation(mTilNewField, REGEX_EMAIL,
            getString(R.string.error_not_regex_field_email));
      } else if (getArguments().getInt(CHANGING_FIELD) == PHONE) {
        mAwesomeValidation.addValidation(mTilNewField, REGEX_PHONE_NOT_EMPTY,
            getString(R.string.error_not_regex_field_phone));
      }
      mAwesomeValidation.addValidation(mTilPasswordField, REGEX_PASSWORD_LENGTH,
          getString(R.string.error_password_length));
    }

    return rootView;
  }

  @OnClick(R2.id.root_layout) public void onRootLayoutClick() {
    ActionUtils.hideKeyboard(getContext());
  }

  @OnClick(R2.id.btnSave) void save(View view) {

    if (mAwesomeValidation.validate()) {
      int field = getArguments().getInt(CHANGING_FIELD);
      mButtonSave.startAnimation();
      UserEntity userEntity = new UserEntity();

      switch (field) {
        case EMAIL:
          userEntity.setEmail(mEditTextNewField.getText().toString());
          userEntity.setPassword(mEtPasswordField.getText().toString());
          mChangeUserInfoFragmentPresenter.updateEmail1(userEntity);
          break;
        case PHONE:
          userEntity.setPhone(mEditTextNewField.getText().toString());
          userEntity.setPassword(mEtPasswordField.getText().toString());
          mChangeUserInfoFragmentPresenter.updatePhone1(userEntity);
          break;
        case NAME:
          mChangeUserInfoFragmentPresenter.saveInfo(field, mEditTextNewField.getText().toString());
          break;
      }
    }
  }

  @Override public void stopAnimation() {
    TypedValue value = new TypedValue();
    getActivity().getTheme().resolveAttribute(R.attr.colorAccent, value, true);
    mButtonSave.doneLoadingAnimation(ContextCompat.getColor(getContext(), value.resourceId),
        BitmapFactory.decodeResource(getResources(), R.drawable.ic_done_white_48dp));
  }

  @Override public void revertVerifyButtonAnimation() {
    if (mBtnVerifyCode != null) {
      mBtnVerifyCode.revertAnimation();
    }
  }

  @Override public void stopVerifyButtonAnimation() {
    if (mBtnVerifyCode != null) {
      TypedValue value = new TypedValue();
      getActivity().getTheme().resolveAttribute(R.attr.colorAccent, value, true);
      mBtnVerifyCode.doneLoadingAnimation(ContextCompat.getColor(getContext(), value.resourceId),
          BitmapFactory.decodeResource(getResources(), R.drawable.ic_done_white_48dp));
    }
  }

  @Override public void showSignInActivity() {
    mPreferencesHelper.clear();
    mNavigator.startActivityClearStack((AppCompatActivity) getContext(),
        new Intent(getContext(), ModuleSignInActivity.class));
  }

  @Override public void showPhoneError(String errorMessage) {
    mTilNewField.setError(errorMessage);
  }

  @Override public void showEmailError(String errorMessage) {
    mTilNewField.setError(errorMessage);
  }

  @Override public void showPasswordError(String errorMessage) {
    mTilPasswordField.setError(errorMessage);
  }

  @Override public void revertAnimation() {
    mButtonSave.revertAnimation();
  }

  @Override public void closeFragment() {
    getActivity().onBackPressed();
  }

  @Override public void showAlerter(@StringRes int resId) {
    showAlert(getString(R.string.error_title), getString(resId));
  }

  @Override public void showConnectErrorMessage() {
    showAlert(getString(R.string.error_title_no_internet_connection),
        getString(R.string.no_internet_connection));
  }

  @Override public void onDestroy() {
    setTitleAppBar(R.string.profile_title);
    super.onDestroy();
  }

  private void setTextViews(int field) {
    switch (field) {
      case NAME:
        mCurrentFieldText.setText(R.string.current_field_name);
        mNewFieldText.setText(R.string.new_field_name);
        mTvPasswordFieldText.setVisibility(View.GONE);
        mTilPasswordField.setVisibility(View.GONE);
        mEditTextNewField.setHint(R.string.user_edit_profile_name_hint);
        break;
      case EMAIL:
        mCurrentFieldText.setText(R.string.current_field_email);
        mNewFieldText.setText(R.string.new_field_email);
        mEditTextNewField.setHint(R.string.user_edit_profile_email_hint);
        break;
      case PHONE:
        mCurrentFieldText.setText(R.string.current_field_phone);
        mNewFieldText.setText(R.string.new_field_phone);
        mEditTextNewField.setHint(R.string.user_edit_profile_phone_hint);
        break;
      case PASSWORD:
        mCurrentFieldText.setText(R.string.current_field_password);
        mNewFieldText.setText(R.string.new_field_password);
        break;
      default:
        break;
    }
  }

  @Override public void showVerifyDialog() {
    mVerifyDialog = new BottomSheetDialog(getContext());

    View v = LayoutInflater.from(getContext()).inflate(R.layout.dialog_verify, null);

    UserEntity userEntity = new UserEntity();

    mTilVerifyCode = v.findViewById(R.id.til_verify_code);
    mEtVerifyCode = v.findViewById(R.id.et_verify_code);
    mBtnVerifyCode = v.findViewById(R.id.btn_verify_code);

    String value = mEditTextNewField.getText().toString();

    if (ValidatorUtils.isValidEmail(value)) {
      userEntity.setEmail(value);
    } else if (ValidatorUtils.isValidPhone(value)) {
      userEntity.setPhone(value);
    }

    mBtnVerifyCode.setOnClickListener(view -> {
      userEntity.setVerifyCode(mEtVerifyCode.getText().toString());
      mBtnVerifyCode.startAnimation();
      if (ValidatorUtils.isValidEmail(value)) {
        mChangeUserInfoFragmentPresenter.updateEmail2(userEntity);
      } else if (ValidatorUtils.isValidPhone(value)) {
        mChangeUserInfoFragmentPresenter.updatePhone2(userEntity);
      }
    });

    mVerifyDialog.applyStyle(mPreferencesHelper.getStyleResId())
        .addContentView(v, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT));
    mVerifyDialog.setOnCancelListener(
        dialogInterface -> mChangeUserInfoFragmentPresenter.closeVerifyDialog());
    mVerifyDialog.show();
  }

  @Override public void closeVerifyDialog() {
    if (mVerifyDialog != null) {
      mVerifyDialog.dismiss();
    }
  }

  @Override public void showVerifyError(String errorMessage) {
    if (mTilVerifyCode != null) {
      mTilVerifyCode.setError(errorMessage);
    }
  }

  @Override public void showDialogMessage(String message) {
    mDialog = DialogFactory.createSimpleOkDialog(getContext(),
        getString(R.string.dialog_title_change_info), message, (dialog, which) -> {
          mChangeUserInfoFragmentPresenter.closeDialogMessage();
          mChangeUserInfoFragmentPresenter.showVerifyDialog();
        });
    mDialog.setOnCancelListener(dialog -> {
      mChangeUserInfoFragmentPresenter.closeDialogMessage();
      mChangeUserInfoFragmentPresenter.showVerifyDialog();
    });
    mDialog.show();
  }

  @Override public void closeDialogMessage() {
    if (mDialog != null) {
      mDialog.dismiss();
    }
  }

  @Override public void onDestroyView() {
    super.onDestroyView();
    unbinder.unbind();
    ActionUtils.hideKeyboard(getContext());
    if (mDialog != null) {
      mDialog.dismiss();
    }
    if (mVerifyDialog != null) {
      mVerifyDialog.dismiss();
    }
  }
}
