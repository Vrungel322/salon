package com.apps.twelve.floor.salon.data.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Vrungel on 28.02.2017.
 */

public class LastBookingEntity implements Parcelable {
  @SuppressWarnings({
      "unchecked"
  }) public static final Creator<LastBookingEntity> CREATOR = new Creator<LastBookingEntity>() {
    @Override public LastBookingEntity createFromParcel(Parcel in) {
      return new LastBookingEntity(in);
    }

    @Override public LastBookingEntity[] newArray(int size) {
      return new LastBookingEntity[size];
    }
  };
  @SerializedName("id") private Integer id;
  @SerializedName("phone") private String userPhone;
  @SerializedName("name") private String userName;
  @SerializedName("master") private String masterName;
  @SerializedName("service_image") private String imageUri;
  @SerializedName("schedule") private Integer serviceTime;
  @SerializedName("service_title") private String serviceName;
  @SerializedName("service_id") private Integer serviceId;
  @SerializedName("master_id") private Integer masterId;

  public LastBookingEntity(Integer id, String userPhone, String userName, String masterName,
      String imageUri, Integer serviceTime, String serviceName, Integer serviceId,
      Integer masterId) {
    this.id = id;
    this.userPhone = userPhone;
    this.userName = userName;
    this.masterName = masterName;
    this.imageUri = imageUri;
    this.serviceTime = serviceTime;
    this.serviceName = serviceName;
    this.serviceId = serviceId;
    this.masterId = masterId;
  }

  protected LastBookingEntity(Parcel in) {
    userPhone = in.readString();
    userName = in.readString();
    masterName = in.readString();
    imageUri = in.readString();
    serviceName = in.readString();
  }

  @Override public void writeToParcel(Parcel dest, int flags) {
    dest.writeString(userPhone);
    dest.writeString(userName);
    dest.writeString(masterName);
    dest.writeString(imageUri);
    dest.writeString(serviceName);
  }

  @Override public int describeContents() {
    return 0;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getUserPhone() {
    return userPhone;
  }

  public void setUserPhone(String userPhone) {
    this.userPhone = userPhone;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getMasterName() {
    return masterName;
  }

  public void setMasterName(String masterName) {
    this.masterName = masterName;
  }

  public String getImageUri() {
    return imageUri;
  }

  public void setImageUri(String imageUri) {
    this.imageUri = imageUri;
  }

  public Integer getServiceTime() {
    return serviceTime;
  }

  public void setServiceTime(Integer serviceTime) {
    this.serviceTime = serviceTime;
  }

  public String getServiceName() {
    return serviceName;
  }

  public void setServiceName(String serviceName) {
    this.serviceName = serviceName;
  }

  public Integer getServiceId() {
    return serviceId;
  }

  public void setServiceId(Integer serviceId) {
    this.serviceId = serviceId;
  }

  public Integer getMasterId() {
    return masterId;
  }

  public void setMasterId(Integer masterId) {
    this.masterId = masterId;
  }
}
