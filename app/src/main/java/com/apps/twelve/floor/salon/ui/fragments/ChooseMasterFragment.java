package com.apps.twelve.floor.salon.ui.fragments;

import android.os.Bundle;
import com.apps.twelve.floor.salon.R;
import com.apps.twelve.floor.salon.mvp.presenters.fragments.ChooseMasterFragmentPresenter;
import com.apps.twelve.floor.salon.mvp.views.IChooseMasterFragmentView;
import com.apps.twelve.floor.salon.ui.base.BaseFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;

/**
 * Created by Vrungel on 28.03.2017.
 */

public class ChooseMasterFragment extends BaseFragment implements IChooseMasterFragmentView {
  @InjectPresenter ChooseMasterFragmentPresenter mChooseMasterFragmentPresenter;

  public static ChooseMasterFragment newInstance() {
    Bundle args = new Bundle();
    ChooseMasterFragment fragment = new ChooseMasterFragment();
    fragment.setArguments(args);
    return fragment;
  }

  public ChooseMasterFragment() {
    super(R.layout.fragment_choose_master);
  }
}
