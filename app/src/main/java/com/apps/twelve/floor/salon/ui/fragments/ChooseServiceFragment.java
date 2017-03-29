package com.apps.twelve.floor.salon.ui.fragments;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.LinearLayout;
import butterknife.BindView;
import com.apps.twelve.floor.salon.R;
import com.apps.twelve.floor.salon.mvp.data.model.ServiceEntity;
import com.apps.twelve.floor.salon.mvp.presenters.pr_fragments.ChooseServiceFragmentPresenter;
import com.apps.twelve.floor.salon.mvp.views.IChooseServiceFragmentView;
import com.apps.twelve.floor.salon.ui.adapters.ServicesAdapter;
import com.apps.twelve.floor.salon.ui.base.BaseFragment;
import com.apps.twelve.floor.salon.utils.ItemClickSupport;
import com.arellomobile.mvp.presenter.InjectPresenter;
import java.util.List;
import timber.log.Timber;

/**
 * Created by Vrungel on 29.03.2017.
 */

public class ChooseServiceFragment extends BaseFragment implements IChooseServiceFragmentView {
  @InjectPresenter ChooseServiceFragmentPresenter mChooseServiceFragmentPresenter;

  @BindView(R.id.etChooseService) EditText mEditTextChooseService;
  @BindView(R.id.llDeepItems) LinearLayout mLinearLayoutDeepItems;
  @BindView(R.id.rvServices) RecyclerView mRecyclerViewServices;
  @BindView(R.id.llAllitems) LinearLayout mLinearLayoutAllitems;
  @BindView(R.id.rvTreeOfServices) RecyclerView mRecyclerViewTreeOfServices;
  private ServicesAdapter mServicesAdapter;

  public static ChooseServiceFragment newInstance() {
    Bundle args = new Bundle();
    ChooseServiceFragment fragment = new ChooseServiceFragment();
    fragment.setArguments(args);
    return fragment;
  }

  public ChooseServiceFragment() {
    super(R.layout.fragment_choose_service);
  }

  @Override public void setUpRvServices() {
    mRecyclerViewServices.setLayoutManager(new LinearLayoutManager(getContext()));
    mServicesAdapter = new ServicesAdapter();
    mRecyclerViewServices.setAdapter(mServicesAdapter);
    ItemClickSupport.addTo(mRecyclerViewServices)
        .setOnItemClickListener(
            (recyclerView, position, v) -> mServicesAdapter.setSelectedItem(position));

    mEditTextChooseService.addTextChangedListener(new TextWatcher() {
      @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {

      }

      @Override public void onTextChanged(CharSequence s, int start, int before, int count) {
        mChooseServiceFragmentPresenter.filterServices(mEditTextChooseService.getText().toString());

      }

      @Override public void afterTextChanged(Editable s) {

      }
    });
  }

  @Override public void updateRvServices(List<ServiceEntity> serviceEntities) {
    Timber.e(String.valueOf(serviceEntities.size()));
    mServicesAdapter.setServiceEntity(serviceEntities);
  }
}
