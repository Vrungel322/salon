package com.apps.twelve.floor.salon.feature.catalog.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import butterknife.BindView;
import com.apps.twelve.floor.salon.R;
import com.apps.twelve.floor.salon.base.BaseFragment;
import com.apps.twelve.floor.salon.data.model.GoodsEntity;
import com.apps.twelve.floor.salon.feature.catalog.adapters.GoodsFavoriteListAdapter;
import com.apps.twelve.floor.salon.feature.catalog.presenters.CatalogFavoriteFragmentPresenter;
import com.apps.twelve.floor.salon.feature.catalog.views.ICataloFavoriteFragmentView;
import com.apps.twelve.floor.salon.feature.start_point.activities.StartActivity;
import com.apps.twelve.floor.salon.utils.ItemClickSupport;
import com.apps.twelve.floor.salon.utils.ThemeUtils;
import com.arellomobile.mvp.presenter.InjectPresenter;
import java.util.List;

/**
 * Created by John on 06.06.2017.
 */

public class CatalogFavoriteFragment extends BaseFragment implements ICataloFavoriteFragmentView {

  @InjectPresenter CatalogFavoriteFragmentPresenter mCatalogFavoriteFragmentPresenter;

  @BindView(R.id.progressBar2) ProgressBar mProgressBar;
  @BindView(R.id.rvCatalogFavorite) RecyclerView mRvCatalogFavorite;

  private GoodsFavoriteListAdapter mGoodsFavoriteListAdapter;

  public CatalogFavoriteFragment() {
    super(R.layout.fragment_catalog_favorite);
  }

  public static CatalogFavoriteFragment newInstance() {
    Bundle args = new Bundle();
    CatalogFavoriteFragment fragment = new CatalogFavoriteFragment();
    fragment.setArguments(args);
    return fragment;
  }

  @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

    mGoodsFavoriteListAdapter = new GoodsFavoriteListAdapter();
    mRvCatalogFavorite.setLayoutManager(new GridLayoutManager(getContext(), 2));
    mRvCatalogFavorite.setAdapter(mGoodsFavoriteListAdapter);
    ItemClickSupport.addTo(mRvCatalogFavorite)
        .setOnItemClickListener((recyclerView, position, v) -> mNavigator.addFragmentBackStack(
            (StartActivity) getActivity(), R.id.container_main,
            GoodsDetailsFragment.newInstance(mGoodsFavoriteListAdapter.getEntity(position))));
  }

  @Override public void stopProgressBar() {
    mProgressBar.setVisibility(View.GONE);
  }

  @Override public void updateGoodsFavoriteList(List<GoodsEntity> goodsEntities) {
    mGoodsFavoriteListAdapter.addListGoodsEntity(goodsEntities);
  }

  @Override public void startLoginActivity() {
    mAuthorizationManager.startSignInActivity((AppCompatActivity) getActivity(),
        ThemeUtils.getThemeActionBar(getContext()));
  }
}
