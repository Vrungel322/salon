package com.apps.twelve.floor.salon.mvp.data.model;

import android.net.Uri;

/**
 * Created by Vrungel on 23.02.2017.
 */

public class PreviewNewsEntity {
  private Uri mImageNewsPreviewURL;
  private String mNewsShortDescription;
  private String mNewsData;

  public PreviewNewsEntity(Uri imageNewsPreviewURL, String newsShortDescription, String newsData) {
    mImageNewsPreviewURL = imageNewsPreviewURL;
    mNewsShortDescription = newsShortDescription;
    mNewsData = newsData;
  }

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
}
