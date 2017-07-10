package com.apps.twelve.floor.salon.feature.settings.fragments;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatRadioButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import com.apps.twelve.floor.salon.App;
import com.apps.twelve.floor.salon.R;
import com.apps.twelve.floor.salon.base.Navigator;
import com.apps.twelve.floor.salon.feature.settings.presenters.ThemeDialogFragmentPresenter;
import com.apps.twelve.floor.salon.feature.settings.views.IThemeDialogFragmentView;
import com.apps.twelve.floor.salon.feature.start_point.activities.StartActivity;
import com.arellomobile.mvp.MvpDialogFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import javax.inject.Inject;

import static com.apps.twelve.floor.salon.utils.Constants.Theme.BLUE;
import static com.apps.twelve.floor.salon.utils.Constants.Theme.GRAY;
import static com.apps.twelve.floor.salon.utils.Constants.Theme.GREEN;
import static com.apps.twelve.floor.salon.utils.Constants.Theme.PINK;
import static com.apps.twelve.floor.salon.utils.Constants.Theme.PURPLE;
import static com.apps.twelve.floor.salon.utils.Constants.Theme.RED;
import static com.apps.twelve.floor.salon.utils.Constants.Theme.YELLOW;

/**
 * Created by John on 29.06.2017.
 */

public class ThemeDialogFragment extends MvpDialogFragment implements IThemeDialogFragmentView {

  @InjectPresenter ThemeDialogFragmentPresenter mThemeDialogFragmentPresenter;

  @Inject protected Navigator mNavigator;

  @BindView(R.id.radioButtonPink) AppCompatRadioButton mRadioButtonPink;
  @BindView(R.id.radioButtonBlue) AppCompatRadioButton mRadioButtonBlue;
  @BindView(R.id.radioButtonGreen) AppCompatRadioButton mRadioButtonGreen;
  @BindView(R.id.radioButtonYellow) AppCompatRadioButton mRadioButtonYellow;
  @BindView(R.id.radioButtonGray) AppCompatRadioButton mRadioButtonGray;
  @BindView(R.id.radioButtonPurple) AppCompatRadioButton mRadioButtonPurple;
  @BindView(R.id.radioButtonRed) AppCompatRadioButton mRadioButtonRed;

  private Unbinder mUnbinder;

  private int positionTheme;

  @Override public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    App.getAppComponent().inject(this);
  }

  @Nullable @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
      Bundle savedInstanceState) {
    final View fragmentView = inflater.inflate(R.layout.fragment_theme_dialog, container, false);
    mUnbinder = ButterKnife.bind(this, fragmentView);
    return fragmentView;
  }

  @Override public Dialog onCreateDialog(Bundle savedInstanceState) {
    return new Dialog(getActivity(), R.style.ThemeDialog);
  }

  @Override public void onDestroyView() {
    super.onDestroyView();
    mUnbinder.unbind();
  }

  @OnClick({
      R.id.radioButtonPink, R.id.radioButtonBlue, R.id.radioButtonGreen, R.id.radioButtonYellow,
      R.id.radioButtonGray, R.id.radioButtonPurple, R.id.radioButtonRed
  }) public void onViewClicked(View view) {
    switch (view.getId()) {
      case R.id.radioButtonPink:
        positionTheme = PINK;
        mRadioButtonBlue.setChecked(false);
        mRadioButtonGreen.setChecked(false);
        mRadioButtonYellow.setChecked(false);
        mRadioButtonGray.setChecked(false);
        mRadioButtonPurple.setChecked(false);
        mRadioButtonRed.setChecked(false);
        break;
      case R.id.radioButtonBlue:
        positionTheme = BLUE;
        mRadioButtonPink.setChecked(false);
        mRadioButtonGreen.setChecked(false);
        mRadioButtonYellow.setChecked(false);
        mRadioButtonGray.setChecked(false);
        mRadioButtonPurple.setChecked(false);
        mRadioButtonRed.setChecked(false);
        break;
      case R.id.radioButtonGreen:
        positionTheme = GREEN;
        mRadioButtonPink.setChecked(false);
        mRadioButtonBlue.setChecked(false);
        mRadioButtonYellow.setChecked(false);
        mRadioButtonGray.setChecked(false);
        mRadioButtonPurple.setChecked(false);
        mRadioButtonRed.setChecked(false);
        break;
      case R.id.radioButtonYellow:
        positionTheme = YELLOW;
        mRadioButtonPink.setChecked(false);
        mRadioButtonBlue.setChecked(false);
        mRadioButtonGreen.setChecked(false);
        mRadioButtonGray.setChecked(false);
        mRadioButtonPurple.setChecked(false);
        mRadioButtonRed.setChecked(false);
        break;
      case R.id.radioButtonGray:
        positionTheme = GRAY;
        mRadioButtonPink.setChecked(false);
        mRadioButtonBlue.setChecked(false);
        mRadioButtonGreen.setChecked(false);
        mRadioButtonYellow.setChecked(false);
        mRadioButtonPurple.setChecked(false);
        mRadioButtonRed.setChecked(false);
        break;
      case R.id.radioButtonPurple:
        positionTheme = PURPLE;
        mRadioButtonPink.setChecked(false);
        mRadioButtonBlue.setChecked(false);
        mRadioButtonGreen.setChecked(false);
        mRadioButtonYellow.setChecked(false);
        mRadioButtonGray.setChecked(false);
        mRadioButtonRed.setChecked(false);
        break;
      case R.id.radioButtonRed:
        positionTheme = RED;
        mRadioButtonPink.setChecked(false);
        mRadioButtonBlue.setChecked(false);
        mRadioButtonGreen.setChecked(false);
        mRadioButtonYellow.setChecked(false);
        mRadioButtonGray.setChecked(false);
        mRadioButtonPurple.setChecked(false);
        break;
    }
  }

  private void setThemeApp(int themeApp) {
    mThemeDialogFragmentPresenter.setThemeApp(themeApp);
    mNavigator.startActivityClearStack((AppCompatActivity) getActivity(),
        new Intent(getActivity(), StartActivity.class));
  }

  @Override public void showSetThemeDialog(int position) {
    switch (position) {
      case PINK:
        mRadioButtonPink.setChecked(true);
        positionTheme = PINK;
        break;
      case BLUE:
        mRadioButtonBlue.setChecked(true);
        positionTheme = BLUE;
        break;
      case GREEN:
        mRadioButtonGreen.setChecked(true);
        positionTheme = GREEN;
        break;
      case YELLOW:
        mRadioButtonYellow.setChecked(true);
        positionTheme = YELLOW;
        break;
      case GRAY:
        mRadioButtonGray.setChecked(true);
        positionTheme = GRAY;
        break;
      case PURPLE:
        mRadioButtonPurple.setChecked(true);
        positionTheme = PURPLE;
        break;
      case RED:
        mRadioButtonRed.setChecked(true);
        positionTheme = RED;
        break;
    }
  }

  @OnClick(R.id.textViewOk) public void onViewClickedOk() {
    setThemeApp(positionTheme);
  }

  @OnClick(R.id.textViewCancel) public void onViewClickedCancel() {
    dismiss();
  }
}
