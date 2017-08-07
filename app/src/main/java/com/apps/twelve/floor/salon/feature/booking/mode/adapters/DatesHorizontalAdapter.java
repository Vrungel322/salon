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
import com.apps.twelve.floor.salon.utils.Converters;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vrungel on 27.03.2017.
 */

public class DatesHorizontalAdapter
    extends RecyclerView.Adapter<DatesHorizontalAdapter.DatesViewHolder> {
  private List<DataServiceEntity> mWorkStartEndEntityList = new ArrayList<>();

  private int selectedItem = -1;
  private int mSelectedDay = -1;
  private Context mContext;

  public DatesHorizontalAdapter(Context context) {
    this.mContext = context;
  }

  public void addListWorkStartEndEntity(List<DataServiceEntity> workStartEndEntities) {
    mWorkStartEndEntityList.clear();
    mWorkStartEndEntityList.addAll(workStartEndEntities);
    notifyDataSetChanged();
  }

  public void addListWorkStartEndEntity(List<DataServiceEntity> workStartEndEntities,
      int selectedDay) {
    mWorkStartEndEntityList.clear();
    mWorkStartEndEntityList.addAll(workStartEndEntities);
    mSelectedDay = selectedDay;
    notifyDataSetChanged();
  }

  @Override public DatesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View v = LayoutInflater.from(parent.getContext())
        .inflate(R.layout.item_date_start_end_hour, parent, false);
    return new DatesHorizontalAdapter.DatesViewHolder(v);
  }

  @Override public void onBindViewHolder(DatesViewHolder holder, int position) {
    holder.mTextViewDate.setText(Converters.dayFromSeconds(mContext,
        mWorkStartEndEntityList.get(position).getStartTime().toString()));
    holder.mTextViewStartTime.setText(Converters.timeFromSeconds(mContext,
        mWorkStartEndEntityList.get(position).getStartTime().toString()));
    holder.mTextViewEndTime.setText(Converters.timeFromSeconds(mContext,
        mWorkStartEndEntityList.get(position).getEndTime().toString()));

    TypedValue value = new TypedValue();
    mContext.getTheme().resolveAttribute(R.attr.colorAccent, value, true);

    if (this.selectedItem == position) {
      holder.mLinearLayoutParent.setBackgroundColor(
          ContextCompat.getColor(holder.mLinearLayoutParent.getContext(), value.resourceId));
      holder.mViewLine.setBackgroundColor(
          ContextCompat.getColor(holder.mViewLine.getContext(), R.color.colorWhite));
      holder.mTextViewDate.setTextColor(
          ContextCompat.getColor(holder.mTextViewDate.getContext(), R.color.colorWhite));
      holder.mTextViewStartTime.setTextColor(
          ContextCompat.getColor(holder.mTextViewStartTime.getContext(), R.color.colorWhite));
      holder.mTextViewEndTime.setTextColor(
          ContextCompat.getColor(holder.mTextViewEndTime.getContext(), R.color.colorWhite));
    } else if (this.mSelectedDay == position) {
      holder.mLinearLayoutParent.setBackground(
          ContextCompat.getDrawable(holder.mLinearLayoutParent.getContext(),
              R.drawable.selected_item_background));
      holder.mViewLine.setBackgroundColor(
          ContextCompat.getColor(holder.mViewLine.getContext(), value.resourceId));
      holder.mTextViewDate.setTextColor(
          ContextCompat.getColor(holder.mTextViewDate.getContext(), R.color.colorGray));
      holder.mTextViewStartTime.setTextColor(
          ContextCompat.getColor(holder.mTextViewStartTime.getContext(), R.color.colorGray));
      holder.mTextViewEndTime.setTextColor(
          ContextCompat.getColor(holder.mTextViewEndTime.getContext(), R.color.colorGray));
    } else {
      holder.mLinearLayoutParent.setBackgroundColor(
          ContextCompat.getColor(holder.mLinearLayoutParent.getContext(), R.color.colorWhite));
      holder.mViewLine.setBackgroundColor(
          ContextCompat.getColor(holder.mViewLine.getContext(), value.resourceId));
      holder.mTextViewDate.setTextColor(
          ContextCompat.getColor(holder.mTextViewDate.getContext(), R.color.colorGray));
      holder.mTextViewStartTime.setTextColor(
          ContextCompat.getColor(holder.mTextViewStartTime.getContext(), R.color.colorGray));
      holder.mTextViewEndTime.setTextColor(
          ContextCompat.getColor(holder.mTextViewEndTime.getContext(), R.color.colorGray));
    }
  }

  @Override public int getItemCount() {
    return mWorkStartEndEntityList.size();
  }

  public void setSelectedItem(int position) {
    this.selectedItem = position;
    this.notifyDataSetChanged();
  }

  static class DatesViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.tvDate) TextView mTextViewDate;
    @BindView(R.id.tvStartTime) TextView mTextViewStartTime;
    @BindView(R.id.tvEndTime) TextView mTextViewEndTime;
    @BindView(R.id.llParent) LinearLayout mLinearLayoutParent;
    @BindView(R.id.vLine) View mViewLine;

    DatesViewHolder(View view) {
      super(view);
      ButterKnife.bind(this, view);
    }
  }
}
