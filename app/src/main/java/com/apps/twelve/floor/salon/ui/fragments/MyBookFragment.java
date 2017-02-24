package com.apps.twelve.floor.salon.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;
import butterknife.BindView;
import com.apps.twelve.floor.salon.R;
import com.apps.twelve.floor.salon.mvp.presenters.fragments.MyBookFragmentPresenter;
import com.apps.twelve.floor.salon.mvp.views.IMyBookFragmentView;
import com.apps.twelve.floor.salon.ui.base.BaseFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;

/**
 * Created by Vrungel on 21.02.2017.
 */

public class MyBookFragment extends BaseFragment implements IMyBookFragmentView {
  @InjectPresenter MyBookFragmentPresenter mMyBookFragmentPresenter;

  @BindView(R.id.tvTest) TextView mTvTest;

  public static MyBookFragment newInstance() {
    Bundle args = new Bundle();
    MyBookFragment fragment = new MyBookFragment();
    fragment.setArguments(args);
    return fragment;
  }

  public MyBookFragment() {
    super(R.layout.fragment_my_book);
  }

  @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

    mTvTest.setOnClickListener(v -> showToastMessage("tvTest"));
  }
}
