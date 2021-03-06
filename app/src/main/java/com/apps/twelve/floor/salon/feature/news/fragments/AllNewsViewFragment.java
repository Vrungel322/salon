package com.apps.twelve.floor.salon.feature.news.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.View;
import android.widget.TextView;
import butterknife.BindView;
import com.apps.twelve.floor.salon.R;
import com.apps.twelve.floor.salon.base.BaseFragment;
import com.apps.twelve.floor.salon.data.model.NewsEntity;
import com.apps.twelve.floor.salon.feature.news.adapters.AllNewsAdapter;
import com.apps.twelve.floor.salon.feature.news.presenters.AllNewsFragmentPresenter;
import com.apps.twelve.floor.salon.feature.news.views.IAllNewsFragmentView;
import com.apps.twelve.floor.salon.feature.start_point.activities.StartActivity;
import com.apps.twelve.floor.salon.utils.ItemClickSupport;
import com.arellomobile.mvp.presenter.InjectPresenter;
import java.util.List;

/**
 * Created by Vrungel on 24.02.2017.
 */

public class AllNewsViewFragment extends BaseFragment implements IAllNewsFragmentView {

  @InjectPresenter AllNewsFragmentPresenter mAllNewsFragmentPresenter;

  @BindView(R.id.rvAllNews) RecyclerView mRecyclerViewAllNews;
  @BindView(R.id.srlRefreshLayout) SwipeRefreshLayout mSwipeRefreshLayout;
  @BindView(R.id.tvNewsEmptyList) TextView mTextViewNewsEmptyList;

  private AllNewsAdapter mAllNewsAdapter;

  public AllNewsViewFragment() {
    super(R.layout.fragment_all_news);
  }

  public static AllNewsViewFragment newInstance() {
    Bundle args = new Bundle();
    AllNewsViewFragment fragment = new AllNewsViewFragment();
    fragment.setArguments(args);
    return fragment;
  }

  @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

    ((StartActivity) getActivity()).setTitleAppBar(R.string.news);

    TypedValue value = new TypedValue();
    getActivity().getTheme().resolveAttribute(R.attr.colorAccent, value, true);
    mSwipeRefreshLayout.setColorSchemeColors(
        ContextCompat.getColor(getContext(), value.resourceId));

    mAllNewsAdapter = new AllNewsAdapter(getContext());
    mRecyclerViewAllNews.setLayoutManager(new LinearLayoutManager(getContext()));
    mRecyclerViewAllNews.setAdapter(mAllNewsAdapter);
    ItemClickSupport.addTo(mRecyclerViewAllNews)
        .setOnItemClickListener((recyclerView, position, v) -> {
          mNavigator.addFragmentBackStack((AppCompatActivity) getActivity(), R.id.container_main,
              NewsDetailFragment.newInstance(mAllNewsAdapter.getItem(position)));
        });
    mSwipeRefreshLayout.setOnRefreshListener(() -> mAllNewsFragmentPresenter.fetchListOfNews());
  }

  @Override public void addListOfNews(List<NewsEntity> newsEntities) {
    mAllNewsAdapter.addListNewsEntity(newsEntities);
    if (!newsEntities.isEmpty()) {
      mTextViewNewsEmptyList.setVisibility(View.GONE);
    } else {
      mTextViewNewsEmptyList.setVisibility(View.VISIBLE);
    }
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
