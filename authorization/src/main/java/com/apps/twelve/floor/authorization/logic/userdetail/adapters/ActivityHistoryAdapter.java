package com.apps.twelve.floor.authorization.logic.userdetail.adapters;
/*
 * Copyright 2017, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.apps.twelve.floor.authorization.R;
import com.apps.twelve.floor.authorization.R2;
import com.apps.twelve.floor.authorization.data.model.DeviceInfoEntity;
import java.util.List;

public class ActivityHistoryAdapter
    extends RecyclerView.Adapter<ActivityHistoryAdapter.ViewHolder> {

  private List<DeviceInfoEntity> entities;

  public void setEntities(List<DeviceInfoEntity> entities) {
    this.entities = entities;
  }

  @Override public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View v = LayoutInflater.from(parent.getContext())
        .inflate(R.layout.list_item_device_activity, parent, false);
    ViewHolder vh = new ViewHolder(v);
    return vh;
  }

  @Override public void onBindViewHolder(ViewHolder holder, int position) {
    DeviceInfoEntity deviceInfoEntity = entities.get(position);

    holder.mDeviceName.setText(deviceInfoEntity.getName());
    holder.mOsVersion.setText(deviceInfoEntity.getOs());
    holder.mActivityDate.setText(deviceInfoEntity.getUpdatedAt());
  }

  @Override public int getItemCount() {
    return entities.size();
  }

  public static class ViewHolder extends RecyclerView.ViewHolder {

    @BindView(R2.id.device_name) TextView mDeviceName;
    @BindView(R2.id.os_version) TextView mOsVersion;
    @BindView(R2.id.activity_date) TextView mActivityDate;

    public ViewHolder(View itemView) {
      super(itemView);
      ButterKnife.bind(this, itemView);
    }
  }
}
