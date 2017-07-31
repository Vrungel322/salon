package com.apps.twelve.floor.salon.data.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;
import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Vrungel on 22.02.2017.
 */

public class OurWorkEntity extends RealmObject implements Parcelable {

  @PrimaryKey @SerializedName("id") private int id;
  @SerializedName("title") private String mTitle;
  @SerializedName("description") private String mShortDescription;
  @SerializedName("master_id") private int masterId;
  @SerializedName("service_id") private int serviceId;
  @SerializedName("image") private String mImageURL;
  @SerializedName("photos_count") private int mImageCount;
  @SerializedName("photos") private RealmList<PhotoWorksEntity> mListPhotoWorks;

  public OurWorkEntity() {
  }

  public OurWorkEntity(String imageURL, int imageCount, String title,
      RealmList<PhotoWorksEntity> listPhotoWorks) {
    mImageURL = imageURL;
    mImageCount = imageCount;
    mTitle = title;
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
  }

  public static final Creator<OurWorkEntity> CREATOR = new Creator<OurWorkEntity>() {
    @Override public OurWorkEntity createFromParcel(Parcel in) {
      return new OurWorkEntity(in);
    }

    @Override public OurWorkEntity[] newArray(int size) {
      return new OurWorkEntity[size];
    }
  };

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

  public RealmList<PhotoWorksEntity> getListPhotoWorks() {
    return mListPhotoWorks;
  }

  public void setListPhotoWorks(RealmList<PhotoWorksEntity> listPhotoWorks) {
    mListPhotoWorks = listPhotoWorks;
  }

  @Override public int describeContents() {
    return 0;
  }

  @Override public void writeToParcel(Parcel parcel, int i) {
    parcel.writeInt(id);
    parcel.writeString(mTitle);
    parcel.writeString(mShortDescription);
    parcel.writeInt(masterId);
    parcel.writeInt(serviceId);
    parcel.writeString(mImageURL);
    parcel.writeInt(mImageCount);
  }
}
