package com.apps.twelve.floor.salon.mvp.data.model;

/**
 * Created by Vrungel on 24.03.2017.
 */
public class BookingEntity {
  private String mMasterServiceId;
  private String mUserName;
  private String mUserPhone;
  private String mDateId;
  private String mMasterId;

  public String getMasterServiceId() {
    return mMasterServiceId;
  }

  public void setMasterServiceId(String masterServiceId) {
    mMasterServiceId = masterServiceId;
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
}
