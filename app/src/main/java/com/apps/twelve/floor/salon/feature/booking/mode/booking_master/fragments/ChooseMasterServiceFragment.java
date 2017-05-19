package com.apps.twelve.floor.salon.feature.booking.mode.booking_master.fragments;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import butterknife.BindView;
import com.afollestad.aesthetic.Aesthetic;
import com.apps.twelve.floor.salon.R;
import com.apps.twelve.floor.salon.base.BaseFragment;
import com.apps.twelve.floor.salon.data.model.ServiceEntity;
import com.apps.twelve.floor.salon.feature.booking.mode.booking_master.adapters.ServicesAdapter;
import com.apps.twelve.floor.salon.feature.booking.mode.booking_master.presenters.ChooseMasterServiceFragmentPresenter;
import com.apps.twelve.floor.salon.feature.booking.mode.booking_master.views.IChooseMasterServiceFragmentView;
import com.apps.twelve.floor.salon.utils.ItemClickSupport;
import com.arellomobile.mvp.presenter.InjectPresenter;
import java.util.List;

public class ChooseMasterServiceFragment extends BaseFragment
    implements IChooseMasterServiceFragmentView {

  @InjectPresenter ChooseMasterServiceFragmentPresenter mChooseMasterServiceFragmentPresenter;

  @BindView(R.id.tv_master_description) TextView mTextViewMasterDescription;
  @BindView(R.id.etChooseService) EditText mEditTextChooseService;
  @BindView(R.id.rvServices) RecyclerView mRecyclerViewServices;
  @BindView(R.id.progressBarChooseService) ProgressBar mProgressBar;

  private ServicesAdapter mServicesAdapter;

  public ChooseMasterServiceFragment() {
    super(R.layout.fragment_choose_master_service);
  }

  public static ChooseMasterServiceFragment newInstance() {
    Bundle args = new Bundle();
    ChooseMasterServiceFragment fragment = new ChooseMasterServiceFragment();
    fragment.setArguments(args);
    return fragment;
  }

  @Override public void setUpRvServices() {
    mRecyclerViewServices.setLayoutManager(new LinearLayoutManager(getContext()));
    mServicesAdapter = new ServicesAdapter();
    mRecyclerViewServices.setAdapter(mServicesAdapter);

    ItemClickSupport.addTo(mRecyclerViewServices)
        .setOnItemClickListener(
            (recyclerView, position, v) -> mChooseMasterServiceFragmentPresenter.setItemSelected(
                position));

    mEditTextChooseService.addTextChangedListener(new TextWatcher() {
      @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {

      }

      @Override public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (s.length() != 0) {
          mChooseMasterServiceFragmentPresenter.filterServices(
              mEditTextChooseService.getText().toString());
        }
      }

      @Override public void afterTextChanged(Editable s) {

      }
    });

    Aesthetic.get().colorPrimary().subscribe(color -> {
      mRecyclerViewServices.setBackgroundColor(color);
    });
  }

  @Override public void updateRvServices(List<ServiceEntity> serviceEntities) {
    mServicesAdapter.setServiceEntity(serviceEntities);
  }

  @Override public void setItemSelected(int position) {
    mServicesAdapter.setSelectedItem(position);
  }

  @Override public void hideProgressBar() {
    mProgressBar.setVisibility(View.GONE);
  }

  @Override public void setMasterName(String masterName) {
    mTextViewMasterDescription.setText(masterName);
  }
}
