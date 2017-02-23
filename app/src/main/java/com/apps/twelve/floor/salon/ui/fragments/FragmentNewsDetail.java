package com.apps.twelve.floor.salon.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import com.apps.twelve.floor.salon.R;
import com.apps.twelve.floor.salon.mvp.data.model.PreviewNewsEntity;
import com.apps.twelve.floor.salon.mvp.presenters.FragmentDetailNewsPresenter;
import com.apps.twelve.floor.salon.mvp.views.IFragmentNewsDetailView;
import com.apps.twelve.floor.salon.ui.base.BaseFragment;
import com.apps.twelve.floor.salon.utils.Constants;
import com.arellomobile.mvp.presenter.InjectPresenter;

/**
 * Created by Vrungel on 23.02.2017.
 */

public class FragmentNewsDetail extends BaseFragment implements IFragmentNewsDetailView {
  @InjectPresenter FragmentDetailNewsPresenter mFragmentDetailNewsPresenter;
  private PreviewNewsEntity mPreviewNewsEntity;

  public static FragmentNewsDetail newInstance(PreviewNewsEntity previewNewsEntity) {
    Bundle args = new Bundle();
    args.putParcelable(Constants.FragmentsArgumentKeys.NEWS_DETAIL_KEY, previewNewsEntity);
    FragmentNewsDetail fragment = new FragmentNewsDetail();
    fragment.setArguments(args);
    return fragment;
  }

  public FragmentNewsDetail() {
    super(R.layout.fragment_detail_news);
  }

  @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    this.mPreviewNewsEntity = getArguments().getParcelable(Constants.FragmentsArgumentKeys.NEWS_DETAIL_KEY);
  }
}
