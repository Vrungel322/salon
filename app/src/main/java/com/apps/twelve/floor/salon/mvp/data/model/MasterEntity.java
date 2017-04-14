package com.apps.twelve.floor.salon.mvp.data.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Vrungel on 28.03.2017.
 */

public class MasterEntity {
  @SerializedName("id")
  private String masterId;
  @SerializedName("name")
  private String masterName;
  @SerializedName("photo")
  private String masterImg;
  @SerializedName("description")
  private String masterDescription;

  public MasterEntity(String masterName, String masterImg, String masterDescription,
      String masterId) {
    this.masterName = masterName;
    this.masterImg = masterImg;
    this.masterDescription = masterDescription;
    this.masterId = masterId;
  }

  public String getMasterName() {
    return masterName;
  }

  public void setMasterName(String masterName) {
    this.masterName = masterName;
  }

  public String getMasterImg() {
    return masterImg;
  }

  public void setMasterImg(String masterImg) {
    this.masterImg = masterImg;
  }

  public String getMasterDescription() {
    return masterDescription;
  }

  public void setMasterDescription(String masterDescription) {
    this.masterDescription = masterDescription;
  }

  public String getMasterId() {
    return masterId;
  }

  public void setMasterId(String masterId) {
    this.masterId = masterId;
  }
}
