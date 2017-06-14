package com.apps.twelve.floor.salon.feature.main_screen.fragments;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import com.apps.twelve.floor.salon.R;
import com.apps.twelve.floor.salon.base.BaseFragment;
import com.apps.twelve.floor.salon.data.model.NewsEntity;
import com.apps.twelve.floor.salon.feature.main_screen.presenters.SubNewsFragmentPresenter;
import com.apps.twelve.floor.salon.feature.main_screen.views.ISubNewsFragmentView;
import com.apps.twelve.floor.salon.feature.news.fragments.AllNewsViewFragment;
import com.apps.twelve.floor.salon.feature.news.fragments.NewsDetailFragment;
import com.apps.twelve.floor.salon.utils.Constants;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.bumptech.glide.Glide;

import static com.apps.twelve.floor.salon.utils.Converters.dateFromSeconds;

/**
 * Created by Vrungel on 23.02.2017.
 */

public class SubNewsFragment extends BaseFragment implements ISubNewsFragmentView {

  @InjectPresenter SubNewsFragmentPresenter mSubNewsFragmentPresenter;

  @BindView(R.id.ivNewsPreview) ImageView mImageViewNewsPreview;
  @BindView(R.id.tvNewsShortDescription) TextView mTextViewNewsShortDescription;
  @BindView(R.id.tvNewsData) TextView mTextViewNewsData;

  private NewsEntity mNewsEntity;

  public SubNewsFragment() {
    super(R.layout.fragment_sub_news);
  }

  public static SubNewsFragment newInstance() {
    Bundle args = new Bundle();
    SubNewsFragment fragment = new SubNewsFragment();
    fragment.setArguments(args);
    return fragment;
  }

  @Override public void updateNewsPreview(NewsEntity newsEntity) {
    mNewsEntity = newsEntity;
    Glide.with(getContext())
        .load(newsEntity.getImg())
        .placeholder(R.drawable.ic_news_placeholder_130dp).dontAnimate()
        .into(mImageViewNewsPreview);
    mTextViewNewsShortDescription.setText(newsEntity.getTitle());
    mTextViewNewsData.setText(dateFromSeconds(newsEntity.getCreatedAt()));
  }

  @OnClick(R.id.tvAllNews) public void tvAllNewsClicked() {
    mNavigator.addFragmentTagClearBackStackNotCopy((AppCompatActivity) getActivity(),
        R.id.container_main, AllNewsViewFragment.newInstance(),
        Constants.FragmentTag.ALL_NEWS_FRAGMENT);
  }

  @OnClick(R.id.layoutAllNews) public void setLayoutAllNewsClicked() {
    mNavigator.addFragmentBackStack((AppCompatActivity) getActivity(), R.id.container_main,
        NewsDetailFragment.newInstance(mNewsEntity));
  }
}
