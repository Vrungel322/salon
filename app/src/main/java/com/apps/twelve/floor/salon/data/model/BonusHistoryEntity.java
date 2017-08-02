package com.apps.twelve.floor.salon.data.model;

import com.google.gson.annotations.SerializedName;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Alexandra on 22.06.2017.
 */

public class BonusHistoryEntity extends RealmObject {

  @PrimaryKey
  @SerializedName("id") private Integer id;
  @SerializedName("user_id") private Integer userId;
  @SerializedName("bonus_type") private String bonusType;
  @SerializedName("bonus_description") private String bonusDescription;
  //@SerializedName("meta") private Object meta;
  @SerializedName("points") private Integer points;
  @SerializedName("created_at") private String createdAt;

  public BonusHistoryEntity() {
  }

  public BonusHistoryEntity(Integer id, Integer userId, String bonusType, String bonusDescription,
     /* Object meta,*/ Integer points, String createdAt) {
    super();
    this.id = id;
    this.userId = userId;
    this.bonusType = bonusType;
    this.bonusDescription = bonusDescription;
    //this.meta = meta;
    this.points = points;
    this.createdAt = createdAt;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Integer getUserId() {
    return userId;
  }

  public void setUserId(Integer userId) {
    this.userId = userId;
  }

  public String getBonusType() {
    return bonusType;
  }

  public void setBonusType(String bonusType) {
    this.bonusType = bonusType;
  }

  public String getBonusDescription() {
    return bonusDescription;
  }

  public void setBonusDescription(String bonusDescription) {
    this.bonusDescription = bonusDescription;
  }

  //public Object getMeta() {
  //  return meta;
  //}

  //public void setMeta(Object meta) {
  //  this.meta = meta;
  //}

  public Integer getPoints() {
    return points;
  }

  public void setPoints(Integer points) {
    this.points = points;
  }

  public String getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(String createdAt) {
    this.createdAt = createdAt;
  }
}

