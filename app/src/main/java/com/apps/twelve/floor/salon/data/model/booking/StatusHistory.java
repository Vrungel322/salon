package com.apps.twelve.floor.salon.data.model.booking;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Vrungel on 16.08.2017.
 */

public class StatusHistory extends RealmObject {

  @PrimaryKey private Integer id;
  @SerializedName("status") @Expose private String status;
  @SerializedName("updated_at") @Expose private String updatedAt;

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getUpdatedAt() {
    return updatedAt;
  }

  public void setUpdatedAt(String updatedAt) {
    this.updatedAt = updatedAt;
  }
}
