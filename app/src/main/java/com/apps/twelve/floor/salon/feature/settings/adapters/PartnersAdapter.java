package com.apps.twelve.floor.salon.feature.settings.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.util.Linkify;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.apps.twelve.floor.salon.R;
import com.apps.twelve.floor.salon.base.MvpBaseRecyclerAdapter;
import com.apps.twelve.floor.salon.data.model.PartnerEntity;
import com.apps.twelve.floor.salon.feature.settings.presenters.PartnersAdapterPresenter;
import com.apps.twelve.floor.salon.feature.settings.views.IPartnersAdapter;
import com.arellomobile.mvp.MvpDelegate;
import com.arellomobile.mvp.presenter.InjectPresenter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by alexandersvyatetsky on 16/08/17.
 */

public class PartnersAdapter extends MvpBaseRecyclerAdapter<PartnersAdapter.PartnersViewHolder>
    implements IPartnersAdapter {

  @InjectPresenter PartnersAdapterPresenter mPresenter;

  private Context mContext;
  private ArrayList<PartnerEntity> mPartnerEntities = new ArrayList<PartnerEntity>();

  public PartnersAdapter(MvpDelegate<?> parentDelegate, Context context) {
    super(parentDelegate, "PartnersAdapter ");
    this.mContext = context;
  }

  public void addPartners(List<PartnerEntity> partnerEntities) {
    this.mPartnerEntities.clear();
    this.mPartnerEntities.addAll(partnerEntities);
    notifyDataSetChanged();
  }

  @Override public PartnersViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    return new PartnersViewHolder(
        LayoutInflater.from(parent.getContext()).inflate(R.layout.item_partners, parent, false));
  }

  @Override public void onBindViewHolder(PartnersViewHolder holder, int position) {
    holder.tvName.setText(mPartnerEntities.get(position).getName());
    setContextAndVisibility(holder.llAddresses, holder.vAddressDivider, holder.llAddressContainer,
        mPartnerEntities.get(position).getAddresses());
    setContextAndVisibility(holder.llPhones, holder.vPhonessDivider, holder.llPhonesContainer,
        mPartnerEntities.get(position).getPhones());
    setContextAndVisibility(holder.llEmails, holder.vEmailsDivider, holder.llEmailsContainer,
        mPartnerEntities.get(position).getEmails());
    setContextAndVisibility(holder.llWebsites, holder.vWebsitesDivider, holder.llWebsitesContainer,
        mPartnerEntities.get(position).getWebSites());
  }

  private void setContextAndVisibility(LinearLayout contactPlaceholder, View divider,
      LinearLayout contactContainer, List<String> data) {
    if (data.size() == 0) {
      divider.setVisibility(View.GONE);
      contactContainer.setVisibility(View.GONE);
    } else {
      divider.setVisibility(View.VISIBLE);
      contactContainer.setVisibility(View.VISIBLE);
      contactPlaceholder.removeAllViews();
      TextView tvTmpView = null;
      for (int i = 0; i < data.size(); i++) {
        tvTmpView = (TextView) LayoutInflater.from(contactPlaceholder.getContext())
            .inflate(R.layout.contact_text, contactPlaceholder, false);
        tvTmpView.setText(data.get(i));
        Linkify.addLinks(tvTmpView, Linkify.ALL);
        contactPlaceholder.addView(tvTmpView);
      }
    }
  }

  @Override public int getItemCount() {
    return mPartnerEntities.size();
  }

  public class PartnersViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.tv_name) TextView tvName;
    @BindView(R.id.ll_addresses) LinearLayout llAddresses;
    @BindView(R.id.ll_phones) LinearLayout llPhones;
    @BindView(R.id.ll_emails) LinearLayout llEmails;
    @BindView(R.id.ll_websites) LinearLayout llWebsites;
    @BindView(R.id.v_address_divider) View vAddressDivider;
    @BindView(R.id.ll_address_container) LinearLayout llAddressContainer;
    @BindView(R.id.v_phoness_divider) View vPhonessDivider;
    @BindView(R.id.ll_phones_container) LinearLayout llPhonesContainer;
    @BindView(R.id.v_emails_divider) View vEmailsDivider;
    @BindView(R.id.ll_emails_container) LinearLayout llEmailsContainer;
    @BindView(R.id.v_websites_divider) View vWebsitesDivider;
    @BindView(R.id.ll_websites_container) LinearLayout llWebsitesContainer;

    public PartnersViewHolder(View itemView) {
      super(itemView);
      ButterKnife.bind(this, itemView);
    }
  }
}
