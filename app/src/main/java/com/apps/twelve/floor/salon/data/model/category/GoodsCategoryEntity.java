package com.apps.twelve.floor.salon.data.model.category;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

/**
 * Created by vrungel on 30.05.17.
 */

public class GoodsCategoryEntity {
  @SerializedName("id") @Expose private Integer id;
  @SerializedName("title") @Expose private String title;
  @SerializedName("text") @Expose private String text;
  @SerializedName("children") @Expose private List<GoodsSubCategoryEntity> children = null;

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

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  public List<GoodsSubCategoryEntity> getChildren() {
    return children;
  }

  public void setChildren(List<GoodsSubCategoryEntity> children) {
    this.children = children;
  }
}
