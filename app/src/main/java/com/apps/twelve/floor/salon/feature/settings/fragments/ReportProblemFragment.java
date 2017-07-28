package com.apps.twelve.floor.salon.feature.settings.fragments;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.util.TypedValue;
import android.view.View;
import android.widget.EditText;
import br.com.simplepass.loading_button_lib.customViews.CircularProgressButton;
import butterknife.BindView;
import butterknife.OnClick;
import com.apps.twelve.floor.salon.R;
import com.apps.twelve.floor.salon.base.BaseFragment;
import com.apps.twelve.floor.salon.feature.settings.activities.SettingsActivity;
import com.apps.twelve.floor.salon.feature.settings.presenters.ReportProblemFragmentPresenter;
import com.apps.twelve.floor.salon.feature.settings.views.IReportProblemFragmentView;
import com.apps.twelve.floor.salon.utils.ViewUtil;
import com.arellomobile.mvp.presenter.InjectPresenter;

/**
 * Created by Alexandra on 05.05.2017.
 */

public class ReportProblemFragment extends BaseFragment implements IReportProblemFragmentView {

  @InjectPresenter ReportProblemFragmentPresenter mReportProblemFragmentPresenter;

  @BindView(R.id.btnSendProblem) CircularProgressButton mBtnSend;
  @BindView(R.id.etProblem) EditText mTextProblem;

  public ReportProblemFragment() {
    super(R.layout.fragment_report_problem);
  }

  public static ReportProblemFragment newInstance() {
    Bundle args = new Bundle();
    ReportProblemFragment fragment = new ReportProblemFragment();
    fragment.setArguments(args);
    return fragment;
  }

  @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

    ((SettingsActivity) getActivity()).setTitleAppBar(R.string.settings_problem);
  }

  @OnClick(R.id.btnSendProblem) void sendProblem() {
    if (mTextProblem.getText().toString().length()<=3) {
      showAlertMessage(getString(R.string.dialog_error_title),
          getString(R.string.empty_complaint_error));
    } else {
      mBtnSend.startAnimation();
      mReportProblemFragmentPresenter.sendProblem(mTextProblem.getText().toString());
    }
  }

  @Override public void onDestroy() {
    ((SettingsActivity) getActivity()).setTitleAppBar(R.string.menu_settings);
    super.onDestroy();
  }

  @Override public void closeFragment() {
    ViewUtil.hideKeyboard(getActivity());
    getActivity().onBackPressed();
  }

  @Override public void showAlert() {
    showAlertMessage("Error", "Warning");
  }

  @Override public void stopAnimation() {
    TypedValue value = new TypedValue();
    getActivity().getTheme().resolveAttribute(R.attr.colorAccent, value, true);
    mBtnSend.doneLoadingAnimation(ContextCompat.getColor(getContext(), value.resourceId),
        BitmapFactory.decodeResource(getResources(), R.drawable.ic_done_white_48dp));
  }

  @Override public void revertAnimation() {
    mBtnSend.revertAnimation();
  }

  @Override public void openSnackBarOK() {
    Snackbar.make(mTextProblem, getString(R.string.sent_message), Snackbar.LENGTH_SHORT).show();
  }
}
