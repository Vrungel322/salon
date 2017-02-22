package com.apps.twelve.floor.salon.ui.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.apps.twelve.floor.salon.R;
import com.apps.twelve.floor.salon.mvp.data.model.OurWorkEntity;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vrungel on 22.02.2017.
 */

public class OurWorkAdapter extends RecyclerView.Adapter<OurWorkAdapter.OurWorkViewHolder> {
  private ArrayList<OurWorkEntity> mOurWorkEntities = new ArrayList<>();
  private LayoutInflater inflater;
  private Context mContext;

  public OurWorkAdapter(Context context) {
    this.inflater = LayoutInflater.from(context);
    this.mContext = context;
  }

  public void updateList(List<OurWorkEntity> ourWorkEntities) {
    mOurWorkEntities.clear();
    mOurWorkEntities.addAll(ourWorkEntities);
    notifyDataSetChanged();
  }

  @Override public OurWorkViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View v = inflater.inflate(R.layout.our_work_item, parent, false);
    return new OurWorkViewHolder(v);
  }

  @Override public void onBindViewHolder(OurWorkViewHolder holder, int position) {
    Picasso.with(mContext)
        .load(mOurWorkEntities.get(position).getImageURL())
        .into(holder.mImageViewWorkPreview);

    holder.mTextViewShortDescription.setText(mOurWorkEntities.get(position).getShortDescription());

    holder.mTextViewNumOfImgToPreview.setText(
        String.valueOf(mOurWorkEntities.get(position).getImageCount()));
  }

  @Override public int getItemCount() {
    return mOurWorkEntities.size();
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
