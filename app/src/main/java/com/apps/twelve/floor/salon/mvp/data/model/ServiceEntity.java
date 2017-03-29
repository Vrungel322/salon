package com.apps.twelve.floor.salon.mvp.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Vrungel on 29.03.2017.
 */

public class ServiceEntity {

  @SerializedName("service_id") @Expose private Integer serviceId;
  @SerializedName("title") @Expose private String title;
  @SerializedName("parent_id") @Expose private String parentId;
  @SerializedName("is_service") @Expose private Boolean isService;
  @SerializedName("price") @Expose private String price;
  @SerializedName("children") @Expose private Boolean children;
  @SerializedName("img_href") @Expose private String imgHref;
  @SerializedName("description") @Expose private String description;
  @SerializedName("time") @Expose private Integer time;

  public ServiceEntity(Integer serviceId, String title, String parentId, Boolean isService,
      String price, Boolean children, String imgHref, String description, Integer time) {
    this.serviceId = serviceId;
    this.title = title;
    this.parentId = parentId;
    this.isService = isService;
    this.price = price;
    this.children = children;
    this.imgHref = imgHref;
    this.description = description;
    this.time = time;
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

  public String getParentId() {
    return parentId;
  }

  public void setParentId(String parentId) {
    this.parentId = parentId;
  }

  public Boolean getIsService() {
    return isService;
  }

  public void setIsService(Boolean isService) {
    this.isService = isService;
  }

  public String getPrice() {
    return price;
  }

  public void setPrice(String price) {
    this.price = price;
  }

  public Boolean getChildren() {
    return children;
  }

  public void setChildren(Boolean children) {
    this.children = children;
  }

  public String getImgHref() {
    return imgHref;
  }

  public void setImgHref(String imgHref) {
    this.imgHref = imgHref;
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
}
