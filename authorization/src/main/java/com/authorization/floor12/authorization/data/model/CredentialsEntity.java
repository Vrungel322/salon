package com.authorization.floor12.authorization.data.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CredentialsEntity implements Parcelable {

  public final static Creator<CredentialsEntity> CREATOR = new Creator<CredentialsEntity>() {

    @SuppressWarnings({
        "unchecked"
    }) public CredentialsEntity createFromParcel(Parcel in) {
      CredentialsEntity instance = new CredentialsEntity();
      instance.phone = ((String) in.readValue((String.class.getClassLoader())));
      instance.email = ((String) in.readValue((String.class.getClassLoader())));
      instance.password = ((String) in.readValue((String.class.getClassLoader())));
      return instance;
    }

    public CredentialsEntity[] newArray(int size) {
      return (new CredentialsEntity[size]);
    }
  };
  @SerializedName("phone") @Expose private String phone;
  @SerializedName("email") @Expose private String email;
  @SerializedName("password") @Expose private String password;

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public void writeToParcel(Parcel dest, int flags) {
    dest.writeValue(phone);
    dest.writeValue(email);
    dest.writeValue(password);
  }

  public int describeContents() {
    return 0;
  }
}