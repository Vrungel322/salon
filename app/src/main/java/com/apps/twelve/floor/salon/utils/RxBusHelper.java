package com.apps.twelve.floor.salon.utils;

/**
 * Created by John on 26.01.2017.
 */

public final class RxBusHelper {
  public static class ServiceID {
    public String serviceId;
    public String serviceName;

    public ServiceID(String serviceId, String serviceName) {
      this.serviceId = serviceId;
      this.serviceName = serviceName;
    }
  }

  public static class DataID {
    public String dataId;
    public String serviceTime;

    public DataID(String dataId, String serviceTime) {
      this.dataId = dataId;
      this.serviceTime = serviceTime;
    }
  }

  public static class MasterID {
    public String masterId;
    public String masterName;

    public MasterID(String masterId, String masterName) {
      this.masterId = masterId;
      this.masterName = masterName;
    }
  }

  public static class BackCategories {
  }

  public static class StateBooking {
  }

  public static class VisibleFragmentChooseService {
    public boolean visible;

    public VisibleFragmentChooseService(boolean visible) {
      this.visible = visible;
    }
  }

  public static class UpdateLastBookingListEvent {
  }

  public static class UpdateNews {
  }

  public static class StopRefreshBookingMainFragment {
  }

  public static class StopRefreshNewsMainFragment {
  }
}
