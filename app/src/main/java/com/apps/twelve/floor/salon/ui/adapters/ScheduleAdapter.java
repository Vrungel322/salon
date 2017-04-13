package com.apps.twelve.floor.salon.ui.adapters;

import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.apps.twelve.floor.salon.R;
import com.apps.twelve.floor.salon.mvp.data.model.DataServiceEntity;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vrungel on 27.03.2017.
 */

public class ScheduleAdapter extends RecyclerView.Adapter<ScheduleAdapter.ScheduleViewHolder> {
  private List<DataServiceEntity.ScheduleEntity> mTimes = new ArrayList<>();
  private int selectedItem = -1;

  public void setTimeSchedule(List<DataServiceEntity.ScheduleEntity> times) {
    selectedItem = -1;
    mTimes.clear();
    mTimes.addAll(times);
    notifyDataSetChanged();
  }

  @Override public ScheduleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_time, parent, false);
    return new ScheduleAdapter.ScheduleViewHolder(v);
  }

  @Override public void onBindViewHolder(ScheduleViewHolder holder, int position) {
    holder.mTextViewTime.setText(mTimes.get(position).getTime());

    if (mTimes.get(position).getStatus()) {
      if (this.selectedItem == position) {
        holder.mLinearLayoutParent.setBackgroundColor(
            ContextCompat.getColor(holder.mLinearLayoutParent.getContext(),
                R.color.colorChooseDateChosenTimeBackground));
        holder.mTextViewTime.setTextColor(
            ContextCompat.getColor(holder.mLinearLayoutParent.getContext(),
                R.color.colorChooseDateChosenTimeText));
      } else {
        holder.mLinearLayoutParent.setBackgroundColor(
            ContextCompat.getColor(holder.mLinearLayoutParent.getContext(),
                R.color.colorChooseDateNotChosenTimeBackground));
        holder.mTextViewTime.setTextColor(
            ContextCompat.getColor(holder.mLinearLayoutParent.getContext(),
                R.color.colorChooseDateNotChosenTimeText));
      }
    } else {
      holder.mLinearLayoutParent.setBackgroundColor(
          ContextCompat.getColor(holder.mLinearLayoutParent.getContext(),
              R.color.colorLLLightGray));
    }
  }

  public void setSelectedItem(int position) {
    this.selectedItem = position;
    this.notifyDataSetChanged();
  }

  @Override public int getItemCount() {
    return mTimes.size();
  }

  static class ScheduleViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.tvTime) TextView mTextViewTime;
    @BindView(R.id.llTimeParent) LinearLayout mLinearLayoutParent;

    ScheduleViewHolder(View view) {
      super(view);
      ButterKnife.bind(this, view);
    }
  }
}
