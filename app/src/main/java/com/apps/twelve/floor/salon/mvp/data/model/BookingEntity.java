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

  public BookingEntity(String masterServiceId, String userName, String userPhone, String dateId,
      String masterId) {
    mMasterServiceId = masterServiceId;
    mUserName = userName;
    mUserPhone = userPhone;
    mDateId = dateId;
    mMasterId = masterId;
  }

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
