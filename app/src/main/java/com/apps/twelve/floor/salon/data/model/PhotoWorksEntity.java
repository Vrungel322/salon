package com.apps.twelve.floor.salon.data.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;

/**
 * Created by John on 09.03.2017.
 */

public class PhotoWorksEntity implements Parcelable {
  @SerializedName("id") private int id;
  //@SerializedName("gallery_id") private int galleryId;
  @SerializedName("image") private String mUrlPhoto;
  @SerializedName("description") private String mDescriptionPhoto;
  @SerializedName("favorite") private boolean mFavorite;

  public PhotoWorksEntity(int id, String urlPhoto, String descriptionPhoto, boolean favorite) {
    this.id = id;
    mUrlPhoto = urlPhoto;
    mDescriptionPhoto = descriptionPhoto;
    mFavorite = favorite;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getUrlPhoto() {
    return mUrlPhoto;
  }

  public void setUrlPhoto(String urlPhoto) {
    mUrlPhoto = urlPhoto;
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

  public static Creator<PhotoWorksEntity> getCREATOR() {
    return CREATOR;
  }

  protected PhotoWorksEntity(Parcel in) {
    id = in.readInt();
    mUrlPhoto = in.readString();
    mDescriptionPhoto = in.readString();
    mFavorite = in.readByte() != 0;
  }

  @Override public void writeToParcel(Parcel dest, int flags) {
    dest.writeInt(id);
    dest.writeString(mUrlPhoto);
    dest.writeString(mDescriptionPhoto);
    dest.writeByte((byte) (mFavorite ? 1 : 0));
  }

  @Override public int describeContents() {
    return 0;
  }

  public static final Creator<PhotoWorksEntity> CREATOR = new Creator<PhotoWorksEntity>() {
    @Override public PhotoWorksEntity createFromParcel(Parcel in) {
      return new PhotoWorksEntity(in);
    }

    @Override public PhotoWorksEntity[] newArray(int size) {
      return new PhotoWorksEntity[size];
    }
  };
}
