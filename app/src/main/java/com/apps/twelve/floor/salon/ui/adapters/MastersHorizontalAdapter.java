package com.apps.twelve.floor.salon.ui.adapters;

import android.net.Uri;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.apps.twelve.floor.salon.R;
import com.apps.twelve.floor.salon.mvp.data.model.MasterEntity;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vrungel on 28.03.2017.
 */

public class MastersHorizontalAdapter
    extends RecyclerView.Adapter<MastersHorizontalAdapter.MastersViewHolder> {
  private List<MasterEntity> mMasterEntities = new ArrayList<>();

  private int selectedItem = -1;

  public void addListMasterEntity(List<MasterEntity> masterEntities) {
    mMasterEntities.clear();
    mMasterEntities.addAll(masterEntities);
    notifyDataSetChanged();
  }

  @Override public MastersViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_master, parent, false);
    return new MastersHorizontalAdapter.MastersViewHolder(v);
  }

  @Override public void onBindViewHolder(MastersViewHolder holder, int position) {
    Picasso.with(holder.mImageViewMasterImg.getContext())
        .load(Uri.parse(mMasterEntities.get(position).getMasterImg()))
        .into(holder.mImageViewMasterImg);
    holder.mTextViewMasterName.setText(mMasterEntities.get(position).getMasterName());
    holder.mTextViewMasterDescription.setText(mMasterEntities.get(position).getMasterDescription());

    if (this.selectedItem == position) {
      holder.itemView.setBackgroundColor(
          ContextCompat.getColor(holder.itemView.getContext(), R.color.colorPrimaryDark));
      holder.mTextViewMasterName.setTextColor(
          ContextCompat.getColor(holder.mTextViewMasterName.getContext(), R.color.colorWhite));
      holder.mTextViewMasterDescription.setTextColor(
          ContextCompat.getColor(holder.mTextViewMasterDescription.getContext(),
              R.color.colorWhite));
    } else {
      holder.itemView.setBackgroundColor(
          ContextCompat.getColor(holder.itemView.getContext(), R.color.colorWhite));
      holder.mTextViewMasterName.setTextColor(
          ContextCompat.getColor(holder.mTextViewMasterName.getContext(), R.color.colorBlack));
      holder.mTextViewMasterDescription.setTextColor(
          ContextCompat.getColor(holder.mTextViewMasterDescription.getContext(),
              R.color.colorBlack));
    }
  }

  public void setSelectedItem(int position) {
    this.selectedItem = position;
    this.notifyDataSetChanged();
  }

  @Override public int getItemCount() {
    return mMasterEntities.size();
  }

  static class MastersViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.ivMasterImg) ImageView mImageViewMasterImg;
    @BindView(R.id.tvMasterName) TextView mTextViewMasterName;
    @BindView(R.id.tvMasterDescription) TextView mTextViewMasterDescription;

    MastersViewHolder(View view) {
      super(view);
      ButterKnife.bind(this, view);
    }
  }
}
