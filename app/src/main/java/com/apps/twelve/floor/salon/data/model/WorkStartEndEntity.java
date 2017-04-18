package com.apps.twelve.floor.salon.data.model;

import java.util.List;

/**
 * Created by Vrungel on 27.03.2017.
 */

public class WorkStartEndEntity {
  private String date;
  private String startDate;
  private String endDate;
  private List<String> freeTime;

  public WorkStartEndEntity(String date, String startDate, String endDate, List<String> freeTime) {

    this.date = date;
    this.startDate = startDate;
    this.endDate = endDate;
    this.freeTime = freeTime;
  }

  public String getDate() {
    return date;
  }

  public void setDate(String date) {
    this.date = date;
  }

  public String getStartDate() {
    return startDate;
  }

  public void setStartDate(String startDate) {
    this.startDate = startDate;
  }

  public String getEndDate() {
    return endDate;
  }

  public void setEndDate(String endDate) {
    this.endDate = endDate;
  }

  public List<String> getFreeTime() {
    return freeTime;
  }

  public void setFreeTime(List<String> freeTime) {
    this.freeTime = freeTime;
  }
}
