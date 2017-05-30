package com.authorization.floor12.authorization.logic.userdetail.fragments;

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
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.authorization.floor12.authorization.R;
import com.authorization.floor12.authorization.R2;
import com.authorization.floor12.authorization.base.BaseFragment;
import com.authorization.floor12.authorization.data.model.UserEntity;
import com.authorization.floor12.authorization.logic.authorization.activities.ModuleSignInActivity;
import com.authorization.floor12.authorization.logic.userdetail.activities.UserProfileActivity;
import com.authorization.floor12.authorization.logic.userdetail.presenters.ChangeUserInfoFragmentPresenter;
import com.authorization.floor12.authorization.logic.userdetail.views.IChangeUserInfoFragmentView;

import static com.authorization.floor12.authorization.utils.Constants.ChangingUserInfoField.EMAIL;
import static com.authorization.floor12.authorization.utils.Constants.ChangingUserInfoField.NAME;
import static com.authorization.floor12.authorization.utils.Constants.ChangingUserInfoField.PASSWORD;
import static com.authorization.floor12.authorization.utils.Constants.ChangingUserInfoField.PHONE;
import static com.authorization.floor12.authorization.utils.Constants.FragmentsArgumentKeys.CHANGING_FIELD;
import static com.authorization.floor12.authorization.utils.Constants.FragmentsArgumentKeys.CHANGING_FIELD_VALUE;

/**
 * Created by Alexandra on 04.05.2017.
 */

public class UserInfoFragment extends BaseFragment implements IChangeUserInfoFragmentView {

  @InjectPresenter ChangeUserInfoFragmentPresenter mChangeUserInfoFragmentPresenter;

  @BindView(R2.id.tvCurrentFieldText) TextView mCurrentFieldText;
  @BindView(R2.id.tvCurrentField) TextView mCurrentField;
  @BindView(R2.id.tvNewFieldText) TextView mNewFieldText;
  @BindView(R2.id.etNewField) EditText mEditTextNewField;
  @BindView(R2.id.btnSave) CircularProgressButton mButtonSave;
  @BindView(R2.id.tvPasswordFieldText) TextView mTvPasswordFieldText;
  @BindView(R2.id.etPasswordField) EditText mEtPasswordField;
  Unbinder unbinder;

  public UserInfoFragment() {
    super(R.layout.fragment_user_info);
  }

  public static UserInfoFragment newInstance(int changingField, CharSequence currentValue) {
    Bundle args = new Bundle();
    args.putInt(CHANGING_FIELD, changingField);
    args.putCharSequence(CHANGING_FIELD_VALUE, currentValue);
    UserInfoFragment fragment = new UserInfoFragment();
    fragment.setArguments(args);
    return fragment;
  }

  @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

    String field = getResources().getStringArray(R.array.changing_field)[getArguments().getInt(
        CHANGING_FIELD)];

    ActionBar actionBar = ((UserProfileActivity) getActivity()).getSupportActionBar();
    if (actionBar != null) {
      actionBar.setTitle(getString(R.string.change_user_info, field));
    }

    setTextViews(getArguments().getInt(CHANGING_FIELD));

    mCurrentField.setText(getArguments().getCharSequence(CHANGING_FIELD_VALUE));
  }

  @OnClick(R2.id.btnSave) void save() {
    mButtonSave.startAnimation();

    int field = getArguments().getInt(CHANGING_FIELD);

    UserEntity userEntity = new UserEntity();

    switch (field) {
      case EMAIL:
        userEntity.setEmail(mEditTextNewField.getText().toString());
        userEntity.setPassword(mEtPasswordField.getText().toString());
        mChangeUserInfoFragmentPresenter.updateLogin(field, userEntity);
        break;
      case PHONE:
        userEntity.setPhone(mEditTextNewField.getText().toString());
        userEntity.setPassword(mEtPasswordField.getText().toString());
        mChangeUserInfoFragmentPresenter.updateLogin(field, userEntity);
        break;
      case NAME:
        mChangeUserInfoFragmentPresenter.saveInfo(field, mEditTextNewField.getText().toString());
        break;
    }
  }

  @Override public void stopAnimation() {
    TypedValue value = new TypedValue();
    getActivity().getTheme().resolveAttribute(R.attr.colorAccent, value, true);
    mButtonSave.doneLoadingAnimation(ContextCompat.getColor(getContext(), value.resourceId),
        BitmapFactory.decodeResource(getResources(), R.drawable.ic_done_white_48dp));
  }

  @Override public void showSignInActivity() {
    mPreferencesHelper.clear();
    mNavigator.startActivityClearStack((AppCompatActivity) getContext(),
        new Intent(getContext(), ModuleSignInActivity.class));
  }

  @Override public void showPhoneError(String errorMessage) {
    mEditTextNewField.setError(errorMessage);
  }

  @Override public void showEmailError(String errorMessage) {
    mEditTextNewField.setError(errorMessage);
  }

  @Override public void showPasswordError(String errorMessage) {
    mEtPasswordField.setError(errorMessage);
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
    ((UserProfileActivity) getActivity()).setTitleAppBar(R.string.menu_settings);
    super.onDestroy();
  }

  private void setTextViews(int field) {
    switch (field) {
      case NAME:
        mCurrentFieldText.setText(R.string.current_field_name);
        mNewFieldText.setText(R.string.new_field_name);
        mTvPasswordFieldText.setVisibility(View.GONE);
        mEtPasswordField.setVisibility(View.GONE);
        break;
      case EMAIL:
        mCurrentFieldText.setText(R.string.current_field_email);
        mNewFieldText.setText(R.string.new_field_email);
        break;
      case PHONE:
        mCurrentFieldText.setText(R.string.current_field_phone);
        mNewFieldText.setText(R.string.new_field_phone);
        break;
      case PASSWORD:
        mCurrentFieldText.setText(R.string.current_field_password);
        mNewFieldText.setText(R.string.new_field_password);
        break;
      default:
        break;
    }
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
