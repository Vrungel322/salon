package com.apps.twelve.floor.salon.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import com.apps.twelve.floor.salon.R;
import com.apps.twelve.floor.salon.mvp.data.model.OurWorkEntity;
import com.apps.twelve.floor.salon.mvp.presenters.fragments.WorkDetailsPresenter;
import com.apps.twelve.floor.salon.mvp.views.IWorkDetailsFragmentView;
import com.apps.twelve.floor.salon.ui.adapters.ImageWorkViewPagerAdapter;
import com.apps.twelve.floor.salon.ui.base.BaseFragment;
import com.apps.twelve.floor.salon.utils.Constants;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.mzelzoghbi.zgallery.CustomViewPager;
import com.mzelzoghbi.zgallery.adapters.HorizontalListAdapters;

/**
 * Created by Alexandra on 23.02.2017.
 */

public class WorkDetailsFragment extends BaseFragment implements IWorkDetailsFragmentView {

  @InjectPresenter WorkDetailsPresenter mWorkDetailsFragmentPresenter;

  @BindView(R.id.textViewTitleWork) TextView mTextViewTitleWork;
  @BindView(R.id.textViewDescriptionWork) TextView mTextViewDescriptionWork;
  @BindView(R.id.textViewMore) TextView mTextViewMore;
  @BindView(R.id.viewPagerImages) CustomViewPager mViewPagerImages;
  @BindView(R.id.relativeLayout) RelativeLayout mRelativeLayout;
  @BindView(R.id.recyclerViewImages) RecyclerView mRecyclerViewImages;

  private OurWorkEntity mOurWorkEntity;

  ImageWorkViewPagerAdapter adapter;
  LinearLayoutManager mLayoutManager;
  HorizontalListAdapters hAdapter;

  public static WorkDetailsFragment newInstance(OurWorkEntity entity) {
    Bundle args = new Bundle();
    args.putSerializable(Constants.FragmentsArgumentKeys.OUR_ENTITY_KEY, entity);
    WorkDetailsFragment fragment = new WorkDetailsFragment();
    fragment.setArguments(args);
    return fragment;
  }

  public WorkDetailsFragment() {
    super(R.layout.fragment_work_details);
  }

  @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    mOurWorkEntity = (OurWorkEntity) getArguments().getSerializable(
        Constants.FragmentsArgumentKeys.OUR_ENTITY_KEY);

    /* turn off scrolling */
    Toolbar mToolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
    AppBarLayout.LayoutParams toolbarLayoutParams =
        (AppBarLayout.LayoutParams) mToolbar.getLayoutParams();
    toolbarLayoutParams.setScrollFlags(0);
    mToolbar.setLayoutParams(toolbarLayoutParams);

    mLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);

    // pager adapter
    adapter = new ImageWorkViewPagerAdapter(getActivity(), mOurWorkEntity.getListImageUrl());
    mViewPagerImages.setAdapter(adapter);

    // horizontal list adapter
    hAdapter = new HorizontalListAdapters(getActivity(), mOurWorkEntity.getListImageUrl(),
        pos -> mViewPagerImages.setCurrentItem(pos, true));
    mRecyclerViewImages.setLayoutManager(mLayoutManager);
    mRecyclerViewImages.setAdapter(hAdapter);
    hAdapter.notifyDataSetChanged();

    mViewPagerImages.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
      @Override
      public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
      }

      @Override public void onPageSelected(int position) {
        mRecyclerViewImages.smoothScrollToPosition(position);
        hAdapter.setSelectedItem(position);
      }

      @Override public void onPageScrollStateChanged(int state) {

      }
    });

    int currentPos = 0;
    hAdapter.setSelectedItem(currentPos);
    mViewPagerImages.setCurrentItem(currentPos);
  }

  @OnClick(R.id.textViewMore) public void onClick() {
  }

  @Override public void onDestroy() {
    /* turn on scrolling */
    Toolbar mToolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
    AppBarLayout.LayoutParams toolbarLayoutParams =
        (AppBarLayout.LayoutParams) mToolbar.getLayoutParams();
    toolbarLayoutParams.setScrollFlags(AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL
        | AppBarLayout.LayoutParams.SCROLL_FLAG_ENTER_ALWAYS);
    mToolbar.setLayoutParams(toolbarLayoutParams);

    super.onDestroy();
  }
}
