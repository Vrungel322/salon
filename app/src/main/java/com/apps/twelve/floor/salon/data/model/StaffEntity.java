package com.apps.twelve.floor.salon.data.model;

import java.util.ArrayList;

/**
 * Created by Vrungel on 18.05.2017.
 */

public class StaffEntity {
  private String mTitle;
  private String mShortDescription;
  private String mPrice;
  private boolean mIsNew;
  private Integer mType;
  private String mImageURL;
  private ArrayList<StaffDetailContent> mStaffDetailContents;

  public StaffEntity(String title, String shortDescription, String price, boolean isNew,
      Integer type, String imageURL, ArrayList<StaffDetailContent> staffDetailContents) {
    mTitle = title;
    mShortDescription = shortDescription;
    mPrice = price;
    mIsNew = isNew;
    mType = type;
    mImageURL = imageURL;
    mStaffDetailContents = staffDetailContents;
  }

  public String getTitle() {
    return mTitle;
  }

  public void setTitle(String title) {
    mTitle = title;
  }

  public String getShortDescription() {
    return mShortDescription;
  }

  public void setShortDescription(String shortDescription) {
    mShortDescription = shortDescription;
  }

  public String getPrice() {
    return mPrice;
  }

  public void setPrice(String price) {
    mPrice = price;
  }

  public boolean isNew() {
    return mIsNew;
  }

  public void setNew(boolean aNew) {
    mIsNew = aNew;
  }

  public Integer getType() {
    return mType;
  }

  public void setType(Integer type) {
    mType = type;
  }

  public String getImageURL() {
    return mImageURL;
  }

  public void setImageURL(String imageURL) {
    mImageURL = imageURL;
  }

  public ArrayList<StaffDetailContent> getStaffDetailContents() {
    return mStaffDetailContents;
  }

  public void setStaffDetailContents(ArrayList<StaffDetailContent> staffDetailContents) {
    mStaffDetailContents = staffDetailContents;
  }
}
