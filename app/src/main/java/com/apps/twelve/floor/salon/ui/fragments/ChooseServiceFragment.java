package com.apps.twelve.floor.salon.ui.fragments;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import butterknife.BindView;
import com.apps.twelve.floor.salon.R;
import com.apps.twelve.floor.salon.mvp.data.model.ServiceEntity;
import com.apps.twelve.floor.salon.mvp.data.model.service_tree_item.ParentService;
import com.apps.twelve.floor.salon.mvp.presenters.pr_fragments.ChooseServiceFragmentPresenter;
import com.apps.twelve.floor.salon.mvp.views.IChooseServiceFragmentView;
import com.apps.twelve.floor.salon.ui.adapters.ServiceTreeAdapter;
import com.apps.twelve.floor.salon.ui.adapters.ServicesAdapter;
import com.apps.twelve.floor.salon.ui.base.BaseFragment;
import com.apps.twelve.floor.salon.utils.ItemClickSupport;
import com.arellomobile.mvp.presenter.InjectPresenter;
import java.util.List;

/**
 * Created by Vrungel on 29.03.2017.
 */

public class ChooseServiceFragment extends BaseFragment implements IChooseServiceFragmentView {

  @InjectPresenter ChooseServiceFragmentPresenter mChooseServiceFragmentPresenter;

  @BindView(R.id.etChooseService) EditText mEditTextChooseService;
  @BindView(R.id.pbLoadServices) ProgressBar mProgressBarLoadServices;
  @BindView(R.id.llTreeItems) LinearLayout mLinerLayoutTreeItems;
  @BindView(R.id.rvServices) RecyclerView mRecyclerViewServices;
  @BindView(R.id.rvTreeOfServices) RecyclerView mRecyclerViewTreeOfServices;
  @BindView(R.id.llAllitems) LinearLayout mLinearLayoutAllitems;
  @BindView(R.id.progressBarChooseService) ProgressBar mProgressBar;

  private ServicesAdapter mServicesAdapter;
  private ServiceTreeAdapter mServicesTreeAdapter;

  public static ChooseServiceFragment newInstance() {
    Bundle args = new Bundle();
    ChooseServiceFragment fragment = new ChooseServiceFragment();
    fragment.setArguments(args);
    return fragment;
  }

  public ChooseServiceFragment() {
    super(R.layout.fragment_choose_service);
  }

  @Override public void setUpRvTreeServices() {
    mRecyclerViewTreeOfServices.setLayoutManager(new LinearLayoutManager(getContext()));
    mServicesTreeAdapter = new ServiceTreeAdapter();
    mLinearLayoutAllitems.setVisibility(View.GONE);
  }

  @Override public void updateRvTreeServices(List<ParentService> parentServices) {
    mServicesTreeAdapter.setData(parentServices);
    mRecyclerViewTreeOfServices.setAdapter(mServicesTreeAdapter);
  }

  @Override public void setUpRvAllServices() {
    mRecyclerViewServices.setLayoutManager(new LinearLayoutManager(getContext()));
    mServicesAdapter = new ServicesAdapter();
    mRecyclerViewServices.setAdapter(mServicesAdapter);
    ItemClickSupport.addTo(mRecyclerViewServices)
        .setOnItemClickListener(
            (recyclerView, position, v) -> mChooseServiceFragmentPresenter.setItemSelected(
                position));

    mEditTextChooseService.setOnFocusChangeListener((v, hasFocus) -> {
      if (hasFocus && mEditTextChooseService.getText().toString().isEmpty()) {
        mLinerLayoutTreeItems.setVisibility(View.GONE);
        mLinearLayoutAllitems.setVisibility(View.VISIBLE);
        mChooseServiceFragmentPresenter.fetchAllServices();
      }
    });

    mEditTextChooseService.addTextChangedListener(new TextWatcher() {
      @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {

      }

      @Override public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (s.length() != 0) {
          //mChooseServiceFragmentPresenter.showRvAllServices();
          mChooseServiceFragmentPresenter.showLLAllServices();
          mChooseServiceFragmentPresenter.hideLLTreeServices();
          mChooseServiceFragmentPresenter.filterServices(
              mEditTextChooseService.getText().toString());
        } else {
          mChooseServiceFragmentPresenter.hideLLAllServices();
          mChooseServiceFragmentPresenter.showLLTreeServices();
        }
      }

      @Override public void afterTextChanged(Editable s) {

      }
    });
  }

  @Override public void updateRvAllServices(List<ServiceEntity> serviceAllEntities) {
    mServicesAdapter.setServiceEntity(serviceAllEntities);
  }

  @Override public void hideLLAllServices() {
    mLinearLayoutAllitems.setVisibility(View.GONE);
  }

  @Override public void showLLAllServices() {

    mLinearLayoutAllitems.setVisibility(View.VISIBLE);
  }

  @Override public void hideLLTreeServices() {
    mLinerLayoutTreeItems.setVisibility(View.GONE);
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

  @Override public void showErrorMsg(String s) {
    showAlertMessage(s, "Try again later");
  }
}
