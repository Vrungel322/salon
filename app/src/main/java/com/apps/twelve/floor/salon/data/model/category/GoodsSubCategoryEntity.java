package com.apps.twelve.floor.salon.data.model.category;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by vrungel on 30.05.17.
 */

public class GoodsSubCategoryEntity extends RealmObject implements Parcelable {
  @PrimaryKey
  @SerializedName("id") @Expose private Integer id;
  @SerializedName("title") @Expose private String title;
  @SerializedName("text") @Expose private String text;

  public GoodsSubCategoryEntity() {
  }

  public GoodsSubCategoryEntity(Integer id, String title, String text) {
    this.id = id;
    this.title = title;
    this.text = text;
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

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  public static Creator<GoodsSubCategoryEntity> getCREATOR() {
    return CREATOR;
  }

  protected GoodsSubCategoryEntity(Parcel in) {
    title = in.readString();
    text = in.readString();
  }

  public static final Creator<GoodsSubCategoryEntity> CREATOR =
      new Creator<GoodsSubCategoryEntity>() {
        @Override public GoodsSubCategoryEntity createFromParcel(Parcel in) {
          return new GoodsSubCategoryEntity(in);
        }

        @Override public GoodsSubCategoryEntity[] newArray(int size) {
          return new GoodsSubCategoryEntity[size];
        }
      };

  @Override public int describeContents() {
    return 0;
  }

  @Override public void writeToParcel(Parcel dest, int flags) {
    dest.writeString(title);
    dest.writeString(text);
  }
}
