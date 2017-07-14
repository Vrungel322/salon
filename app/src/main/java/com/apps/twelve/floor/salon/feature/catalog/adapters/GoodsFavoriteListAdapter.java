package com.apps.twelve.floor.salon.feature.catalog.adapters;

import android.content.Context;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.net.Uri;
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
import com.apps.twelve.floor.salon.data.model.GoodsEntity;
import com.bumptech.glide.Glide;
import java.util.ArrayList;
import java.util.List;

import static com.apps.twelve.floor.salon.utils.Constants.Other.SERVER_ANSWER_EMPTY_STRING;

/**
 * Created by John on 06.06.2017.
 */

public class GoodsFavoriteListAdapter
    extends RecyclerView.Adapter<GoodsFavoriteListAdapter.GoodsFavoriteListViewHolder> {

  private ArrayList<GoodsEntity> mGoodsEntities = new ArrayList<>();
  private Context mContext;

  public GoodsFavoriteListAdapter(Context context) {
    this.mContext = context;
  }

  public void addListGoodsEntity(List<GoodsEntity> goodsEntities) {
    mGoodsEntities.clear();
    mGoodsEntities.addAll(goodsEntities);
    notifyDataSetChanged();
  }

  @Override public GoodsFavoriteListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    return new GoodsFavoriteListViewHolder(
        LayoutInflater.from(parent.getContext()).inflate(R.layout.item_goods, parent, false));
  }

  @Override public void onBindViewHolder(GoodsFavoriteListViewHolder holder, int position) {
    Glide.with(holder.mImageViewGoodsPhoto.getContext())
        .load(Uri.parse(mGoodsEntities.get(position).getImageURL()))
        .placeholder(AppCompatResources.getDrawable(mContext, R.drawable.ic_catalog_placeholder))
        .error(AppCompatResources.getDrawable(mContext, R.drawable.ic_catalog_placeholder))
        .dontAnimate()
        .into(holder.mImageViewGoodsPhoto);

        /* check if good is new */
    if (mGoodsEntities.get(position).isNew()) {
      holder.mImageViewIsNew.setImageResource(R.drawable.ic_badge_new_32dp);
    } else {
      holder.mImageViewIsNew.setVisibility(View.GONE);
    }
        /* check if for sale */
    if (mGoodsEntities.get(position).isForSale()) {
      holder.mImageViewForSale.setImageResource(R.drawable.ic_badge_sale_32dp);
      holder.mTextViewOldPrice.setText(mGoodsEntities.get(position).getPrice());
      holder.mTextViewOldPrice.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
      holder.mTextViewOldPrice.setVisibility(View.VISIBLE);
      holder.mTextViewPrice.setText(mGoodsEntities.get(position).getNewPrice());
      holder.mTextViewPrice.setTypeface(null, Typeface.BOLD_ITALIC);
    } else {
      holder.mImageViewForSale.setVisibility(View.GONE);
      holder.mTextViewOldPrice.setVisibility(View.GONE);
      holder.mTextViewPrice.setText(mGoodsEntities.get(position).getPrice());
      holder.mTextViewPrice.setTypeface(null, Typeface.NORMAL);
    }

    holder.mTextViewGoodsName.setText(mGoodsEntities.get(position).getTitle());
    holder.mTextViewShortDescription.setText(mGoodsEntities.get(position).getShortDescription());
    if (mGoodsEntities.get(position).getBonusPrice().equals(SERVER_ANSWER_EMPTY_STRING)) {
      holder.mTextViewPriceBonus.setVisibility(View.GONE);
      holder.mImageViewBonusPrice.setVisibility(View.GONE);
    } else {
      holder.mTextViewPriceBonus.setText(mGoodsEntities.get(position).getBonusPrice());
    }
  }

  @Override public int getItemCount() {
    return mGoodsEntities.size();
  }

  public GoodsEntity getEntity(int position) {
    return mGoodsEntities.get(position);
  }

  static class GoodsFavoriteListViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.ivIsNew) ImageView mImageViewIsNew;
    @BindView(R.id.ivForSale) ImageView mImageViewForSale;
    @BindView(R.id.ivGoodsPhoto) ImageView mImageViewGoodsPhoto;
    @BindView(R.id.tvGoodsName) TextView mTextViewGoodsName;
    @BindView(R.id.tvShortDescription) TextView mTextViewShortDescription;
    @BindView(R.id.tvPrice) TextView mTextViewPrice;
    @BindView(R.id.tvPriceBonus) TextView mTextViewPriceBonus;
    @BindView(R.id.ivBonusPrice) ImageView mImageViewBonusPrice;
    @BindView(R.id.tvOldPrice) TextView mTextViewOldPrice;

    GoodsFavoriteListViewHolder(View view) {
      super(view);
      ButterKnife.bind(this, view);
    }
  }
}
