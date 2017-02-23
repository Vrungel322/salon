package com.apps.twelve.floor.salon.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
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

  public static FragmentMain newInstance() {
    Bundle args = new Bundle();
    FragmentMain fragment = new FragmentMain();
    fragment.setArguments(args);
    return fragment;
  }

  public FragmentMain() {
    super(R.layout.fragment_main);
  }

  @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    mNavigator.replaceChildFragment(this, R.id.subFragmentContainerNews, SubFragmentNews.newInstance());
  }
}
