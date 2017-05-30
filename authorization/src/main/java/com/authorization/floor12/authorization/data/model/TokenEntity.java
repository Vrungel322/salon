package com.authorization.floor12.authorization.data.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TokenEntity implements Parcelable {

  public final static Creator<TokenEntity> CREATOR = new Creator<TokenEntity>() {

    @SuppressWarnings({
        "unchecked"
    }) public TokenEntity createFromParcel(Parcel in) {
      TokenEntity instance = new TokenEntity();
      instance.token = ((String) in.readValue((String.class.getClassLoader())));
      return instance;
    }

    public TokenEntity[] newArray(int size) {
      return (new TokenEntity[size]);
    }
  };
  @SerializedName("token") @Expose private String token;

  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }

  public void writeToParcel(Parcel dest, int flags) {
    dest.writeValue(token);
  }

  public int describeContents() {
    return 0;
  }
}