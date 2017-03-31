package com.apps.twelve.floor.salon.mvp.data.model.service_tree_item;

import com.choiintack.recursiverecyclerview.RecursiveItem;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class ParentService implements RecursiveItem {

  @SerializedName("service_id") @Expose private Integer serviceId;
  @SerializedName("title") @Expose private String title;
  @SerializedName("parent_id") @Expose private Object parentId;
  @SerializedName("is_service") @Expose private Boolean isService;
  @SerializedName("price") @Expose private Object price;
  @SerializedName("img_href") @Expose private String imgHref;
  @SerializedName("description") @Expose private String description;
  @SerializedName("time") @Expose private Integer time;
  @SerializedName("children") @Expose private List<Child_1> children = null;

  /**
   * No args constructor for use in serialization
   */
  public ParentService() {
  }

  /**
   *
   * @param parentId
   * @param time
   * @param title
   * @param price
   * @param isService
   * @param serviceId
   * @param description
   * @param imgHref
   * @param children
   */
  public ParentService(Integer serviceId, String title, Object parentId, Boolean isService,
      Object price, String imgHref, String description, Integer time, List<Child_1> children) {
    super();
    this.serviceId = serviceId;
    this.title = title;
    this.parentId = parentId;
    this.isService = isService;
    this.price = price;
    this.imgHref = imgHref;
    this.description = description;
    this.time = time;
    this.children = children;
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

  public Object getParentId() {
    return parentId;
  }

  public void setParentId(Object parentId) {
    this.parentId = parentId;
  }

  public Boolean getIsService() {
    return isService;
  }

  public void setIsService(Boolean isService) {
    this.isService = isService;
  }

  public Object getPrice() {
    return price;
  }

  public void setPrice(Object price) {
    this.price = price;
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

  public void setChildren(List<Child_1> children) {
    this.children = children;
  }

  @Override public List<Child_1> getChildren() {
    return children;
  }
}
