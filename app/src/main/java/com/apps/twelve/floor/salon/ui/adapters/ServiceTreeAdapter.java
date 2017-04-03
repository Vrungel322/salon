package com.apps.twelve.floor.salon.ui.adapters;

import android.support.constraint.ConstraintLayout;
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
import com.apps.twelve.floor.salon.mvp.data.model.service_tree_item.Child_1;
import com.apps.twelve.floor.salon.mvp.data.model.service_tree_item.Child_2;
import com.apps.twelve.floor.salon.mvp.data.model.service_tree_item.Child_3;
import com.apps.twelve.floor.salon.mvp.data.model.service_tree_item.ParentService;
import com.choiintack.recursiverecyclerview.RecursiveRecyclerAdapter;
import java.util.List;

/**
 * Created by choiintack on 2017. 1. 31..
 */

public class ServiceTreeAdapter extends RecursiveRecyclerAdapter<RecyclerView.ViewHolder> {


  public void setData(List<ParentService> data) {
    setItems(data);
  }

  @Override public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view;
    if (viewType != 1) {
      view = LayoutInflater.from(parent.getContext())
          .inflate(R.layout.item_service_parent_recurcive, parent, false);
      return new ParentViewHolder(view);
    } else {
      view = LayoutInflater.from(parent.getContext())
          .inflate(R.layout.item_service_child_recurcive, parent, false);
      return new ChildViewHolder(view);
    }
  }

  @Override public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
    super.onBindViewHolder(holder, position);

    if (getItem(position) instanceof ParentService) {
      //service service_id = 4
      ParentViewHolder currentHolder = (ParentViewHolder) holder;
      currentHolder.sampleTextView.setText(((ParentService) getItem(position)).getTitle());
    }
    if (getItem(position) instanceof Child_1) {
      //service service_id = 3
      ParentViewHolder currentHolder = (ParentViewHolder) holder;
      currentHolder.sampleTextView.setText(((Child_1) getItem(position)).getTitle());
    }
    if (getItem(position) instanceof Child_2) {
      //service service_id = 2
      ParentViewHolder currentHolder = (ParentViewHolder) holder;
      currentHolder.sampleTextView.setText(((Child_2) getItem(position)).getTitle());
    }
    if (getItem(position) instanceof Child_3) {
      //service service_id = 1 - final
      ChildViewHolder currentHolder = (ChildViewHolder) holder;
      currentHolder.mTextViewServiceName.setText(((Child_3) getItem(position)).getTitle());
      currentHolder.mTextViewServicePrice.setText(((Child_3) getItem(position)).getPrice());
    }
  }

  @Override public int getItemViewType(int position) {
    if (getItem(position) instanceof ParentService) {
      //service service_id = 4
      return 4;
    }
    if (getItem(position) instanceof Child_1) {
      //service service_id = 3
      return 3;
    }
    if (getItem(position) instanceof Child_2) {
      //service service_id = 2
      return 2;
    }
    if (getItem(position) instanceof Child_3) {
      //service service_id = 1 - final
      return 1;
    }
    return 0;
  }

  class ParentViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.sample_text) TextView sampleTextView;

    public ParentViewHolder(View itemView) {
      super(itemView);
      ButterKnife.bind(this, itemView);
    }
  }

  class ChildViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.clParent) ConstraintLayout mConstraintLayoutParent;
    @BindView(R.id.ivServiceImg) ImageView mImageViewServiceImg;
    @BindView(R.id.tvServiceName) TextView mTextViewServiceName;
    @BindView(R.id.tvAboutService) TextView mTextViewAboutService;
    @BindView(R.id.tvServicePrice) TextView mTextViewServicePrice;
    @BindView(R.id.tvServiceDuration) TextView mTextViewSServiceDuration;

    public ChildViewHolder(View itemView) {
      super(itemView);
      ButterKnife.bind(this, itemView);
    }
  }
}
