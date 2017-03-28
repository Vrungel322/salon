package com.apps.twelve.floor.salon.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import com.apps.twelve.floor.salon.R;
import com.apps.twelve.floor.salon.mvp.data.model.NewsEntity;
import com.apps.twelve.floor.salon.mvp.presenters.pr_fragments.DetailNewsFragmentPresenter;
import com.apps.twelve.floor.salon.mvp.views.INewsDetailFragmentView;
import com.apps.twelve.floor.salon.ui.base.BaseFragment;
import com.apps.twelve.floor.salon.utils.Constants;
import com.arellomobile.mvp.presenter.InjectPresenter;
import timber.log.Timber;

/**
 * Created by Vrungel on 23.02.2017.
 */

public class NewsDetailFragment extends BaseFragment implements INewsDetailFragmentView {

  @InjectPresenter DetailNewsFragmentPresenter mDetailNewsFragmentPresenter;
  private NewsEntity mNewsEntity;

  public static NewsDetailFragment newInstance(NewsEntity newsEntity) {
    Bundle args = new Bundle();
    args.putParcelable(Constants.FragmentsArgumentKeys.NEWS_DETAIL_KEY, newsEntity);
    NewsDetailFragment fragment = new NewsDetailFragment();
    fragment.setArguments(args);
    return fragment;
  }

  public NewsDetailFragment() {
    super(R.layout.fragment_detail_news);
  }

  @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    this.mNewsEntity =
        getArguments().getParcelable(Constants.FragmentsArgumentKeys.NEWS_DETAIL_KEY);

    Timber.e("" + mNewsEntity.getNewsData());
  }
}
