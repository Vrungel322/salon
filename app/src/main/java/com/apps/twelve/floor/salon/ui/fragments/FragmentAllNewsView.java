package com.apps.twelve.floor.salon.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import butterknife.BindView;
import com.apps.twelve.floor.salon.R;
import com.apps.twelve.floor.salon.mvp.data.model.NewsEntity;
import com.apps.twelve.floor.salon.mvp.presenters.FragmentAllNewsPresenter;
import com.apps.twelve.floor.salon.mvp.views.IFragmentAllNewsView;
import com.apps.twelve.floor.salon.ui.adapters.AllNewsAdapter;
import com.apps.twelve.floor.salon.ui.base.BaseFragment;
import com.apps.twelve.floor.salon.utils.ItemClickSupport;
import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import java.util.List;

/**
 * Created by Vrungel on 24.02.2017.
 */

public class FragmentAllNewsView extends BaseFragment implements IFragmentAllNewsView {
  @InjectPresenter FragmentAllNewsPresenter mFragmentAllNewsPresenter;

  @BindView(R.id.rvAllNews) RecyclerView mRecyclerViewAllNews;

  private AllNewsAdapter mAllNewsAdapter;

  public static FragmentAllNewsView newInstance() {
    Bundle args = new Bundle();
    FragmentAllNewsView fragment = new FragmentAllNewsView();
    fragment.setArguments(args);
    return fragment;
  }

  public FragmentAllNewsView() {
    super(R.layout.fragment_all_news);
  }

  @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

    if (((MvpAppCompatActivity) getActivity()).getSupportActionBar() != null) {
      ((MvpAppCompatActivity) getActivity()).getSupportActionBar().setTitle(R.string.news);
    }

    mAllNewsAdapter = new AllNewsAdapter();
    mRecyclerViewAllNews.setAdapter(mAllNewsAdapter);
    mRecyclerViewAllNews.setLayoutManager(new LinearLayoutManager(getContext()));
    ItemClickSupport.addTo(mRecyclerViewAllNews)
        .setOnItemClickListener((recyclerView, position, v) -> {
          showToastMessage("" + position);
          // TODO: 24.02.2017 create FragmentDetailNews
        });
  }

  @Override public void addListOfNews(List<NewsEntity> newsEntities) {
    mAllNewsAdapter.addListNewsEntity(newsEntities);
  }
}
