package com.apps.twelve.floor.salon.data.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NewsEntity implements Parcelable {

  public static final Creator<NewsEntity> CREATOR = new Creator<NewsEntity>() {
    @Override public NewsEntity createFromParcel(Parcel in) {
      return new NewsEntity(in);
    }

    @Override public NewsEntity[] newArray(int size) {
      return new NewsEntity[size];
    }
  };
  @SerializedName("id") @Expose private Integer id;
  @SerializedName("title") @Expose private String title;
  @SerializedName("text") @Expose private String text;
  @SerializedName("img") @Expose private String img;
  @SerializedName("created_at") @Expose private String createdAt;
  @SerializedName("updated_at") @Expose private String updatedAt;

  /**
   * No args constructor for use in serialization
   */
  public NewsEntity() {
  }

  /**
   *
   * @param updatedAt
   * @param id
   * @param text
   * @param title
   * @param createdAt
   * @param img
   */
  public NewsEntity(Integer id, String title, String text, String img, String createdAt,
      String updatedAt) {
    super();
    this.id = id;
    this.title = title;
    this.text = text;
    this.img = img;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
  }

  protected NewsEntity(Parcel in) {
    title = in.readString();
    text = in.readString();
    img = in.readString();
    createdAt = in.readString();
    updatedAt = in.readString();
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

  public String getImg() {
    return img;
  }

  public void setImg(String img) {
    this.img = img;
  }

  public String getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(String createdAt) {
    this.createdAt = createdAt;
  }

  public String getUpdatedAt() {
    return updatedAt;
  }

  public void setUpdatedAt(String updatedAt) {
    this.updatedAt = updatedAt;
  }

  @Override public int describeContents() {
    return 0;
  }

  @Override public void writeToParcel(Parcel dest, int flags) {
    dest.writeString(title);
    dest.writeString(text);
    dest.writeString(img);
    dest.writeString(createdAt);
    dest.writeString(updatedAt);
  }
}
