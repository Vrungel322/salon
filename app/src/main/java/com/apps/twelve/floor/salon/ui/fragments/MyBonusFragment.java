package com.apps.twelve.floor.salon.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;
import butterknife.BindView;
import com.apps.twelve.floor.salon.R;
import com.apps.twelve.floor.salon.mvp.presenters.fragments.MyBonusPresenter;
import com.apps.twelve.floor.salon.mvp.views.IMyBonusFragmentView;
import com.apps.twelve.floor.salon.ui.base.BaseFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;

/**
 * Created by Vrungel on 21.02.2017.
 */

public class MyBonusFragment extends BaseFragment implements IMyBonusFragmentView {
  @InjectPresenter MyBonusPresenter mMyBonusFragmentPresenter;

  @BindView(R.id.tvTest) TextView mTvTest;

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

    mTvTest.setOnClickListener(v -> showToastMessage("tvTest"));
  }
}