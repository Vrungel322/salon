package com.apps.twelve.floor.salon.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;
import butterknife.BindView;
import com.apps.twelve.floor.salon.R;
import com.apps.twelve.floor.salon.mvp.data.model.OurWorkEntity;
import com.apps.twelve.floor.salon.mvp.presenters.fragments.WorkDetailsPresenter;
import com.apps.twelve.floor.salon.mvp.views.IWorkDetailsFragmentView;
import com.apps.twelve.floor.salon.ui.base.BaseFragment;
import com.apps.twelve.floor.salon.utils.Constants;
import com.arellomobile.mvp.presenter.InjectPresenter;

/**
 * Created by Alexandra on 23.02.2017.
 */

public class WorkDetailsFragment extends BaseFragment implements IWorkDetailsFragmentView {

  @InjectPresenter WorkDetailsPresenter mWorkDetailsFragmentPresenter;

  @BindView(R.id.tvTest) TextView mTvTest;

  private OurWorkEntity mEntity;

  public static WorkDetailsFragment newInstance(OurWorkEntity entity) {
    Bundle args = new Bundle();
    args.putSerializable(Constants.FragmentsArgumentKeys.OUR_ENTITY_KEY, entity);
    WorkDetailsFragment fragment = new WorkDetailsFragment();
    fragment.setArguments(args);
    return fragment;
  }

  public WorkDetailsFragment() {
    super(R.layout.fragment_work_details);
  }

  @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    mEntity = (OurWorkEntity) getArguments().getSerializable(
        Constants.FragmentsArgumentKeys.OUR_ENTITY_KEY);
    mTvTest.setText(mEntity.getShortDescription());
  }
}
