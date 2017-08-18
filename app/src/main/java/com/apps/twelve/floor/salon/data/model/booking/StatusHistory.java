package com.apps.twelve.floor.salon.data.model.booking;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Vrungel on 16.08.2017.
 */

public class StatusHistory extends RealmObject implements Parcelable {

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

  @Override public int describeContents() {
    return 0;
  }

  @Override public void writeToParcel(Parcel dest, int flags) {
    dest.writeValue(this.id);
    dest.writeString(this.status);
    dest.writeString(this.updatedAt);
  }

  public StatusHistory() {
  }

  protected StatusHistory(Parcel in) {
    this.id = (Integer) in.readValue(Integer.class.getClassLoader());
    this.status = in.readString();
    this.updatedAt = in.readString();
  }

  public static final Parcelable.Creator<StatusHistory> CREATOR =
      new Parcelable.Creator<StatusHistory>() {
        @Override public StatusHistory createFromParcel(Parcel source) {
          return new StatusHistory(source);
        }

        @Override public StatusHistory[] newArray(int size) {
          return new StatusHistory[size];
        }
      };
}
