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
import com.apps.twelve.floor.salon.ui.base.BaseFragment;
import com.apps.twelve.floor.salon.utils.Constants;
import com.arellomobile.mvp.presenter.InjectPresenter;

/**
 * Created by Alexandra on 23.02.2017.
 */

public class FragmentWorkDetails extends BaseFragment implements IFragmentWorkDetailsView {

  @InjectPresenter FragmentWorkDetailsPresenter mFragmentWorkDetailsPresenter;

  @BindView(R.id.tvTest) TextView mTvTest;

  private OurWorkEntity mEntity;

  public static FragmentWorkDetails newInstance(OurWorkEntity entity) {
    Bundle args = new Bundle();
    args.putParcelable(Constants.FragmentsArgumentKeys.OUR_ENTITY_KEY, entity);
    FragmentWorkDetails fragment = new FragmentWorkDetails();
    fragment.setArguments(args);
    return fragment;
  }

  public FragmentWorkDetails() {
    super(R.layout.fragment_work_details);
  }

  @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    mEntity = getArguments().getParcelable(Constants.FragmentsArgumentKeys.OUR_ENTITY_KEY);
    mTvTest.setText(mEntity.getShortDescription());
  }

}
