package com.apps.twelve.floor.salon.feature.catalog.adapters;

import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.apps.twelve.floor.salon.R;
import com.apps.twelve.floor.salon.data.model.GoodsEntity;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vrungel on 18.05.2017.
 */

public class GoodsListAdapter extends RecyclerView.Adapter<GoodsListAdapter.GoodsListViewHolder> {
  private ArrayList<GoodsEntity> mGoodsEntities = new ArrayList<>();

  public void addListGoodsEntity(List<GoodsEntity> goodsEntities) {
    mGoodsEntities.clear();
    mGoodsEntities.addAll(goodsEntities);
    notifyDataSetChanged();
  }

  @Override public GoodsListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    return new GoodsListViewHolder(
        LayoutInflater.from(parent.getContext()).inflate(R.layout.item_goods, parent, false));
  }

  @Override public void onBindViewHolder(GoodsListViewHolder holder, int position) {
    Picasso.with(holder.mImageViewGoodsPhoto.getContext())
        .load(Uri.parse(mGoodsEntities.get(position).getImageURL()))
        .into(holder.mImageViewGoodsPhoto);

    if (mGoodsEntities.get(position).isNew()) {
      holder.mImageViewIsNew.setImageResource(R.drawable.alerter_ic_face);
    } else {
      holder.mImageViewIsNew.setImageResource(R.drawable.alerter_ic_notifications);
    }

    // TODO: 18.05.2017 set mImageViewType depending mGoodsEntities.get(position).getType()

    holder.mTextViewGoodsName.setText(mGoodsEntities.get(position).getTitle());
    holder.mTextViewShortDescription.setText(mGoodsEntities.get(position).getShortDescription());
    holder.mTextViewPrice.setText(mGoodsEntities.get(position).getPrice());
  }

  @Override public int getItemCount() {
    return mGoodsEntities.size();
  }

  public GoodsEntity getEntity(int position) {
    return mGoodsEntities.get(position);
  }

  static class GoodsListViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.ivIsNew) ImageView mImageViewIsNew;
    @BindView(R.id.ivType) ImageView mImageViewType;
    @BindView(R.id.ivGoodsPhoto) ImageView mImageViewGoodsPhoto;
    @BindView(R.id.tvGoodsName) TextView mTextViewGoodsName;
    @BindView(R.id.tvShortDescription) TextView mTextViewShortDescription;
    @BindView(R.id.tvPrice) TextView mTextViewPrice;

    GoodsListViewHolder(View view) {
      super(view);
      ButterKnife.bind(this, view);
    }
  }
}
