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
        setThemeApp(PINK);
        break;
      case R.id.radioButtonBlue:
        setThemeApp(BLUE);
        break;
      case R.id.radioButtonGreen:
        setThemeApp(GREEN);
        break;
      case R.id.radioButtonYellow:
        setThemeApp(YELLOW);
        break;
      case R.id.radioButtonGray:
        setThemeApp(GRAY);
        break;
      case R.id.radioButtonPurple:
        setThemeApp(PURPLE);
        break;
      case R.id.radioButtonRed:
        setThemeApp(RED);
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
        break;
      case BLUE:
        mRadioButtonBlue.setChecked(true);
        break;
      case GREEN:
        mRadioButtonGreen.setChecked(true);
        break;
      case YELLOW:
        mRadioButtonYellow.setChecked(true);
        break;
      case GRAY:
        mRadioButtonGray.setChecked(true);
        break;
      case PURPLE:
        mRadioButtonPurple.setChecked(true);
        break;
      case RED:
        mRadioButtonRed.setChecked(true);
        break;
    }
  }
}
