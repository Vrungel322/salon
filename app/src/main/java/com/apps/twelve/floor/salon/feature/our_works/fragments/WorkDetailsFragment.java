package com.apps.twelve.floor.salon.feature.our_works.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import com.apps.twelve.floor.salon.R;
import com.apps.twelve.floor.salon.base.BaseFragment;
import com.apps.twelve.floor.salon.data.model.OurWorkEntity;
import com.apps.twelve.floor.salon.data.model.PhotoWorksEntity;
import com.apps.twelve.floor.salon.feature.our_works.adapters.ImageWorkViewPagerAdapter;
import com.apps.twelve.floor.salon.feature.our_works.presenters.WorkDetailsFragmentPresenter;
import com.apps.twelve.floor.salon.feature.our_works.views.IWorkDetailsFragmentView;
import com.apps.twelve.floor.salon.utils.Constants;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.mzelzoghbi.zgallery.CustomViewPager;
import com.mzelzoghbi.zgallery.adapters.HorizontalListAdapters;
import java.util.ArrayList;

/**
 * Created by Alexandra on 23.02.2017.
 */

public class WorkDetailsFragment extends BaseFragment implements IWorkDetailsFragmentView {

  @InjectPresenter WorkDetailsFragmentPresenter mWorkDetailsFragmentPresenter;

  @BindView(R.id.textViewTitleWork) TextView mTextViewTitleWork;
  @BindView(R.id.textViewDescriptionWork) TextView mTextViewDescriptionWork;
  @BindView(R.id.textViewMore) TextView mTextViewMore;
  @BindView(R.id.viewPagerImages) CustomViewPager mViewPagerImages;
  @BindView(R.id.recyclerViewImages) RecyclerView mRecyclerViewImages;
  @BindView(R.id.checkBoxFavorite) CheckBox mCheckBoxFavorite;
  @BindView(R.id.textViewDescriptionItemPhoto) TextView mTextViewDescriptionItemPhoto;
  @BindView(R.id.btn_previous) ImageButton mImageButtonPrevious;
  @BindView(R.id.btn_next) ImageButton mImageButtonNext;
  @BindView(R.id.tv_count) TextView mTextViewCount;

  private ImageWorkViewPagerAdapter mViewPagerAdapter;
  private HorizontalListAdapters mHorizontalListAdapter;
  private PhotoWorksEntity mPhotoWorksEntity;

  public WorkDetailsFragment() {
    super(R.layout.fragment_our_work_details);
  }

  public static WorkDetailsFragment newInstance(OurWorkEntity entity) {
    Bundle args = new Bundle();
    args.putParcelable(Constants.FragmentsArgumentKeys.OUR_ENTITY_KEY, entity);
    WorkDetailsFragment fragment = new WorkDetailsFragment();
    fragment.setArguments(args);
    return fragment;
  }

  @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    OurWorkEntity ourWorkEntity =
        getArguments().getParcelable(Constants.FragmentsArgumentKeys.OUR_ENTITY_KEY);

    /* turn off scrolling */
    Toolbar mToolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
    AppBarLayout.LayoutParams toolbarLayoutParams =
        (AppBarLayout.LayoutParams) mToolbar.getLayoutParams();
    toolbarLayoutParams.setScrollFlags(0);
    mToolbar.setLayoutParams(toolbarLayoutParams);

