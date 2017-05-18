package com.apps.twelve.floor.salon.data.model;

/**
 * Created by Vrungel on 18.05.2017.
 */

public class StaffDetailContent {
  private int id;
  private String mUrlPhoto;

  public StaffDetailContent(int id, String urlPhoto) {
    this.id = id;
    mUrlPhoto = urlPhoto;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getUrlPhoto() {
    return mUrlPhoto;
  }

  public void setUrlPhoto(String urlPhoto) {
    mUrlPhoto = urlPhoto;
  }
}
