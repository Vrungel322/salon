package com.apps.twelve.floor.salon.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.ButterKnife;
import com.apps.twelve.floor.salon.R;
import com.apps.twelve.floor.salon.mvp.data.model.LastBookingEntity;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vrungel on 28.02.2017.
 */

public class MyLastBookingAdapter
    extends RecyclerView.Adapter<MyLastBookingAdapter.MyLastBookingViewHolder> {
  private ArrayList<LastBookingEntity> mLastBookingEntities = new ArrayList<>();

  public void addListLastBookingEntity(List<LastBookingEntity> lastBookingEntities) {
    mLastBookingEntities.addAll(lastBookingEntities);
    notifyDataSetChanged();
  }

  @Override public MyLastBookingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    return new MyLastBookingAdapter.MyLastBookingViewHolder(LayoutInflater.from(parent.getContext())
        .inflate(R.layout.item_my_last_booking, parent, false));
  }

  @Override public void onBindViewHolder(MyLastBookingViewHolder holder, int position) {

  }

  @Override public int getItemCount() {
    return mLastBookingEntities.size();
  }

  static class MyLastBookingViewHolder extends RecyclerView.ViewHolder {

    MyLastBookingViewHolder(View view) {
      super(view);
      ButterKnife.bind(this, view);
    }
  }
}
