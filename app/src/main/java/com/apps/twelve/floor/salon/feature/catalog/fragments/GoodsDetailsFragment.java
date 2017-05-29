package com.apps.twelve.floor.salon.feature.catalog.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import com.apps.twelve.floor.salon.R;
import com.apps.twelve.floor.salon.base.BaseFragment;
import com.apps.twelve.floor.salon.data.model.StaffDetailContent;
import com.apps.twelve.floor.salon.data.model.StaffEntity;
import com.apps.twelve.floor.salon.feature.catalog.adapters.ImageStaffViewPagerAdapter;
import com.apps.twelve.floor.salon.feature.catalog.presenters.StaffDetailsFragmentPresenter;
import com.apps.twelve.floor.salon.feature.catalog.views.IStaffDetailsFragmentView;
import com.apps.twelve.floor.salon.utils.Constants;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.mzelzoghbi.zgallery.CustomViewPager;
import com.mzelzoghbi.zgallery.adapters.HorizontalListAdapters;
import java.util.ArrayList;

/**
 * Created by Vrungel on 18.05.2017.
 */

public class GoodsDetailsFragment extends BaseFragment implements IStaffDetailsFragmentView {
  @InjectPresenter StaffDetailsFragmentPresenter mStaffDetailsFragmentPresenter;

  @BindView(R.id.tvStaffTitle) TextView mTextViewTitle;

  @BindView(R.id.tvStaffDescription) TextView mTextViewDescription;
  @BindView(R.id.viewPagerImages) CustomViewPager mViewPagerImages;
  @BindView(R.id.recyclerViewImages) RecyclerView mRecyclerViewImages;
  @BindView(R.id.bPrevStaffImg) ImageButton mImageButtonPrevious;
  @BindView(R.id.bNextStaffImg) ImageButton mImageButtonNext;
  @BindView(R.id.tvPrice) TextView mTextViewPrice;
  @BindView(R.id.checkBoxFavoriteGoods) AppCompatCheckBox mCheckBoxFavoriteGoods;

  private ImageStaffViewPagerAdapter mViewPagerAdapter;

  private HorizontalListAdapters mHorizontalListAdapter;

  public GoodsDetailsFragment() {
    super(R.layout.fragment_catalog_detail);
  }

  public static GoodsDetailsFragment newInstance(StaffEntity entity) {
    Bundle args = new Bundle();
    args.putParcelable(Constants.FragmentsArgumentKeys.STAFF_ENTITY_KEY, entity);
    GoodsDetailsFragment fragment = new GoodsDetailsFragment();
    fragment.setArguments(args);
    return fragment;
  }

  @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    StaffEntity staffEntity =
        getArguments().getParcelable(Constants.FragmentsArgumentKeys.STAFF_ENTITY_KEY);

    /* turn off scrolling */
    Toolbar mToolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
    AppBarLayout.LayoutParams toolbarLayoutParams =
        (AppBarLayout.LayoutParams) mToolbar.getLayoutParams();
    toolbarLayoutParams.setScrollFlags(0);
    mToolbar.setLayoutParams(toolbarLayoutParams);

    LinearLayoutManager mLayoutManager =
        new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);

    if (staffEntity != null) {
      //some TV (title and gallery description)
      mTextViewTitle.setText(staffEntity.getTitle());
      mTextViewDescription.setText(staffEntity.getShortDescription());
      mTextViewPrice.setText(staffEntity.getPrice());

      // pager adapter
      mViewPagerAdapter =
          new ImageStaffViewPagerAdapter(getActivity(), staffEntity.getStaffDetailContents());
      mViewPagerImages.setAdapter(mViewPagerAdapter);

      // horizontal list adapter
      ArrayList<String> listUrlPhotos = new ArrayList<>();
      for (StaffDetailContent staffDetailContent : staffEntity.getStaffDetailContents()) {
        listUrlPhotos.add(staffDetailContent.getUrlPhoto());
      }
      mHorizontalListAdapter = new HorizontalListAdapters(getActivity(), listUrlPhotos,
          pos -> mViewPagerImages.setCurrentItem(pos, true));
      mRecyclerViewImages.setLayoutManager(mLayoutManager);
      mRecyclerViewImages.setAdapter(mHorizontalListAdapter);
      mHorizontalListAdapter.notifyDataSetChanged();

      int currentPos = 0;
      mHorizontalListAdapter.setSelectedItem(currentPos);
      mViewPagerImages.setCurrentItem(currentPos);

      updateImageInfoAndButtons();
    }

    mViewPagerImages.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
      @Override
      public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
      }

      @Override public void onPageSelected(int position) {

        mRecyclerViewImages.smoothScrollToPosition(position);
        mHorizontalListAdapter.setSelectedItem(position);
      }

      @Override public void onPageScrollStateChanged(int state) {
        updateImageInfoAndButtons();
      }
    });
  }

  private void updateImageInfoAndButtons() {
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

  @OnClick(R.id.bPrevStaffImg) public void nextPicture() {
    mViewPagerImages.setCurrentItem(mViewPagerImages.getCurrentItem() - 1, true);
  }

  @OnClick(R.id.bNextStaffImg) public void previousPicture() {
    mViewPagerImages.setCurrentItem(mViewPagerImages.getCurrentItem() + 1, true);
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
