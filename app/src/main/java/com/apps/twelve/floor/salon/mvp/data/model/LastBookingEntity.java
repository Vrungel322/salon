package com.apps.twelve.floor.salon.mvp.data.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Vrungel on 28.02.2017.
 */

public class LastBookingEntity implements Parcelable {
  private String imageUri;
  private String serviceName;
  private String serviceTime;

  public LastBookingEntity(String imageUri, String serviceName, String serviceTime) {
    this.imageUri = imageUri;
    this.serviceName = serviceName;
    this.serviceTime = serviceTime;
  }

  protected LastBookingEntity(Parcel in) {
    imageUri = in.readString();
    serviceName = in.readString();
    serviceTime = in.readString();
  }

  public static final Creator<LastBookingEntity> CREATOR = new Creator<LastBookingEntity>() {
    @Override public LastBookingEntity createFromParcel(Parcel in) {
      return new LastBookingEntity(in);
    }

    @Override public LastBookingEntity[] newArray(int size) {
      return new LastBookingEntity[size];
    }
  };

  public String getImageUri() {
    return imageUri;
  }

  public void setImageUri(String imageUri) {
    this.imageUri = imageUri;
  }

  public String getServiceName() {
    return serviceName;
  }

  public void setServiceName(String serviceName) {
    this.serviceName = serviceName;
  }

  public String getServiceTime() {
    return serviceTime;
  }

  public void setServiceTime(String serviceTime) {
    this.serviceTime = serviceTime;
  }

  @Override public int describeContents() {
    return 0;
  }

  @Override public void writeToParcel(Parcel dest, int flags) {
    dest.writeString(imageUri);
    dest.writeString(serviceName);
    dest.writeString(serviceTime);
  }
}
