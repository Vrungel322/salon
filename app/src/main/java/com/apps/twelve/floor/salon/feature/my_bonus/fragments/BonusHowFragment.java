package com.apps.twelve.floor.salon.feature.my_bonus.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import com.apps.twelve.floor.salon.R;
import com.apps.twelve.floor.salon.base.BaseFragment;
import com.apps.twelve.floor.salon.feature.booking.activities.BookingActivity;
import com.apps.twelve.floor.salon.feature.my_bonus.presenters.BonusHowFragmentPresenter;
import com.apps.twelve.floor.salon.feature.my_bonus.views.IBonusHowFragmentView;
import com.apps.twelve.floor.salon.feature.start_point.activities.StartActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;

/**
 * Created by Alexandra on 29.05.2017.
 */

public class BonusHowFragment extends BaseFragment implements IBonusHowFragmentView {

  @InjectPresenter BonusHowFragmentPresenter mBonusHowFragmentPresenter;

  public BonusHowFragment() {
    super(R.layout.fragment_bonus_how_works);
  }

  public static BonusHowFragment newInstance() {
    Bundle args = new Bundle();
    BonusHowFragment fragment = new BonusHowFragment();
    fragment.setArguments(args);
    return fragment;
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
    if (getActivity() instanceof StartActivity) {
      ((StartActivity) getActivity()).setTitleAppBar(R.string.title_activity_start);
    }
    if (getActivity() instanceof BookingActivity) {
      ((BookingActivity) getActivity()).setTitleAppBar(R.string.book_create);
    }
    super.onDestroyView();
  }
}
