package com.apps.twelve.floor.salon.feature.catalog.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import com.apps.twelve.floor.salon.R;
import com.apps.twelve.floor.salon.data.model.category.Genre;
import com.apps.twelve.floor.salon.data.model.category.GoodsSubCategoryEntity;
import com.apps.twelve.floor.salon.feature.catalog.adapters.CategoryAdapter;
import com.apps.twelve.floor.salon.feature.catalog.presenters.CategoryDialogFragmentPresenter;
import com.apps.twelve.floor.salon.feature.catalog.views.ICategoryDialogFragmentView;
import com.arellomobile.mvp.MvpDialogFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import java.util.ArrayList;
import timber.log.Timber;

/**
 * Created by Vrungel on 29.05.2017.
 */

public class CategoryDialogFragment extends MvpDialogFragment
    implements ICategoryDialogFragmentView {

  @InjectPresenter CategoryDialogFragmentPresenter mCategoryDialogFragmentPresenter;

  @BindView(R.id.rvCategories) RecyclerView mRecyclerViewCategories;

  private Unbinder mUnbinder;
  private CategoryAdapter mAdapter;

  @Nullable @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    final View fragmentView = inflater.inflate(R.layout.fragment_category_dialog, container, false);
    mUnbinder = ButterKnife.bind(this, fragmentView);
    return fragmentView;
  }

  @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

    RecyclerView.ItemAnimator animator = mRecyclerViewCategories.getItemAnimator();
    if (animator instanceof DefaultItemAnimator) {
      ((DefaultItemAnimator) animator).setSupportsChangeAnimations(false);
    }
    mRecyclerViewCategories.setLayoutManager(new LinearLayoutManager(getActivity()));
  }

  @Override public void fillCategories(ArrayList<Genre> genres) {

    mAdapter = new CategoryAdapter(genres);
    mRecyclerViewCategories.setAdapter(mAdapter);

    mAdapter.setChildClickListener((v, checked, group, childIndex) -> {
      Timber.e(String.valueOf(((GoodsSubCategoryEntity) group.getItems().get(childIndex)).getId()));

      mCategoryDialogFragmentPresenter.postEventToReloadList(
          ((GoodsSubCategoryEntity) group.getItems().get(childIndex)).getId());
      this.dismiss();
    });
  }

  @Override public void onDestroyView() {
    super.onDestroyView();
    mUnbinder.unbind();
  }
}
