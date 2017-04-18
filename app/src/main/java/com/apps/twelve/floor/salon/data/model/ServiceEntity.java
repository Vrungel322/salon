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
  @SerializedName("duration") @Expose private Integer time;
  @SerializedName("average_price") @Expose private String price;
  @SerializedName("category_id") @Expose private String categoryId;

  public ServiceEntity() {
  }

  public ServiceEntity(Integer serviceId, String title, String description, Integer time,
      String price, String categoryId) {
    this.serviceId = serviceId;
    this.title = title;
    this.description = description;
    this.time = time;
    this.price = price;
    this.categoryId = categoryId;
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

  public Integer getTime() {
    return time;
  }

  public void setTime(Integer time) {
    this.time = time;
  }

  public String getPrice() {
    return price;
  }

  public void setPrice(String price) {
    this.price = price;
  }

  public String getCategoryId() {
    return categoryId;
  }

  public void setCategoryId(String categoryId) {
    this.categoryId = categoryId;
  }
}
