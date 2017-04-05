package com.apps.twelve.floor.salon.mvp.data.model;

/**
 * Created by Vrungel on 24.03.2017.
 */
public class BookingEntity {
  private String mServiceId;
  private String mUserName;
  private String mUserPhone;
  private String mDateId;
  private String mMasterId;
  private String mServiceName;
  private String mServiceTime;
  private String mMasterName;
  private String mDurationServices;

  public BookingEntity(String serviceId, String userName, String userPhone, String dateId,
      String masterId, String serviceName, String serviceTime, String masterName,
      String durationServices) {
    mServiceId = serviceId;
    mUserName = userName;
    mUserPhone = userPhone;
    mDateId = dateId;
    mMasterId = masterId;
    mServiceName = serviceName;
    mServiceTime = serviceTime;
    mMasterName = masterName;
    mDurationServices = durationServices;
  }

  public String getDurationServices() {
    return mDurationServices;
  }

  public void setDurationServices(String durationServices) {
    mDurationServices = durationServices;
  }

  public String getServiceId() {
    return mServiceId;
  }

  public void setServiceId(String serviceId) {
    mServiceId = serviceId;
  }

  public String getUserName() {
    return mUserName;
  }

  public void setUserName(String userName) {
    mUserName = userName;
  }

  public String getUserPhone() {
    return mUserPhone;
  }

  public void setUserPhone(String userPhone) {
    mUserPhone = userPhone;
  }

  public String getDateId() {
    return mDateId;
  }

  public void setDateId(String dateId) {
    mDateId = dateId;
  }

  public String getMasterId() {
    return mMasterId;
  }

  public void setMasterId(String masterId) {
    mMasterId = masterId;
  }

  public String getServiceName() {
    return mServiceName;
  }

  public void setServiceName(String serviceName) {
    mServiceName = serviceName;
  }

  public String getServiceTime() {
    return mServiceTime;
  }

  public void setServiceTime(String serviceTime) {
    mServiceTime = serviceTime;
  }

  public String getMasterName() {
    return mMasterName;
  }

  public void setMasterName(String masterName) {
    mMasterName = masterName;
  }
}
