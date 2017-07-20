package com.apps.twelve.floor.authorization.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class ErrorContentEntity {

  @SerializedName("email") @Expose private List<String> emailError = null;
  @SerializedName("phone") @Expose private List<String> phoneError = null;
  @SerializedName("password") @Expose private List<String> passwordError = null;
  @SerializedName("new_password") @Expose private List<String> newPasswordError = null;
  @SerializedName("error") @Expose private List<String> error = null;
  @SerializedName("verify_code") @Expose private List<String> verifyCode = null;

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

  public List<String> getVerifyCode() {
    return verifyCode;
  }

  public void setVerifyCode(List<String> verifyCode) {
    this.verifyCode = verifyCode;
  }
}