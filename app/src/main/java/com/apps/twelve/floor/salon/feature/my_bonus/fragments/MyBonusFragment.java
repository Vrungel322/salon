package com.apps.twelve.floor.salon.feature.my_bonus.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
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
  @BindView(R.id.btnHowBonusWorks) Button mButtonHowBonusWorks;
  @BindView(R.id.btnSendCode) Button mButtonSendCode;
  @BindView(R.id.tvYour) TextView mTextViewYourCode;

  public MyBonusFragment() {
    super(R.layout.fragment_my_bonus);
  }

  public static MyBonusFragment newInstance() {
    Bundle args = new Bundle();
    MyBonusFragment fragment = new MyBonusFragment();
    fragment.setArguments(args);
    return fragment;
  }

  @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

    mButtonHowBonusWorks.setOnClickListener(c -> {
      if (getActivity() instanceof StartActivity) {
        mNavigator.addFragmentBackStack((StartActivity) getActivity(), R.id.container_main,
            BonusHowFragment.newInstance());
      } else {
        mNavigator.addFragmentBackStack((BookingActivity) getActivity(), R.id.container_booking,
            BonusHowFragment.newInstance());
      }
    });

    mButtonSendCode.setOnClickListener(c -> showToastMessage("Send"));

    mTextViewYourCode.setText("007");
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

  @OnClick(R.id.tvHistory) void openHistory() {
    if (getActivity() instanceof StartActivity) {
      mNavigator.addFragmentBackStack((StartActivity) getActivity(), R.id.container_main,
          BonusHistoryFragment.newInstance());
    } else {
      mNavigator.addFragmentBackStack((BookingActivity) getActivity(), R.id.container_booking,
          BonusHistoryFragment.newInstance());
    }
  }
}