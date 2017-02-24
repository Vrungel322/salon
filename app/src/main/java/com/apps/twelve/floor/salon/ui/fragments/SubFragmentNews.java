package com.apps.twelve.floor.salon.ui.fragments;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import com.apps.twelve.floor.salon.R;
import com.apps.twelve.floor.salon.mvp.data.model.NewsEntity;
import com.apps.twelve.floor.salon.mvp.presenters.SubFragmentNewsPresenter;
import com.apps.twelve.floor.salon.mvp.views.ISubFragmentNewsView;
import com.apps.twelve.floor.salon.ui.base.BaseFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.squareup.picasso.Picasso;

/**
 * Created by Vrungel on 23.02.2017.
 */

public class SubFragmentNews extends BaseFragment implements ISubFragmentNewsView {
  @InjectPresenter SubFragmentNewsPresenter mSubFragmentNewsPresenter;
  @BindView(R.id.ivNewsPreview) ImageView mImageViewNewsPreview;
  @BindView(R.id.tvNewsShortDescription) TextView mTextViewNewsShortDescription;
  @BindView(R.id.tvNewsData) TextView mTextViewNewsData;

  private NewsEntity mNewsEntity;

  public SubFragmentNews() {
    super(R.layout.fragment_sub_news);
  }

  public static SubFragmentNews newInstance() {
    Bundle args = new Bundle();
    SubFragmentNews fragment = new SubFragmentNews();
    fragment.setArguments(args);
    return fragment;
  }

  @Override public void updateNewsPreview(NewsEntity newsEntity) {
    mNewsEntity = newsEntity;
    Picasso.with(getContext())
        .load(newsEntity.getImageNewsPreviewURL())
        .into(mImageViewNewsPreview);
    mTextViewNewsShortDescription.setText(newsEntity.getNewsShortDescription());
    mTextViewNewsData.setText(newsEntity.getNewsData());
  }

  @OnClick(R.id.tvAllNews) public void tvAllNewsClicked() {
    // TODO: 23.02.2017 create FragmentNews
    showToastMessage("tvAllNews clicked");
    mNavigator.addFragmentAndAddToBackStack((AppCompatActivity) getActivity(),
        R.id.container_main, FragmentAllNewsView.newInstance());
  }

  @OnClick({ R.id.ivNewsPreview, R.id.tvNewsShortDescription }) public void onShowDetailNews() {
    mNavigator.addFragmentAndAddToBackStack((AppCompatActivity) getActivity(),
        R.id.container_main, FragmentNewsDetail.newInstance(mNewsEntity));
  }
}
