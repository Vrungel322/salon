package com.apps.twelve.floor.salon.feature.our_works.adapters;

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
import com.apps.twelve.floor.salon.data.model.OurWorkEntity;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vrungel on 22.02.2017.
 */

public class OurWorkAdapter extends RecyclerView.Adapter<OurWorkAdapter.OurWorkViewHolder> {
  private ArrayList<OurWorkEntity> mOurWorkEntities = new ArrayList<>();

  public void addListWorkEntities(List<OurWorkEntity> ourWorkEntities) {
    mOurWorkEntities.clear();
    mOurWorkEntities.addAll(ourWorkEntities);
    notifyDataSetChanged();
  }

  @Override public OurWorkViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    return new OurWorkViewHolder(
        LayoutInflater.from(parent.getContext()).inflate(R.layout.item_our_work, parent, false));
  }

  @Override public void onBindViewHolder(OurWorkViewHolder holder, int position) {
    Picasso.with(holder.mImageViewWorkPreview.getContext())
        .load(Uri.parse(mOurWorkEntities.get(position).getImageURL()))
        .into(holder.mImageViewWorkPreview);

    holder.mTextViewShortDescription.setText(mOurWorkEntities.get(position).getTitle());

    holder.mTextViewNumOfImgToPreview.setText(
        String.valueOf(mOurWorkEntities.get(position).getImageCount()));
  }

  @Override public int getItemCount() {
    return mOurWorkEntities.size();
  }

  public OurWorkEntity getEntity(int position) {
    return mOurWorkEntities.get(position);
  }

  static class OurWorkViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.ivWorkPreview) ImageView mImageViewWorkPreview;
    @BindView(R.id.tvShortDescription) TextView mTextViewShortDescription;
    @BindView(R.id.tvNumOfImgToPreview) TextView mTextViewNumOfImgToPreview;

    OurWorkViewHolder(View view) {
      super(view);
      ButterKnife.bind(this, view);
    }
  }
}