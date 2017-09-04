package com.apps.twelve.floor.salon.feature.catalog.adapters;

import android.content.Context;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.net.Uri;
import android.support.v7.content.res.AppCompatResources;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
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

import static com.apps.twelve.floor.salon.utils.Constants.Other.SERVER_ANSWER_EMPTY_STRING;

/**
 * Created by Vrungel on 18.05.2017.
 */

public class GoodsListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

  private static final int FAVORITE = 0;
  private static final int OTHER = 1;

  private ArrayList<GoodsEntity> mGoodsEntities = new ArrayList<>();

  private Context mContext;

  public GoodsListAdapter(Context context) {
    this.mContext = context;
  }

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

        goodsFavoriteListViewHolder.mImageViewGoodsPhoto.setPadding(50, 50, 50, 50);
        Glide.with(goodsFavoriteListViewHolder.mImageViewGoodsPhoto.getContext())
            .load(Uri.parse(mGoodsEntities.get(position).getImageURL()))
            .placeholder(
                AppCompatResources.getDrawable(mContext, R.drawable.ic_favorite_catalog_32dp))
            .error(AppCompatResources.getDrawable(mContext, R.drawable.ic_favorite_catalog_32dp))
            .dontAnimate()
            .into(goodsFavoriteListViewHolder.mImageViewGoodsPhoto);
        break;
      case OTHER:
        GoodsListViewHolder goodsListViewHolder = (GoodsListViewHolder) holder;

        Glide.with(goodsListViewHolder.mImageViewGoodsPhoto.getContext())
            .load(Uri.parse(mGoodsEntities.get(position).getImageURL()))
            .placeholder(
                AppCompatResources.getDrawable(mContext, R.drawable.ic_catalog_placeholder))
            .error(AppCompatResources.getDrawable(mContext, R.drawable.ic_catalog_placeholder))
            .dontAnimate()
            .into(goodsListViewHolder.mImageViewGoodsPhoto);

        /* check if good is new */
        if (mGoodsEntities.get(position).isNew()) {
          goodsListViewHolder.mImageViewIsNew.setImageResource(R.drawable.ic_badge_new_32dp);
        } else {
          goodsListViewHolder.mImageViewIsNew.setVisibility(View.GONE);
        }
        /* check if for sale */
        if (mGoodsEntities.get(position).isForSale()) {
          goodsListViewHolder.mImageViewForSale.setImageResource(R.drawable.ic_badge_sale_32dp);
          goodsListViewHolder.mTextViewOldPrice.setText(mGoodsEntities.get(position).getPrice());
          goodsListViewHolder.mTextViewOldPrice.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
          goodsListViewHolder.mTextViewOldPrice.setVisibility(View.VISIBLE);
          goodsListViewHolder.mTextViewPrice.setText(mGoodsEntities.get(position).getNewPrice());
          goodsListViewHolder.mTextViewPrice.setTypeface(null, Typeface.BOLD_ITALIC);
          goodsListViewHolder.mTextViewShortDescription.setMaxLines(1);
        } else {
          goodsListViewHolder.mImageViewForSale.setVisibility(View.GONE);
          goodsListViewHolder.mTextViewOldPrice.setVisibility(View.GONE);
          goodsListViewHolder.mTextViewPrice.setText(mGoodsEntities.get(position).getPrice());
          goodsListViewHolder.mTextViewPrice.setTypeface(null, Typeface.NORMAL);
          goodsListViewHolder.mTextViewShortDescription.setMaxLines(2);
        }

        goodsListViewHolder.mTextViewGoodsName.setText(mGoodsEntities.get(position).getTitle());
        goodsListViewHolder.mTextViewShortDescription.setText(
            Html.fromHtml(mGoodsEntities.get(position).getShortDescription()));
        if (mGoodsEntities.get(position).getBonusPrice().equals(SERVER_ANSWER_EMPTY_STRING)) {
          goodsListViewHolder.mTextViewPriceBonus.setVisibility(View.GONE);
          goodsListViewHolder.mImageViewBonusPrice.setVisibility(View.GONE);
        } else {
          goodsListViewHolder.mTextViewPriceBonus.setText(
              mGoodsEntities.get(position).getBonusPrice());
        }
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
    @BindView(R.id.ivForSale) ImageView mImageViewForSale;
    @BindView(R.id.ivGoodsPhoto) ImageView mImageViewGoodsPhoto;
    @BindView(R.id.tvGoodsName) TextView mTextViewGoodsName;
    @BindView(R.id.tvShortDescription) TextView mTextViewShortDescription;
    @BindView(R.id.tvPrice) TextView mTextViewPrice;
    @BindView(R.id.tvPriceBonus) TextView mTextViewPriceBonus;
    @BindView(R.id.ivBonusPrice) ImageView mImageViewBonusPrice;
    @BindView(R.id.tvOldPrice) TextView mTextViewOldPrice;

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
