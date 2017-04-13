package com.apps.twelve.floor.salon.mvp.data.model;

import com.google.gson.annotations.SerializedName;
import java.util.List;

/**
 * Created by Vrungel on 04.04.2017.
 */

public class DataServiceEntity {
  @SerializedName("min") private Integer startTime;
  @SerializedName("max") private Integer endTime;
  @SerializedName("time") private List<ScheduleEntity> mScheduleEntities;

  public DataServiceEntity(Integer startTime, Integer endTime,
      List<ScheduleEntity> scheduleEntities) {
    this.startTime = startTime;
    this.endTime = endTime;
    mScheduleEntities = scheduleEntities;
  }

  public Integer getStartTime() {
    return startTime;
  }

  public void setStartTime(Integer startTime) {
    this.startTime = startTime;
  }

  public Integer getEndTime() {
    return endTime;
  }

  public void setEndTime(Integer endTime) {
    this.endTime = endTime;
  }

  public List<ScheduleEntity> getScheduleEntities() {
    return mScheduleEntities;
  }

  public void setScheduleEntities(List<ScheduleEntity> scheduleEntities) {
    mScheduleEntities = scheduleEntities;
  }

  public static class ScheduleEntity {
    @SerializedName("id") private String id;
    @SerializedName("start") private String time;
    @SerializedName("ts_start") private String timeInSec;
    @SerializedName("status") private Boolean status;

    public ScheduleEntity(String id, String time, String timeInSec, Boolean status) {
      this.id = id;
      this.time = time;
      this.timeInSec = timeInSec;
      this.status = status;
    }

    public String getId() {
      return id;
    }

    public void setId(String id) {
      this.id = id;
    }

    public String getTime() {
      return time;
    }

    public void setTime(String time) {
      this.time = time;
    }

    public String getTimeInSec() {
      return timeInSec;
    }

    public void setTimeInSec(String timeInSec) {
      this.timeInSec = timeInSec;
    }

    public Boolean getStatus() {
      return status;
    }

    public void setStatus(Boolean status) {
      this.status = status;
    }
  }
}
