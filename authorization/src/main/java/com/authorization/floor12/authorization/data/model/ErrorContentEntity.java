package com.authorization.floor12.authorization.data.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class ErrorContentEntity implements Parcelable {

  public final static Creator<ErrorContentEntity> CREATOR = new Creator<ErrorContentEntity>() {

    @SuppressWarnings({
        "unchecked"
    }) public ErrorContentEntity createFromParcel(Parcel in) {
      ErrorContentEntity instance = new ErrorContentEntity();
      in.readList(instance.emailError, (String.class.getClassLoader()));
      in.readList(instance.phoneError, (String.class.getClassLoader()));
      in.readList(instance.passwordError, (String.class.getClassLoader()));
      in.readList(instance.newPasswordError, (String.class.getClassLoader()));
      in.readList(instance.error, (String.class.getClassLoader()));
      return instance;
    }

    public ErrorContentEntity[] newArray(int size) {
      return (new ErrorContentEntity[size]);
    }
  };

  @SerializedName("email") @Expose private List<String> emailError = null;
  @SerializedName("phone") @Expose private List<String> phoneError = null;
  @SerializedName("password") @Expose private List<String> passwordError = null;
  @SerializedName("new_password") @Expose private List<String> newPasswordError = null;
  @SerializedName("error") @Expose private List<String> error = null;

  public List<String> getEmailError() {
    return emailError;
  }

  public void setEmailError(List<String> emailError) {
    this.emailError = emailError;
  }

  public List<String> getPhoneError() {
    return phoneError;
  }

  public void setPhoneError(List<String> phoneError) {
    this.phoneError = phoneError;
  }

  public List<String> getPasswordError() {
    return passwordError;
  }

  public void setPasswordError(List<String> passwordError) {
    this.passwordError = passwordError;
  }

  public List<String> getNewPasswordError() {
    return newPasswordError;
  }

  public void setNewPasswordError(List<String> newPasswordError) {
    this.newPasswordError = newPasswordError;
  }

  public List<String> getError() {
    return error;
  }

  public void setError(List<String> error) {
    this.error = error;
  }

  public void writeToParcel(Parcel dest, int flags) {
    dest.writeList(emailError);
    dest.writeList(phoneError);
    dest.writeList(passwordError);
    dest.writeList(newPasswordError);
    dest.writeList(error);
  }

  public int describeContents() {
    return 0;
  }
}