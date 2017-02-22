package com.apps.twelve.floor.salon.mvp.data.model;

import android.net.Uri;

/**
 * Created by Vrungel on 22.02.2017.
 */

public class OurWorkEntity {
  private Uri mImageURL;
  private String mShortDescription;
  private int mImageCount;

  public OurWorkEntity(Uri imageURL, String shortDescription, int imageCount) {
    mImageURL = imageURL;
    mShortDescription = shortDescription;
    mImageCount = imageCount;
  }

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
}
