package com.apps.twelve.floor.salon.data.model.category;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import java.util.List;

/**
 * Created by vrungel on 30.05.17.
 */

public class GoodsCategoryEntity extends RealmObject {
  @PrimaryKey @SerializedName("id") @Expose private Integer id;
  @SerializedName("title") @Expose private String title;
  @SerializedName("text") @Expose private String text;
  @SerializedName("children") @Expose private RealmList<GoodsSubCategoryEntity> children = null;

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

  public void setChildren(RealmList<GoodsSubCategoryEntity> children) {
    this.children = children;
  }
}
