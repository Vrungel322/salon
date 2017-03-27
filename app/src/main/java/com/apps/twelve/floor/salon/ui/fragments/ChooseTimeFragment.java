package com.apps.twelve.floor.salon.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;
import butterknife.BindView;
import com.apps.twelve.floor.salon.R;
import com.apps.twelve.floor.salon.mvp.presenters.fragments.ChooseTimeFragmentPresenter;
import com.apps.twelve.floor.salon.mvp.views.IChooseTimeFragmentView;
import com.apps.twelve.floor.salon.ui.base.BaseFragment;
import com.apps.twelve.floor.salon.utils.Constants;
import com.arellomobile.mvp.presenter.InjectPresenter;

/**
 * Created by Vrungel on 27.03.2017.
 */

public class ChooseTimeFragment extends BaseFragment implements IChooseTimeFragmentView {
  @InjectPresenter ChooseTimeFragmentPresenter mChooseTimeFragmentPresenter;
  @BindView(R.id.tvServiceName) TextView mTextViewServiceName;
  private String serviceName;

  public static ChooseTimeFragment newInstance() {
    Bundle args = new Bundle();
    args.putString(Constants.FragmentsArgumentKeys.SERVICE_NAME, "ТЕСТОВАЯ УСЛУГА");
    ChooseTimeFragment fragment = new ChooseTimeFragment();
    fragment.setArguments(args);
    return fragment;
  }

  public ChooseTimeFragment() {
    super(R.layout.fragment_choose_time);
  }

  @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    serviceName = getArguments().getString(Constants.FragmentsArgumentKeys.SERVICE_NAME);
    mTextViewServiceName.setText(serviceName);
  }
}
