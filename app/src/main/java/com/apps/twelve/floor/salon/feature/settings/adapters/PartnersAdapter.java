package com.apps.twelve.floor.salon.feature.settings.adapters;

import android.content.Context;
import android.support.v7.content.res.AppCompatResources;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
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
import com.bumptech.glide.Glide;
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
    Glide.with(holder.ivPartnerLogo.getContext())
        .load(mPartnerEntities.get(position).getImage())
        .placeholder(AppCompatResources.getDrawable(mContext, R.drawable.ic_news_placeholder_130dp))
        .dontAnimate()
        .into(holder.ivPartnerLogo);

    holder.tvName.setText(mPartnerEntities.get(position).getTitle());
  }

  @Override public int getItemCount() {
    return mPartnerEntities.size();
  }

  public class PartnersViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.tvPartnerName) TextView tvName;
    @BindView(R.id.ivPartnerLogo) ImageView ivPartnerLogo;

    public PartnersViewHolder(View itemView) {
      super(itemView);
      ButterKnife.bind(this, itemView);
    }
  }
}
