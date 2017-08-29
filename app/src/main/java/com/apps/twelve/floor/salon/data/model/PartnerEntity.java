package com.apps.twelve.floor.salon.data.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by alexandersvyatetsky on 16/08/17.
 */

public class PartnerEntity {
  @SerializedName("id") private String id;
  @SerializedName("title") private String title;
  @SerializedName("logo") private String image;
  @SerializedName("url") private String partnersPage;

  public PartnerEntity() {
  }

  public PartnerEntity(String id, String title, String image, String partnersPage) {
    this.id = id;
    this.title = title;
    this.image = image;
    this.partnersPage = partnersPage;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getImage() {
    return image;
  }

  public void setImage(String image) {
    this.image = image;
  }

  public String getPartnersPage() {
    return partnersPage;
  }

  public void setPartnersPage(String partnersPage) {
    this.partnersPage = partnersPage;
  }
}
