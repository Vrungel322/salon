package com.apps.twelve.floor.salon.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;
import butterknife.BindView;
import com.apps.twelve.floor.salon.R;
import com.apps.twelve.floor.salon.mvp.data.model.OurWorkEntity;
import com.apps.twelve.floor.salon.mvp.presenters.FragmentWorkDetailsPresenter;
import com.apps.twelve.floor.salon.mvp.views.IFragmentWorkDetailsView;
import com.apps.twelve.floor.salon.ui.activities.StartActivity;
import com.apps.twelve.floor.salon.ui.base.BaseFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;

/**
 * Created by Alexandra on 23.02.2017.
 */

public class FragmentWorkDetails extends BaseFragment implements IFragmentWorkDetailsView {

  @InjectPresenter FragmentWorkDetailsPresenter mFragmentWorkDetailsPresenter;

  @BindView(R.id.tvTest) TextView mTvTest;

  private OurWorkEntity mEntity;

  public static FragmentWorkDetails newInstance() {
    Bundle args = new Bundle();
    FragmentWorkDetails fragment = new FragmentWorkDetails();
    fragment.setArguments(args);
    return fragment;
  }

  public FragmentWorkDetails() {
    super(R.layout.fragment_work_details);
  }

  @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    mTvTest.setText(mEntity.getShortDescription());
    mTvTest.setOnClickListener(
        v -> mNavigator.replaceFragment((StartActivity) getActivity(), R.id.container_main,
            FragmentOurWork.newInstance()));
  }

  public void setEntity(OurWorkEntity entity) {
    this.mEntity = entity;
  }
}
