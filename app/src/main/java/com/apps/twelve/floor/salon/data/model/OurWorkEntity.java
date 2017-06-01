package com.apps.twelve.floor.salon.data.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vrungel on 22.02.2017.
 */

public class OurWorkEntity implements Parcelable {
  public static final Creator<OurWorkEntity> CREATOR = new Creator<OurWorkEntity>() {
    @Override public OurWorkEntity createFromParcel(Parcel in) {
      return new OurWorkEntity(in);
    }

    @Override public OurWorkEntity[] newArray(int size) {
      return new OurWorkEntity[size];
    }
  };
  @SerializedName("id") private int id;
  @SerializedName("title") private String mTitle;
  @SerializedName("description") private String mShortDescription;
  @SerializedName("master_id") private int masterId;
  @SerializedName("service_id") private int serviceId;
  @SerializedName("image") private String mImageURL;
  @SerializedName("photos_count") private int mImageCount;
  @SerializedName("photos") private List<PhotoWorksEntity> mListPhotoWorks;

  public OurWorkEntity(String imageURL, int imageCount,
      List<PhotoWorksEntity> listPhotoWorks) {
    mImageURL = imageURL;
    mImageCount = imageCount;
    mListPhotoWorks = listPhotoWorks;
  }

  public OurWorkEntity(int id, String title, String shortDescription, int masterId, int serviceId,
      String imageURL, int imageCount, ArrayList<PhotoWorksEntity> listPhotoWorks) {
    this.id = id;
    mTitle = title;
    mShortDescription = shortDescription;
    this.masterId = masterId;
    this.serviceId = serviceId;
    mImageURL = imageURL;
    mImageCount = imageCount;
    mListPhotoWorks = listPhotoWorks;
  }

  protected OurWorkEntity(Parcel in) {
    id = in.readInt();
    mTitle = in.readString();
    mShortDescription = in.readString();
    masterId = in.readInt();
    serviceId = in.readInt();
    mImageURL = in.readString();
    mImageCount = in.readInt();
    mListPhotoWorks = in.createTypedArrayList(PhotoWorksEntity.CREATOR);
  }

  @Override public void writeToParcel(Parcel dest, int flags) {
    dest.writeInt(id);
    dest.writeString(mTitle);
    dest.writeString(mShortDescription);
    dest.writeInt(masterId);
    dest.writeInt(serviceId);
    dest.writeString(mImageURL);
    dest.writeInt(mImageCount);
    dest.writeTypedList(mListPhotoWorks);
  }

  @Override public int describeContents() {
    return 0;
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

  public int getMasterId() {
    return masterId;
  }

  public void setMasterId(int masterId) {
    this.masterId = masterId;
  }

  public int getServiceId() {
    return serviceId;
  }

  public void setServiceId(int serviceId) {
    this.serviceId = serviceId;
  }

  public String getImageURL() {
    return mImageURL;
  }

  public void setImageURL(String imageURL) {
    mImageURL = imageURL;
  }

  public int getImageCount() {
    return mImageCount;
  }

  public void setImageCount(int imageCount) {
    mImageCount = imageCount;
  }

  public List<PhotoWorksEntity> getListPhotoWorks() {
    return mListPhotoWorks;
  }

  public void setListPhotoWorks(ArrayList<PhotoWorksEntity> listPhotoWorks) {
    mListPhotoWorks = listPhotoWorks;
  }
}
