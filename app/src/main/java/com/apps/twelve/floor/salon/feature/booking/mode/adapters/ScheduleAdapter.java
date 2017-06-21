package com.apps.twelve.floor.salon.feature.booking.mode.adapters;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.apps.twelve.floor.salon.R;
import com.apps.twelve.floor.salon.data.model.DataServiceEntity;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vrungel on 27.03.2017.
 */

public class ScheduleAdapter extends RecyclerView.Adapter<ScheduleAdapter.ScheduleViewHolder> {
  private List<DataServiceEntity.ScheduleEntity> mTimes = new ArrayList<>();
  private int selectedItem = -1;

  private Context mContext;

  public ScheduleAdapter(Context context) {
    this.mContext = context;
  }

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
        TypedValue value = new TypedValue();
        mContext.getTheme().resolveAttribute(R.attr.colorAccent, value, true);
        holder.mLinearLayoutParent.setBackgroundColor(
            ContextCompat.getColor(holder.mLinearLayoutParent.getContext(), value.resourceId));
        holder.mTextViewTime.setTextColor(
            ContextCompat.getColor(holder.mLinearLayoutParent.getContext(), R.color.colorWhite));
      } else {
        holder.mLinearLayoutParent.setBackgroundColor(
            ContextCompat.getColor(holder.mLinearLayoutParent.getContext(), R.color.colorWhite));
        holder.mTextViewTime.setTextColor(
            ContextCompat.getColor(holder.mLinearLayoutParent.getContext(), R.color.colorGray));
      }
    } else {
      holder.mLinearLayoutParent.setBackgroundColor(
          ContextCompat.getColor(holder.mLinearLayoutParent.getContext(),
              R.color.colorTransparentGray));
      holder.mTextViewTime.setTextColor(
          ContextCompat.getColor(holder.mLinearLayoutParent.getContext(), R.color.colorLightGray));
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
