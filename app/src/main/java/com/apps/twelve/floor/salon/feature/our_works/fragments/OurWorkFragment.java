package com.apps.twelve.floor.salon.feature.our_works.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import butterknife.BindView;
import com.apps.twelve.floor.salon.R;
import com.apps.twelve.floor.salon.base.BaseFragment;
import com.apps.twelve.floor.salon.data.model.OurWorkEntity;
import com.apps.twelve.floor.salon.feature.our_works.adapters.OurWorkAdapter;
import com.apps.twelve.floor.salon.feature.our_works.presenters.OurWorkFragmentPresenter;
import com.apps.twelve.floor.salon.feature.our_works.views.IOurWorkFragmentView;
import com.apps.twelve.floor.salon.feature.start_point.activities.StartActivity;
import com.apps.twelve.floor.salon.utils.ItemClickSupport;
import com.arellomobile.mvp.presenter.InjectPresenter;
import java.util.List;

/**
 * Created by Vrungel on 21.02.2017.
 */

public class OurWorkFragment extends BaseFragment implements IOurWorkFragmentView {

  @InjectPresenter OurWorkFragmentPresenter mOurWorkFragmentPresenter;

  @BindView(R.id.rvOurWorks) RecyclerView mRecyclerViewOurWorks;
  @BindView(R.id.textViewOurWorkIsEmpty) TextView mTextViewOurWorkIsEmpty;

  private OurWorkAdapter mOurWorkAdapter;

  public static OurWorkFragment newInstance() {
    Bundle args = new Bundle();
    OurWorkFragment fragment = new OurWorkFragment();
    fragment.setArguments(args);
    return fragment;
  }

  public OurWorkFragment() {
    super(R.layout.fragment_our_work);
  }

  @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

    ((StartActivity) getActivity()).setTitleAppBar(R.string.menu_our_work);

    mOurWorkAdapter = new OurWorkAdapter();
    mRecyclerViewOurWorks.setAdapter(mOurWorkAdapter);
    mRecyclerViewOurWorks.setLayoutManager(new GridLayoutManager(getContext(), 2));

    ItemClickSupport.addTo(mRecyclerViewOurWorks)
        .setOnItemClickListener((recyclerView, position, v) -> mNavigator.addFragmentBackStack(
            (StartActivity) getActivity(), R.id.container_main,
            WorkDetailsFragment.newInstance(mOurWorkAdapter.getEntity(position))));
    mOurWorkFragmentPresenter.fetchListOfWorks();
  }

  @Override public void addListOfWorks(List<OurWorkEntity> ourWorkEntities) {
    if (!ourWorkEntities.isEmpty()) {
      mTextViewOurWorkIsEmpty.setVisibility(View.GONE);
      mOurWorkAdapter.addListWorkEntities(ourWorkEntities);
    } else {
      mTextViewOurWorkIsEmpty.setVisibility(View.VISIBLE);
    }
  }
}