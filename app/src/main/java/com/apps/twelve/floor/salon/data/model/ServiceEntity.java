package com.apps.twelve.floor.salon.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Vrungel on 29.03.2017.
 */

public class ServiceEntity {

  @SerializedName("id") @Expose private Integer serviceId;
  @SerializedName("title") @Expose private String title;
  @SerializedName("text") @Expose private String description;
  @SerializedName("image") @Expose private String image;
  @SerializedName("duration") @Expose private String time;
  @SerializedName("price") @Expose private String price;
  @SerializedName("bonus_price") @Expose private String bonusPrice;
  @SerializedName("category_id") @Expose private String categoryId;
  @SerializedName("is_new") private boolean mIsNew;
  @SerializedName("for_sale") private boolean mIsForSale;

  public ServiceEntity() {
  }

  public ServiceEntity(Integer serviceId, String title, String description, String image,
      String time, String price, String bonusPrice, String categoryId, boolean isNew,
      boolean isForSale) {
    this.serviceId = serviceId;
    this.title = title;
    this.description = description;
    this.image = image;
    this.time = time;
    this.price = price;
    this.bonusPrice = bonusPrice;
    this.categoryId = categoryId;
    mIsNew = isNew;
    mIsForSale = isForSale;
  }

  public String getImage() {
    return image;
  }

  public void setImage(String image) {
    this.image = image;
  }

  public Integer getServiceId() {
    return serviceId;
  }

  public void setServiceId(Integer serviceId) {
    this.serviceId = serviceId;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getTime() {
    return time;
  }

  public void setTime(String time) {
    this.time = time;
  }

  public String getPrice() {
    return price;
  }

  public void setPrice(String price) {
    this.price = price;
  }

  public String getBonusPrice() {
    return bonusPrice;
  }

  public void setBonusPrice(String bonusPrice) {
    this.bonusPrice = bonusPrice;
  }

  public String getCategoryId() {
    return categoryId;
  }

  public void setCategoryId(String categoryId) {
    this.categoryId = categoryId;
  }

  public boolean isNew() {
    return mIsNew;
  }

  public void setNew(boolean aNew) {
    mIsNew = aNew;
  }

  public boolean isForSale() {
    return mIsForSale;
  }

  public void setForSale(boolean forSale) {
    mIsForSale = forSale;
  }
}
