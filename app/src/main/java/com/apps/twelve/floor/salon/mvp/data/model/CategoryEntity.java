package com.apps.twelve.floor.salon.mvp.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CategoryEntity {

  @SerializedName("id") @Expose private Integer id;
  @SerializedName("parent_id") @Expose private Integer parentId;
  @SerializedName("title") @Expose private String title;
  @SerializedName("text") @Expose private String text;
  @SerializedName("image") @Expose private String image;
  @SerializedName("children") @Expose private String children;
  private Boolean hasChildren = true;

  /**
   * No args constructor for use in serialization
   */
  public CategoryEntity() {
  }

  /**
   *
   * @param id
   * @param parentId
   * @param text
   * @param title
   * @param children
   * @param image
   */
  public CategoryEntity(Integer id, Integer parentId, String title, String text, String image,
      String children) {
    super();
    this.id = id;
    this.parentId = parentId;
    this.title = title;
    this.text = text;
    this.image = image;
    this.children = children;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Integer getParentId() {
    return parentId;
  }

  public void setParentId(Integer parentId) {
    this.parentId = parentId;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  public String getImage() {
    return image;
  }

  public void setImage(String image) {
    this.image = image;
  }

  public String getChildren() {
    return children;
  }

  public void setChildren(String children) {
    this.children = children;
  }

  public Boolean hasChildren() {
    return hasChildren;
  }

  public void setHasChildren(Boolean hasChildren) {
    this.hasChildren = hasChildren;
  }
}
