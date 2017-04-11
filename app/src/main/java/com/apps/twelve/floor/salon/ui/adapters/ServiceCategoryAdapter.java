package com.apps.twelve.floor.salon.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.apps.twelve.floor.salon.R;
import com.apps.twelve.floor.salon.mvp.data.model.CategoryEntity;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by choiintack on 2017. 1. 31..
 */

public class ServiceCategoryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
  private List<CategoryEntity> mServiceEntities = new ArrayList<>();

  public void setData(List<CategoryEntity> data) {
    mServiceEntities.clear();
    mServiceEntities.addAll(data);
    notifyDataSetChanged();
  }

  @Override public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view;
    view = LayoutInflater.from(parent.getContext())
        .inflate(R.layout.item_service_parent_recurcive, parent, false);
    return new ParentViewHolder(view);
  }

  @Override public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
    ParentViewHolder currentHolder = (ParentViewHolder) holder;
    currentHolder.mTextViewServiceTitle.setText(mServiceEntities.get(position).getTitle());
  }

  @Override public int getItemCount() {
    return mServiceEntities.size();
  }

  public CategoryEntity getItem(int position) {
    return mServiceEntities.get(position);
  }

  class ParentViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.tvServiceTitle) TextView mTextViewServiceTitle;

    public ParentViewHolder(View itemView) {
      super(itemView);
      ButterKnife.bind(this, itemView);
    }
  }
}
