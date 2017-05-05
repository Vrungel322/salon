package com.apps.twelve.floor.salon.feature.settings.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
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
    getActivity().onBackPressed();
  }

  @Override public void onDestroy() {
    ((SettingsActivity) getActivity()).setTitleAppBar(R.string.menu_settings);
    super.onDestroy();
  }
}
