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
  @SerializedName("master_description") private String masterDescription;
  @SerializedName("master_photo") private String masterPhoto;
  @SerializedName("master_id") private Integer masterId;
  @SerializedName("schedule") private Integer serviceTime;
  @SerializedName("schedule_id") private Integer scheduleId;
  @SerializedName("service_title") private String serviceName;
  @SerializedName("service_id") private Integer serviceId;
  @SerializedName("service_image") private String serviceImage;
  @SerializedName("service_text") private String serviceDescription;
  @SerializedName("service_bonus_price") private String serviceBonusPrice;
  @SerializedName("service_price") private String servicePrice;
  @SerializedName("service_new_price") private String serviceNewPrice;
  @SerializedName("service_duration") private String serviceDuration;

  public LastBookingEntity(Integer id, String userPhone, String userName, String masterName,
      String serviceImage, Integer serviceTime, String serviceName, Integer serviceId,
      Integer masterId) {
    this.id = id;
    this.userPhone = userPhone;
    this.userName = userName;
    this.masterName = masterName;
    this.serviceImage = serviceImage;
    this.serviceTime = serviceTime;
    this.serviceName = serviceName;
    this.serviceId = serviceId;
    this.masterId = masterId;
  }

  protected LastBookingEntity(Parcel in) {
    userPhone = in.readString();
    userName = in.readString();
    masterName = in.readString();
    serviceImage = in.readString();
    serviceName = in.readString();
  }

  @Override public void writeToParcel(Parcel dest, int flags) {
    dest.writeString(userPhone);
    dest.writeString(userName);
    dest.writeString(masterName);
    dest.writeString(serviceImage);
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

  public String getServiceImage() {
    return serviceImage;
  }

  public void setServiceImage(String serviceImage) {
    this.serviceImage = serviceImage;
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

  public String getMasterDescription() {
    return masterDescription;
  }

  public void setMasterDescription(String masterDescription) {
    this.masterDescription = masterDescription;
  }

  public String getMasterPhoto() {
    return masterPhoto;
  }

  public void setMasterPhoto(String masterPhoto) {
    this.masterPhoto = masterPhoto;
  }

  public Integer getScheduleId() {
    return scheduleId;
  }

  public void setScheduleId(Integer scheduleId) {
    this.scheduleId = scheduleId;
  }

  public String getServiceDescription() {
    return serviceDescription;
  }

  public void setServiceDescription(String serviceDescription) {
    this.serviceDescription = serviceDescription;
  }

  public String getServiceBonusPrice() {
    return serviceBonusPrice;
  }

  public void setServiceBonusPrice(String serviceBonusPrice) {
    this.serviceBonusPrice = serviceBonusPrice;
  }

  public String getServicePrice() {
    return servicePrice;
  }

  public void setServicePrice(String servicePrice) {
    this.servicePrice = servicePrice;
  }

  public String getServiceNewPrice() {
    return serviceNewPrice;
  }

  public void setServiceNewPrice(String serviceNewPrice) {
    this.serviceNewPrice = serviceNewPrice;
  }

  public String getServiceDuration() {
    return serviceDuration;
  }

  public void setServiceDuration(String serviceDuration) {
    this.serviceDuration = serviceDuration;
  }
}
