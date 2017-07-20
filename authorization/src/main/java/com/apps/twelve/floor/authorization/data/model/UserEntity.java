package com.apps.twelve.floor.authorization.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserEntity {

  @SerializedName("full_name") @Expose private String fullName;
  @SerializedName("first_name") @Expose private String firstName;
  @SerializedName("middle_name") @Expose private String middleName;
  @SerializedName("last_name") @Expose private String lastName;
  @SerializedName("email") @Expose private String email;
  @SerializedName("phone") @Expose private String phone;
  @SerializedName("picture") @Expose private String picture;
  @SerializedName("picture_url") @Expose private String pictureUrl;
  @SerializedName("password") @Expose private String password;
  @SerializedName("updated_at") @Expose private String updatedAt;
  @SerializedName("created_at") @Expose private String createdAt;
  @SerializedName("id") @Expose private Long id;
  @SerializedName("provider_id") @Expose private String providerId;
  @SerializedName("provider") @Expose private String provider;
  @SerializedName("gender") @Expose private String gender;
  @SerializedName("birthdate") @Expose private String birthday;
  @SerializedName("device_info") @Expose private String deviceInfo;
  @SerializedName("verify_code") @Expose private String verifyCode;
  @SerializedName("additional") @Expose private String additionalFields;

  public UserEntity() {
  }

  public UserEntity(String firstName, String email, String phone, String password) {
    this.firstName = firstName;
    this.email = email;
    this.phone = phone;
    this.password = password;
  }

  public UserEntity(String fullName, String email, String phone) {
    this.fullName = fullName;
    this.email = email;
    this.phone = phone;
  }

  public UserEntity(Long id, String fullName, String email) {
    this.id = id;
    this.fullName = fullName;
    this.email = email;
  }

  public String getFullName() {
    return fullName;
  }

  public void setFullName(String fullName) {
    this.fullName = fullName;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getMiddleName() {
    return middleName;
  }

  public void setMiddleName(String middleName) {
    this.middleName = middleName;
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

  public String getPictureUrl() {
    return pictureUrl;
  }

  public void setPictureUrl(String pictureUrl) {
    this.pictureUrl = pictureUrl;
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

  public String getBirthday() {
    return birthday;
  }

  public void setBirthday(String birthday) {
    this.birthday = birthday;
  }

  public String getDeviceInfo() {
    return deviceInfo;
  }

  public void setDeviceInfo(String deviceInfo) {
    this.deviceInfo = deviceInfo;
  }

  public String getVerifyCode() {
    return verifyCode;
  }

  public void setVerifyCode(String verifyCode) {
    this.verifyCode = verifyCode;
  }

  public String getAdditionalFields() {
    return additionalFields;
  }

  public void setAdditionalFields(String additionalFields) {
    this.additionalFields = additionalFields;
  }
}