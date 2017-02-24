package com.apps.twelve.floor.salon.mvp.data.model;

import android.net.Uri;
import java.io.Serializable;

/**
 * Created by Vrungel on 23.02.2017.
 */

public class NewsEntity implements Serializable {
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
