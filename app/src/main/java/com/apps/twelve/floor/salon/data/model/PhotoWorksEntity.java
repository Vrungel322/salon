package com.apps.twelve.floor.salon.data.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by John on 09.03.2017.
 */

public class PhotoWorksEntity implements Parcelable {

  private int id;
  private String mDescriptionPhoto;
  private boolean mFavorite;
  private String mUrlPhoto;

  public PhotoWorksEntity(int id, String descriptionPhoto, boolean favorite, String urlPhoto) {
    this.id = id;
    mDescriptionPhoto = descriptionPhoto;
    mFavorite = favorite;
    mUrlPhoto = urlPhoto;
  }

  protected PhotoWorksEntity(Parcel in) {
    id = in.readInt();
    mDescriptionPhoto = in.readString();
    mFavorite = in.readByte() != 0;
    mUrlPhoto = in.readString();
  }

  public static final Creator<PhotoWorksEntity> CREATOR = new Creator<PhotoWorksEntity>() {
    @Override public PhotoWorksEntity createFromParcel(Parcel in) {
      return new PhotoWorksEntity(in);
    }

    @Override public PhotoWorksEntity[] newArray(int size) {
      return new PhotoWorksEntity[size];
    }
  };

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getDescriptionPhoto() {
    return mDescriptionPhoto;
  }

  public void setDescriptionPhoto(String descriptionPhoto) {
    mDescriptionPhoto = descriptionPhoto;
  }

  public boolean isFavorite() {
    return mFavorite;
  }

  public void setFavorite(boolean favorite) {
    mFavorite = favorite;
  }

  public String getUrlPhoto() {
    return mUrlPhoto;
  }

  public void setUrlPhoto(String urlPhoto) {
    mUrlPhoto = urlPhoto;
  }

  @Override public int describeContents() {
    return 0;
  }

  @Override public void writeToParcel(Parcel dest, int flags) {
    dest.writeInt(id);
    dest.writeString(mDescriptionPhoto);
    dest.writeByte((byte) (mFavorite ? 1 : 0));
    dest.writeString(mUrlPhoto);
  }
}
