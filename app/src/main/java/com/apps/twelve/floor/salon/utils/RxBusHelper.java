package com.apps.twelve.floor.salon.utils;

/**
 * Created by John on 26.01.2017.
 */

public final class RxBusHelper {

  public static class EventForNextStep {
    public String fragmentTag;

    public EventForNextStep(String fragmentTag) {
      this.fragmentTag = fragmentTag;
    }
  }

  public static class BackCategories {
  }

  public static class StateBackBookingMaster {
  }

  public static class StateBackBookingService {
  }

  public static class CloseBookingService {
  }

  public static class UpdateLastBookingListEvent {
  }

  public static class UpdateNews {
  }

  public static class StopRefreshBookingMainFragment {
  }

  public static class UpdateUserInfo {
  }

  public static class StopRefreshNewsMainFragment {
  }

  public static class HideFloatingButton {
  }

  public static class ShowFloatingButton {
  }

  public static class SetBookingItemInMenu {
  }

  public static class SetNewsItemInMenu {
  }

  public static class SetCatalogItemInMenu {
  }

  public static class UpdateOurWorkList {
  }
}
