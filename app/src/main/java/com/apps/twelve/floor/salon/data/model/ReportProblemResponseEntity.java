package com.apps.twelve.floor.salon.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by vrungel on 19.07.17.
 */

public class ReportProblemResponseEntity {
  @SerializedName("message") @Expose private String message;
  @SerializedName("user_id") @Expose private Integer userId;
  @SerializedName("updated_at") @Expose private String updatedAt;
  @SerializedName("created_at") @Expose private String createdAt;
  @SerializedName("id") @Expose private Integer id;

  public ReportProblemResponseEntity(String message, Integer userId, String updatedAt,
      String createdAt, Integer id) {
    this.message = message;
    this.userId = userId;
    this.updatedAt = updatedAt;
    this.createdAt = createdAt;
    this.id = id;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public Integer getUserId() {
    return userId;
  }

  public void setUserId(Integer userId) {
    this.userId = userId;
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

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }
}
