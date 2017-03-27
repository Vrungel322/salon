package com.apps.twelve.floor.salon.mvp.data.model;

/**
 * Created by Vrungel on 27.03.2017.
 */

public class WorkStartEndEntity {
  private String date;
  private String startDate;
  private String endDate;

  public WorkStartEndEntity(String date, String startDate, String endDate) {
    this.date = date;
    this.startDate = startDate;
    this.endDate = endDate;
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
}
