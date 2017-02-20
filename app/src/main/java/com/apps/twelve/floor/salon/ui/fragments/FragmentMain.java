package com.apps.twelve.floor.salon.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import com.apps.twelve.floor.salon.R;
import com.apps.twelve.floor.salon.mvp.presenters.FragmentMainPresenter;
import com.apps.twelve.floor.salon.mvp.views.IFragmentMainView;
import com.apps.twelve.floor.salon.ui.base.BaseFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;

/**
 * Created by Vrungel on 20.02.2017.
 */

public class FragmentMain extends BaseFragment implements IFragmentMainView {
  @InjectPresenter FragmentMainPresenter mFragmentMainPresenter;

  @BindView(R.id.tvTest) TextView mTvTest;

  public FragmentMain() {
    super(R.layout.fragment_main);
  }

  @Nullable @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    return super.onCreateView(inflater, container, savedInstanceState);
  }

  @OnClick(R.id.tvTest) public void tvTestClicked() {
    showToastMessage("tvTest");
  }
}
