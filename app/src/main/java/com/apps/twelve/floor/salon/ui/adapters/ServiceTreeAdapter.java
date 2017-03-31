package com.apps.twelve.floor.salon.ui.adapters;

import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.apps.twelve.floor.salon.R;
import com.apps.twelve.floor.salon.mvp.data.model.service_tree_item.Child_1;
import com.apps.twelve.floor.salon.mvp.data.model.service_tree_item.Child_2;
import com.apps.twelve.floor.salon.mvp.data.model.service_tree_item.Child_3;
import com.apps.twelve.floor.salon.mvp.data.model.service_tree_item.ParentService;
import com.choiintack.recursiverecyclerview.RecursiveRecyclerAdapter;
import java.util.List;
import timber.log.Timber;

/**
 * Created by choiintack on 2017. 1. 31..
 */

public class ServiceTreeAdapter extends RecursiveRecyclerAdapter<ServiceTreeAdapter.ViewHolder> {

  private List<ParentService> mData;

  public void setData(List<ParentService> data) {
    setItems(data);
    mData = data;
  }

  @Override
  public ServiceTreeAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view;
    //if (viewType != 1) {
    //  Timber.e(String.valueOf(viewType));
    //  view =
    //      LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recurcive, parent, false);
    //} else {
    //  view =
    //      LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recurcive, parent, false);
    //  view.setBackgroundColor(
    //      ContextCompat.getColor(view.getContext(), R.color.colorPinkTransparent));
    //}

    view =
        LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recurcive, parent, false);
    return new ViewHolder(view);
  }

  @Override public void onBindViewHolder(ServiceTreeAdapter.ViewHolder holder, int position) {
    super.onBindViewHolder(holder, position);
    Timber.e(String.valueOf(position));

    if (getItem(position) instanceof ParentService) {
      //service service_id = 4
      holder.sampleTextView.setText(((ParentService) getItem(position)).getTitle());
    }
    if (getItem(position) instanceof Child_1) {
      //service service_id = 3
      holder.sampleTextView.setText(((Child_1) getItem(position)).getTitle());
    }
    if (getItem(position) instanceof Child_2) {
      //service service_id = 2
      holder.sampleTextView.setText(((Child_2) getItem(position)).getTitle());
    }
    if (getItem(position) instanceof Child_3) {
      //service service_id = 1 - final
      holder.sampleTextView.setText(((Child_3) getItem(position)).getTitle());
    }
  }

  //@Override public int getItemViewType(int position) {
  //  if (getItem(position) instanceof ParentService) {
  //    //service service_id = 4
  //    return 4;
  //  }
  //  if (getItem(position) instanceof Child_1) {
  //    //service service_id = 3
  //    return 3;
  //  }
  //  if (getItem(position) instanceof Child_2) {
  //    //service service_id = 2
  //    return 2;
  //  }
  //  if (getItem(position) instanceof Child_3) {
  //    //service service_id = 1 - final
  //    return 1;
  //  }
  //  return 0;
  //}

  class ViewHolder extends RecyclerView.ViewHolder {

    TextView sampleTextView;

    public ViewHolder(View itemView) {
      super(itemView);
      sampleTextView = (TextView) itemView.findViewById(R.id.sample_text);
    }
  }
}
