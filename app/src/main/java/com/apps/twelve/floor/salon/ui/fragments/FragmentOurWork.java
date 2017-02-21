package com.apps.twelve.floor.salon.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;
import butterknife.BindView;
import com.apps.twelve.floor.salon.R;
import com.apps.twelve.floor.salon.mvp.presenters.FragmentMyBookPresenter;
import com.apps.twelve.floor.salon.mvp.presenters.FragmentOurWorkPresenter;
import com.apps.twelve.floor.salon.mvp.views.IFragmentOurWorkView;
import com.apps.twelve.floor.salon.ui.base.BaseFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;

/**
 * Created by Vrungel on 21.02.2017.
 */

public class FragmentOurWork extends BaseFragment implements IFragmentOurWorkView {
  @InjectPresenter FragmentOurWorkPresenter mFragmentOurWorkPresenter;

  @BindView(R.id.tvTest) TextView mTvTest;

  public static FragmentOurWork newInstance() {
    Bundle args = new Bundle();
    FragmentOurWork fragment = new FragmentOurWork();
    fragment.setArguments(args);
    return fragment;
  }

  public FragmentOurWork() {
    super(R.layout.fragment_our_work);
  }

  @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

    mTvTest.setOnClickListener(v -> showToastMessage("tvTest"));
  }
}
