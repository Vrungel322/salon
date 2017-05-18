package com.apps.twelve.floor.salon.feature.my_bonus.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;
import butterknife.BindView;
import com.apps.twelve.floor.salon.R;
import com.apps.twelve.floor.salon.base.BaseFragment;
import com.apps.twelve.floor.salon.feature.booking.activities.BookingActivity;
import com.apps.twelve.floor.salon.feature.my_bonus.presenters.MyBonusFragmentPresenter;
import com.apps.twelve.floor.salon.feature.my_bonus.views.IMyBonusFragmentView;
import com.apps.twelve.floor.salon.feature.start_point.activities.StartActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;

/**
 * Created by Vrungel on 21.02.2017.
 */

public class MyBonusFragment extends BaseFragment implements IMyBonusFragmentView {

  @InjectPresenter MyBonusFragmentPresenter mMyBonusFragmentPresenter;

  @BindView(R.id.tvCountBonus) TextView mTvCountBonus;

  public static MyBonusFragment newInstance() {
    Bundle args = new Bundle();
    MyBonusFragment fragment = new MyBonusFragment();
    fragment.setArguments(args);
    return fragment;
  }

  public MyBonusFragment() {
    super(R.layout.fragment_my_bonus);
  }

  @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

    if (getActivity() instanceof StartActivity) {
      ((StartActivity) getActivity()).setTitleAppBar(R.string.bonus);
    }

    if (getActivity() instanceof BookingActivity) {
      ((BookingActivity) getActivity()).setTitleAppBar(R.string.bonus);
    }
  }

  @Override public void onDestroyView() {
    super.onDestroyView();
    if (getActivity() instanceof StartActivity) {
      ((StartActivity) getActivity()).setTitleAppBar(R.string.title_activity_start);
    }

    if (getActivity() instanceof BookingActivity) {
      ((BookingActivity) getActivity()).setTitleAppBar(R.string.title_activity_start);
    }
  }

  @Override public void setBonusCount(Integer count) {
    mTvCountBonus.setText(String.valueOf(count));
  }
}