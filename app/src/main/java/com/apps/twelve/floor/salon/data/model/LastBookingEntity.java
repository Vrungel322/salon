package com.apps.twelve.floor.salon.data.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.RealmClass;

/**
 * Created by Vrungel on 28.02.2017.
 */

public class LastBookingEntity extends RealmObject implements Parcelable {

  @PrimaryKey
  @SerializedName("id") private Integer id;
  @SerializedName("phone") private String userPhone;
  @SerializedName("name") private String userName;
  @SerializedName("master") private String masterName;
  @SerializedName("master_description") private String masterDescription;
  @SerializedName("master_photo") private String masterPhoto;
  @SerializedName("master_id") private Integer masterId;
  @SerializedName("schedule") private Integer serviceTime;
  @SerializedName("schedule_id") private Integer scheduleId;
  @SerializedName("service_id") private Integer serviceId;
  @SerializedName("service_title") private String serviceName;
  @SerializedName("service_image") private String serviceImage;
  @SerializedName("service_text") private String serviceDescription;
  @SerializedName("service_bonus_price") private String serviceBonusPrice;
  @SerializedName("service_bonus_add") private String serviceBonusAdd;
  @SerializedName("service_price") private String servicePrice;
  @SerializedName("service_new_price") private String serviceNewPrice;
  @SerializedName("service_duration") private String serviceDuration;

  public LastBookingEntity() {
  }

  public LastBookingEntity(Integer id, String userPhone, String userName, String masterName,
      String masterDescription, String masterPhoto, Integer masterId, Integer serviceTime,
      Integer scheduleId, Integer serviceId, String serviceName, String serviceImage,
      String serviceDescription, String serviceBonusPrice, String serviceBonusAdd,
      String servicePrice, String serviceNewPrice, String serviceDuration) {
    this.id = id;
    this.userPhone = userPhone;
    this.userName = userName;
    this.masterName = masterName;
    this.masterDescription = masterDescription;
    this.masterPhoto = masterPhoto;
    this.masterId = masterId;
    this.serviceTime = serviceTime;
    this.scheduleId = scheduleId;
    this.serviceId = serviceId;
    this.serviceName = serviceName;
    this.serviceImage = serviceImage;
    this.serviceDescription = serviceDescription;
    this.serviceBonusPrice = serviceBonusPrice;
    this.serviceBonusAdd = serviceBonusAdd;
    this.servicePrice = servicePrice;
    this.serviceNewPrice = serviceNewPrice;
    this.serviceDuration = serviceDuration;
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

  public Integer getMasterId() {
    return masterId;
  }

  public void setMasterId(Integer masterId) {
    this.masterId = masterId;
  }

  public Integer getServiceTime() {
    return serviceTime;
  }

  public void setServiceTime(Integer serviceTime) {
    this.serviceTime = serviceTime;
  }

  public Integer getScheduleId() {
    return scheduleId;
  }

  public void setScheduleId(Integer scheduleId) {
    this.scheduleId = scheduleId;
  }

  public Integer getServiceId() {
    return serviceId;
  }

  public void setServiceId(Integer serviceId) {
    this.serviceId = serviceId;
  }

  public String getServiceName() {
    return serviceName;
  }

  public void setServiceName(String serviceName) {
    this.serviceName = serviceName;
  }

  public String getServiceImage() {
    return serviceImage;
  }

  public void setServiceImage(String serviceImage) {
    this.serviceImage = serviceImage;
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

  public String getServiceBonusAdd() {
    return serviceBonusAdd;
  }

  public void setServiceBonusAdd(String serviceBonusAdd) {
    this.serviceBonusAdd = serviceBonusAdd;
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

  public static Creator<LastBookingEntity> getCREATOR() {
    return CREATOR;
  }

  protected LastBookingEntity(Parcel in) {
    id = in.readInt();
    userPhone = in.readString();
    userName = in.readString();
    masterName = in.readString();
    masterDescription = in.readString();
    masterPhoto = in.readString();
    masterId = in.readInt();
    serviceTime = in.readInt();
    serviceName = in.readString();
    serviceImage = in.readString();
    serviceDescription = in.readString();
    serviceBonusPrice = in.readString();
    serviceBonusAdd = in.readString();
    servicePrice = in.readString();
    serviceNewPrice = in.readString();
    serviceDuration = in.readString();
  }

  @Override public void writeToParcel(Parcel dest, int flags) {
    dest.writeInt(id);
    dest.writeString(userPhone);
    dest.writeString(userName);
    dest.writeString(masterName);
    dest.writeString(masterDescription);
    dest.writeString(masterPhoto);
    dest.writeInt(masterId);
    dest.writeInt(serviceTime);
    dest.writeString(serviceName);
    dest.writeString(serviceImage);
    dest.writeString(serviceDescription);
    dest.writeString(serviceBonusPrice);
    dest.writeString(serviceBonusAdd);
    dest.writeString(servicePrice);
    dest.writeString(serviceNewPrice);
    dest.writeString(serviceDuration);
  }

  @Override public int describeContents() {
    return 0;
  }

  public static final Creator<LastBookingEntity> CREATOR = new Creator<LastBookingEntity>() {
    @Override public LastBookingEntity createFromParcel(Parcel in) {
      return new LastBookingEntity(in);
    }

    @Override public LastBookingEntity[] newArray(int size) {
      return new LastBookingEntity[size];
    }
  };
}
