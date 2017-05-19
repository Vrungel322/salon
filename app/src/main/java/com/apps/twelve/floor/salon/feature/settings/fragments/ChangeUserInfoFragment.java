package com.apps.twelve.floor.salon.feature.settings.fragments;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import br.com.simplepass.loading_button_lib.customViews.CircularProgressButton;
import butterknife.BindView;
import butterknife.OnClick;
import com.apps.twelve.floor.salon.R;
import com.apps.twelve.floor.salon.base.BaseFragment;
import com.apps.twelve.floor.salon.feature.settings.activities.SettingsActivity;
import com.apps.twelve.floor.salon.feature.settings.presenters.ChangeUserInfoFragmentPresenter;
import com.apps.twelve.floor.salon.feature.settings.views.IChangeUserInfoFragmentView;
import com.arellomobile.mvp.presenter.InjectPresenter;

import static com.apps.twelve.floor.salon.utils.Constants.ChangingUserInfoField.EMAIL;
import static com.apps.twelve.floor.salon.utils.Constants.ChangingUserInfoField.LOGIN;
import static com.apps.twelve.floor.salon.utils.Constants.ChangingUserInfoField.NAME;
import static com.apps.twelve.floor.salon.utils.Constants.ChangingUserInfoField.PASSWORD;
import static com.apps.twelve.floor.salon.utils.Constants.ChangingUserInfoField.PHONE;
import static com.apps.twelve.floor.salon.utils.Constants.FragmentsArgumentKeys.CHANGING_FIELD;
import static com.apps.twelve.floor.salon.utils.Constants.FragmentsArgumentKeys.CHANGING_FIELD_VALUE;

/**
 * Created by Alexandra on 04.05.2017.
 */

public class ChangeUserInfoFragment extends BaseFragment implements IChangeUserInfoFragmentView {

  @InjectPresenter ChangeUserInfoFragmentPresenter mChangeUserInfoFragmentPresenter;

  @BindView(R.id.tvCurrentFieldText) TextView mCurrentFieldText;
  @BindView(R.id.tvCurrentField) TextView mCurrentField;
  @BindView(R.id.tvNewFieldText) TextView mNewFieldText;
  @BindView(R.id.etNewField) EditText mEditTextNewField;
  @BindView(R.id.btnSave) CircularProgressButton mButtonSave;

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

    ActionBar actionBar = ((SettingsActivity) getActivity()).getSupportActionBar();
    if (actionBar != null) {
      actionBar.setTitle(getString(R.string.change_user_info, field));
    }

    setTextViews(getArguments().getInt(CHANGING_FIELD));

    mCurrentField.setText(getArguments().getCharSequence(CHANGING_FIELD_VALUE));
  }

  @OnClick(R.id.btnSave) void save() {
    mButtonSave.startAnimation();
    mChangeUserInfoFragmentPresenter.saveInfo(getArguments().getInt(CHANGING_FIELD),
        mEditTextNewField.getText().toString());
  }

  @Override public void stopAnimation() {
    mButtonSave.doneLoadingAnimation(ContextCompat.getColor(getContext(), R.color.colorAccent),
        BitmapFactory.decodeResource(getResources(), R.drawable.ic_done_white_48dp));
  }

  @Override public void revertAnimation() {
    mButtonSave.revertAnimation();
  }

  @Override public void closeFragment() {
    getActivity().onBackPressed();
  }

  @Override public void showAlert() {
    showAlertMessage("Error", "Warning");
  }

  @Override public void onDestroy() {
    ((SettingsActivity) getActivity()).setTitleAppBar(R.string.menu_settings);
    super.onDestroy();
  }

  private void setTextViews(int field) {
    switch (field) {
      case NAME:
        mCurrentFieldText.setText(R.string.current_field_name);
        mNewFieldText.setText(R.string.new_field_name);
        break;
      case LOGIN:
        mCurrentFieldText.setText(R.string.current_field_login);
        mNewFieldText.setText(R.string.new_field_login);
        break;
      case PASSWORD:
        mCurrentFieldText.setText(R.string.current_field_password);
        mNewFieldText.setText(R.string.new_field_password);
        break;
      case EMAIL:
        mCurrentFieldText.setText(R.string.current_field_email);
        mNewFieldText.setText(R.string.new_field_email);
        break;
      case PHONE:
        mCurrentFieldText.setText(R.string.current_field_phone);
        mNewFieldText.setText(R.string.new_field_phone);
        break;
      default:
        break;
    }
  }
}
