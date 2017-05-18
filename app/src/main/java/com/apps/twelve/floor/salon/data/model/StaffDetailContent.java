package com.apps.twelve.floor.salon.data.model;

/**
 * Created by Vrungel on 18.05.2017.
 */

public class StaffDetailContent {
  private int id;
  private String mDescriptionStaff;
  private String mUrlPhoto;

  public StaffDetailContent(int id, String descriptionStaff, String urlPhoto) {
    this.id = id;
    mDescriptionStaff = descriptionStaff;
    mUrlPhoto = urlPhoto;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getDescriptionStaff() {
    return mDescriptionStaff;
  }

  public void setDescriptionStaff(String descriptionStaff) {
    mDescriptionStaff = descriptionStaff;
  }

  public String getUrlPhoto() {
    return mUrlPhoto;
  }

  public void setUrlPhoto(String urlPhoto) {
    mUrlPhoto = urlPhoto;
  }
}
