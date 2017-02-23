package com.apps.twelve.floor.salon.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import butterknife.BindView;
import com.apps.twelve.floor.salon.R;
import com.apps.twelve.floor.salon.mvp.data.model.OurWorkEntity;
import com.apps.twelve.floor.salon.mvp.presenters.FragmentOurWorkPresenter;
import com.apps.twelve.floor.salon.mvp.views.IFragmentOurWorkView;
import com.apps.twelve.floor.salon.ui.adapters.OurWorkAdapter;
import com.apps.twelve.floor.salon.ui.base.BaseFragment;
import com.apps.twelve.floor.salon.utils.ItemClickSupport;
import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import java.util.List;

/**
 * Created by Vrungel on 21.02.2017.
 */

public class FragmentOurWork extends BaseFragment implements IFragmentOurWorkView {

  @InjectPresenter FragmentOurWorkPresenter mFragmentOurWorkPresenter;

  @BindView(R.id.rvOurWorks) RecyclerView mRecyclerViewOurWorks;

  private OurWorkAdapter mOurWorkAdapter;

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

    if (((MvpAppCompatActivity) getActivity()).getSupportActionBar() != null) {
      ((MvpAppCompatActivity) getActivity()).getSupportActionBar().setTitle(R.string.menu_our_work);
    }

    mOurWorkAdapter = new OurWorkAdapter();
    mRecyclerViewOurWorks.setAdapter(mOurWorkAdapter);
    mRecyclerViewOurWorks.setLayoutManager(new GridLayoutManager(getContext(), 2));

    ItemClickSupport.addTo(mRecyclerViewOurWorks)
        .setOnItemClickListener((recyclerView, position, v) -> showToastMessage("" + position));
    mFragmentOurWorkPresenter.fetchListOfWorks();
  }

  @Override public void addListOfWorks(List<OurWorkEntity> ourWorkEntities) {
    mOurWorkAdapter.addListWorkEntities(ourWorkEntities);
  }
}
