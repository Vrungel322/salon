package com.authorization.floor12.authorization.data.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserEntity implements Parcelable {

  public final static Creator<UserEntity> CREATOR = new Creator<UserEntity>() {

    @SuppressWarnings({
        "unchecked"
    }) public UserEntity createFromParcel(Parcel in) {
      UserEntity instance = new UserEntity();
      instance.name = ((String) in.readValue((String.class.getClassLoader())));
      instance.email = ((String) in.readValue((String.class.getClassLoader())));
      instance.phone = ((String) in.readValue((String.class.getClassLoader())));
      instance.picture = ((String) in.readValue((String.class.getClassLoader())));
      instance.password = ((String) in.readValue((String.class.getClassLoader())));
      instance.updatedAt = ((String) in.readValue((String.class.getClassLoader())));
      instance.createdAt = ((String) in.readValue((String.class.getClassLoader())));
      instance.id = ((Long) in.readValue((Long.class.getClassLoader())));
      instance.providerId = ((String) in.readValue((String.class.getClassLoader())));
      instance.provider = ((String) in.readValue((String.class.getClassLoader())));
      instance.gender = ((String) in.readValue((String.class.getClassLoader())));
      return instance;
    }

    public UserEntity[] newArray(int size) {
      return (new UserEntity[size]);
    }
  };
  @SerializedName("name") @Expose private String name;
  @SerializedName("email") @Expose private String email;
  @SerializedName("phone") @Expose private String phone;
  @SerializedName("picture") @Expose private String picture;
  @SerializedName("password") @Expose private String password;
  @SerializedName("updated_at") @Expose private String updatedAt;
  @SerializedName("created_at") @Expose private String createdAt;
  @SerializedName("id") @Expose private Long id;
  @SerializedName("provider_id") @Expose private String providerId;
  @SerializedName("provider") @Expose private String provider;
  @SerializedName("gender") @Expose private String gender;

  public UserEntity() {
  }

  public UserEntity(String name, String email, String phone, String password) {
    this.name = name;
    this.email = email;
    this.phone = phone;
    this.password = password;
  }

  public UserEntity(String name, String email, String phone) {
    this.name = name;
    this.email = email;
    this.phone = phone;
  }

  public UserEntity(Long id, String name, String email) {
    this.id = id;
    this.name = name;
    this.email = email;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getPicture() {
    return picture;
  }

  public void setPicture(String picture) {
    this.picture = picture;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getUpdatedAt() {
    return updatedAt;
  }

  public void setUpdatedAt(String updatedAt) {
    this.updatedAt = updatedAt;
  }

  public String getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(String createdAt) {
    this.createdAt = createdAt;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getProviderId() {
    return providerId;
  }

  public void setProviderId(String providerId) {
    this.providerId = providerId;
  }

  public String getProvider() {
    return provider;
  }

  public void setProvider(String provider) {
    this.provider = provider;
  }

  public String getGender() {
    return gender;
  }

  public void setGender(String gender) {
    this.gender = gender;
  }

  public void writeToParcel(Parcel dest, int flags) {
    dest.writeValue(name);
    dest.writeValue(email);
    dest.writeValue(phone);
    dest.writeValue(picture);
    dest.writeValue(password);
    dest.writeValue(updatedAt);
    dest.writeValue(createdAt);
    dest.writeValue(id);
    dest.writeValue(providerId);
    dest.writeValue(provider);
    dest.writeValue(gender);
  }

  public int describeContents() {
    return 0;
  }
}