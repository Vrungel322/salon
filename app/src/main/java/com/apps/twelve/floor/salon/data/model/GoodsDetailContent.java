package com.apps.twelve.floor.salon.data.model;

import com.google.gson.annotations.SerializedName;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Vrungel on 18.05.2017.
 */

public class GoodsDetailContent extends RealmObject {
  @PrimaryKey @SerializedName("id") private int id;
  @SerializedName("product_id") private int mProductId;
  @SerializedName("image") private String mUrlPhoto;

  public GoodsDetailContent() {
  }

  public GoodsDetailContent(int id, int productId, String urlPhoto) {
    this.id = id;
    mProductId = productId;
    mUrlPhoto = urlPhoto;
  }

  public int getProductId() {
    return mProductId;
  }

  public void setProductId(int productId) {
    mProductId = productId;
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
