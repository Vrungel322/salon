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
import com.apps.twelve.floor.salon.mvp.data.model.WorkStartEndEntity;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vrungel on 27.03.2017.
 */

public class DatesHorizontalAdapter
    extends RecyclerView.Adapter<DatesHorizontalAdapter.DatesViewHolder> {
  private List<WorkStartEndEntity> mWorkStartEndEntityList = new ArrayList<>();

  private int selectedItem = -1;

  public void addListWorkStartEndEntity(List<WorkStartEndEntity> workStartEndEntities) {
    mWorkStartEndEntityList.clear();
    mWorkStartEndEntityList.addAll(workStartEndEntities);
    notifyDataSetChanged();
  }

  @Override public DatesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View v = LayoutInflater.from(parent.getContext())
        .inflate(R.layout.item_date_start_end_hour, parent, false);
    return new DatesHorizontalAdapter.DatesViewHolder(v);
  }

  @Override public void onBindViewHolder(DatesViewHolder holder, int position) {
    holder.mTextViewDate.setText(mWorkStartEndEntityList.get(position).getDate());
    holder.mTextViewStartTime.setText(mWorkStartEndEntityList.get(position).getStartDate());
    holder.mTextViewEndTime.setText(mWorkStartEndEntityList.get(position).getEndDate());

    if (this.selectedItem == position) {
      holder.mLinearLayoutParent.setBackgroundColor(
          ContextCompat.getColor(holder.mLinearLayoutParent.getContext(),
              R.color.colorPrimaryDark));
      holder.mViewLine.setBackgroundColor(
          ContextCompat.getColor(holder.mViewLine.getContext(), R.color.colorWhite));
      holder.mTextViewDate.setTextColor(
          ContextCompat.getColor(holder.mTextViewDate.getContext(), R.color.colorWhite));
      holder.mTextViewStartTime.setTextColor(
          ContextCompat.getColor(holder.mTextViewStartTime.getContext(), R.color.colorWhite));
      holder.mTextViewEndTime.setTextColor(
          ContextCompat.getColor(holder.mTextViewEndTime.getContext(), R.color.colorWhite));
    } else {
      holder.mLinearLayoutParent.setBackgroundColor(
          ContextCompat.getColor(holder.mLinearLayoutParent.getContext(), R.color.colorWhite));
      holder.mViewLine.setBackgroundColor(
          ContextCompat.getColor(holder.mViewLine.getContext(), R.color.colorPrimaryDark));
      holder.mTextViewDate.setTextColor(
          ContextCompat.getColor(holder.mTextViewDate.getContext(), R.color.colorBlack));
      holder.mTextViewStartTime.setTextColor(
          ContextCompat.getColor(holder.mTextViewStartTime.getContext(), R.color.colorBlack));
      holder.mTextViewEndTime.setTextColor(
          ContextCompat.getColor(holder.mTextViewEndTime.getContext(), R.color.colorBlack));
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
