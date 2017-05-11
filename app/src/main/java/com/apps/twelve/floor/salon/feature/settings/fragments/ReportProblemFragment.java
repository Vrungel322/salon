package com.apps.twelve.floor.salon.feature.settings.fragments;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.View;
import br.com.simplepass.loading_button_lib.customViews.CircularProgressButton;
import butterknife.BindView;
import butterknife.OnClick;
import com.apps.twelve.floor.salon.R;
import com.apps.twelve.floor.salon.base.BaseFragment;
import com.apps.twelve.floor.salon.feature.settings.activities.SettingsActivity;
import com.apps.twelve.floor.salon.feature.settings.presenters.ReportProblemFragmentPresenter;
import com.apps.twelve.floor.salon.feature.settings.views.IReportProblemFragmentView;
import com.arellomobile.mvp.presenter.InjectPresenter;

/**
 * Created by Alexandra on 05.05.2017.
 */

public class ReportProblemFragment extends BaseFragment implements IReportProblemFragmentView {

  @InjectPresenter ReportProblemFragmentPresenter mReportProblemFragmentPresenter;

  @BindView(R.id.btnSendProblem) CircularProgressButton mBtnSend;

  public static ReportProblemFragment newInstance() {
    Bundle args = new Bundle();
    ReportProblemFragment fragment = new ReportProblemFragment();
    fragment.setArguments(args);
    return fragment;
  }

  public ReportProblemFragment() {
    super(R.layout.fragment_report_problem);
  }

  @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

    ((SettingsActivity) getActivity()).setTitleAppBar(R.string.settings_problem);
  }

  @OnClick(R.id.btnSendProblem) void sendAndClose() {
    mBtnSend.startAnimation();
    mBtnSend.doneLoadingAnimation(
        ContextCompat.getColor(getContext(), R.color.colorSettingsSaveButton),
        BitmapFactory.decodeResource(getResources(), R.drawable.ic_done_white_48dp));
    Handler handler = new Handler();
    handler.postDelayed(this::closeFragment, 1000);
  }

  public void closeFragment() {
    ((SettingsActivity) getActivity()).setUpUserInfo();
    getActivity().onBackPressed();
  }

  @Override public void onDestroy() {
    ((SettingsActivity) getActivity()).setTitleAppBar(R.string.menu_settings);
    super.onDestroy();
  }
}
