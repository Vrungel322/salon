package com.apps.twelve.floor.salon.data.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by John on 09.03.2017.
 */

public class PhotoWorksEntity extends RealmObject implements Parcelable {
  public static final Creator<PhotoWorksEntity> CREATOR = new Creator<PhotoWorksEntity>() {
    @Override public PhotoWorksEntity createFromParcel(Parcel in) {
      return new PhotoWorksEntity(in);
    }

    @Override public PhotoWorksEntity[] newArray(int size) {
      return new PhotoWorksEntity[size];
    }
  };
  @PrimaryKey @SerializedName("id") private int id;
  //@SerializedName("gallery_id") private int galleryId;
  @SerializedName("image") private String mUrlPhoto;
  @SerializedName("description") private String mDescriptionPhoto;
  @SerializedName("favorite") private boolean mFavorite;

  public PhotoWorksEntity() {
  }

  public PhotoWorksEntity(int id, String urlPhoto, String descriptionPhoto, boolean favorite) {
    this.id = id;
    mUrlPhoto = urlPhoto;
    mDescriptionPhoto = descriptionPhoto;
    mFavorite = favorite;
  }

  protected PhotoWorksEntity(Parcel in) {
    id = in.readInt();
    mUrlPhoto = in.readString();
    mDescriptionPhoto = in.readString();
    mFavorite = in.readByte() != 0;
  }

  public static Creator<PhotoWorksEntity> getCREATOR() {
    return CREATOR;
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

  @Override public void writeToParcel(Parcel dest, int flags) {
    dest.writeInt(id);
    dest.writeString(mUrlPhoto);
    dest.writeString(mDescriptionPhoto);
    dest.writeByte((byte) (mFavorite ? 1 : 0));
  }

  @Override public int describeContents() {
    return 0;
  }
}
