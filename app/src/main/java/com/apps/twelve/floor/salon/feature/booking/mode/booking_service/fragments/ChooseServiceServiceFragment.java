package com.apps.twelve.floor.salon.feature.booking.mode.booking_service.fragments;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.content.res.AppCompatResources;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import butterknife.BindView;
import com.apps.twelve.floor.salon.R;
import com.apps.twelve.floor.salon.base.BaseFragment;
import com.apps.twelve.floor.salon.data.model.CategoryEntity;
import com.apps.twelve.floor.salon.data.model.ServiceEntity;
import com.apps.twelve.floor.salon.feature.booking.mode.booking_master.adapters.ServicesAdapter;
import com.apps.twelve.floor.salon.feature.booking.mode.booking_service.adapters.ServiceCategoryAdapter;
import com.apps.twelve.floor.salon.feature.booking.mode.booking_service.presenters.ChooseServiceServiceFragmentPresenter;
import com.apps.twelve.floor.salon.feature.booking.mode.booking_service.views.IChooseServiceServiceFragmentView;
import com.apps.twelve.floor.salon.utils.ItemClickSupport;
import com.arellomobile.mvp.presenter.InjectPresenter;
import java.util.List;

/**
 * Created by Vrungel on 29.03.2017.
 */

public class ChooseServiceServiceFragment extends BaseFragment
    implements IChooseServiceServiceFragmentView {

  @InjectPresenter ChooseServiceServiceFragmentPresenter mChooseServiceServiceFragmentPresenter;

  @BindView(R.id.etChooseService) EditText mEditTextChooseService;
  @BindView(R.id.pbLoadServices) ProgressBar mProgressBarLoadServices;
  @BindView(R.id.llTreeItems) LinearLayout mLinerLayoutTreeItems;
  @BindView(R.id.rvServices) RecyclerView mRecyclerViewAllServices;
  @BindView(R.id.rvTreeOfServices) RecyclerView mRecyclerViewCategory;
  @BindView(R.id.llAllItems) LinearLayout mLinearLayoutAllItems;
  @BindView(R.id.progressBarChooseService) ProgressBar mProgressBar;
  @BindView(R.id.tvPath) TextView tvPath;
  @BindView(R.id.progressBarCategoryService) ProgressBar mProgressBarCategoryService;

  private ServicesAdapter mServicesAdapter;
  private ServiceCategoryAdapter mServiceCategoryAdapter;

  public ChooseServiceServiceFragment() {
    super(R.layout.fragment_choose_service_service);
  }

  public static ChooseServiceServiceFragment newInstance() {
    Bundle args = new Bundle();
    ChooseServiceServiceFragment fragment = new ChooseServiceServiceFragment();
    fragment.setArguments(args);
    return fragment;
  }

  @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    tvPath.setSelected(true);
    tvPath.setEllipsize(TextUtils.TruncateAt.MARQUEE);
    tvPath.setSingleLine(true);
    Drawable leftDrawable =
        AppCompatResources.getDrawable(getActivity(), R.drawable.ic_back_service_24dp);
    tvPath.setCompoundDrawablesWithIntrinsicBounds(leftDrawable, null, null, null);

    tvPath.setOnClickListener(view1 -> getActivity().onBackPressed());
  }

  @Override public void setUpRvCategory() {
    mRecyclerViewCategory.setLayoutManager(new LinearLayoutManager(getContext()));
    mServiceCategoryAdapter = new ServiceCategoryAdapter();
    mRecyclerViewCategory.setAdapter(mServiceCategoryAdapter);
    mLinearLayoutAllItems.setVisibility(View.GONE);
    ItemClickSupport.addTo(mRecyclerViewCategory)
        .setOnItemClickListener((recyclerView, position, v) -> {
          if (recyclerView.getAdapter() instanceof ServiceCategoryAdapter && mServiceCategoryAdapter
              .getItem(position)
              .hasChildren()) {
            mChooseServiceServiceFragmentPresenter.showTextPath(
                mServiceCategoryAdapter.getItem(position).getTitle());
            mChooseServiceServiceFragmentPresenter.getCategoriesWithParentId(
                mServiceCategoryAdapter.getItem(position).getId());
          } else {
            if (recyclerView.getAdapter() instanceof ServicesAdapter) {
              mChooseServiceServiceFragmentPresenter.setItemSelected(position);
            } else {
              mChooseServiceServiceFragmentPresenter.showTextPath(
                  mServiceCategoryAdapter.getItem(position).getTitle());
              mChooseServiceServiceFragmentPresenter.getServiceWithParentId(
                  mServiceCategoryAdapter.getItem(position).getId());
            }
          }
        });
  }

  @Override public void updateRvCategory(List<CategoryEntity> parentServices) {
    mServiceCategoryAdapter.setData(parentServices);
  }

  @Override public void setUpRvAllServices() {
    mRecyclerViewAllServices.setLayoutManager(new LinearLayoutManager(getContext()));
    mServicesAdapter = new ServicesAdapter(getContext());
    mRecyclerViewAllServices.setAdapter(mServicesAdapter);
    ItemClickSupport.addTo(mRecyclerViewAllServices)
        .setOnItemClickListener(
            (recyclerView, position, v) -> mChooseServiceServiceFragmentPresenter.setItemSelected(
                position));

    mEditTextChooseService.setOnFocusChangeListener((v, hasFocus) -> {
      if (hasFocus && mEditTextChooseService.getText().toString().isEmpty()) {
        mChooseServiceServiceFragmentPresenter.fetchAllServices();
      }
    });

    mEditTextChooseService.addTextChangedListener(new TextWatcher() {
      @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {

      }

      @Override public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (s.length() != 0) {
          mChooseServiceServiceFragmentPresenter.showLLAllServices();
          mChooseServiceServiceFragmentPresenter.hideLLTreeServices();
          mChooseServiceServiceFragmentPresenter.filterServices(
              mEditTextChooseService.getText().toString());
        } else {
          mChooseServiceServiceFragmentPresenter.hideLLAllServices();
          mChooseServiceServiceFragmentPresenter.showLLTreeServices();
          mChooseServiceServiceFragmentPresenter.filterServices(
              mEditTextChooseService.getText().toString());
        }
      }

      @Override public void afterTextChanged(Editable s) {

      }
    });
  }

  @Override public void updateRvAllServices(List<ServiceEntity> serviceAllEntities) {
    mServicesAdapter.setServiceEntity(serviceAllEntities);
    mRecyclerViewAllServices.setAdapter(mServicesAdapter);
  }

  @Override public void hideLLAllServices() {
    mLinearLayoutAllItems.setVisibility(View.GONE);
  }

  @Override public void showLLAllServices() {

    mLinearLayoutAllItems.setVisibility(View.VISIBLE);
  }

  @Override public void hideLLTreeServices() {
    mLinerLayoutTreeItems.setVisibility(View.GONE);
  }

  @Override public void setServicesWithParentId(List<ServiceEntity> serviceEntities) {
    mServicesAdapter.setServiceEntity(serviceEntities);
    mRecyclerViewCategory.setAdapter(mServicesAdapter);
  }

  @Override public void setCategoriesWithParentId(List<CategoryEntity> categoryEntities) {
    mServiceCategoryAdapter.setData(categoryEntities);
    mRecyclerViewCategory.setAdapter(mServiceCategoryAdapter);
  }

  @Override public void showTextPath(String text) {
    tvPath.setVisibility(View.VISIBLE);
    tvPath.setText(text);
  }

  @Override public void hideTextPath() {
    tvPath.setVisibility(View.GONE);
  }

  @Override public void stateCategoriesServices(boolean state) {
    if (state) {
      mRecyclerViewCategory.setVisibility(View.VISIBLE);
      mProgressBarCategoryService.setVisibility(View.GONE);
    } else {
      mRecyclerViewCategory.setVisibility(View.GONE);
      mProgressBarCategoryService.setVisibility(View.VISIBLE);
    }
  }

  @Override public void showLLTreeServices() {
    mLinerLayoutTreeItems.setVisibility(View.VISIBLE);
  }

  @Override public void setItemSelected(int position) {
    mServicesAdapter.setSelectedItem(position);
  }

  @Override public void hideProgressBar() {
    mProgressBar.setVisibility(View.GONE);
  }

  @Override public void showProgressBar() {
    mProgressBar.setVisibility(View.VISIBLE);
  }

  @Override public void showProgressBarAllServices() {
    mProgressBarLoadServices.setVisibility(View.VISIBLE);
  }

  @Override public void hideProgressBarAllServices() {
    mProgressBarLoadServices.setVisibility(View.GONE);
  }
}
