package com.apps.twelve.floor.salon.data.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Vrungel on 19.04.2017.
 */

public class BookingServerEntity {
  @SerializedName("master_id") private Integer masterId;
  @SerializedName("service_id") private Integer serviceId;
  @SerializedName("schedule_id") private Integer scheduleId;
  @SerializedName("name") private String clientName;
  @SerializedName("phone") private String clientPhone;

  public BookingServerEntity(Integer masterId, Integer serviceId,
      Integer scheduleId, String clientName, String clientPhone) {
    this.masterId = masterId;
    this.serviceId = serviceId;
    this.scheduleId = scheduleId;
    this.clientName = clientName;
    this.clientPhone = clientPhone;
  }

  public Integer getMasterId() {
    return masterId;
  }

  public void setMasterId(Integer masterId) {
    this.masterId = masterId;
  }

  public Integer getServiceId() {
    return serviceId;
  }

  public void setServiceId(Integer serviceId) {
    this.serviceId = serviceId;
  }

  public Integer getScheduleId() {
    return scheduleId;
  }

  public void setScheduleId(Integer scheduleId) {
    this.scheduleId = scheduleId;
  }

  public String getClientName() {
    return clientName;
  }

  public void setClientName(String clientName) {
    this.clientName = clientName;
  }

  public String getClientPhone() {
    return clientPhone;
  }

  public void setClientPhone(String clientPhone) {
    this.clientPhone = clientPhone;
  }
}
