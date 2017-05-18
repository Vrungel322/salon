package com.apps.twelve.floor.salon.feature.catalog.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import butterknife.BindView;
import com.apps.twelve.floor.salon.R;
import com.apps.twelve.floor.salon.base.BaseFragment;
import com.apps.twelve.floor.salon.data.model.StaffEntity;
import com.apps.twelve.floor.salon.feature.catalog.adapters.StaffListAdapter;
import com.apps.twelve.floor.salon.feature.catalog.presenters.CatalogFragmentPresenter;
import com.apps.twelve.floor.salon.feature.catalog.views.ICatalogFragmentView;
import com.apps.twelve.floor.salon.feature.our_works.fragments.WorkDetailsFragment;
import com.apps.twelve.floor.salon.feature.start_point.activities.StartActivity;
import com.apps.twelve.floor.salon.utils.ItemClickSupport;
import com.arellomobile.mvp.presenter.InjectPresenter;
import java.util.List;

/**
 * Created by John on 17.05.2017.
 */

public class CatalogFragment extends BaseFragment implements ICatalogFragmentView {

  @InjectPresenter CatalogFragmentPresenter mCatalogFragmentPresenter;

  @BindView(R.id.bChooseCategory) Button mButtonChooseCategory;
  @BindView(R.id.rvStaff) RecyclerView mRecyclerViewStaff;

  private StaffListAdapter mStaffListAdapter;

  public CatalogFragment() {
    super(R.layout.fragment_catalog);
  }

  public static CatalogFragment newInstance() {
    Bundle args = new Bundle();
    CatalogFragment fragment = new CatalogFragment();
    fragment.setArguments(args);
    return fragment;
  }

  @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    ((StartActivity) getActivity()).setTitleAppBar(R.string.catalog);
  }

  @Override public void setUpUi() {
    mStaffListAdapter = new StaffListAdapter();
    mRecyclerViewStaff.setLayoutManager(new GridLayoutManager(getContext(),2));
    mRecyclerViewStaff.setAdapter(mStaffListAdapter);
    ItemClickSupport.addTo(mRecyclerViewStaff).setOnItemClickListener((recyclerView, position, v) -> {
        mNavigator.addFragmentBackStack((StartActivity) getActivity(), R.id.container_main,
            StaffDetailsFragment.newInstance(mStaffListAdapter.getEntity(position)));

    });
  }

  @Override public void updateStaffList(List<StaffEntity> staffEntities) {
    mStaffListAdapter.addListStaffEntity(staffEntities);
  }

  @Override public void onDestroyView() {
    super.onDestroyView();
    ((StartActivity) getActivity()).setTitleAppBar(R.string.title_activity_start);
  }
}
