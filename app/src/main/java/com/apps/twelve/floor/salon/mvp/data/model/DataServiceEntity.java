package com.apps.twelve.floor.salon.mvp.data.model;

import java.util.List;

/**
 * Created by Vrungel on 04.04.2017.
 */

public class DataServiceEntity {
  private String id;
  private String startTime;
  private String endTime;
  private String day;
  private List<ScheduleEntity> mScheduleEntities;

  public DataServiceEntity(String id, String startTime, String endTime, String day,
      List<ScheduleEntity> scheduleEntities) {
    this.id = id;
    this.startTime = startTime;
    this.endTime = endTime;
    this.day = day;
    mScheduleEntities = scheduleEntities;
  }

  public String getDay() {
    return day;
  }

  public void setDay(String day) {
    this.day = day;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getStartTime() {
    return startTime;
  }

  public void setStartTime(String startTime) {
    this.startTime = startTime;
  }

  public String getEndTime() {
    return endTime;
  }

  public void setEndTime(String endTime) {
    this.endTime = endTime;
  }

  public List<ScheduleEntity> getScheduleEntities() {
    return mScheduleEntities;
  }

  public void setScheduleEntities(List<ScheduleEntity> scheduleEntities) {
    mScheduleEntities = scheduleEntities;
  }

  public static class ScheduleEntity {
    private String id;
    private String time;

    public ScheduleEntity(String id, String time) {
      this.id = id;
      this.time = time;
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
  }
}
