package com.apps.twelve.floor.salon.data.model;

import com.google.gson.annotations.SerializedName;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Alexandra on 22.06.2017.
 */

public class BonusEntity extends RealmObject {
  @PrimaryKey private Integer id;
  @SerializedName("bonuses") private Integer bonusesCount;

  public BonusEntity() {
  }

  public BonusEntity(Integer count) {
    bonusesCount = count;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Integer getBonusesCount() {
    return bonusesCount;
  }

  public void setBonusesCount(Integer bonusesCount) {
    this.bonusesCount = bonusesCount;
  }
}
