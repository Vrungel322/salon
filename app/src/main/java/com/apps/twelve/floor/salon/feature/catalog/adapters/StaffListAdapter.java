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
import com.apps.twelve.floor.salon.data.model.StaffEntity;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vrungel on 18.05.2017.
 */

public class StaffListAdapter extends RecyclerView.Adapter<StaffListAdapter.StaffListViewHolder> {
  private ArrayList<StaffEntity> mStaffEntities = new ArrayList<>();

  public void addListStaffEntity(List<StaffEntity> staffEntities) {
    mStaffEntities.clear();
    mStaffEntities.addAll(staffEntities);
    notifyDataSetChanged();
  }

  @Override public StaffListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    return new StaffListAdapter.StaffListViewHolder(
        LayoutInflater.from(parent.getContext()).inflate(R.layout.item_staff, parent, false));
  }

  @Override public void onBindViewHolder(StaffListViewHolder holder, int position) {
    Picasso.with(holder.mImageViewStaffPhoto.getContext())
        .load(Uri.parse(mStaffEntities.get(position).getImageURL()))
        .into(holder.mImageViewStaffPhoto);

    if (mStaffEntities.get(position).isNew()) {
      holder.mImageViewIsNew.setImageResource(R.drawable.alerter_ic_face);
    } else {
      holder.mImageViewIsNew.setImageResource(R.drawable.alerter_ic_notifications);
    }

    // TODO: 18.05.2017 set mImageViewType depending mStaffEntities.get(position).getType()

    holder.mTextViewStaffName.setText(mStaffEntities.get(position).getTitle());
    holder.mTextViewShortDescription.setText(mStaffEntities.get(position).getShortDescription());
    holder.mTextViewPrice.setText(mStaffEntities.get(position).getPrice());
  }

  @Override public int getItemCount() {
    return mStaffEntities.size();
  }

  public StaffEntity getEntity(int position) {
    return mStaffEntities.get(position);
  }

  static class StaffListViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.ivIsNew) ImageView mImageViewIsNew;
    @BindView(R.id.ivType) ImageView mImageViewType;
    @BindView(R.id.ivStaffPhoto) ImageView mImageViewStaffPhoto;
    @BindView(R.id.tvStaffName) TextView mTextViewStaffName;
    @BindView(R.id.tvShortDescription) TextView mTextViewShortDescription;
    @BindView(R.id.tvPrice) TextView mTextViewPrice;

    StaffListViewHolder(View view) {
      super(view);
      ButterKnife.bind(this, view);
    }
  }
}
