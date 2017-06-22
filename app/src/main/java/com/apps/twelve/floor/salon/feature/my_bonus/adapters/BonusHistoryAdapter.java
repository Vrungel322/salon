package com.apps.twelve.floor.salon.feature.my_bonus.adapters;

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
import com.apps.twelve.floor.salon.data.model.BonusHistoryEntity;
import com.bumptech.glide.Glide;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alexandra on 22.06.2017.
 */

public class BonusHistoryAdapter
    extends RecyclerView.Adapter<BonusHistoryAdapter.BonusHistoryViewHolder> {

  private List<BonusHistoryEntity> mBonusHistoryEntities = new ArrayList<>();

  private Context mContext;

  public BonusHistoryAdapter(Context context) {
    this.mContext = context;
  }

  public void addBonusHistoryList(List<BonusHistoryEntity> bonusHistoryEntities) {
    mBonusHistoryEntities.clear();
    mBonusHistoryEntities.addAll(bonusHistoryEntities);
    notifyDataSetChanged();
  }

  @Override public BonusHistoryAdapter.BonusHistoryViewHolder onCreateViewHolder(ViewGroup parent,
      int viewType) {
    View v = LayoutInflater.from(parent.getContext())
        .inflate(R.layout.item_bonus_history, parent, false);
    return new BonusHistoryAdapter.BonusHistoryViewHolder(v);
  }

  @Override
  public void onBindViewHolder(BonusHistoryAdapter.BonusHistoryViewHolder holder, int position) {
    Glide.with(mContext)
        .load("")
        .placeholder(
            mBonusHistoryEntities.get(position).getPoints() > 0 ? AppCompatResources.getDrawable(
                mContext, R.drawable.ic_bonus_history_in_24dp)
                : AppCompatResources.getDrawable(mContext, R.drawable.ic_bonus_history_out_24dp))
        .into(holder.mImageViewHistoryItem);

    holder.mTextViewServiceName.setText(mBonusHistoryEntities.get(position).getBonusDescription());
    holder.mTextViewBonusCost.setText(
        String.valueOf(mBonusHistoryEntities.get(position).getPoints()));
    holder.mTransTime.setText(mBonusHistoryEntities.get(position).getCreatedAt());
  }

  @Override public int getItemCount() {
    return mBonusHistoryEntities.size();
  }

  static class BonusHistoryViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.ivHistoryItem) ImageView mImageViewHistoryItem;
    @BindView(R.id.tvServiceName) TextView mTextViewServiceName;
    @BindView(R.id.tvBonusCost) TextView mTextViewBonusCost;
    @BindView(R.id.tvTransactionTime) TextView mTransTime;

    BonusHistoryViewHolder(View view) {
      super(view);
      ButterKnife.bind(this, view);
    }
  }
}
