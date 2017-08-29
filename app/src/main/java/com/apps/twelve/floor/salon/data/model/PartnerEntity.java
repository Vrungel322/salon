package com.apps.twelve.floor.salon.data.model;

import com.google.gson.annotations.SerializedName;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by alexandersvyatetsky on 16/08/17.
 */

public class PartnerEntity extends RealmObject {
  @PrimaryKey @SerializedName("id") private Integer id;
  @SerializedName("title") private String title;
  @SerializedName("logo") private String image;
  @SerializedName("url") private String partnersPage;

  public PartnerEntity() {
  }

  public PartnerEntity(Integer id, String title, String image, String partnersPage) {
    this.id = id;
    this.title = title;
    this.image = image;
    this.partnersPage = partnersPage;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
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
