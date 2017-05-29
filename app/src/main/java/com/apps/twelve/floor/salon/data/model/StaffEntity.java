package com.apps.twelve.floor.salon.data.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;

/**
 * Created by Vrungel on 18.05.2017.
 */

public class StaffEntity implements Parcelable {
  @SerializedName("id") private int id;
  @SerializedName("title") private String mTitle;
  @SerializedName("text") private String mShortDescription;
  @SerializedName("price") private String mPrice;
  @SerializedName("new_price") private String mNewPrice;
  @SerializedName("products_category_id") private int mCategoryId;
  @SerializedName("created_at") private String mCreateDate;
  @SerializedName("updated_at") private String mUpdateDate;
  @SerializedName("image") private String mImageURL;
  @SerializedName("images_count") private int mImgCount;
  @SerializedName("images") private ArrayList<StaffDetailContent> mStaffDetailContents;
  @SerializedName("favorite") private boolean mFavorite;
  private boolean mIsNew;
  private Integer mType;

  public StaffEntity(int id, String title, String shortDescription, String price, String newPrice,
      int categoryId, String createDate, String updateDate, String imageURL, int imgCount,
      ArrayList<StaffDetailContent> staffDetailContents, boolean favorite, boolean isNew,
      Integer type) {
    this.id = id;
    mTitle = title;
    mShortDescription = shortDescription;
    mPrice = price;
    mNewPrice = newPrice;
    mCategoryId = categoryId;
    mCreateDate = createDate;
    mUpdateDate = updateDate;
    mImageURL = imageURL;
    mImgCount = imgCount;
    mStaffDetailContents = staffDetailContents;
    mFavorite = favorite;
    mIsNew = isNew;
    mType = type;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getTitle() {
    return mTitle;
  }

  public void setTitle(String title) {
    mTitle = title;
  }

  public String getShortDescription() {
    return mShortDescription;
  }

  public void setShortDescription(String shortDescription) {
    mShortDescription = shortDescription;
  }

  public String getPrice() {
    return mPrice;
  }

  public void setPrice(String price) {
    mPrice = price;
  }

  public String getNewPrice() {
    return mNewPrice;
  }

  public void setNewPrice(String newPrice) {
    mNewPrice = newPrice;
  }

  public int getCategoryId() {
    return mCategoryId;
  }

  public void setCategoryId(int categoryId) {
    mCategoryId = categoryId;
  }

  public String getCreateDate() {
    return mCreateDate;
  }

  public void setCreateDate(String createDate) {
    mCreateDate = createDate;
  }

  public String getUpdateDate() {
    return mUpdateDate;
  }

  public void setUpdateDate(String updateDate) {
    mUpdateDate = updateDate;
  }

  public String getImageURL() {
    return mImageURL;
  }

  public void setImageURL(String imageURL) {
    mImageURL = imageURL;
  }

  public int getImgCount() {
    return mImgCount;
  }

  public void setImgCount(int imgCount) {
    mImgCount = imgCount;
  }

  public ArrayList<StaffDetailContent> getStaffDetailContents() {
    return mStaffDetailContents;
  }

  public void setStaffDetailContents(ArrayList<StaffDetailContent> staffDetailContents) {
    mStaffDetailContents = staffDetailContents;
  }

  public boolean isFavorite() {
    return mFavorite;
  }

  public void setFavorite(boolean favorite) {
    mFavorite = favorite;
  }

  public boolean isNew() {
    return mIsNew;
  }

  public void setNew(boolean aNew) {
    mIsNew = aNew;
  }

  public Integer getType() {
    return mType;
  }

  public void setType(Integer type) {
    mType = type;
  }

  public static Creator<StaffEntity> getCREATOR() {
    return CREATOR;
  }

  protected StaffEntity(Parcel in) {
    id = in.readInt();
    mTitle = in.readString();
    mShortDescription = in.readString();
    mPrice = in.readString();
    mNewPrice = in.readString();
    mCategoryId = in.readInt();
    mCreateDate = in.readString();
    mUpdateDate = in.readString();
    mImageURL = in.readString();
    mImgCount = in.readInt();
    mFavorite = in.readByte() != 0;
    mIsNew = in.readByte() != 0;
  }

  @Override public void writeToParcel(Parcel dest, int flags) {
    dest.writeInt(id);
    dest.writeString(mTitle);
    dest.writeString(mShortDescription);
    dest.writeString(mPrice);
    dest.writeString(mNewPrice);
    dest.writeInt(mCategoryId);
    dest.writeString(mCreateDate);
    dest.writeString(mUpdateDate);
    dest.writeString(mImageURL);
    dest.writeInt(mImgCount);
    dest.writeByte((byte) (mFavorite ? 1 : 0));
    dest.writeByte((byte) (mIsNew ? 1 : 0));
  }

  @Override public int describeContents() {
    return 0;
  }

  public static final Creator<StaffEntity> CREATOR = new Creator<StaffEntity>() {
    @Override public StaffEntity createFromParcel(Parcel in) {
      return new StaffEntity(in);
    }

    @Override public StaffEntity[] newArray(int size) {
      return new StaffEntity[size];
    }
  };
}
