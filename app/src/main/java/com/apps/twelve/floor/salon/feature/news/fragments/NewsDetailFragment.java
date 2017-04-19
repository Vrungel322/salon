package com.apps.twelve.floor.salon.feature.news.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import com.apps.twelve.floor.salon.R;
import com.apps.twelve.floor.salon.base.BaseFragment;
import com.apps.twelve.floor.salon.data.model.NewsEntity;
import com.apps.twelve.floor.salon.feature.news.presenters.DetailNewsFragmentPresenter;
import com.apps.twelve.floor.salon.feature.news.views.INewsDetailFragmentView;
import com.apps.twelve.floor.salon.feature.start_point.activities.StartActivity;
import com.apps.twelve.floor.salon.utils.Constants;
import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.squareup.picasso.Picasso;

/**
 * Created by Vrungel on 23.02.2017.
 */

public class NewsDetailFragment extends BaseFragment implements INewsDetailFragmentView {

  @InjectPresenter DetailNewsFragmentPresenter mDetailNewsFragmentPresenter;

  @BindView(R.id.imageViewPhotoNews) ImageView mImageViewPhotoNews;
  @BindView(R.id.textViewDescriptionNews) TextView mTextViewDescriptionNews;

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

    ((StartActivity) getActivity()).setTitleAppBar(R.string.news);

    NewsEntity newsEntity =
        getArguments().getParcelable(Constants.FragmentsArgumentKeys.NEWS_DETAIL_KEY);

    if (newsEntity != null) {
      Picasso.with(mContext).load(newsEntity.getImageNewsPreviewURL()).into(mImageViewPhotoNews);
      mTextViewDescriptionNews.setText(newsEntity.getNewsShortDescription());
    }
  }

  @Override public void onDestroyView() {
    super.onDestroyView();
    if (mNavigator.isEmptyBackStack(((MvpAppCompatActivity) getActivity()))) {
      ((StartActivity) getActivity()).setTitleAppBar(R.string.title_activity_start);
    }
  }
}
