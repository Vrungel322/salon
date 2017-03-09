package com.apps.twelve.floor.salon.mvp.data.model;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;

/**
 * Created by Vrungel on 22.02.2017.
 */

public class OurWorkEntity implements Parcelable {
  private Uri mImageURL;
  private String mShortDescription;
  private int mImageCount;
  private ArrayList<PhotoWorksEntity> mListPhotoWorks;

  public OurWorkEntity(Uri imageURL, String shortDescription, int imageCount,
      ArrayList<PhotoWorksEntity> listPhotoWorks) {
    mImageURL = imageURL;
    mShortDescription = shortDescription;
    mImageCount = imageCount;
    mListPhotoWorks = listPhotoWorks;
  }

  protected OurWorkEntity(Parcel in) {
    mImageURL = in.readParcelable(Uri.class.getClassLoader());
    mShortDescription = in.readString();
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

  public Uri getImageURL() {
    return mImageURL;
  }

  public void setImageURL(Uri imageURL) {
    mImageURL = imageURL;
  }

  public String getShortDescription() {
    return mShortDescription;
  }

  public void setShortDescription(String shortDescription) {
    mShortDescription = shortDescription;
  }

  public int getImageCount() {
    return mImageCount;
  }

  public void setImageCount(int imageCount) {
    mImageCount = imageCount;
  }

  public ArrayList<PhotoWorksEntity> getListPhotoWorks() {
    return mListPhotoWorks;
  }

  public void setListPhotoWorks(ArrayList<PhotoWorksEntity> listPhotoWorks) {
    mListPhotoWorks = listPhotoWorks;
  }

  @Override public int describeContents() {
    return 0;
  }

  @Override public void writeToParcel(Parcel dest, int flags) {
    dest.writeParcelable(mImageURL, flags);
    dest.writeString(mShortDescription);
    dest.writeInt(mImageCount);
  }
}
