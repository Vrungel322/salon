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
import com.bumptech.glide.Glide;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vrungel on 18.05.2017.
 */

public class GoodsListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

  private static final int FAVORITE = 0;
  private static final int OTHER = 1;

  private ArrayList<GoodsEntity> mGoodsEntities = new ArrayList<>();

  public void addListGoodsEntity(List<GoodsEntity> goodsEntities) {
    mGoodsEntities.clear();
    mGoodsEntities.addAll(goodsEntities);
    notifyDataSetChanged();
  }

  @Override public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View v;
    switch (viewType) {
      case FAVORITE: {
        v = LayoutInflater.from(parent.getContext())
            .inflate(R.layout.item_goods_favorite, parent, false);
        return new GoodsListAdapter.GoodsFavoriteListViewHolder(v);
      }
      case OTHER: {
        v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_goods, parent, false);
        return new GoodsListAdapter.GoodsListViewHolder(v);
      }
    }
    return null;
  }

  @Override public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
    switch (holder.getItemViewType()) {
      case FAVORITE:
        GoodsFavoriteListViewHolder goodsFavoriteListViewHolder =
            (GoodsFavoriteListViewHolder) holder;

        Glide.with(goodsFavoriteListViewHolder.mImageViewGoodsPhoto.getContext())
            .load(Uri.parse(mGoodsEntities.get(position).getImageURL()))
            .into(goodsFavoriteListViewHolder.mImageViewGoodsPhoto);
        break;
      case OTHER:
        GoodsListViewHolder goodsListViewHolder = (GoodsListViewHolder) holder;

        Glide.with(goodsListViewHolder.mImageViewGoodsPhoto.getContext())
            .load(Uri.parse(mGoodsEntities.get(position).getImageURL()))
            .placeholder(R.drawable.ic_catalog_placeholder)
            .error(R.drawable.ic_catalog_placeholder)
            .into(goodsListViewHolder.mImageViewGoodsPhoto);

        if (mGoodsEntities.get(position).isNew()) {
          goodsListViewHolder.mImageViewIsNew.setImageResource(R.drawable.alerter_ic_face);
        } else {
          goodsListViewHolder.mImageViewIsNew.setImageResource(R.drawable.alerter_ic_notifications);
        }

        goodsListViewHolder.mTextViewGoodsName.setText(mGoodsEntities.get(position).getTitle());
        goodsListViewHolder.mTextViewShortDescription.setText(
            mGoodsEntities.get(position).getShortDescription());
        goodsListViewHolder.mTextViewPrice.setText(mGoodsEntities.get(position).getPrice());
        break;
    }
  }

  @Override public int getItemCount() {
    return mGoodsEntities.size();
  }

  @Override public int getItemViewType(int position) {
    return position == 0 ? FAVORITE : OTHER;
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

  static class GoodsFavoriteListViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.ivGoodsPhoto) ImageView mImageViewGoodsPhoto;

    GoodsFavoriteListViewHolder(View view) {
      super(view);
      ButterKnife.bind(this, view);
    }
  }
}
