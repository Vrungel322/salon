package com.apps.twelve.floor.salon.data.model;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Vrungel on 23.02.2017.
 */

public class NewsEntity implements Parcelable {
  public static final int LAST_NEWS = 1;
  public static final int DEFAULT_NEWS = 0;
  private Uri mImageNewsPreviewURL;
  private String mNewsShortDescription;
  private String mNewsData;

  public NewsEntity(Uri imageNewsPreviewURL, String newsShortDescription, String newsData) {
    mImageNewsPreviewURL = imageNewsPreviewURL;
    mNewsShortDescription = newsShortDescription;
    mNewsData = newsData;
  }

  protected NewsEntity(Parcel in) {
    mImageNewsPreviewURL = in.readParcelable(Uri.class.getClassLoader());
    mNewsShortDescription = in.readString();
    mNewsData = in.readString();
  }

  public static final Creator<NewsEntity> CREATOR = new Creator<NewsEntity>() {
    @Override public NewsEntity createFromParcel(Parcel in) {
      return new NewsEntity(in);
    }

    @Override public NewsEntity[] newArray(int size) {
      return new NewsEntity[size];
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
