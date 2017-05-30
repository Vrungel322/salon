package com.apps.twelve.floor.salon.feature.catalog.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
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
  @BindView(R.id.srlRefreshLayout) SwipeRefreshLayout mSwipeRefreshLayout;

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

    mGoodsListAdapter = new GoodsListAdapter();
    mRecyclerViewStaff.setLayoutManager(new GridLayoutManager(getContext(), 2));
    mRecyclerViewStaff.setAdapter(mGoodsListAdapter);
    ItemClickSupport.addTo(mRecyclerViewStaff)
        .setOnItemClickListener((recyclerView, position, v) -> mNavigator.addFragmentBackStack(
            (StartActivity) getActivity(), R.id.container_main,
            GoodsDetailsFragment.newInstance(mGoodsListAdapter.getEntity(position))));

    TypedValue value = new TypedValue();
    getActivity().getTheme().resolveAttribute(R.attr.colorAccent, value, true);
    mSwipeRefreshLayout.setColorSchemeResources(value.resourceId);
    mSwipeRefreshLayout.setOnRefreshListener(() -> mCatalogFragmentPresenter.fetchGoodsList());
  }

  @OnClick(R.id.bChooseCategory) public void showCategoryDialog() {
    CategoryDialogFragment categoryDialog = new CategoryDialogFragment();
    categoryDialog.show(getActivity().getFragmentManager(), "");
  }

  @Override public void updateGoodsList(List<GoodsEntity> staffEntities) {
    mGoodsListAdapter.addListGoodsEntity(staffEntities);
  }

  @Override public void startRefreshingView() {
    if (!mSwipeRefreshLayout.isRefreshing()) mSwipeRefreshLayout.setRefreshing(true);
  }

  @Override public void stopRefreshingView() {
    if (mSwipeRefreshLayout.isRefreshing()) mSwipeRefreshLayout.setRefreshing(false);
  }

  @Override public void onDestroyView() {
    super.onDestroyView();
    ((StartActivity) getActivity()).setTitleAppBar(R.string.title_activity_start);
  }
}
