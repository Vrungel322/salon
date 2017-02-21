package com.apps.twelve.floor.salon.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;
import butterknife.BindView;
import com.apps.twelve.floor.salon.R;
import com.apps.twelve.floor.salon.mvp.presenters.FragmentMyBookPresenter;
import com.apps.twelve.floor.salon.mvp.views.IFragmentMyBookView;
import com.apps.twelve.floor.salon.ui.base.BaseFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;

/**
 * Created by Vrungel on 21.02.2017.
 */

public class FragmentMyBook extends BaseFragment implements IFragmentMyBookView {
  @InjectPresenter FragmentMyBookPresenter mFragmentMyBookPresenter;

  @BindView(R.id.tvTest) TextView mTvTest;

  public static FragmentMyBook newInstance() {
    Bundle args = new Bundle();
    FragmentMyBook fragment = new FragmentMyBook();
    fragment.setArguments(args);
    return fragment;
  }

  public FragmentMyBook() {
    super(R.layout.fragment_my_book);
  }

  @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

    mTvTest.setOnClickListener(v -> showToastMessage("tvTest"));
  }
}
