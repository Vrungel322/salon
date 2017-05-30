package com.apps.twelve.floor.salon.feature.catalog.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import butterknife.BindView;
import butterknife.OnClick;
import com.apps.twelve.floor.salon.R;
import com.apps.twelve.floor.salon.base.BaseFragment;
import com.apps.twelve.floor.salon.data.model.GoodsEntity;
import com.apps.twelve.floor.salon.feature.catalog.adapters.GoodsListAdapter;
import com.apps.twelve.floor.salon.feature.catalog.presenters.CatalogFragmentPresenter;
import com.apps.twelve.floor.salon.feature.catalog.views.ICatalogFragmentView;
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

  private GoodsListAdapter mGoodsListAdapter;

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
    mGoodsListAdapter = new GoodsListAdapter();
    mRecyclerViewStaff.setLayoutManager(new GridLayoutManager(getContext(), 2));
    mRecyclerViewStaff.setAdapter(mGoodsListAdapter);
    ItemClickSupport.addTo(mRecyclerViewStaff)
        .setOnItemClickListener((recyclerView, position, v) -> mNavigator.addFragmentBackStack(
            (StartActivity) getActivity(), R.id.container_main,
            GoodsDetailsFragment.newInstance(mGoodsListAdapter.getEntity(position))));
  }

  @OnClick(R.id.bChooseCategory) public void showCategoryDialog() {
    CategoryDialogFragment categoryDialog = new CategoryDialogFragment();
    categoryDialog.show(getActivity().getFragmentManager(), "");
  }

  @Override public void updateStaffList(List<GoodsEntity> staffEntities) {
    mGoodsListAdapter.addListStaffEntity(staffEntities);
  }

  @Override public void onDestroyView() {
    super.onDestroyView();
    ((StartActivity) getActivity()).setTitleAppBar(R.string.title_activity_start);
  }
}
