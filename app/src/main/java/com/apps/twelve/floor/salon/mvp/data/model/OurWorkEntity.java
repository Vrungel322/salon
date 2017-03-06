package com.apps.twelve.floor.salon.mvp.data.model;

import android.net.Uri;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Vrungel on 22.02.2017.
 */

public class OurWorkEntity implements Serializable {
  private Uri mImageURL;
  private String mShortDescription;
  private int mImageCount;
  private ArrayList<String> mListImageUrl;

  public OurWorkEntity(Uri imageURL, String shortDescription, int imageCount,
      ArrayList<String> listImageUrl) {
    mImageURL = imageURL;
    mShortDescription = shortDescription;
    mImageCount = imageCount;
    mListImageUrl = listImageUrl;
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

  public ArrayList<String> getListImageUrl() {
    return mListImageUrl;
  }

  public void setListImageUrl(ArrayList<String> listImageUrl) {
    mListImageUrl = listImageUrl;
  }
}
