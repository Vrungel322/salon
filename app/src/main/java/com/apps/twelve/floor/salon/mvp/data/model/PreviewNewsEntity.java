package com.apps.twelve.floor.salon.mvp.data.model;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Vrungel on 23.02.2017.
 */

public class PreviewNewsEntity implements Parcelable {
  private Uri mImageNewsPreviewURL;
  private String mNewsShortDescription;
  private String mNewsData;

  public PreviewNewsEntity(Uri imageNewsPreviewURL, String newsShortDescription, String newsData) {
    mImageNewsPreviewURL = imageNewsPreviewURL;
    mNewsShortDescription = newsShortDescription;
    mNewsData = newsData;
  }

  protected PreviewNewsEntity(Parcel in) {
    mImageNewsPreviewURL = in.readParcelable(Uri.class.getClassLoader());
    mNewsShortDescription = in.readString();
    mNewsData = in.readString();
  }

  public static final Creator<PreviewNewsEntity> CREATOR = new Creator<PreviewNewsEntity>() {
    @Override public PreviewNewsEntity createFromParcel(Parcel in) {
      return new PreviewNewsEntity(in);
    }

    @Override public PreviewNewsEntity[] newArray(int size) {
      return new PreviewNewsEntity[size];
    }
  };

  public Uri getImageNewsPreviewURL() {
    return mImageNewsPreviewURL;
  }

  public void setImageNewsPreviewURL(Uri imageNewsPreviewURL) {
    mImageNewsPreviewURL = imageNewsPreviewURL;
  }

  public String getNewsShortDescription() {
    return mNewsShortDescription;
  }

  public void setNewsShortDescription(String newsShortDescription) {
    mNewsShortDescription = newsShortDescription;
  }

  public String getNewsData() {
    return mNewsData;
  }

  public void setNewsData(String newsData) {
    mNewsData = newsData;
  }

  @Override public int describeContents() {
    return 0;
  }

  @Override public void writeToParcel(Parcel dest, int flags) {
    dest.writeParcelable(mImageNewsPreviewURL, flags);
    dest.writeString(mNewsShortDescription);
    dest.writeString(mNewsData);
  }
}
