package com.apps.twelve.floor.salon.mvp.data.model;

/**
 * Created by Vrungel on 28.02.2017.
 */

public class LastBookingEntity {
  private String imageUri;
  private String serviceName;
  private String serviceTime;

  public LastBookingEntity(String imageUri, String serviceName, String serviceTime) {
    this.imageUri = imageUri;
    this.serviceName = serviceName;
    this.serviceTime = serviceTime;
  }

  public String getImageUri() {
    return imageUri;
  }

  public void setImageUri(String imageUri) {
    this.imageUri = imageUri;
  }

  public String getServiceName() {
    return serviceName;
  }

  public void setServiceName(String serviceName) {
    this.serviceName = serviceName;
  }

  public String getServiceTime() {
    return serviceTime;
  }

  public void setServiceTime(String serviceTime) {
    this.serviceTime = serviceTime;
  }
}