    LinearLayoutManager mLayoutManager =
        new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);

    if (ourWorkEntity != null) {
      //some TV (title and gallery description)
      mTextViewTitleWork.setText(ourWorkEntity.getTitle());
      mTextViewDescriptionWork.setText(ourWorkEntity.getShortDescription());

      // pager adapter
      mViewPagerAdapter =
          new ImageWorkViewPagerAdapter(getActivity(), ourWorkEntity.getListPhotoWorks());
      mViewPagerImages.setAdapter(mViewPagerAdapter);

      // horizontal list adapter
      ArrayList<String> listUrlPhotos = new ArrayList<>();
      for (PhotoWorksEntity photoWorksEntity : ourWorkEntity.getListPhotoWorks()) {
        listUrlPhotos.add(photoWorksEntity.getUrlPhoto());
      }
      mHorizontalListAdapter = new HorizontalListAdapters(getActivity(), listUrlPhotos,
          pos -> mViewPagerImages.setCurrentItem(pos, true));
      mRecyclerViewImages.setLayoutManager(mLayoutManager);
      mRecyclerViewImages.setAdapter(mHorizontalListAdapter);
      mHorizontalListAdapter.notifyDataSetChanged();

      int currentPos = 0;
      mPhotoWorksEntity = mViewPagerAdapter.getEntity(currentPos);
      mHorizontalListAdapter.setSelectedItem(currentPos);
      mViewPagerImages.setCurrentItem(currentPos);

      mCheckBoxFavorite.setChecked(mPhotoWorksEntity.isFavorite());
      mTextViewDescriptionItemPhoto.setText(mPhotoWorksEntity.getDescriptionPhoto());

      updateImageInfoAndButtons();
    }

    mTextViewDescriptionWork.getViewTreeObserver()
        .addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
          @Override public void onGlobalLayout() {
            if (mTextViewDescriptionWork.getLineCount() > 3) {
              mTextViewMore.setVisibility(View.VISIBLE);
              mTextViewDescriptionWork.setMaxLines(3);
              mTextViewDescriptionWork.setEllipsize(TextUtils.TruncateAt.END);
              mTextViewDescriptionWork.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            } else {
              mTextViewDescriptionWork.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
          }
        });

    mViewPagerImages.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
      @Override
      public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
      }

      @Override public void onPageSelected(int position) {
        mPhotoWorksEntity = mViewPagerAdapter.getEntity(position);

        mRecyclerViewImages.smoothScrollToPosition(position);
        mHorizontalListAdapter.setSelectedItem(position);

        mCheckBoxFavorite.setChecked(mPhotoWorksEntity.isFavorite());
        mTextViewDescriptionItemPhoto.setText(mPhotoWorksEntity.getDescriptionPhoto());
      }

      @Override public void onPageScrollStateChanged(int state) {
        updateImageInfoAndButtons();
      }
    });
  }

  private void updateImageInfoAndButtons() {
    mTextViewCount.setText(
        mViewPagerImages.getCurrentItem() + 1 + "/" + mViewPagerAdapter.getCount());
    if (mViewPagerImages.getCurrentItem() == mViewPagerAdapter.getCount() - 1) {
      mImageButtonNext.setVisibility(View.INVISIBLE);
    } else {
      mImageButtonNext.setVisibility(View.VISIBLE);
    }
    if (mViewPagerImages.getCurrentItem() == 0) {
      mImageButtonPrevious.setVisibility(View.INVISIBLE);
    } else {
      mImageButtonPrevious.setVisibility(View.VISIBLE);
    }
  }

  @OnClick(R.id.textViewMore) public void onClick() {
    if (mTextViewDescriptionWork.getMaxLines() == 3) {
      mTextViewDescriptionWork.setMaxLines(Integer.MAX_VALUE);
      mTextViewDescriptionWork.setEllipsize(null);
    } else {
      mTextViewDescriptionWork.setMaxLines(3);
      mTextViewDescriptionWork.setEllipsize(TextUtils.TruncateAt.END);
    }
  }

  @OnClick(R.id.btn_next) public void nextPicture() {
    mViewPagerImages.setCurrentItem(mViewPagerImages.getCurrentItem() + 1, true);
  }

  @OnClick(R.id.btn_previous) public void previousPicture() {
    mViewPagerImages.setCurrentItem(mViewPagerImages.getCurrentItem() - 1, true);
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

  @OnClick(R.id.checkBoxFavorite) public void onCheckFavorite() {
    if (mAuthorizationManager.isAuthorized()) {
      if (mCheckBoxFavorite.isChecked()) {
        mWorkDetailsFragmentPresenter.addFavorite(mPhotoWorksEntity.getId());
      } else {
        mWorkDetailsFragmentPresenter.deleteFavorite(mPhotoWorksEntity.getId());
      }
    } else {
      mWorkDetailsFragmentPresenter.showAlertDialog();
      mCheckBoxFavorite.setChecked(false);
    }
  }

  @Override public void setStatusFavorite(boolean statusFavorite) {
    mPhotoWorksEntity.setFavorite(statusFavorite);
  }
}
