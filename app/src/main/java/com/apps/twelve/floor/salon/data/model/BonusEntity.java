package com.apps.twelve.floor.salon.data.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Alexandra on 22.06.2017.
 */

public class BonusEntity {
  @SerializedName("bonuses") private Integer bonusesCount;

  public BonusEntity(Integer count) {
    bonusesCount = count;
  }

  public Integer getBonusesCount() {
    return bonusesCount;
  }

  public void setBonusesCount(Integer bonusesCount) {
    this.bonusesCount = bonusesCount;
  }
}
