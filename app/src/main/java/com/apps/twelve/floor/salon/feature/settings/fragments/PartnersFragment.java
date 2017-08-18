package com.apps.twelve.floor.salon.feature.settings.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import butterknife.BindView;
import com.apps.twelve.floor.salon.R;
import com.apps.twelve.floor.salon.base.BaseFragment;
import com.apps.twelve.floor.salon.data.model.PartnerEntity;
import com.apps.twelve.floor.salon.feature.settings.adapters.PartnersAdapter;
import com.apps.twelve.floor.salon.feature.settings.presenters.PartnersFragmentPresenter;
import com.apps.twelve.floor.salon.feature.settings.views.IPartnersFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import java.util.List;

/**
 * Created by alexandersvyatetsky on 16/08/17.
 */

public class PartnersFragment extends BaseFragment implements IPartnersFragment {

  @InjectPresenter PartnersFragmentPresenter mPresenter;

  @BindView(R.id.list_item_view) RecyclerView mRvPartners;
  private PartnersAdapter mPartnersAdapter;

  public PartnersFragment() {
    super(R.layout.fragment_partners);
  }

  public static PartnersFragment newInstance() {
    Bundle args = new Bundle();
    PartnersFragment partnersFragment = new PartnersFragment();
    partnersFragment.setArguments(args);
    return partnersFragment;
  }

  @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    mPartnersAdapter = new PartnersAdapter(getMvpDelegate(), getActivity());
    mRvPartners.setLayoutManager(new LinearLayoutManager(getContext()));
    mRvPartners.setAdapter(mPartnersAdapter);
    mRvPartners.setNestedScrollingEnabled(false);
    mRvPartners.setFocusable(false);
  }

  @Override public void showPartners(List<PartnerEntity> partnerEntityList) {
    mPartnersAdapter.addPartners(partnerEntityList);
  }
}
